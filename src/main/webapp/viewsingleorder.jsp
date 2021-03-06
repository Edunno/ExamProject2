<%-- 
    Document   : viewsingleorder
    Created on : 04-12-2018, 12:51:43
    Author     : Dan
--%>


<%@page import="FunctionLayer.partslist.Carport"%>
<%@page import="FunctionLayer.LogicFacade"%>
<%@page import="FunctionLayer.DTO.Orderline"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Date"%>
<%@page import="FunctionLayer.partslist.Partslist"%>
<%@page import="FunctionLayer.DTO.Order"%>
<%@page import="FunctionLayer.DTO.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!-- This is shown when the customer has chosen to see an order from the customers order history overview on the 'orderhistory.jsp' -->

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ordrevisning</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="https://bootswatch.com/4/flatly/bootstrap.min.css"
              rel="stylesheet" type="text/css">
        <link rel="stylesheet" type="text/css" href="css/NavBar.css">
        <link rel="stylesheet" type="text/css" href="css/Placing.css">

    </head>

    <body>
        <style>
            table, th, td {
                border: 1px solid black;
                border-collapse: collapse;
            }
        </style>
        
            <!-- The navigation bar, directing to different pages, based on the users choice -->
            <!-- The navigation bar is also edited, if its an employee that is logged in -->

        <ul>
            <% User u = (User) request.getSession().getAttribute("user");
                if (u.getRole().equals("employee")) {
            %>
            <a href="employeepage.jsp" class="navbar-left"><img src="images/foglogo.png" height="85"></a>
            <li><a href="employeepage.jsp"><h2>Startside</h2></a></li>
                <%  } else {%>
            <a href="customerhomepage.jsp" class="navbar-left"><img src="images/foglogo.png" height="85"></a>
            <li><a href="customerhomepage.jsp"><h2>Startside</h2></a></li>
                <% } %>
            <li><a href="orderhistory.jsp"><h2>Ordre</h2></a></li>
            <li><a href="about.jsp"><h2>Om</h2></a></li>
            <li style="float:right"><a class="active" href="index.jsp"><h2>Log ud</h2></a></li>
        </ul>
            
        <!-- This gets the order information about the order that has been chosen, so that information can be shown -->    
            
        <%
            Order o = (Order) request.getSession().getAttribute("currentOrder");
            ArrayList<Orderline> aol = o.getAol();
        %>
        <% LogicFacade lf = new LogicFacade();    %>
        
    <legend>

        <div class="jumbotron text-center">
            <h1>Ordrevisning:</h1>
        </div>
        <div class="jumbotron">
            <table class="table table-hover" style="width:50%">

                <br>
                
                <!-- Prints the basic information about the order as 
                    order number (oID), 
                    customer number (UserID), 
                    Employee number (UeID),
                    Total price (tPrice),
                    And if its shipped or not. If its shipped, the shipping date will appear.-->
                
                <tr>
                    <td><b>Ordre ID:</b></td>
                    <td> <% out.print(o.getoID()); %> </td> 
                </tr>
                <tr>
                    <td><b>Bruger ID:</b></td>
                    <td><% out.print(o.getuID()); %> </td> 
                </tr>
                <tr>
                    <td><b>Medarbejder ID:</b></td>
                    <td> <% out.print(o.getUeID()); %> </td> 
                </tr>
                <tr>
                    <td><b>Totalpris:</b></td>
                    <td> <% out.print(o.gettPrice() + "kr"); %></td> 
                </tr>
                <tr>
                    <td><b>Afsendt:</b></td>
                    <td> <% if (o.getdDate() == null) {
                            out.print("Ikke afsendt");
                        } else {
                            out.print(o.getdDate());
                        }

                        %> 
                    </td> 
                </tr>


            </table>
            <br>
            <br>

            <table>
                <td>
                    
                    <!-- This button will will direct the customer to the receipt overview -->
                    
                    <form name="receipt" action="FrontController" method="POST">
                        <input type="hidden" name="command" value="receipt">
                        <button style="height:50px;width:225px" type="submit" class="btn btn-primary"><h2>Se faktura</h2></button>  
                    </form>
                </td>
                <%if (u.getRole().equals("employee") && !o.getCp().isHasShed() && o.getdDate() == null) { %> 
                <td>

                    <!-- This button is only for the employees and will be hidden for customers.
                    This adds a shed to the order, if the customer regrets ordering the carport without it -->

                    <form name="calculate" action="FrontController" method="POST">
                        <input type="hidden" name="command" value="calculate">
                        <input type="hidden" name="addShed" value="yes">
                        <input type="hidden" name="length" value="<% out.print(o.getCp().getcLength()); %>">
                        <input type="hidden" name="width" value="<% out.print(o.getCp().getcWidth()); %>">
                        <input type="hidden" name="sroof" value="<% out.print(o.getCp().getcSlope()); %>">
                        <input type="hidden" name="oid" value="<% out.print(o.getoID()); %>">
                        <input type="hidden" name="uid" value="<% out.print(o.getuID()); %>">
                        <input type="hidden" name="shed" value="true">

                        <button style="height:50px;width:225px" type="submit" class="btn btn-primary"><h2>Tilføj skur</h2></button>
                    </form>
                   

                </td>
                 <%} %>
                 <%if (u.getRole().equals("employee") && o.getdDate() == null) { %> 
                <td>
                    
                    <!-- This button is also only for employees and if pressed will set the order as shipped. This means that the current date will be set to the order as the shipping date-->
                    
                    <form name="receipt" action="FrontController" method="POST">
                        <input type="hidden" name="command" value="shiporder">
                        <input type="hidden" name="oid" value="<% out.print(o.getoID()); %>">
                        <button style="height:50px;width:225px" type="submit" class="btn btn-primary"><h2>Afsend</h2></button>  
                    </form>

                    
                </td>
                <%} %>
            </table>
            <br>
            <br>
            <h2>Materialer:</h2>
            <br>
            <table class="table table-hover" style="width:50%">
                <tr>
                    <th>Produkt ID</th>
                    <th>Produktnavn</th>
                    <th>Antal</th> 
                    <th>Pris pr stk</th> 
                    <th>Pris for alle</th> 
                </tr>
                
                <!-- This is the detailed information about the order, based on the wood and materials.-->
                
                <% for (Orderline ol : aol) { %>
                <tr>
                    <td><% out.print(ol.getpID()); %></td>
                    <td><% out.print(lf.getProductName(ol.getpID()));%></td>
                    <td><% out.print(ol.getQty()); %></td>
                    <td><% out.print(ol.getlPrice() / ol.getQty() + "kr");%></td>
                    <td><% out.print(ol.getlPrice() + "kr");%></td>
                </tr>
                <% }%>
            </table>

    </legend>


</body>
</html>
