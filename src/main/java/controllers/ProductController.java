package controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Product;
import services.IProductService;
import services.impl.ProductServiceImpl;

/**
 * Servlet implementation class ProductController
 */
@WebServlet(urlPatterns = { "/products", "/product/add", "/product/edit", "/product/delete", "/product/pagination" })
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IProductService iProductService = new ProductServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String urlString = request.getRequestURI();
		if (urlString.contains("/products")) {
			List<Product> products = iProductService.getAllProducts();
			request.setAttribute("products", products);
			request.getRequestDispatcher("/views/product.jsp").forward(request, response);
		} else if (urlString.contains("/product/pagination")) {
			String pageParam = request.getParameter("page");
			int page = (pageParam == null) ? 1 : Integer.parseInt(pageParam);
			List<Product> products = iProductService.getProductsByPage(page, utils.Constants.PAGE_SIZE);
			int totalProducts = iProductService.getTotalProducts();
			int totalPages = (int) Math.ceil((double) totalProducts / utils.Constants.PAGE_SIZE);
			request.setAttribute("products", products);
			request.setAttribute("currentPage", page);
			request.setAttribute("totalPages", totalPages);
			request.getRequestDispatcher("/views/product.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String urlString = request.getRequestURI();
		String alertMsg = null;
		if (urlString.contains("/product/add")) {
			try {
				Product product = new Product();
				iProductService.insert(product);
			} catch (Exception e) {
				alertMsg = "Add product failed";
			}
		} else if (urlString.contains("/product/edit")) {
			try {
				int PID = Integer.parseInt(request.getParameter("id"));
				Product product = iProductService.findByPID(PID);
				if (product == null) {
					alertMsg = "No such product";
				} else {
					iProductService.update(product);
				}

			} catch (Exception e) {
				alertMsg = "Update product failed";
			}
		}
		request.setAttribute("alert", alertMsg);
		request.getRequestDispatcher("/views/product.jsp").forward(request, response);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String urlString = request.getRequestURI();
		String alertMsg = null;
		if (urlString.contains("/product/delete")) {
			try {
				int PID = Integer.parseInt(request.getParameter("id"));
				iProductService.delete(PID);
				alertMsg = "Delete product successful";
			} catch (Exception e) {
				alertMsg = "Delete product failed";
			}
		}
		request.setAttribute("alert", alertMsg);
		request.getRequestDispatcher("/views/product.jsp").forward(request, response);

	}

}
