<%-- 
    Document   : customerpage
    Created on : Aug 22, 2017, 2:33:37 PM
    Author     : kasper
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer home page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="https://bootswatch.com/4/flatly/bootstrap.min.css"
              rel="stylesheet" type="text/css">

    </head>
    <body>
        <div class="jumbotron text-center">
            <h1>Hej <%=request.getParameter("email")%></h1>
            <p>Her kan du selv designe din carport</p> 
        </div>
    </body>
    <center>
    <h2> Byg en carport </h2>
    <form name="login" action="FrontController" method="POST">
        <legend>
            <input type="hidden" name="command" value="calculate"> 

            <h3> Indsæt længde: </h3>
            <input type="number" step="0.01" name="length" value="7.8">
            <h3> Indsæt bredde: </h3>
            <input type="number" step="0.01"  name="width" value="6"><br>

            <h2> Ønskes tag med rejsning? </h2><br>

            <script type="text/javascript">

                function yesnoCheck() {
                    if (document.getElementById('yesCheck').checked) {
                        document.getElementById('ifYes').style.display = 'block';
                    } else
                        document.getElementById('ifYes').style.display = 'none';
                }

            </script>
            <input type="radio" onclick="javascript:yesnoCheck();" name="sroof" value="true" id="yesCheck"> Ja
            <input type="radio" onclick="javascript:yesnoCheck();" name="sroof" value="false" id="noCheck"> Nej<br>
            <div id="ifYes" style="display:none">
                Antal grader på hældning af tag: 

                <input type="number" name="slope" value="30"><br>
                
            </div>
            <h2> Ønskes skur? </h2><br>
            <input type="radio" name="shed" value="true"> Ja
            <input type="radio" name="shed" value="false"> Nej
            <br>
            <button style="height:30px;width:100px" type="submit" class="btn btn-primary">Submit</button><br>
        </legend>
    </form>
    </center>
    <!other 3<input type='text' id='other3' name='other3'><br>
    <!other 4<input type='text' id='other4' name='other4'><br>
</html>

