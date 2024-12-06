<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/commons/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/commons/guest/header.jsp"%>
	<div class="main">
      <div class="container">
        <sitemesh:write property="body"/>
      </div>
    </div>
    <%@ include file="/commons/guest/footer.jsp"%>
</body>
</html>