<%-- 
    Document   : employeepage.jsp
    Created on : Aug 24, 2017, 6:31:57 AM
    Author     : kasper
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee home page</title>
    </head>
    <body>
        
        <h1>Krav til Carport:</h1>

        <form name="addProduct" action="FrontController" method="POST">
            <input type="hidden" name="command" value="addProduct">
            Produkt ID: <input type="text" name="pID"><br>
            Navn: <input type="text" name="pName"><br>
            Pris: <input type="text" name="pPrice"><br>
            Længde: <input type="text" name="pLength"><br>
            Højde: <input type="text" name="pHeight"><br>
            Bredde:  <input type="text" name="pWidth"><br>
        </form>

<%--    

        <style>
            table, th, td {
                border: 1px solid black;
                border-collapse: collapse;
            }
        </style>
   Krav til carport
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
--%>    
    
    </body>
</html>
