<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html >
	<head>
	    <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"   type="text/css" />
	    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"  type="text/css" />
		<link href="<c:url value="/resources/css/style.css"  />" rel="stylesheet"  type="text/css"/>
		<link href="<c:url value="/resources/css/typeahead.css"  />" rel="stylesheet"  type="text/css"/>
		<link href="<c:url value="/resources/css/gritter.css" />" rel="stylesheet"  type="text/css"/>
		<link rel="icon"  type="image/x-icon"  href="<c:url value="/resources/images/favicon.png" />"  />
	    <title>quod</title>
	    <style type="text/css">
	       .content-footer{
	          margin-top: 10%;
	       }
	       @media only screen and (orientation: portrait) and (max-width:1000px){
	              
	              }
	              
	      @media only screen and (orientation: landscape) and (max-width:1000px){
	              
	              }
	              
	      @media only screen and (orientation: landscape) and (min-width:1100px){
	              
	              }
	    </style>
	</head>
	
	<body>
	
	<%@include file="fragment/header.jsp" %>
	
		<div class="container-fluid">
		     <div class="row">    
				       <div class="col-sm-8 col-md-6 col-xs-10 col-md-offset-3 col-sm-offset-2 col-xs-offset-1" > 
				           <div class="text-center"><h4 style="margin-top: 33%; font-style: italic;font-weight: 300;">quod</h4></div>
				           <div id="custom-search-input" class="">
				                          <form  action="<c:url value="/querystatement" />"  method="get" >
												 <div class="typeahead__container">
													        <div class="typeahead__field">
														            <span class="typeahead__query">
														                <input class="js-typeahead-country_v1" name="company" placeholder="symbol" autocomplete="off" type="search"   />
														            </span>
														            <span class="typeahead__button">
														                <button type="submit" style="background-color: mintcream;">
														                    <i class="typeahead__search-icon"></i>
														                </button>
														            </span>
												            </div>
												    </div>
						                            <div class="input-group input-group col-md-8 col-md-offset-3 col-sm-8 col-sm-offset-2 col-xs-7 col-xs-offset-2" style="padding-top:5%" >
						                                  <label class="radio-inline"><input type="radio" name="type"  value="income"  checked="true" />income</label>
                                                          <label class="radio-inline"><input type="radio" name="type"   value="balance"     />balance sheet</label>
                                                          <label class="radio-inline"><input type="radio" name="type"   value="cash"      />cash flow</label> 
						                            </div>
						                             <div class="input-group col-md-8 col-md-offset-4 col-sm-8 col-sm-offset-4 col-xs-7 col-xs-offset-3" style="padding-top:5%" >
						                                  <label class="radio-inline"><input type="radio" name="period"  value="annual"  checked="true"     />annual</label>
                                                          <label class="radio-inline"><input type="radio" name="period"   value="quarter"         />quarter</label>
						                            </div>
				                            </form>
				             </div>
				        </div> 
		        </div>
		 </div>
		 
		 
	
		  
		 <%@include file="fragment/footer.jsp" %>
		  
		 <script   src="<c:url value="/resources/js/jquery.js" />" type="text/javascript" ></script>
		 <script   src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript" ></script>
		 <script   src="<c:url value="/resources/js/typeahead.js" />" type="text/javascript"  ></script>
		 <script   src="<c:url value="/resources/js/autocomplete.js" />" type="text/javascript"  ></script>
		 <script   src="<c:url value="/resources/js/gritter.js" />" type="text/javascript"  ></script>
		 
		  <script type="text/javascript"    >

					$(document).ready(function () {
				
  
                               <c:if test="${index_data != null}" >
                                        var name_0 = '${index_data[2].name}';
                                        var body_0 = '${index_data[2].change}';
                                        var perc_0 = '${index_data[2].percentage}';
                                        var price_0 = '${index_data[2].price}';
                                        
                                        var name_1 = '${index_data[1].name}';
                                        var body_1 = '${index_data[1].change}';
                                        var perc_1 = '${index_data[1].percentage}';
                                        var price_1 = '${index_data[1].price}';
                                        
                                        var name_2 = '${index_data[0].name}';
                                        var body_2 = '${index_data[0].change}';
                                        var perc_2 = '${index_data[0].percentage}';
                                        var price_2 = '${index_data[0].price}';
                                        
                                        var body = '';
                                        if(body_0.includes('-')){
                                                  body = "<p class='text-danger' >" + body_0 + '  (' + perc_0 + '%) </p><p>' + price_0 + '</p>';
                                                     }
                                        else{
                                        	      body = "<p class='text-success' >" + body_0 + '  (' + perc_0 + '%) </p><p>' + price_0 + '</p>';
                                                     }
									    $.gritter.add({
									    		  title: name_0,
									    		  text: body ,
									    		  class_name: 'gritter-light',
									    		  after_close: function(){
										    		           if(!price_1.includes('N/A')){
												    		        	 body = '';
									                                     if(body_1.includes('-')){
									                                                  body = "<p class='text-danger' >" + body_1 + '  (' + perc_1 + '%) </p><p>' + price_1 + '</p>';
									                                                     }
									                                     else{
									                                        	      body = "<p class='text-success' >" + body_1 + '  (' + perc_1 + '%) </p><p>' + price_1 + '</p>';
									                                                     }
														    			  $.gritter.add({
																    		  title: name_1,
																    		  text: body,
																    		  class_name: 'gritter-light',
																    		  after_close: function(){
															    		           if(!price_2.includes('N/A')){
																	    		        	 body = '';
														                                     if(body_2.includes('-')){
														                                                  body = "<p class='text-danger' >" + body_2 + '  (' + perc_2 + '%) </p><p>' + price_2 + '</p>';
														                                                     }
														                                     else{
														                                        	      body = "<p class='text-success' >" + body_2 + '  (' + perc_2 + '%) </p><p>' + price_2 + '</p>';
														                                                     }
																			    			  $.gritter.add({
																					    		  title: name_2,
																					    		  text: body,
																					    		  class_name: 'gritter-light'
																					    		  
																			    			  });
															    		                }
																		  }
																    		  
														    			  });
										    		                }
													  }
									    		});
                                   </c:if>
				
				         });



					  //]]>
		</script>
		
		
	</body>
	
</html>