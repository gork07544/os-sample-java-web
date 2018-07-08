<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
      <%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> Contact TVSConsult | Business consulting | Aalborg </title>
 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="If you have any questions about TVS Consult please feel free to contact us via phone, email or book a meeting. TVS Consult has excellent clients support ready to answer any questions.">
  <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Montserrat">
  <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Varela Round">
   <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.1/animate.min.css">
   
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <c:url var="eUrl" value="/csss/site.css"/>
<link rel="stylesheet" href="${eUrl}" type="text/css" />
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/wow/1.1.2/wow.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/parallax.js/1.4.1/parallax.min.js"></script>
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
        <li><a href="${indexUrl}">Home</a></li>
        
        <c:url var="projectsUrl" value="/projects"/>
        <li><a href="${projectsUrl}">Projects</a></li>

        <c:url var="aboutusUrl" value="/aboutus"/>
        <li><a href="${aboutusUrl}">About us</a></li>

        <c:url var="contactUrl" value="/contacts"/>
        <li class="active"><a href="${contactUrl}">Contact</a></li>
      </ul>

       <ul class="nav navbar-nav navbar-right">
       <c:url var="signUrl" value="/createuser"/>
        <li><a href="${signUrl}"><span class="glyphicon glyphicon-new-window"></span> Sign Up</a></li>
        
<c:choose>
  <c:when test="${pageContext.request.userPrincipal.authenticated}">
	<c:url var="userUrl" value="/user"/>
	<li><a href="${userUrl}"><span class="glyphicon glyphicon-user"></span>
	 	<c:out value="${pageContext.request.remoteUser}"/></a>
	</li>
	<li>
    <c:url var="logoutUrl" value="/logout"/>
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


<c:url var="paralax1" value="/images/paralax1.jpg"/>
<div class="parallax-window" data-parallax="scroll" data-image-src="${paralax1}" data-speed="0.4" data-bleed="60" data-ios-fix="true" data-android-fix="true" data-z-index="-10"
	 style="text-align: center;">
<div class="row" style="width:100%;text-align: center; ">
<div class="col-md-1">
	</div>
<div class="col-md-7  wow fadeIn" data-wow-duration="1s"  data-wow-delay="0s" style="text-align: left;">

<c:url value="/sendemil" var="sendEmail"/>
<br /><br />
<h2 class="frontImagetext1 wow fadeIn" style="text-align: left;color: white;">Contact us</h2>

<h4 style="text-align: left;color: white;">We would be happy to answer all questions related with TVS Consult services. Please send us a message using the from below with any questions you may have.</h4>

<br /><br />

<div class="row" style="width:100%; ">
<div class="col-md-10" style="text-align: left;">
<form action="${sendEmail}" method="post">
<fieldset class="form-group fildsetCust">
  		<label for="inputEmail"><h4 class="fildsetH4">Email address</h4></label>
  		<input type="email" id="inputEmail" name="inputEmail" class="form-control  input-lg"  placeholder="Enter a valid email address" aria-describedby="basic-addon1">
	</fieldset>
	<br /> 
	<fieldset class="form-group fildsetCust">
  		<label for="inputName"><h4 class="fildsetH4">Name</h4></label>
  		<input type="text" id="inputName" name="inputName" class="form-control  input-lg"  placeholder="Enter a valid email address" aria-describedby="basic-addon1">
	</fieldset>
	<br />
	<fieldset class="form-group fildsetCust">
  		<label for="inputTitle"><h4 class="fildsetH4">Message title</h4></label>
  		<input type="text"  id="inputTitle" name="inputTitle" class="form-control  input-lg" placeholder="Enter a valid message title" >
	</fieldset>
	<br />
	<fieldset class="form-group fildsetCust">
  		<label for="comment"><h4 class="fildsetH4">Message body</h4></label>
  		<textarea class="form-control  input-lg" rows="7" id="comment" name="comment" placeholder="Enter a valid message body" ></textarea>
	</fieldset>
	<br />	<br />
	<input type="hidden"                        
		name="${_csrf.parameterName}"
		value="${_csrf.token}"/>
	<button type="submit" class="btn btn-primary btn-large">Send the email</button>
	<br /> ${message}  <br /><br />

</form>

</div>
<div class="col-md-2" style="text-align: center;">

</div>
</div>
</div>
	<div class="col-md-4 wow fadeIn" data-wow-duration="1s"  data-wow-delay="0s">
		<br /><br /><br /><br />
		<h2 style="text-align: left;">Email</h2>
		<h4 style="text-align: left;">ts@tvsconsult.com</h4>

		<h2 style="text-align: left;">Phone</h2>
		<h4 style="text-align: left;">123456</h4>

		<h2 style="text-align: left;">Address</h2>
		<h4 style="text-align: left;">Thomas Boss Gade 21, Aalborg 9000, Denmark</h4>

		<h2 style="text-align: left;">On the web</h2>
		<h4 style="text-align: left;">LinkedIn  |  Facebook  |  Twitter</h4>
		<br />
	</div>
