<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
      <%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page isELIgnored="false" %>
<title>TVSConsult | Business consulting | Aalborg </title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="TVSConsult offers the business help with applying for financial funds. We provide the business what it needs. Payment after you actually benefit.">
  <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Montserrat">
  <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Varela Round">
   <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.1/animate.min.css">
   
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <c:url var="eUrl" value="/csss/site.css"/>
<link rel="stylesheet" href="${eUrl}" type="text/css" />
<!-- Custom Fonts -->
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">


  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- <script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
  -->
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/wow/1.1.2/wow.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/parallax.js/1.4.1/parallax.min.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
  <script>
              new WOW().init();
              </script>

<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-69969007-1', 'auto');
  ga('send', 'pageview');

</script>
</head>
<body style="text-align:center;">

<div id="topdetection" class="container body-content" >
	<div id="dvid2" style="position:fixed;top:0px;">
	</div>

<!-- Renders the GoTop botton -->
	<div id="dvid">
  		<div class="btn btn-circle page-scroll" >
        	<i class="fa fa-angle-double-up animated"></i>
		</div>
	</div>
</div>

<c:url var="indexUrl" value="/index.jsp"/>
<nav class="navbar navbar-default" style="background-color: #333;">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a data-ajax="false" class="navbar-brand" href="${indexUrl}">TVSConsult</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
     
   <ul class="nav navbar-nav">
        <li class="active"><a data-ajax="false" href="${indexUrl}">Home</a></li>
        
        <c:url var="projectsUrl" value="/projects"/>
        <li><a  href="${projectsUrl}">Projects</a></li>

        <c:url var="aboutusUrl" value="/aboutus"/>
        <li><a  href="${aboutusUrl}">About us</a></li>

        <c:url var="contactUrl" value="/contacts"/>
        <li><a  href="${contactUrl}">Contact</a></li>
      </ul>
      
    <ul class="nav navbar-nav navbar-right">
       <c:url var="signUrl" value="/createuser"/>
        <li><a  href="${signUrl}"><span class="glyphicon glyphicon-new-window"></span> Sign Up</a></li>
        
<c:choose>
  <c:when test="${pageContext.request.userPrincipal.authenticated}">
	<c:url var="userUrl" value="/user"/>
	<li><a  href="${userUrl}"><span class="glyphicon glyphicon-user"></span>
	 	<c:out value="${pageContext.request.remoteUser}"/></a>
	</li>
	<li>
    <c:url var="logoutUrl" value="/logout"/>
    <form  class="form-inline" action="${logoutUrl}" method="post" style="width:100%;">
      <button type="submit" value="Log out"  class="btn-link" style="color:#000000;width:100%;"><span class="glyphicon glyphicon-log-out"></span> Log out</button>
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
    </li>
</c:when>
  <c:otherwise>
  		<c:url var="loginUrl" value="/login"/>		
		<li><a  href="${loginUrl}"><span class="glyphicon glyphicon-log-in"></span> Log in</a></li>
	</c:otherwise>
</c:choose>
		

      </ul>
    </div>
  </div>
</nav>
 

 <div class="container-fluid" style="padding:0px;margin-top:-20px;">


