<%-- 
    Document   : viewsingleorder
    Created on : 04-12-2018, 12:51:43
    Author     : Dan
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
        <% User u = (User) request.getSession().getAttribute("user"); %>
        <%
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
                    <th> <% out.print(o.getdDate()); %> </th> 
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
</html>
