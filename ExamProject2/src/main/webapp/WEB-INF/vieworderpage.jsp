<%-- 
    Document   : CustomerOrder
    Created on : 15-11-2018, 09:32:45
    Author     : Esben
--%>

<%@page import="partslist.*"%>
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
        <title>Stykliste oversigt</title>
    </head>
    <body>


        <br>
        <br>
    <legend>
        <h2>Træ:</h2>
        <table style="width:66%">
            <tr>
                <th>Type</th>
                <th>Bredde</th> 
                <th>Højde</th> 
                <th>Længde</th> 
                <th>Antal</th> 
                <th>Beskrivelse</th> 
            </tr>
            <% Partslist pl = (Partslist) request.getAttribute("pl"); %>
            <% for (Wood w : pl.getWoodList()) { %>
            <tr>
                <td><% out.print(w.getName()); %></td>
                <td><% out.print(w.getWidth() * 10 + "mm"); %></td>
                <td><% out.print(w.getHeight() * 10 + "mm");%></td>
                <td><% out.print(w.getLength() + "cm");%></td>
                <td><% out.print(w.getQty());%></td>
                <td><% out.print(w.getDescription());%></td>
            </tr>


            <% }%>


        </table>
    </legend>
    <br>
    <legend>
        <h2>Øvrige matieraler:</h2>
        <table style="width:66%">
            <tr>
                <th>Type</th>
                <th>Antal</th> 
                <th>Beskrivelse</th> 
            </tr>
            <% for (Material m : pl.getMatList()) { %>
            <tr>
                <td><% out.print(m.getName()); %></td>
                <td><% out.print(m.getQty());%></td>
                <td><% out.print(m.getDescription());%></td>
            </tr>


            <% }%>


        </table>
    </legend>
    <br>
    <br>
    <legend> <h2> Plantegning: </h2>
        <br>
        <br>
        <% String carportHTML = (String) request.getAttribute("carportHTML");%>
        <% out.println(carportHTML);%>
    </legend>
</body>
</html>
