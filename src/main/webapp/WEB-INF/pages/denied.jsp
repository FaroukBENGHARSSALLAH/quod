<%@page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
	    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
	    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
	    <link href="<c:url value="/resources/images/favicon.png" />"  rel="icon"  type="image/x-icon"   >
	    <title>Quod</title>
	</head>
<body>

	
     
	 <div class="container-fluid">
	    <div class="row">
		   error
		</div>
	 </div>

	<%@include file="fragment/footer.jsp" %>
			
    <script type="text/javascript"  src="<c:url value="/resources/js/jquery.js" />" ></script>
    <script type="text/javascript"  src="<c:url value="/resources/js/bootstrap.js" />"  ></script>	
</body>
</html>