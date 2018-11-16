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

        <style>
            table, th, td {
                border: 1px solid black;
                border-collapse: collapse;
            }
        </style>
    </head>
<body>

    <table style="width:50%">
        <tr>
            <th>Type</th>
            <th>Mængde</th> 
        </tr>
        <tr>
            <td>Antal stolper: </td>
            <td><% out.print(numberOfLogs + " stk");%></td>
        </tr>
        <tr>
            <td>Længde af stålbånd: </td>
            <td><% out.print(lenghtOfBand + "m"); %></td>
        </tr>
        <tr>
            <td>Antal spær: </td>
            <td><% out.print(numberOfRafters + " stk"); %></td>
        </tr>
        <tr>
            <td>Areal af tag: </td>
            <td><% out.print(areaOfRoof + "m²"); %></td>
        </tr>

        <% if (request.getParameter("sroof").equals("true")) {
                double heightOfRoof = (double) request.getAttribute("heightOfRoof");
                double rafterLenght = (double) request.getAttribute("rafterLenght");
                double areaOfGable = (double) request.getAttribute("areaOfGable");

        %>
        <tr>
            <td>Højde af tag: </td>
            <td><% out.print(heightOfRoof + "m"); %></td>
        </tr>
        <tr>
            <td>Længde af spær: </td>
            <td><% out.print(rafterLenght + "m"); %></td>
        </tr>
        <tr>
            <td>Areal af gavl: </td>
            <td><% out.print(areaOfGable + "m²"); %></td>
        </tr>
        <%
            }
        %>
    </table>


</body>
</html>
