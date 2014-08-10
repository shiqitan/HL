<%-- 
    Document   : mainLogin
    Created on : Aug 4, 2014, 1:33:29 PM
    Author     : weiyi.ngow.2012
--%>
<link rel="stylesheet" href="css/foundation.css" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <title>Hospital Ward Management System for NP Health Sciences</title>
    </head>
    <body>
    <center>        
        <h1>HEALTH LAB</h1>
        <h2>Enriching Lives, Enriching You</h2> 
    </center>
        <div class="row">
        <div class="large-centered large-4 columns">
            
            <% 
            
            String userid = ""; 
            String error = (String) request.getAttribute("error");
            if (error != null) {
                userid = request.getParameter("userid");
                out.println(error);
            }
            
        %> 
        
        <div class="card">

            <div class="card-content">
                
        <form action = "ProcessLogin" method = "post">
                        <label>User ID</label>
                        <input type = "text" placeholder="User ID" name = "userid" value = <%= userid %>>
                        <label>Password</label>
                        <input type = "password" placeholder="Password" name = "password">
                    
                        <label>Domain</label>
                        <select name = "userType">
                            <option value="student">NPSTUDENT</option>
                            <option value="staff">NPSTAFF</option>
                        </select>
                    
            <p><input type = "submit" class="button small" value = "Enter"></p>
        </form>
            </div>
        </div>
        </div>
        </div>
                        
    </body>
</html>