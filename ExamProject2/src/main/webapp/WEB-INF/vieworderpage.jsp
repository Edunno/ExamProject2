<%-- 
    Document   : CustomerOrder
    Created on : 15-11-2018, 09:32:45
    Author     : Esben
--%>

<%@page import="partslist.*"%>
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

    <table style="width:25%">
        <tr>
            <th>Type</th>
            <th>Mængde</th> 
        </tr>
        <tr>
            <td>Antal stolper: </td>
            <td><% out.print(numberOfLogs + " stk");%></td>
        </tr>
        <% if (request.getParameter("sroof").equals("false")) {
                double lenghtOfBand = (double) request.getAttribute("lenghtOfBand");%>
        <tr>
            <td>Længde af stålbånd: </td>
            <td><% out.print(lenghtOfBand + "m"); %></td>
        </tr>
        <% }  %>
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
    <br>
    <br>
    Træ:
    <table style="width:66%">
        <tr>
            <th>Type</th>
            <th>Bredde</th> 
            <th>Højde</th> 
            <th>Længde</th> 
            <th>Antal</th> 
            <th>Beskrivelse</th> 
        </tr>
        <% Partslist pl = (Partslist) request.getAttribute("pl"); %>
        <% for (Wood w : pl.getWoodList()) { %>
        <tr>
            <td><% out.print(w.getName()); %></td>
            <td><% out.print(w.getWidth() * 10 + "mm"); %></td>
            <td><% out.print(w.getHeight() * 10 + "mm");%></td>
            <td><% out.print(w.getLength() + "cm");%></td>
            <td><% out.print(w.getQty());%></td>
            <td><% out.print(w.getDescription());%></td>
        </tr>


        <% }%>


    </table>
    <br>
    Øvrige matieraler:
    <table style="width:66%">
        <tr>
            <th>Type</th>
            <th>Antal</th> 
            <th>Beskrivelse</th> 
        </tr>
        <% for (Material m : pl.getMatList()) { %>
        <tr>
            <td><% out.print(m.getName()); %></td>
            <td><% out.print(m.getQty());%></td>
            <td><% out.print(m.getDescription());%></td>
        </tr>


        <% }%>


    </table>


</body>
</html>
