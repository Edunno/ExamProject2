<%-- 
    Document   : index
    Created on : Aug 22, 2017, 2:01:06 PM
    Author     : kasper
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!-- This is the login page and the first page the customer sees.
    The customer can choose to login with an already existing user or to create a new-->

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
            
            <!-- This is the login for an already existing user.
                The form is sent to Login command, that puts it in the session. Afterwards its going through LogicFace and last its validated in the UserMapper -->
            
            <tr> <h2> Login </h2><br>
            <td>
                <form name="login" action="FrontController" method="POST">
                    <input type="hidden" name="command" value="login">
                    <h3>Brugernavn<br></h3>
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
                
            <!-- This is where a user is created.
                It goes through our Register command -> LogicFacade -> Usermapper and then the UserMapper stashes the new user. 
                The customer needs to fill out the fields email and password. Password is required to be written twice,
                so that the customer is sure its written correctly, because when its typed, its hidden with dots instead of letters, for safety.-->
                
            <h2>Opret bruger</h2><br>
            <td>
                <form name="register" action="FrontController" method="POST">
                    <input type="hidden" name="command" value="register">
                    <h3>Brugernavn:<br></h3>
                    <legend><input type="text" name="email">
                        <br>
                        <h3>Password:<br></h3>
                        <input type="password" name="password1">
                        <br>
                        <h3>Retype Password:<br></h3>
                        <input type="password" name="password2">
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
            
            <!-- The method that throws and Error if somethings not right -->
            
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