</div>
</div>



 <div class="row" style="background-color:#34302D;">
	<div class="col-md-12">
	   <br />
	   <h2 class="frontImagetext1 wow fadeInUp" style="text-align: center;color: white;">TVS Consult on the map</h2>
	   <br /> <br /> 
		<div id="googleMap" class=" wow fadeIn" data-wow-duration="1.2s" style="width:100%;height:400px;margin:auto;"></div>
	 	<br /><br />
	
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

  <script>
	var cent;
	var map;
        var infWind = null;
        function initMap() {
        	var cent = new google.maps.LatLng(57.044647,9.928416);
        	map = new google.maps.Map(document.getElementById('googleMap'), {
        		center: cent,
        		zoom: 15,
			mapTypeId:google.maps.MapTypeId.ROADMAP,
			
			// =====Disables the default Google Maps UI components=====
			// disableDefaultUI: true,
	        // scrollwheel: false,
	        // draggable: false,
			// ============
				
	        // How you would like to style the map. 
	        // This is where you would paste any style found on Snazzy Maps.
	        styles: [{
	            "featureType": "water",
	            "elementType": "geometry",
	            "stylers": [{
	                "color": "#000000"
	            }, {
	                "lightness": 17
	            }]
	        }, {
	            "featureType": "landscape",
	            "elementType": "geometry",
	            "stylers": [{
	                "color": "#000000"
	            }, {
	                "lightness": 20
	            }]
	        }, {
	            "featureType": "road.highway",
	            "elementType": "geometry.fill",
	            "stylers": [{
	                "color": "#000000"
	            }, {
	                "lightness": 17
	            }]
	        }, {
	            "featureType": "road.highway",
	            "elementType": "geometry.stroke",
	            "stylers": [{
	                "color": "#000000"
	            }, {
	                "lightness": 29
	            }, {
	                "weight": 0.2
	            }]
	        }, {
	            "featureType": "road.arterial",
	            "elementType": "geometry",
	            "stylers": [{
	                "color": "#000000"
	            }, {
	                "lightness": 18
	            }]
	        }, {
	            "featureType": "road.local",
	            "elementType": "geometry",
	            "stylers": [{
	                "color": "#000000"
	            }, {
	                "lightness": 16
	            }]
	        }, {
	            "featureType": "poi",
	            "elementType": "geometry",
	            "stylers": [{
	                "color": "#000000"
	            }, {
	                "lightness": 21
	            }]
	        }, {
	            "elementType": "labels.text.stroke",
	            "stylers": [{
	                "visibility": "on"
	            }, {
	                "color": "#000000"
	            }, {
	                "lightness": 16
	            }]
	        }, {
	            "elementType": "labels.text.fill",
	            "stylers": [{
	                "saturation": 36
	            }, {
	                "color": "#000000"
	            }, {
	                "lightness": 40
	            }]
	        }, {
	            "elementType": "labels.icon",
	            "stylers": [{
	                "visibility": "off"
	            }]
	        }, {
	            "featureType": "transit",
	            "elementType": "geometry",
	            "stylers": [{
	                "color": "#000000"
	            }, {
	                "lightness": 19
	            }]
	        }, {
	            "featureType": "administrative",
	            "elementType": "geometry.fill",
	            "stylers": [{
	                "color": "#000000"
	            }, {
	                "lightness": 20
	            }]
	        }, {
	            "featureType": "administrative",
	            "elementType": "geometry.stroke",
	            "stylers": [{
	                "color": "#000000"
	            }, {
	                "lightness": 17
	            }, {
	                "weight": 1.2
	            }]
	        }]
                });

    		var myLatlng = cent;
    		var contentString = '<div id="content">' +
                            '<div id="siteNotice">' +
                            '</div>' +
                            '<div id="bodyContent">' +
                            '<h4 class="frontImagetext1" style="text-shadow: 1px 1px 2px black;text-align: center;color: black;">TVS Consult</h4>' +
                            '<p>Address: </p>' +
    			'<p>Thomas Boss Gade 21, Aalborg, 9000, Denmark </p>' +
                            '</div>' +
                            '</div>';

    		var marker=new google.maps.Marker({
      			position:cent,
      			animation:google.maps.Animation.BOUNCE,
    			title: 'TVS Consult',
    			content: contentString
    		});
    		
    		infWind = new google.maps.InfoWindow({
    				content: contentString 
    			});
    		infWind.open(map, marker);
    		google.maps.event.addListener(marker, 'click', function () {
    			/* infWind.open(map, this);  */
    		});

    		marker.setMap(map);

	}

</script>
<script  async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCLy-HJWgxzut2UncxC5A96h_uCIgMILs4&callback=initMap"></script>

</body>

</html>