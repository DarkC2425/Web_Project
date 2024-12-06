<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglib.jsp"%>
<h1>Product List</h1>
<div>
	<c:forEach var="product" items="${products}">
		<div>
			<h2>${product.name}</h2>
			<p>${product.description}</p>
			<p>Price: ${product.price}</p>
		</div>
	</c:forEach>
</div>
<div>
	<c:if test="${currentPage > 1}">
		<a
			href="${pageContext.request.contextPath}/product/pagination?page=${currentPage - 1}">Previous</a>
	</c:if>
	Page ${currentPage} of ${totalPages}
	<c:if test="${currentPage < totalPages}">
		<a
			href="${pageContext.request.contextPath}/product/pagination?page=${currentPage + 1}">Next</a>
	</c:if>
</div>
