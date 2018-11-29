<%-- 
    Document   : customerloginpage
    Created on : 29-11-2018, 11:38:05
    Author     : caspe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Velkommen</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="https://bootswatch.com/4/flatly/bootstrap.min.css"
              rel="stylesheet" type="text/css">

    </head>
    <body>
        <div class="jumbotron text-center">
            <h1>Hej <%=request.getParameter("email")%></h1>
            <p>Her kan du enten bestille en carport eller se din tidligere bestillinger.</p> 
        </div>

        <br>
        <br>
        <br>
        <br>
    <center>
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
