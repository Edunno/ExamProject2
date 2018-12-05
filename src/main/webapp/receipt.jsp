<%-- 
    Document   : receipt
    Created on : 05-12-2018, 10:01:29
    Author     : caspe
--%>

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
        <link rel="stylesheet" type="text/css" href="css/NavBar.css">

        <% User user = (User) request.getSession().getAttribute("user");%>

    </head>
    <div class="jumbotron text-center">
        <legend>
            <div align="left">
                <img src="images/foglogo.png" height="150">
            </div>
            <text><h1> Faktura </h1></text>
        </legend>

        <br> 
        <br>

        <div align="left">
            <div align="right">
                <text><h3>Fog Trælast</h3></text>


            </div>
            <text><h3>Firma:</h3></text>
            <text><h3>Navn: <%out.print(user.getEmail());%></h3></text>
            <text><h3>Adresse:</h3></text>
            <text><h3>By og postnummer:</h3></text>
            <br>
            <br>
            <text><h3>Kundenummer: <% out.print(user.getId());%></h3></text>

        </div>


        <text><h3></h3></text>


    </div>
</body>
</html>
