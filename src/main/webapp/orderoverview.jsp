<%-- 
    Document   : orderoverview
    Created on : 15-11-2018, 09:32:45
    Author     : Esben
--%>

<%@page import="FunctionLayer.DTO.User"%>
<%@page import="FunctionLayer.partslist.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!-- This is the overview that will appear after the customer has submitted a new order with customers given measurements and is the final step to place the order -->

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

    <!-- The navigation bar, directing to different pages, based on the users choice -->
    <!-- The navigation bar is also edited, if its an employee that is logged in -->
    
 <ul>
        <% User u = (User) request.getSession().getAttribute("user");
            if (u.getRole().equals("employee")) {
        %>
        <a href="employeepage.jsp" class="navbar-left"><img src="images/foglogo.png" height="85"></a>
        <li><a href="employeepage.jsp"><h2>Startside</h2></a></li>
            <%  } else {%>
        <a href="customerhomepage.jsp" class="navbar-left"><img src="images/foglogo.png" height="85"></a>
        <li><a href="customerhomepage.jsp"><h2>Startside</h2></a></li>
            <% } %>
        <li><a href="orderhistory.jsp"><h2>Ordre</h2></a></li>
        <li><a href="about.jsp"><h2>Om</h2></a></li>
        <li style="float:right"><a class="active" href="index.jsp"><h2>Log ud</h2></a></li>
    </ul>

    <body>
        <!-- Partlist that is used all the way down, in both "Træ" and "Øvrige materialer", as well in total price -->
    <legend>

        <% Partslist pl = (Partslist) request.getAttribute("pl"); %>


        <div class="jumbotron">

            <center>                                                                        
                <h1 style=font-size:100px class="display-3">Ordre oversigt</h1>
                <hr class="my-4">
                
                <!-- This is the calculation of the totalprice, that will be shown very clearly to the customer -->
                
                <h1 style=font-size:50px> Prisen på ønsket carport: <% out.print(pl.getTotalPrice());%> kr </h1>
                <br>
                <br>
                
                    <!-- This is the button that confirms the order and will send it to the database, and the customer can then see it in the order overview (history/historik) -->
                <div class="row align center-div">                  
                    <form name="order" action="FrontController" method="POST">
                        <input type="hidden" name="command" value="order">
                        <button style="height:50px;width:100px" type="submit" class="btn btn-primary"><h2>Bestil</h2></button>
                    </form>
                    
                    <!-- This is the 'back' button that returns the user to the previous page, in case the user wants to make changes on the order. -->
                    
                    <button style="height:50px;width:100px" type="button" onclick="history.back()" class="btn btn-primary"><h2>Tilbage</h2></button>
                </div>
            </center>
        </div>

        <br>
        <br>
        <br>
        <br>
        <br>
    </legend>
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
                    
                    <!-- This is the print out for all the different kind of wood needed for building the carport, based on the customers choices -->
                    
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
                    
                <!-- This is the print out for all the materials needed for building the carport, based on the customers choices -->
                    
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
    </legend>
    <br>
    <br>
    <br>
    
    <!-- This is three floor plans of the customers chosen carport -->
    
    <div class="jumbotron">
        <h1> Plantegning: </h1>

            <!-- This is the first floor plan that will appear on the order overview
                This floor plan is drawn from the top-->
        
        <table>
            <% String topCarportHTML = (String) request.getAttribute("topCarportHTML");%>
            <% out.println(topCarportHTML);%>


        </table>
            
            <!-- This is the second floor plan that will appear on the order overview
                This floor plan is from from the side -->
            
        <table>
            <% String sideCarportHTML = (String) request.getAttribute("sideCarportHTML");%>
            <% out.println(sideCarportHTML);%>
        </table>
        
            <!-- This is the third floor plan that will appear on the order overview
                This floor plan is from the front -->
        
        <table>
            <% String frontCarportHTML = (String) request.getAttribute("frontCarportHTML");%>
            <% out.println(frontCarportHTML);%>
        </table>
    </div>

</body>
</html>
