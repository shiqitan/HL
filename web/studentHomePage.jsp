<%-- 
    Document   : studentHomePage
    Created on : Aug 4, 2014, 2:00:03 PM
    Author     : weiyi.ngow.2012
--%>

<%@page import="dao.*"%>
<%@page import="entity.*"%>
<%@page import="java.util.List"%>
<link rel="stylesheet" href="css/foundation.css" />
<%@include file="/topbar/topbar.jsp" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="protect.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <script src="js/vendor/modernizr.js" type="text/javascript"></script>
        <script src="js/vendor/jquery.js"></script>
        <script src="js/foundation/foundation.js"></script>
        <script src="/js/foundation.min.js"></script>
        <script src="js/foundation/foundation.tooltip.js"></script>
        <script src="js/foundation/foundation.reveal.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hospital Ward Management System for NP Health Sciences</title>
    </head>
    <body>
        <h1>Welcome, Student</h1>
        
        <a href = "ProcessLogout"> Log Out </a>
        
        <script type="text/javascript">
            var auto = setInterval(function()
            {
                $('#bedInformation').load('reloadbed.jsp').fadeIn("slow");
            }, 1000); // refresh every 1 second
        </script>
        
        <!-- calls the script above for the live feed of beds-->
        <div id="bedInformation"></div>
        
        <!-- script to initialize foundation-->
        <script>
            $(document).ready(function() {
                $(document).foundation();
            });
        </script>
        
    </body>
</html>
