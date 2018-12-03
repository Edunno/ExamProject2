<%-- 
    Document   : index
    Created on : Aug 22, 2017, 2:01:06 PM
    Author     : kasper
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="https://bootswatch.com/4/flatly/bootstrap.min.css"
              rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
    <center>
        <body>
            <div class="jumbotron text-center">
                <h1>Velkommen til byg selv</h1>
            </div>
        </body>

        <table>
            <tr> <h2> Login </h2><br>
            <td>
                <form name="login" action="FrontController" method="POST">
                    <input type="hidden" name="command" value="login">
                    <h3>Email:<br></h3>
                    <legend><input type="text" name="email" value="Dan">
                        <br>
                        <h3>Password:<br></h3>
                        <input type="password" name="password" value="123">
                        <br>
                        <center>
                            <br>
                            <h4><button style="height:30px;width:100px" type="submit" class="btn btn-primary">Submit</button></h4>
                            <br>
                        </center>
                    </legend>
                </form>
            </td>
            </tr>
        </table>
        <table>
            <tr>
            <h2>Opret bruger</h2><br>
            <td>
                <form name="register" action="FrontController" method="POST">
                    <input type="hidden" name="command" value="register">
                    <h3>Email:<br></h3>
                    <legend><input type="text" name="email" value="someone@nowhere.com">
                        <br>
                        <h3>Password:<br></h3>
                        <input type="password" name="password1" value="sesam">
                        <br>
                        <h3>Retype Password:<br></h3>
                        <input type="password" name="password2" value="sesam">
                        <br>
                        <center>
                            <br>
                            <h4><button style="height:30px;width:100px" type="submit" class="btn btn-primary">Submit</button></h4>
                            <br>
                        </center>
                    </legend>
                </form>
            </td>
            </tr>
        </table>
        <% String error = (String) request.getAttribute("error");
            if (error != null) {
                out.println("<H2>Error!!</h2>");
                out.println(error);
            }
        %>
    </body>
</center>
</html>
