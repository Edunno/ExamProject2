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
    </head>
    <body>
        <h1>Hello <%=request.getParameter("email")%> </h1>
        You are now logged in as a customer of our wonderful site.
    </body>
    <h2> Byg en carport </h2>

    <td><select name="length" value="Vælg længde">

            <option> Vælg længde </option>
            <option> 240cm </option>
            <option> 780cm </option>
        </select></td>

    <td><select name="width" value="Vælg bredde">

            <option> Vælg bredde </option>
            <option> 240cm </option>
            <option> 750cm </option>

        </select></td>

    <h2> Ønskes tag med rejsning? </h2>

    <script type="text/javascript">

        function yesnoCheck() {
            if (document.getElementById('yesCheck').checked) {
                document.getElementById('ifYes').style.display = 'block';
            } else
                document.getElementById('ifYes').style.display = 'none';
        }

    </script>
    <input type="radio" onclick="javascript:yesnoCheck();" name="sroof" value="true" id="yesCheck"> Ja<br> 
    <input type="radio" onclick="javascript:yesnoCheck();" name="sroof" value="false" id="noCheck"> Nej<br>
    <div id="ifYes" style="display:none">
        Antal grader på hældning af tag: <td><select name="width" value="Vælg bredde">

                <option> Vælg grader </option>
                <option> 20 </option>
                <option> 45 </option>
    </div>

    <!other 3<input type='text' id='other3' name='other3'><br>
    <!other 4<input type='text' id='other4' name='other4'><br>
</html>

