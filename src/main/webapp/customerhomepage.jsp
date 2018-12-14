<%-- 
    Document   : customerhomepage
    Created on : 29-11-2018, 11:38:05
    Author     : caspe
--%>

<%@page import="FunctionLayer.DTO.User"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!-- 
This JSP/Page is where the user is directed to, after he/she is done with log in.
On this page the user have different choices, either in the navigation bar or 
the two buttons, displayed in the center of the page. -->

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Velkommen</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="https://bootswatch.com/4/flatly/bootstrap.min.css"
              rel="stylesheet" type="text/css">
        <link rel="stylesheet" type="text/css" href="css/NavBar.css">
    </head>

    <!-- The navigation bar, directing to different pages, based on the users choice -->
    
    <ul>
        <a href="customerhomepage.jsp" class="navbar-left"><img src="images/foglogo.png" height="85"></a>
        <li><a href="customerhomepage.jsp"><h2>Startside</h2></a></li>
        <li><a href="orderhistory.jsp"><h2>Ordre</h2></a></li>
        <li><a href="about.jsp"><h2>Om</h2></a></li>
        <li style="float:right"><a class="active" href="index.jsp"><h2>Log ud</h2></a></li>
    </ul>

    <!-- Taking the user from the session and greeting the user by his/hers email/name -->
    
    <body>
        <div class="jumbotron text-center">
            <%User user = (User) request.getSession().getAttribute("user");%>
            <h1>Hej <%out.print(user.getEmail());%>  </h1>
            <p>Her kan du enten bestille en carport eller se din tidligere bestillinger.</p> 
        </div>

        <br>
        <br>
        <br>
        <br>
    <center>
        
    <!-- For each of these two forms, there is a button, directing the user to either 'new order' or the second one 'see order history' -->
        
        <form name="neworder" action="FrontController" method="POST">
            <input type="hidden" name="command" value="neworder">
            <button style="height:50px;width:225px" type="submit" class="btn btn-primary"><h2>Opret ny ordre</h2></button>
        </form>
        <br>
        <br>
        <br>
        <br>
        <form name="history" action="FrontController" method="POST">
            <input type="hidden" name="command" value="history">
            <button style="height:50px;width:225px" type="submit" class="btn btn-primary"><h2>Se historik</h2></button>
        </form>
    </center>

</body>
</html>
