<%@page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
	    <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"  type="text/css">
	    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"  type="text/css">
	    <link href="<c:url value="/resources/images/favicon.png" />"  rel="icon"  type="image/x-icon"   >
	    <title>Quod</title>
	    <style type="text/css">
	       @media only screen and (orientation: portrait) and (max-width:1000px){
	               div#container{
	                       width: 95%;
	                       margin-top : 80px;
	                       }
	                div.embed-responsive-16by9{
	                      padding-bottom: 56.25%; 
	                     
	                   }
	              }
	              
	      @media only screen and (orientation: landscape) and (max-width:1000px){
	               div#container{
	                       width: 80%;
	                       margin-top : 2px;
	                       }
	                       
	              div.embed-responsive-16by9{
	                      padding-bottom: 50%; 
	                     
	                   }
	              }
	              
	      @media only screen and (orientation: landscape) and (min-width:1100px){
	               div#container{
	                       width: 1000px;
	                       }
	                       
	                div.embed-responsive-16by9{
	                      padding-bottom: 56.25%; 
	                   }
	              }
          
	    </style>
	</head>
<body>

     <%@include file="fragment/header.jsp" %>
	 
	 <div id="container" class="container-fluid" >
	    <div class="row">
		   <div class="embed-responsive embed-responsive-16by9"  >
                       <video class="embed-responsive-item"  autoplay controls >
					      <source  src="<c:url value="/resources/video/${index}.mp4" />" type="video/mp4">
					   </video>
           </div>
		</div>
	 </div>

	<%@include file="fragment/footer.jsp" %>
			
    <script type="text/javascript"  src="<c:url value="/resources/js/jquery.js" />" ></script>
    <script type="text/javascript"  src="<c:url value="/resources/js/bootstrap.js" />"  ></script>	
</body>
</html>