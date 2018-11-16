<%-- 
    Document   : CustomerOrder
    Created on : 15-11-2018, 09:32:45
    Author     : Esben
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Krav til Carport:</h1>

        <%
            int numberOfLogs = (int) request.getAttribute("numberOfLogs");
            double lenghtOfBand = (double) request.getAttribute("lenghtOfBand");
            int numberOfRafters = (int) request.getAttribute("numberOfRafters");
            double areaOfRoof = (double) request.getAttribute("areaOfRoof");
        %>
        

        <%
            if (request.getParameter("sroof").equals("true")) {
                double heightOfRoof = (double) request.getAttribute("heightOfRoof");
                double rafterLenght = (double) request.getAttribute("rafterLenght");
                double areaOfGable = (double) request.getAttribute("areaOfGable");
            }
        %>

        <h2>Number of logs: <% out.print(numberOfLogs);%> </h2>
    </body>
</html>
