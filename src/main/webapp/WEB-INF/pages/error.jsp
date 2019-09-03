<!DOCTYPE html>
<html lang="en"
      xmlns:th="http://www.thymeleaf.org" >
     
	<head>
	
		<title>quod</title>
		
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="keywords" content="Motive Mag Responsive web template, 
		                               Bootstrap Web Templates, Flat Web Templates, 
		                               Andriod Compatible web template, 
		                               Smartphone Compatible web template, 
		                               free webdesigns for Nokia, Samsung, 
		                               LG, SonyErricsson, Motorola web design" />
		                               
		 <link  th:href="@{/static/resources/css/bootstrap-3.1.1.min.css}" rel="stylesheet"  type="text/css"/>
		 <link  th:href="@{/static/resources/css/style.css}" rel="stylesheet"  type="text/css"/>
		 
		  <link rel="icon"  type="image/x-icon"  th:href="@{/static/resources/images/favicon.png}"  />
		 
	</head>
	
	<body>
	
			<div class="container">
				  <div class="row">
				    <div class="span12">
				      <div class="hero-unit center">
					          <h1>Page Not Found <small><font face="Tahoma" color="red">Error 404</font></small></h1>
					          <br />
					          <p>The page you requested could not be found, either contact your webmaster or try again. Use your browsers <b>Back</b> button to navigate to the page you have prevously come from</p>
					          <p><b>Or you could just press this neat little button</b></p>
					          <a type="button" href="/" class="btn btn-large btn-default"><i class="icon-home icon-white"></i> Take Me Home</a>
				         </div>
				    </div>
				  </div>
		   </div>
		  
		  <script   th:src="@{/static/resources/js/bootstrap-3.1.1.min.js}" type="text/javascript" ></script>
	</body>
	
</html>