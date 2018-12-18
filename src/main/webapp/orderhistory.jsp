%<-- 
    Document   : orderhistory
    Created on : 29-11-2018, 12:43:21
    Author     : caspe
--%>
<%@page import="FunctionLayer.DTO.Order"%>
<%@page import="FunctionLayer.DTO.User"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!-- This is where the customer gets the full overview on their orders. Both current and finished orders.
    The customer will have the choice to click on an order and get to a new page with a full overview of the chosen order.-->
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ordre historik</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="https://bootswatch.com/4/flatly/bootstrap.min.css"
              rel="stylesheet" type="text/css">
        <link rel="stylesheet" type="text/css" href="css/NavBar.css">
        <link rel="stylesheet" type="text/css" href="css/Placing.css">

    </head>

    <!-- This gets the session of the customer, so we only have that specific customers orders
        It also checks if its an employee that is logged in, because then there will appear every order made,
        but the first view is only orders not shipped. A button needs to be clicked to also see shipped orders.-->
    
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

    <body>

        <div class="jumbotron text-center">
            <h1>Her kan du se igangværende og tidligere ordre</h1>
            <p></p> 
        </div>
        
        <!-- This button is only shown for employees, and if clicked is showing all orders (not only non-shipped that is only shown by default) -->
        
        <% if(u.getRole().equals("employee")){ %>
        <form name="history" action="FrontController" method="POST">
            <input type="hidden" name="command" value="history">
            <input type="hidden" name="allOrders" value="yes">
            <button style="height:25px;width:75px" type="submit" class="btn btn-primary"><h4>Alle ordre</h4></button>
        </form>
        
        <!-- This is the button that will refresh to the default showing on only non-shipped -->
        
        <form name="history" action="FrontController" method="POST">
            <input type="hidden" name="command" value="history">
            <input type="hidden" name="allOrders" value="no">
            <button style="height:25px;width:75px" type="submit" class="btn btn-primary"><h4>Ikke afsendte</h4></button>
        </form>
        <% }%>
        <br>
        <br>
        <br>
    <legend>
        <div class="jumbotron">

            <table class="table table-hover">
                <tr>
                    <th>Ordre id</th>
                    <th>Afsendelses dato</th>
                    <th>Pris</th>
                    <th>Vis ordre</th>
                <!-- ob is a list pulled on the user that is all the current users orders. Both active and finished -->
                    
                </tr>
                <%
                    ArrayList<Order> ob = (ArrayList<Order>) request.getSession().getAttribute("orderList");
                    for (Order o : ob) {%>

                 
               <!-- Then we print it so that its visible for the customer.
                    The 'if' is checking if a valid date is available. Its ony available if the order has been sent.--> 
                <tr>
                    <td><% out.print(o.getoID()); %></td>
                    <td><% if (o.getdDate() == null) {
                            out.print("Ikke afsendt");
                        } else {
                            out.print(o.getdDate());
                        }%> </td>
                    <td><% out.print(o.gettPrice()); %></td>
                    <td>
                        
                        <!-- This button will appear on every single order shown.
                            If clicked, it will show the chosen order in a full overview-->
                        
                        <form name="vieworder" action="FrontController" method="POST">
                            <input type="hidden" name="command" value="vieworder">
                            <button style="height:25px;width:75px" type="submit" class="btn btn-primary"><h4>Vis</h4></button>
                            <input type="hidden" name="oid" value="<% out.print(o.getoID());%>">
                        </form>
                    </td>
                </tr>

                <% }%>
            </table>
        </div>
    </legend>
</body>
</html>
