<%
   if(session.getAttribute("User")==null){
       response.sendRedirect("mainLogin.jsp");
   }
    
%>