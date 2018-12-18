<%-- 
    Document   : about
    Created on : 05-12-2018, 13:10:59
    Author     : caspe
--%>

<%@page import="FunctionLayer.DTO.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Om</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="https://bootswatch.com/4/flatly/bootstrap.min.css"
              rel="stylesheet" type="text/css">
        <link rel="stylesheet" type="text/css" href="css/NavBar.css">
    </head>

    <ul>
        <% User u = (User) request.getSession().getAttribute("user");
            if (u.getRole().equals("employee")) {
        %>
        <a href="employeepage.jsp" class="navbar-left"><img src="images/foglogo.png" height="85"></a>
        <li><a href="employeepage.jsp"><h2>Startside</h2></a></li>
            <%  } else {%>
        <a href="customerhomepage.jsp" class="navbar-left"><img src="images/foglogo.png" height="85"></a>
        <li><a href="customerhomepage.jsp"><h2>Startside</h2></a></li>
            <% }%>
        <li><a href="orderhistory.jsp"><h2>Ordre</h2></a></li>
        <li><a href="about.jsp"><h2>Om</h2></a></li>
        <li style="float:right"><a class="active" href="index.jsp"><h2>Log ud</h2></a></li>
    </ul>

    <body>
        <div class="jumbotron text-center">
            <h1> Kontakt os: </h1>
            <br>
            <br>
            <h3><b>Johannes Fog A/S</b><br>
                <br>
                Firsskovvej 20, 2800 Lyngby<br>

                <br>
                Tlf.: 45 87 10 01<br>
                <br>
                CVR.: 16314439<br></h3>

        </div>

    </body>
</html>
