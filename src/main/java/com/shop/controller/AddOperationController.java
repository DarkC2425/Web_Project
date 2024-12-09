package com.shop.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.shop.model.Category;
import com.shop.model.Message;
import com.shop.model.Product;
import com.shop.service.ICategoryService;
import com.shop.service.IProductService;
import com.shop.service.Impl.CategoryServiceImpl;
import com.shop.service.Impl.ProductServiceImpl;
import com.shop.util.JWTAuthentication;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@MultipartConfig
public class AddOperationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (JWTAuthentication.Authorized(request, response, "admin")) {
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String operation = request.getParameter("operation");
		ICategoryService catService = new CategoryServiceImpl();
		IProductService pService = new ProductServiceImpl();
		HttpSession session = request.getSession();
		Message message = null;

		if (operation.trim().equals("addCategory")) {

			String categoryName = request.getParameter("category_name");
			Part part = request.getPart("category_img");
			Category category = new Category(categoryName, part.getSubmittedFileName());
			boolean flag = catService.saveCategory(category);

			String path = request.getServletContext().getRealPath("/") + "Product_imgs" + File.separator
					+ part.getSubmittedFileName();

			try {
				FileOutputStream fos = new FileOutputStream(path);
				InputStream is = part.getInputStream();
				byte[] data = new byte[is.available()];
				is.read(data);
				fos.write(data);
				fos.flush();
				fos.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

			if (flag) {
				message = new Message("Đã thêm danh mục thành công!!", "success", "alert-success");
			} else {
				message = new Message("Đã xảy ra lỗi! Thử lại!!", "error", "alert-danger");
			}
			session.setAttribute("message", message);
			response.sendRedirect("views/admin/admin.jsp");

		} else if (operation.trim().equals("addProduct")) {

			// add product to database
			String pName = request.getParameter("name");
			String pDesc = request.getParameter("description");
			int pPrice = Integer.parseInt(request.getParameter("price"));
			int pDiscount = Integer.parseInt(request.getParameter("discount"));
			if (pDiscount < 0 || pDiscount > 100) {
				pDiscount = 0;
			}
			int pQuantity = Integer.parseInt(request.getParameter("quantity"));
			Part part = request.getPart("photo");
			int categoryType = Integer.parseInt(request.getParameter("categoryType"));

			Product product = new Product(pName, pDesc, pPrice, pDiscount, pQuantity, part.getSubmittedFileName(),
					categoryType);
			boolean flag = pService.saveProduct(product);

			String path = request.getServletContext().getRealPath("/") + "Product_imgs" + File.separator
					+ part.getSubmittedFileName();
			try {
				FileOutputStream fos = new FileOutputStream(path);
				InputStream is = part.getInputStream();
				byte[] data = new byte[is.available()];
				is.read(data);
				fos.write(data);
				fos.flush();
				fos.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
			if (flag) {
				message = new Message("Đã thêm sản phẩm thành công!!", "success", "alert-success");
			} else {
				message = new Message("Đã xảy ra lỗi! Thử lại!!", "error", "alert-danger");
			}
			session.setAttribute("message", message);
			response.sendRedirect("views/admin/admin.jsp");

		} else if (operation.trim().equals("updateCategory")) {

			int cid = Integer.parseInt(request.getParameter("cid"));
			String name = request.getParameter("category_name");
			Part part = request.getPart("category_img");
			if (part.getSubmittedFileName().isEmpty()) {
				String image = request.getParameter("image");
				Category category = new Category(cid, name, image);
				catService.updateCategory(category);
			} else {
				Category category = new Category(cid, name, part.getSubmittedFileName());
				catService.updateCategory(category);
				String path = request.getServletContext().getRealPath("/") + "Product_imgs" + File.separator
						+ part.getSubmittedFileName();
				try {
					FileOutputStream fos = new FileOutputStream(path);
					InputStream is = part.getInputStream();
					byte[] data = new byte[is.available()];
					is.read(data);
					fos.write(data);
					fos.flush();
					fos.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			message = new Message("Đã cập nhật danh mục thành công!!", "success", "alert-success");
			session.setAttribute("message", message);
			response.sendRedirect("views/admin/display_category.jsp");

		} else if (operation.trim().equals("deleteCategory")) {

			int cid = Integer.parseInt(request.getParameter("cid"));
			catService.deleteCategory(cid);
			response.sendRedirect("views/admin/display_category.jsp");

		} else if (operation.trim().equals("updateProduct")) {

			int pid = Integer.parseInt(request.getParameter("pid"));
			String name = request.getParameter("name");
			float price = Float.parseFloat(request.getParameter("price"));
			String description = request.getParameter("description");
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			int discount = Integer.parseInt(request.getParameter("discount"));
			if (discount < 0 || discount > 100) {
				discount = 0;
			}
			Part part = request.getPart("product_img");
			int cid = Integer.parseInt(request.getParameter("categoryType"));
			if (cid == 0) {
				cid = Integer.parseInt(request.getParameter("category"));
			}
			if (part.getSubmittedFileName().isEmpty()) {
				String image = request.getParameter("image");
				Product product = new Product(pid, name, description, price, discount, quantity, image, cid);
				pService.updateProduct(product);
			} else {

				Product product = new Product(pid, name, description, price, discount, quantity,
						part.getSubmittedFileName(), cid);
				pService.updateProduct(product);
				// product image upload
				String path = request.getServletContext().getRealPath("/") + "Product_imgs" + File.separator
						+ part.getSubmittedFileName();
				try {
					FileOutputStream fos = new FileOutputStream(path);
					InputStream is = part.getInputStream();
					byte[] data = new byte[is.available()];
					is.read(data);
					fos.write(data);
					fos.flush();
					fos.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			message = new Message("Đã cập nhật sản phẩm thành công!!", "success", "alert-success");
			session.setAttribute("message", message);
			response.sendRedirect("views/admin/display_products.jsp");

		} else if (operation.trim().equals("deleteProduct")) {

			int pid = Integer.parseInt(request.getParameter("pid"));
			pService.deleteProduct(pid);
			response.sendRedirect("views/admin/display_products.jsp");

		}
		return;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
}
