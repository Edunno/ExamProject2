<%-- 
    Document   : customerorderpage
    Created on : Aug 22, 2017, 2:33:37 PM
    Author     : kasper
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!-- This JSP/page is used when the customer starts a new order on a carport 
    The customer chooses the measurements, slope and/or shed.
    There is also choices in the navigation bar if the customer wants to do something else-->


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Design carport</title>
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

    <body>
        <div class="jumbotron text-center">
            <h1>Her kan du selv designe din carport</h1>
        </div>
    </body>
    <center>
        <h2> Byg en carport </h2>
        <br>
        <br>

        <!-- This is the calculate function, where it is directed through one of the commands.
            The calculate command also checks if its valid inputs, for example the length or width can't be 0 or less.-->

        <form name="login" action="FrontController" method="POST">
            <legend>
                <input type="hidden" name="command" value="calculate"> 

                <!-- Here is the field where the users input are required on either length or width -->

                <h3> Indsæt længde: </h3>
                <input type="number" step="0.01" name="length" value="7.8">
                <br>
                <br>
                <h3> Indsæt bredde: </h3>
                <input type="number" step="0.01"  name="width" value="6">
                <br>
                <br>

                <!-- This is based on the users choice of flat or special roof, based on yes/no.
                    The user can choose yes/no by clicking a button (radio) next the one of the choices.-->

                <h2> Ønskes tag med rejsning? </h2><br>

                <script type="text/javascript">

                    <!-- The yes/no check, and decides that the required information based on uers wish for special roof are by default hidden.
                 The angle of slope field will appear by clicking the yes button / radio -->
                            function yesnoCheck() {
                                if (document.getElementById('yesCheck').checked) {
                                    document.getElementById('ifYes').style.display = 'block';
                                } else
                                    document.getElementById('ifYes').style.display = 'none';
                            }

                </script>

                <!-- These are the yes/no choices that sends the value of the choice to the yes/no check -->

                <input type="radio" onclick="javascript:yesnoCheck();" name="sroof" value="true" id="yesCheck"> Ja
                <input type="radio" onclick="javascript:yesnoCheck();" name="sroof" value="false" id="noCheck" checked> Nej<br>
                <div id="ifYes" style="display:none">
                    Antal grader på hældning af tag: 

                    <input type="number" name="slope" value="30"><br>

                </div>
                <br>
                <br>

                <!-- The customer is asked about adding a shed to the carport, again made with yes/no buttons/radios -->

                <h2> Ønskes skur? </h2><br>
                <input type="radio" name="shed" value="true"> Ja
                <input type="radio" name="shed" value="false" checked> Nej
                <br>
                <br>
                <button style="height:45px;width:125px" type="submit" class="btn btn-primary"><h3>Beregn</h3></button>
                <br>
                <br>
                <br>
                <br>
                <br>
            </legend>
        </form>
    </center>
    <!other 3<input type='text' id='other3' name='other3'><br>
    <!other 4<input type='text' id='other4' name='other4'><br>
</html>

