<%-- 
    Document   : CustomerOrder
    Created on : 15-11-2018, 09:32:45
    Author     : Esben
--%>

<%@page import="FunctionLayer.partslist.*"%>
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
        <link rel="stylesheet" type="text/css" href="css/NavBar.css">

    </head>

    <ul>
        <a href="customerloginpage.jsp" class="navbar-left"><img src="images/foglogo.png" height="85"></a>
        <li><a href="customerloginpage.jsp"><h2>Startside</h2></a></li>
        <li><a href="orderhistory.jsp"><h2>Ordre</h2></a></li>
        <li><a href="#contact"><h2>Om</h2></a></li>
        <li style="float:right"><a class="active" href="index.jsp"><h2>Log ud</h2></a></li>
    </ul>

    <body>
    <legend>
        <! Partlist that is used all the way down, in both "Træ" and "Øvrige materialer", as well in total price

        <% Partslist pl = (Partslist) request.getAttribute("pl"); %>


        <div class="jumbotron">

            <center>                                                                        
                <h1 style=font-size:100px class="display-3">Ordre oversigt</h1>
                <hr class="my-4">
                <h1 style=font-size:50px> Prisen på ønsket carport: <% out.print(pl.getTotalPrice());%> kr </h1>
                <br>
                <br>
                <form name="order" action="FrontController" method="POST">
                    <input type="hidden" name="command" value="order">
                    <button style="height:50px;width:100px" type="submit" class="btn btn-primary"><h2>Bestil</h2></button>
                </form>
            </center>
        </div>

        <br>
        <br>
        <br>
        <br>
        <br>
    </legend>
    <legend>
        <div class="center-div">
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
            </div>
        </div>
    </legend>
    <br>
    <br>
    <br>
    <div class="jumbotron">
        <h1> Plantegning: </h1>

        <table>
            <% String topCarportHTML = (String) request.getAttribute("topCarportHTML");%>
            <% out.println(topCarportHTML);%>


        </table>
        <table>
            <% String sideCarportHTML = (String) request.getAttribute("sideCarportHTML");%>
            <% out.println(sideCarportHTML);%>
        </table>
        <table>
            <% String frontCarportHTML = (String) request.getAttribute("frontCarportHTML");%>
            <% out.println(frontCarportHTML);%>
        </table>
    </div>

</body>
</html>
