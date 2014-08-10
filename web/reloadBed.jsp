<%-- 
    Document   : index2
    Created on : Jul 29, 2014, 12:09:08 AM
    Author     : Administrator
--%>

<%@page import="dao.*"%>
<%@page import="entity.*"%>
<%@page import="java.util.List"%>
<link rel="stylesheet" href="css/foundation.css" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
    <center>

        <% List<Bed> allBeds = BedDAO.retrieveAllBeds(); %>
        <table style ="border-spacing:80px 10px">
            <tr>
            <% 
                for (int i = 0; i < allBeds.size(); i++) {
                        Bed bed = allBeds.get(i);
                        String bedId = bed.getBedId();

                        if ((i) % 3 == 0) { // create new row from 3rd row onwards
                            out.println("<tr>");
                        }

                        if (!bed.getAvailabilityStatus().equals("true")) {%>
                            <td height = "200" width ="150">
                                <a href="#" data-reveal-id="<%=bedId%>"><b>Bed <%=bedId%></b><br> Occupied</a>
                            </td>
                <%} else {%>
                        <td height = "200" width ="150" bgcolor = "92d400">
                            <a href="#" data-reveal-id="<%=bedId%>"><b>Bed <%=bedId%></b><br> Available</a>
                        </td>
                <% }
                } 
            %>
            </tr>
        </table>
    </center>
<!--start of reveal modal-->
    <%
        for (int i = 0; i < allBeds.size(); i++) {
            Bed bed = allBeds.get(i);
            String bedId = bed.getBedId();
    %>

            <div id="<%=bedId%>" class="reveal-modal" data-reveal>
                <h2><%=bedId%> Information</h2>
                <p class="lead">Your couch.  It is mine.</p>
                <a class="close-reveal-modal">&#215;</a>
            </div>

    <% }%>   
<!--end of reveal modal-->
    <script>
        $(document).ready(function() {
            $(document).foundation();
        });

    </script>
</body>
</html>
