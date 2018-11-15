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
<body>

<form>
  <input type="radio" name="gender" value="male"> Ja<br>
  <input type="radio" name="gender" value="female"> Nej<br>  
</form>

</body>
</html>

