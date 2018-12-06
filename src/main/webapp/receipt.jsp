<%-- 
    Document   : receipt
    Created on : 05-12-2018, 10:01:29
    Author     : caspe
--%>

<%@page import="FunctionLayer.Orderline"%>
<%@page import="FunctionLayer.Order"%>
<%@page import="FunctionLayer.partslist.Carport"%>
<%@page import="java.util.ArrayList"%>
<%@page import="FunctionLayer.LogicFacade"%>
<%@page import="FunctionLayer.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ordrevisning</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="https://bootswatch.com/4/flatly/bootstrap.min.css"
              rel="stylesheet" type="text/css">
        <link rel="stylesheet" type="text/css" href="css/Placing.css">

        <% User user = (User) request.getSession().getAttribute("user");

            Order o = (Order) request.getSession().getAttribute("currentOrder");
           

        %>



    </head>
    <div class="jumbotron text-center">
        <legend>
            <div class="topleft">
                <img src="images/foglogo.png" height="150">
            </div>
            <text><h1> Faktura </h1></text>
    </div>


    <br>
    <br>
    <br>
    <br>

    <div class="sidebar">
        <h1>
            <p style="float: left;">            
                Firma:<br>
                Navn: <%out.print(user.getEmail());%><br>
                Adresse:<br>
                By og postnummer:<br>
                Kundenummer: <% out.print(user.getId());%> </p>
            <p style="float: right;">
                <b>Fog Trælast</b><br>
                Firmaadresse:<br>
                By og postnummer:<br>
                Tlf:<br>

                Bankoplysninger<br>

            </p>
        </h1>
    </div>

    <br>
    <br>
    <br>
    <br>
    <h2>
        <table class="table table-hover">
            <thead>
                <tr>
                    <th scope="col">Vare</th>
                    <th scope="col">Antal</th>
                    <th scope="col">Pris</th>
                    <th scope="col">Beløb</th>
                </tr>

                <tr>
                    <th scope="col"></th>
                    <th scope="col"></th>
                    <th scope="col"><% out.print(o.gettPrice());%></th>
                    <th scope="col"><% %></th>
                </tr>
            </thead>
        </table>
    </h2>


    <br>
    <br>
    <br>
    <br>
    <br>



</legend>


<text><h3></h3></text>


</body>
</html>
