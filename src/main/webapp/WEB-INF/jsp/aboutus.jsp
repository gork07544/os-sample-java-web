<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
      <%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page isELIgnored="false" %>
<title> About TVSConsult | Business consulting | Aalborg </title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="TVS Consult is trying to provide and apply for the best funding possibilities for a start-up. We achieve that by reducing consulting costs at the beginning but pay only when you get the promised fund.">
  <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Montserrat">
  <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Varela Round">
   <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.1/animate.min.css">
   
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <c:url var="eUrl" value="/csss/site.css"/>
<link rel="stylesheet" href="${eUrl}" type="text/css" />
<!-- Custom Fonts -->
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
<body>



<c:url var="indexUrl" value="/index.jsp"/>
<nav class="navbar navbar-default" style="background-color: none;">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="${indexUrl}">TVSConsult</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
   <ul class="nav navbar-nav">
        <li><a data-ajax="false" href="${indexUrl}">Home</a></li>
        
        <c:url var="projectsUrl" value="/projects"/>
        <li><a data-ajax="false" href="${projectsUrl}">Projects</a></li>

        <c:url var="aboutusUrl" value="/aboutus"/>
        <li data-ajax="false"  class="active"><a href="${aboutusUrl}">About us</a></li>

        <c:url var="contactUrl" value="/contacts"/>
        <li><a data-ajax="false" href="${contactUrl}">Contact</a></li>
      </ul>

       <ul class="nav navbar-nav navbar-right">
       <c:url var="signUrl" value="/createuser"/>
        <li><a data-ajax="false" href="${signUrl}"><span class="glyphicon glyphicon-new-window"></span> Sign Up</a></li>
        
<c:url var="userUrl" value="/user"/>
<c:url var="logoutUrl" value="/logout"/>
<c:choose>
  <c:when test="${pageContext.request.userPrincipal.authenticated}">
	<li><a href="${userUrl}"><span class="glyphicon glyphicon-user"></span>
	 	<c:out value="${pageContext.request.remoteUser}"/></a>
	</li>
	<li>
    <form class="form-inline" action="${logoutUrl}" method="post" style="width:100%;">
      <button type="submit" value="Log out"  class="btn-link" style="color:#000000;width:100%;"><span class="glyphicon glyphicon-log-out"></span> Log out</button>
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
    </li>
</c:when>
  <c:otherwise>
  		<c:url var="loginUrl" value="/login"/>		
		<li><a href="${loginUrl}"><span class="glyphicon glyphicon-log-in"></span> Log in</a></li>
	</c:otherwise>
</c:choose>
		

      </ul>
    </div>
  </div>
</nav>
 
 
 
 <div class="container-fluid" style="padding:0px;margin-top:-20px;">





<c:url var="poaralax1" value="/images/paralax1.jpg"/>
<c:url var="logo" value="/images/logo.png"/>
<div class="parallax-window" data-parallax="scroll" data-image-src="${poaralax1}" data-speed="0.4" data-bleed="60" data-ios-fix="true" data-android-fix="true" data-z-index="-10"
	 style="text-align: center;background: transparent;">
<img class="  wow fadeIn" data-wow-duration="1.5s" data-wow-delay="0.2s" src="${logo}" style="width:45%;min-width:240px;"  />
<div class="row wow fadeIn" data-wow-duration="1.5s" data-wow-delay="0.2s" style="margin-top:0px; width:100%;text-align: center; ">
<h2 class="frontImagetext1" style="display:inline-block;text-align: center;color: white;">
Make your business successful with the help of TVSConsult
</h2>
<br /><br />
<h4>
 Find the best financial fund for your business. We have a long list with public financial funds available for applicants. 

</h4><h4>
We get money only after the job is done. 
</h4>
<br /><br /><br /><br /><br /><br />
</div>
</div>






