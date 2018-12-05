<%-- 
    Document   : orderhistory
    Created on : 29-11-2018, 12:43:21
    Author     : caspe
--%>
<%@page import="FunctionLayer.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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

    </head>

    <ul>
        <li><a href="customerloginpage.jsp"><h2>Startside</h2></a></li>
        <li><a href="orderhistory.jsp"><h2>Ordre</h2></a></li>
        <li><a href="#contact"><h2>Om</h2></a></li>
        <li style="float:right"><a class="active" href="index.jsp"><h2>Log ud</h2></a></li>
    </ul>

    <body>

        <div class="jumbotron text-center">
            <h1>Her kan du se igangværende og tidligere ordre</h1>
            <p></p> 
        </div>

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

                </tr>
                <%
                    ArrayList<Order> ob = (ArrayList<Order>) request.getSession().getAttribute("orderList");
                    for (Order o : ob) {%>

                <tr>
                    <td><% out.print(o.getoID()); %></td>
                    <td><% if (o.getdDate() == null) {
                            out.print("Ikke afsendt");
                        } else {
                            out.print(o.getdDate());
                        }%> </td>
                    <td><% out.print(o.gettPrice()); %></td>
                    <td>
                        <form name="vieworder" action="FrontController" method="POST">
                            <input type="hidden" name="command" value="vieworder">
                            <button style="height:25px;width:75px" type="submit" class="btn btn-primary"><h4>Se ordre</h4></button>
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
