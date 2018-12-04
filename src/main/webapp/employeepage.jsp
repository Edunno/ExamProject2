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
        <% User u = new User("Dan", "123", "customer"); %>
        <%
            Date dDate = new Date(0);
            Order o = (Order) request.getAttribute("currentOrder");
            ArrayList<Orderline> aol = o.getAol();
        %>
        <style>
            table, th, td {
                border: 1px solid black;
                border-collapse: collapse;
            }
        </style>
    <legend>
        <div>
            <h1>Ordrevisning:</h1>
            <br>

            <table style="width:30%"> 
                <tr>
                    <th>Ordre ID:</th>
                    <th> <% out.print(o.getoID()); %> </th> 
                </tr>
                <tr>
                    <th>Bruger ID:</th>
                    <th><% out.print(o.getuID()); %> </th> 
                </tr>
                <tr>
                    <th>Medarbejder ID:</th>
                    <th> <% out.print(o.getUeID()); %> </th> 
                </tr>
                <tr>
                    <th>Totalpris:</th>
                    <th> <% out.print(o.gettPrice() + "kr"); %></th> 
                </tr>
                <tr>
                    <th>Afsendt:</th>
                    <th> <% out.print(dDate); %> </th> 
                </tr>


            </table>
            <br>
        </div>
    </legend>
    <legend>
        <div>
            <h2>Træ:</h2>
            <table class="table-hover" style="width:50%">
                <tr>
                    <th>Produkt ID</th>
                    <th>Antal</th> 
                    <th>Pris pr stk</th> 
                    <th>Pris for alle</th> 
                </tr>
                <% for (Orderline ol : aol) { %>
                <tr>
                    <td><% out.print(ol.getpID()); %></td>
                    <td><% out.print(ol.getQty()); %></td>
                    <td><% out.print(ol.getlPrice() / ol.getQty() + "kr");%></td>
                    <td><% out.print(ol.getlPrice() + "kr");%></td>
                </tr>
                <% }%>
            </table>
        </div>
    </legend>

</body>


<%--
<form name="addProduct" action="FrontController" method="POST">
    <input type="hidden" name="command" value="addProduct">

            <table style="width:25%"> 


            </table>

            Produkt ID: <input type="text" name="pID"><br>
            Navn: <input type="text" name="pName"><br>
            Pris: <input type="text" name="pPrice"><br>
            Længde: <input type="text" name="pLength"><br>
            Højde: <input type="text" name="pHeight"><br>
            Bredde:  <input type="text" name="pWidth"><br>
        </form>
        


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
