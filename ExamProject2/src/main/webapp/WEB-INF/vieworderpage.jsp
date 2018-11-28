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
    <legend>
        <div class="row">
            <div class="jumbotron">
                <h2>Træ:</h2>
                <table class="table table-hover">
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
                        <td><% out.print(w.getWidth() + "mm"); %></td>
                        <td><% out.print(w.getHeight() + "mm");%></td>
                        <td><% out.print(w.getLength() + "cm");%></td>
                        <td><% out.print(w.getQty());%></td>
                        <td><% out.print(w.getDescription());%></td>
                    </tr>
                    <% }%>
                </table>
            </div>

            <div class="jumbotron">
                <table class="table table-hover">
                    <h2>Øvrige matieraler:</h2>
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
            </div>

            <div class="jumbotron">
                <h3>Pris på carport: <% out.print(pl.getTotalPrice());%> kr</h3>
                <center>
                    <h2>  <a class="btn btn-primary btn-lg" href="#" role="button">Bestil</a> </h2>
                </center><
            </div>
        </div>
    </legend>
    <br>
    <br>
    <br>
    <table>
        <h1> Plantegning: </h1>
        <% String carportHTML = (String) request.getAttribute("carportHTML");%>
        <% out.println(carportHTML);%>

    </table>
</body>
</html>
