<%-- 
    Document   : employeepage.jsp
    Created on : Aug 24, 2017, 6:31:57 AM
    Author     : kasper
--%>

<%@page import="FunctionLayer.Orderline"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Date"%>
<%@page import="FunctionLayer.partslist.Partslist"%>
<%@page import="FunctionLayer.Order"%>
<%@page import="FunctionLayer.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="https://bootswatch.com/4/flatly/bootstrap.min.css"
              rel="stylesheet" type="text/css">
        <link rel="stylesheet" type="text/css" href="css/NavBar.css">

    </head>
    <body>

        <ul>
            <li><a href="customerloginpage.jsp"><h2>Startside</h2></a></li>
            <li><a href="orderhistory.jsp"><h2>Ordre</h2></a></li>
            <li><a href="#contact"><h2>Om</h2></a></li>
            <li style="float:right"><a class="active" href="index.jsp"><h2>Log ud</h2></a></li>
        </ul>
    <legend>
        <form name="addProduct" action="FrontController" method="POST">
            <input type="hidden" name="command" value="addProduct">
            <h2>Tilføj et produkt</h2>
            <table style="width:40%"> 
                <tr>
                    <td>Part Number: </td>
                    <td><input type="text" name="partNumber"></td>
                </tr>
                <tr>
                    <td>Navn:</td>
                    <td><input type="text" name="pName"></td>
                </tr>
                <tr>
                    <td>Pris:</td>
                    <td><input type="text" name="pPrice"><br></td>
                </tr>
                <tr>
                    <td>Længde:</td>
                    <td><input type="text" name="pLength"></td>
                </tr>
                <tr>
                    <td>Højde:</td>
                    <td><input type="text" name="pHeight"></td>
                </tr>
                <tr>
                    <td>Bredde:</td>
                    <td><input type="text" name="pWidth"></td>
                </tr>
            </table>
            <h4><button style="height:30px;width:100px" type="submit" class="btn btn-primary">Submit</button></h4>
        </form>
</legend>

    </body>


    <%--

        


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

</html>