<!-- Used to messere the distance from the top -->




 <div id="myCarousel" class="carousel slide wow fadeIn" data-wow-duration=".7s" data-wow-delay=".1s" data-ride="carousel" >
    <!-- Indicators -->
    <ol class="carousel-indicators">
     <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
      <li data-target="#myCarousel" data-slide-to="3"></li>
    </ol>

 <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">


 <div class="item active">
	<c:url var="homePageImageBan1" value="/images/front_page_ban/front11.jpg"/>
	<div class="caritem">
		<div class="carimgholder">
		<img src="${homePageImageBan1}" class="carimg"/>
		</div>
		<div class="carbottomcontrol">
		</div>
		<div class="carousel-caption">
			<h2 class="frontImagetext1 cartext">
				Get the right funds for your start-up
			</h2>
			<h4 class="frontImagetext1 cartext">
				“Our mission is to provide and apply for the most suitable funding possibilities for a start-up by reducing consulting costs at the beginning but pay only when you get the promised fund.”
			</h4>
			<br/>
			<div>
				<a href="${projectsUrl}" class="btn btnfr btn-lg"><h4 style="font-weight: bold;">Book a meeting</h4></a>
			</div>
		</div>
      	  </div>			
       </div>



   <div class="item">
	<c:url var="homePageImageBan2" value="/images/front_page_ban/front21.jpg"/>
	<div class="caritem">
		<div class="carimgholder">
		<img src="${homePageImageBan2}" class="carimg"/>
		</div>
		<div class="carbottomcontrol">
		</div>
		<div class="carousel-caption">
			<h2 class="frontImagetext1 cartext">
				Find the money your business needs
			</h2>
			<h4 class="frontImagetext1 cartext">
				It is time consuming operations to search, apply and deal with funds. TVS Consult can handle all those time consuming operations in order to make your start-up competitive and successful.
			</h4>
			<br/>
			<div>
				<a href="${contactUrl}" class="btn btnfr btn-lg"><h4 style="font-weight: bold;">See available funds</h4></a>
			</div>
		</div>
      	  </div>			
       </div>


 <div class="item">
 	<c:url var="logo" value="/images/logo.png"/>
	<c:url var="homePageImageBan3" value="/images/front_page_ban/front31.jpg"/>
	<div class="caritem">
		<div class="carimgholder">
		<img src="${homePageImageBan3}" class="carimg"/>
		</div>
		<div class="carbottomcontrol">
		</div>
		<div class="carousel-caption"><br/>
			<img src="${logo}" style="width:60%;min-width:280px;margin-top:-40px;margin-bottom:-20px;" />			

			<h4 class="frontImagetext1 cartext">
			“Our vision is to be the consulting company that best understands the start-ups` financial needs and provide them with an ease, less bureaucratic process for applying for funds.  
			</h4><br/>
			<div>
					<a href="${aboutusUrl}" class="fronPageLinks">Contact us &raquo;</a>
			</div><br/><br/>
		</div>
      	  </div>			
       </div>

 <div class="item">
	<c:url var="homePageImageBan4" value="/images/front_page_ban/front41.jpg"/>
	<div class="caritem">
		<div class="carimgholder">
		<img src="${homePageImageBan4}" class="carimg"/>
		</div>
		<div class="carbottomcontrol">
		</div>
		<div class="carousel-caption">
			<h2 class="frontImagetext1 cartext">
				Contact TVS Consult at any time.
			</h2>
			<h4 class="frontImagetext1 cartext">
				Contact TVS Consult at any time if you have any questions about the our services. We believe that the good communication between the customers and our company is the key to success.  
			</h4>
			<br/>
			<div><br/>
				<a href="${projectsUrl}" class="btn btnfr btn-lg"><h4 style="font-weight: bold;">See available funds</h4></a>
			</div>
		</div>
      	  </div>			
       </div>

    </div>

<!-- Left and right controls -->
  <a  class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a  class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>


  </div>







 <!-- Example row of columns -->
      <div class="row aboveFrontImage wow fadeInUp" data-wow-duration="0.5s">
              <br /> 
<h2 class="frontImagetext1 wow fadeIn"  data-wow-duration="1.5s"  data-wow-delay="0.5s"  style="text-align: center;color: #e6e6e6;">
"Ensure your business success with TVSConsult services."
</h2>
<br />
<h4 class="wow fadeIn"  data-wow-duration="1.5s"  data-wow-delay="1s"  style="margin:auto;width:70%;">Let your business take a big advantage using the best TVS Consult services. We provide a wide range of services with a very good pay plans suitable for all kind of businesses.</h4>
<hr style = 'background-color:#1a1a1a; border-width:2; border-color:#4d4d4d; height:1px; lineheight:0; width:90%;' />
 <br />
	<div class="col-md-1">
	</div>
	<div class="col-md-10">
        <div class="col-md-4 wow zoomIn" data-wow-duration="0.7s" data-wow-delay="0.5s" style="text-align: center;">
        	<c:url var="above_front11" value="/images/above_front/rev_rot_color.png"/>
        	<c:url var="above_front12" value="/images/above_front/rev_rot.png"/>
		<div class="cf">
  			<img class="bottom" src="${above_front11}" />
 		 	<img class="top" src="${above_front12}" />
 		</div>
          	<h2>DISCOUNTS</h2>
          	<h4>We get money only after the job is done. For more help and work unrelated with a particular project additional fees may apply.</h4>
			<a  class="fronPageLinks" href="${contactUrl}">Find out more &raquo;</a>
			<br /> <br />
	</div>
 <div class="col-md-4 wow zoomIn" data-wow-duration="0.7s" data-wow-delay="0.5s" style="text-align: center;">
         	<c:url var="above_front21" value="/images/above_front/doc_rot_color.png"/>
        	<c:url var="above_front22" value="/images/above_front/doc_rot.png"/>
		<div class="cf">
  			<img class="bottom" src="${above_front21}" />
 		 	<img class="top" src="${above_front22}" />
 		</div>
          	<h2>FIND PROJECTS</h2>
          	<h4>Find the best financial fund for your business. We have a long list with public financial funds available for applicants.  </h4>
       		<a  class="fronPageLinks" href="${projectsUrl}">Find out more &raquo;</a>
			<br /> <br />
       </div>
 <div class="col-md-4 wow zoomIn" data-wow-duration="0.7s" data-wow-delay="0.5s" style="text-align: center;">
        	<c:url var="above_front31" value="/images/above_front/help_rot_color.png"/>
        	<c:url var="above_front32" value="/images/above_front/help_rot.png"/>
		<div class="cf">
  			<img class="bottom" src="${above_front31}" />
 		 	<img class="top" src="${above_front32}" />
 		</div>
          	<h2>GET HELP</h2>
         	<h4>Let TVSConsult to take care of the application details while your business is focus in other areas of interests.</h4>
			<a  class="fronPageLinks" href="${aboutusUrl}">Find out more &raquo;</a>
			<br /> <br /> <br /><br />
        </div>
	 </div>
	<div class="col-md-1">
	</div>

      </div>




<c:url var="poaralax1" value="/images/paralax1.jpg"/>

<div class="parallax-window" data-parallax="scroll" data-image-src="${poaralax1}" data-speed="0.4" data-bleed="60" data-ios-fix="true" data-android-fix="true"
	 style="text-align: center;background: transparent;">
<img class="  wow fadeIn" data-wow-duration="1.5s" data-wow-delay="0.2s" src="${logo}" style="width:45%;min-width:240px;"  />
<div class="row wow fadeIn" data-wow-duration="1.5s" data-wow-delay="0.2s" style="margin-top:0px; width:100%;text-align: center; ">
<h2 class="frontImagetext1" style="display:inline-block;text-align: center;color: white;">
Make your business successful with the help of TVSConsult
</h2>
<br /><br />
<h4>
 TVSConsult can deal with all of the application details giving the business the time needed to success. Our database has a lot of valuable information about  public financial funds available for applicants. 
</h4>
<br /><br /><br /><br /><br /><br />
</div>
</div>







<c:url var="white_background" value="/images/white_background_frontage.jpg"/>
<div class="row wow fadeInUp" data-wow-duration="0.5s" style="text-align: center;width:100%;background-image: url(${white_background});border-bottom:1px solid #dddbda;">
		
	<div class="col-md-1">
	</div>
	<div class="col-md-10">
	<br /><br />
	<h2 class="frontImagetext1  wow fadeIn"  data-wow-duration="1.5s"  data-wow-delay="0.3s" style="text-align: center;color: #34302D;">
		Use all benefits TVSConsult offers.
	</h2> 
    <div class="offset-feature--text wow fadeIn"   data-wow-duration="1.5s"  data-wow-delay="0.6s"    >
		Whether you're an expert or a newcomer our task-focused to help start-ups find the most suitable funds for them and assists with the application process.Whether you're an expert or a newcomer our task-focused to help start-ups find the most suitable funds for them and assists with the application process.
	</div><br />
	<a  class="fronPageLinks  wow fadeIn" data-wow-duration="1.5s" data-wow-delay="0.9s" style="text-shadow:none" href="${projectsUrl}">
		Browse the Project &raquo;
	</a>
<br /><br /><br />
      <hr style = 'background-color:#cccccc; border-width:2; border-color:#d9d9d9; height:1px; lineheight:0; width:100%;' />

<br /><br /><br />
      <!-- Example row of columns -->
	<div class="row" style="text-align: center;width:100%;">	
		<div class="col-md-4  wow zoomIn" data-wow-duration="0.7s" data-wow-delay="0.5s" >
          <h2 class="offset-feature--title" style="font-size: 30px;color: #34302D;">DISCOUNTS</h2>
          <div class="offset-feature--text" style="margin-top: 10px;">We get money only after the job is done. For more help and work unrelated with a particular project additional fees may apply.</div>
          <br />
          <a " class="fronPageLinks"  style="text-shadow:none" href="${contactUrl}">View details &raquo;</a>
        <br /><br /><br />
        </div>
		<div class="col-md-4 wow zoomIn" data-wow-duration="0.7s" data-wow-delay="0.5s" >
          <h2 class="offset-feature--title" style="font-size: 30px;color: #34302D;">FIND PROJECTS</h2>
          <div class="offset-feature--text" style="margin-top: 10px;">Find the best financial fund for your business. We have a long list with public financial funds available for applicants.</div>
          <br />
          <a  class="fronPageLinks"  style="text-shadow:none" href="${projectsUrl}">View details &raquo;</a>
        <br /><br /><br />
        </div>
		<div class="col-md-4 wow zoomIn" data-wow-duration="0.7s" data-wow-delay="0.5s" >
          <h2 class="offset-feature--title" style="font-size: 30px;color: #34302D;">GET HELP</h2>
          <div class="offset-feature--text" style="margin-top: 10px;">Let TVSConsult to take care of the application details while your business is focus in other areas of interests.</div>
          <br />
          <a  class="fronPageLinks"  style="text-shadow:none" href="${aboutusUrl}">View details &raquo;</a>
          <br /><br /><br /><br /><br /><br />
        </div>
     </div>
		
   </div>

	<div class="col-md-1">
	</div>
</div>  



<c:url var="poaralax1" value="/images/paralax2.jpg"/>
<c:url var="logo" value="/images/logo.png"/>
<div class="parallax-window" data-parallax="scroll" data-image-src="${poaralax1}" data-speed="0.4" data-bleed="60" data-ios-fix="true" data-android-fix="true"
	 style="text-align: center;background: transparent;">
<img class="  wow fadeIn" data-wow-duration="1.5s" data-wow-delay="0.3s" src="${logo}" style="width:45%;min-width:240px;"  />
<div class="row wow fadeIn" data-wow-duration="1.5s" data-wow-delay="0.3s" style="margin-top:0px; width:100%;text-align: center; ">
<h2 class="frontImagetext1" style="display:inline-block;text-align: center;color: white;">
Make your business successful with the help of TVSConsult
</h2>
<br /><br />
<h4> Find the best financial fund for your business. We have a long list with public financial funds available for applicants. 
</h4>
<h4>We get money only after the job is done. 
</h4>
<br /><br /><br /><br /><br /><br />
</div>
</div>





<div class="row wow fadeInUp" data-wow-duration="0.5s" style="width: 100%;background-color:#34302D;">
	<div class="col-sm-2">
	</div>
	<div class="col-sm-4 wow fadeIn"  data-wow-duration="1.5s"  data-wow-delay="0.5s" style="text-align: center;">
			<br /><br />
		<div class="icon icon-community-support"></div>
		<div class="support--title">
			Contact Support
		</div>
		<div>
			<a href="${contactUrl}">Contact us</a>
		</div>
			<br /><br />
	</div>
	<div class="col-sm-4 wow fadeIn"  data-wow-duration="1.5s"  data-wow-delay="0.5s" style="text-align: center;" >
			<br />	<br />
		<div class="icon icon-community-support"></div>
		<div class="support--title">
			Professional Support
		</div>
		<div >
			<a  href="${projectsUrl}">Projects</a>
			<a  style="margin-left: 3%;" href="${aboutusUrl}">About us</a>
			<a  style="margin-left: 3%;" href="${contactUrl}">Consulting</a>
		</div>
			<br /><br />
	</div>
	<div class="col-sm-2">
	</div>
</div>
 





 	<div id="footer"  class="wow fadeIn row" data-wow-duration="0.5s">
		<hr style = 'background-color:#1a1a1a; border-width:2; border-color:#4d4d4d; height:1px; lineheight:0; width:70%;' />
		<br />
    	<ul id="nav" class=" wow fadeIn"  data-wow-duration="1.5s"  data-wow-delay="0.5s" >
        	<li ><a  href="${indexUrl}" title="Home">Home</a></li>
            <li><a  href="${projectsUrl}" title="Projects">Projects</a></li>
            <li><a  href="${aboutusUrl}" title="About us">About us</a></li>
            <li><a  href="${contactUrl}" title="Contact">Contact</a></li>
            <li><a  href="${indexUrl}" title="Register">Register</a></li>
            <li ><a  href="${indexUrl}" style="border-right:0px;" title="Log in">Log in</a></li>   
        </ul>
		<br />
		
		<div id="soccial"  class="row" >
		<div class="col-md-4">
		</div>
		<div class="col-md-4"  style="height:70px;"  >
			<div class="cf  wow fadeIn"  data-wow-duration="1.5s"  data-wow-delay="0.5s" style="margin-left:-40px; display: inline;">
  				<c:url var="facebook" value="/images/social/facebook.png"/>
				<c:url var="facebook_dark" value="/images/social/facebook_dark.png"/>
  				<img class="bottom" src="${facebook}" alt="ieeeeee" style="height:50px;" />
 		 		<img class="top" src="${facebook_dark}" alt="ieeeeee" style="height:50px; " />
 			</div>
			<div class="cf  wow fadeIn"  data-wow-duration="1.5s"  data-wow-delay="0.5s" style="margin-left:60px; display: inline;">
				<c:url var="twitter" value="/images/social/twitter.png"/>
				<c:url var="twitter_dark" value="/images/social/twitter_dark.png"/>
  				<img class="bottom" src="${twitter}" alt="ieeeeee" style="height:50px; " />
 		 		<img class="top" src="${twitter_dark}" alt="ieeeeee" style="height:50px; "/>
 			</div>
			<div class="cf wow fadeIn"  data-wow-duration="1.5s"  data-wow-delay="0.5s" style="margin-left:60px; display: inline;">
  				<c:url var="google" value="/images/social/google.png"/>
				<c:url var="google_dark" value="/images/social/google_dark.png"/>
  				<img class="bottom" src="${google}" alt="ieeeeee" style="height:50px; " />
 		 		<img class="top" src="${google_dark}" alt="ieeeeee" style="height:50px; " />
 			</div>
			<div class="cf wow fadeIn"  data-wow-duration="1.5s"  data-wow-delay="0.5s" style="margin-left:60px; display: inline;">
				<c:url var="rss" value="/images/social/rss.png"/>
				<c:url var="rss_dark" value="/images/social/rss_dark.png"/>
  				<img class="bottom" src="${rss}" alt="ieeeeee" style="height:50px; " />
 		 		<img class="top" src="${rss_dark}" alt="ieeeeee" style="height:50px; " />
 			</div>
			<div class="cf wow fadeIn"  data-wow-duration="1.5s"  data-wow-delay="0.5s" style="margin-left:60px; display: inline;">
				<c:url var="blogger" value="/images/social/blogger.png"/>
				<c:url var="blogger_dark" value="/images/social/blogger_dark.png"/>
  				<img class="bottom" src="${blogger}" alt="ieeeeee" style="height:50px; " />
 		 		<img class="top" src="${blogger_dark}" alt="ieeeeee" style="height:50px; " />
 			</div>
 			<div class="cf wow fadeIn"  data-wow-duration="1.5s"  data-wow-delay="0.8s" style="margin-left:55px; display: inline;">
				<c:url var="link" value="/images/social/link.png"/>
				<c:url var="link_dark" value="/images/social/link_dark.png"/>
  				<img class="bottom" src="${link}" alt="ieeeeee" style="height:50px;width:50px; " />
 		 		<img class="top" src="${link_dark}" alt="ieeeeee" style="height:50px;width:50px; " />
 			</div>
			<div class="cf wow fadeIn"  data-wow-duration="1.5s"  data-wow-delay="0.8s" style="margin-left:60px; display: inline;">

 			</div>
		</div>
		<div class="col-md-4">
		</div>
	</div>
		
		
		
		<br />
        <p><a  href="${aboutusUrl}" title="G.A. web development">G.A. web development</a></p>
        <p>© 2015 <a  href="${aboutusUrl}">G.A. Dev. </a>
        	Inc. All Rights Reserved. <a  href="${aboutusUrl}">Terms of Use</a>
        	 and <a  href="${aboutusUrl}">Privacy</a>
        </p>
        <br />
        
 
	</div>
</div> 
<script>




$(document).ready(function() {
	   
	
	collapseNavbar();
	//jQuery to collapse the navbar on scroll
	function collapseNavbar() {
		var topLine = $("#dvid2");
		var goTop = $("#dvid");
	 if (topLine.offset().top > 300) {
			goTop.fadeIn();
			console.log("Yes");
	 } else {
			goTop.fadeOut();
	 }
	}

	$(window).scroll(collapseNavbar);
	
	
	
	$(function() {
	    $('.page-scroll').bind('click', function(event) {
	        $('html, body').stop().animate({
	            scrollTop: $('#topdetection').offset().top
	        }, 1300, 'easeInOutExpo');
	        event.preventDefault();
	    });
	});
	
	
		$.mobile.loading().hide();
	
		$("#myCarousel").swiperight(function() {
	      $(this).carousel('prev');
	    });
	   $("#myCarousel").swipeleft(function() {
	      $(this).carousel('next');
	   });
	   
	   $('#myCarousel').on('slide.bs.carousel', function () {
	 		$('.carousel-caption').fadeOut(400, 'swing', function() {
	 			$('.carousel-caption').fadeIn(400, 'swing');
	 		  });
		});
	   

	});


	
</script>
</body>

</html>