<div class="wow row fadeIn" data-wow-duration="0.7s"  style="background-color:#34302D;width: 100%;">
<br /><br /><br />
<h2 class="frontImagetext1 wow fadeIn"  data-wow-duration="1.5s"  data-wow-delay="0.5s" style="text-align: center;color: white;">
TVS Consult's team
</h2><br />
<h4 class="wow fadeIn"  data-wow-duration="1.5s"  data-wow-delay="0.5s" style="max-width:80%;text-align: center;margin:auto;">
TVS Consult is a small startup company located in Aalborg. The company helps, consults and give advices new startups in order to find the best available funds. 
</h4>
<br /><br />
<hr style = 'background-color:#1a1a1a; border-width:2; border-color:#4d4d4d; height:1px; lineheight:0; width:70%;' />
<br /><br />
	<div class="col-md-1">
	</div>
	<div class="col-md-10">
<c:url var="maskTeam" value="/images/team/mask_team.png"/>
<div class="col-md-4 wow fadeIn " data-wow-duration="1.2s" data-wow-delay="0.3s">
		<div style="height:170px;margin-left:-170px;text-align: center;">
			<c:url var="mem1" value="/images/team/5.jpg"/>
			<img src="${mem1}" alt="ieeeeee" style="height:170px;position:absolute;  ">
			<img src="${maskTeam}" alt="ieeeeee" style="height:170px;position:absolute;  ">
		</div>
		<div class="offset-feature--text" style="margin-top:0px;color:white;text-align: center;">  
			<h2>CEO</h2>
			<h4>Teodora Staneva</h4>

		</div>
	<br /><br />
</div>
<div class="col-md-4 wow fadeIn" data-wow-duration="1.2s" data-wow-delay="0.6s">
		<div style="height:170px;margin-left:-170px;text-align: center;">
			<c:url var="mem2" value="/images/team/4.jpg"/>
			<img src="${mem2}" alt="ieeeeee" style="height:170px;position:absolute;  ">
			<img src="${maskTeam}" alt="ieeeeee" style="height:170px;position:absolute;  ">
		</div>
		<div class="offset-feature--text" style="margin-top:0px;color:white;text-align: center;"> 
		<h2>Software developer</h2>
		<h4>Georgi Andonov</h4>
		</div>
</div>
<div class="col-md-4 wow fadeIn" data-wow-duration="1.2s" data-wow-delay="0.9s">
		<div style="height:170px;margin-left:-170px;text-align: center;">
			<c:url var="mem3" value="/images/team/6.jpg"/>
			<img src="${mem3}" alt="ieeeeee" style="height:170px;position:absolute;  ">
			<img src="${maskTeam}" alt="ieeeeee" style="height:170px;position:absolute;  ">
		</div>
		<div class="offset-feature--text" style="margin-top:0px;color:white;text-align: center;"> 
			<h2>Business Developer</h2>
			<h4>Jekaterina Buraja</h4>
		</div>
</div>

	</div>
	
	<div class="col-md-1">
	</div>

</div>


</div>



   
<footer>	
 	<div id="footer"  class="foot wow fadeIn row" data-wow-duration="0.5s" >
		<hr style = 'background-color:#1a1a1a; border-width:2; border-color:#4d4d4d; height:1px; lineheight:0; width:70%;' />
		<br />
    	<ul id="nav" class=" wow fadeIn"  data-wow-duration="1.5s"  data-wow-delay="0.5s" >
        	<li ><a href="${indexUrl}" title="Home">Home</a></li>
            <li><a href="${projectsUrl}" title="Projects">Projects</a></li>
            <li><a href="${aboutusUrl}" title="About us">About us</a></li>
            <li><a href="${contactUrl}" title="Contact">Contact</a></li>
            <li><a href="${indexUrl}" title="Register">Register</a></li>
            <li ><a href="${indexUrl}" style="border-right:0px;" title="Log in">Log in</a></li>   
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
        <p><a href="${aboutusUrl}" title="G.A. web development">G.A. web development</a></p>
        <p>© 2015 <a href="${aboutusUrl}">G.A. Dev. </a>
        	Inc. All Rights Reserved. <a href="${aboutusUrl}">Terms of Use</a>
        	 and <a href="${aboutusUrl}">Privacy</a>
        </p>
        <br />
        
 
	</div>
</footer>
</body>

</html>