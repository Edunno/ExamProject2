<%-- 
    Document   : employeepage.jsp
    Created on : Aug 24, 2017, 6:31:57 AM
    Author     : kasper
--%>

<%@page import="FunctionLayer.Orderline"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Date"%>
<%@page import="partslist.Partslist"%>
<%@page import="FunctionLayer.Order"%>
<%@page import="FunctionLayer.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee home page</title>
    </head>
    <body>
        <% User u = new User("Dan", "123", "customer"); %>
        <%
            Date dDate = new Date(0);

            ArrayList<Orderline> aol = new ArrayList();
            Orderline o1 = new Orderline(10, 10, 200);
            Orderline o2 = new Orderline(4, 20, 400);
            aol.add(o1);
            aol.add(o2);
            Order o = new Order(dDate, 2, 3, 12780, aol);
            o.setuID(5);
        %>
        <style>
            table, th, td {
                border: 1px solid black;
                border-collapse: collapse;
            }
        </style>

        <h1>Eksempel på ordrevisning: (dummy data)</h1>
        <table style="width:25%"> 
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


        <h2>Træ:</h2>
        <table class="table table-hover">
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
                <td><% out.print(ol.getlPrice()/ol.getQty() + "kr");%></td>
                <td><% out.print(ol.getlPrice()+ "kr");%></td>

            </tr>
            <% }%>
        </table>





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

    </body>
</html>
