<%-- 
    Document   : employeepage.jsp
    Created on : Aug 24, 2017, 6:31:57 AM
    Author     : kasper
--%>

<%@page import="FunctionLayer.DTO.User"%>
<%@page import="FunctionLayer.partslist.Wood"%>
<%@page import="FunctionLayer.partslist.Material"%>
<%@page import="FunctionLayer.DTO.Orderline"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Date"%>
<%@page import="FunctionLayer.partslist.Partslist"%>
<%@page import="FunctionLayer.DTO.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!-- This is the page that will appear to the employee when logged in.
    The employee will have the possibilities to edit add/remove stuff to the stock. 
    Also edit a speficic order made by customer and get to see every single order made.-->

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin side</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="https://bootswatch.com/4/flatly/bootstrap.min.css"
              rel="stylesheet" type="text/css">
        <link rel="stylesheet" type="text/css" href="css/NavBar.css">

    </head>
    <body>

        <!-- The navigation bar, directing to different pages, based on the users choice. 
            In this case its getting in the first 'if' and gives the choices for employee -->

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
        <div class="jumbotron text-center">

            <h1> Admin side </h1>
        </div>

        <%  ArrayList<Wood> woodList = (ArrayList<Wood>) request.getSession().getAttribute("woodList");
            ArrayList<Material> matList = (ArrayList<Material>) request.getSession().getAttribute("matList");

        %>

    <legend>

        <!-- This is the overview of the stock, here the employee can add items the existing stock.
            On the page, there is a field that takes an integer, and then you can choose to remove or add to the existing stock of that particular item
            There is first a printout and remove/add buttons for wood and then material. On the page you wont notice any difference,
            but because its two different object and tables in the database, we have to do it with two different forms.-->

        <table style="width:100%" class="table table-hover"> 

            <tr>
                <th >Produkt ID</th>
                <th>Navn</th>
                <th>Pris</th>
                <th>Længde</th>
                <th>Bredde</th>
                <th>Højde</th>
                <th>Part number</th>
                <th>Lagerbeholdning</th>
                <th>Tilføj til lager</th>
                <th>Skift partnumber</th>
                <th>Fjern produkt</th>
            </tr>
            <% for (Wood w : woodList) { %>
            <tr>
                <td><% out.print(w.getId()); %></td>
                <td><% out.print(w.getName()); %></td>
                <td><% out.print(w.getPrice() + " kr"); %></td>
                <td><% out.print(w.getLength() + "cm"); %></td>
                <td><% out.print(w.getWidth() + "mm"); %></td>
                <td><% out.print(w.getHeight() + "mm"); %></td>
                <td><% out.print(w.getPartNumber()); %></td>
                <td><% out.print(w.getStock()); %></td>
                <td>
                    <form name="addProduct" action="FrontController" method="POST">
                        <div class="row">
                            <input type="hidden" name="command" value="addProduct">
                            <input type="text" name="qty" size="4">
                            <input type="hidden" name="addStock" value="<% out.print(w.getId()); %>">
                            <h5><button style="height:30px;width:100px" type="submit" class="btn btn-primary">Tilføj</button></h5>
                        </div>
                    </form>
                </td>
                <td>
                    <form name="changePartNumber" action="FrontController" method="POST">
                        <div class="row">
                            <input type="hidden" name="command" value="addProduct">
                            <input type="text" name="newPartNumber" size="4">
                            <input type="hidden" name="changePartNumber" value="<% out.print(w.getId()); %>">
                            <h5><button style="height:30px;width:100px" type="submit" class="btn btn-primary">Skift</button></h5>
                        </div>
                    </form>
                </td>
                <td>
                    <form name="addProduct" action="FrontController" method="POST">
                        <input type="hidden" name="command" value="addProduct">
                        <input type="hidden" name="remove" value="<% out.print(w.getId()); %>">
                        <h5><button style="height:30px;width:100px" type="submit" class="btn btn-primary">Fjern</button></h5>
                    </form>
                </td>
            </tr>
            <% } %>
            <% for (Material m : matList) { %>
            <tr>
                <td><% out.print(m.getId()); %></td>
                <td><% out.print(m.getName()); %></td>
                <td><% out.print(m.getPrice() + " kr"); %></td>
                <td><% out.print("N/A"); %></td>
                <td><% out.print("N/A"); %></td>
                <td><% out.print("N/A"); %></td>
                <td><% out.print(m.getPartNumber()); %></td>
                <td><% out.print(m.getStock()); %></td>
                <td>

                    <form name="addProduct" action="FrontController" method="POST">
                        <div class="row">
                            <input type="hidden" name="command" value="addProduct">
                            <input type="text" name="qty" size="4">
                            <input type="hidden" name="addStock" value="<% out.print(m.getId()); %>">
                            <h5><button style="height:30px;width:100px" type="submit" class="btn btn-primary">Tilføj</button></h5>
                        </div>
                    </form>

                </td>
                <td>

                    <form name="changePartNumber" action="FrontController" method="POST">
                        <div class="row">
                            <input type="hidden" name="command" value="addProduct">
                            <input type="text" name="newPartNumber" size="4">
                            <input type="hidden" name="changePartNumber" value="<% out.print(m.getId()); %>">
                            <h5><button style="height:30px;width:100px" type="submit" class="btn btn-primary">Skift Partnumber</button></h5>
                        </div>
                    </form>

                </td>
                <td>
                    <form name="addProduct" action="FrontController" method="POST">
                        <div class="row">
                            <input type="hidden" name="command" value="addProduct">
                            <input type="hidden" name="remove" value="<% out.print(m.getId()); %>">
                            <h5><button style="height:30px;width:100px" type="submit" class="btn btn-primary">Fjern</button></h5>
                        </div>
                    </form>
                </td>
            </tr>
            <% }%>

        </table>

        <!-- With this, the employee can add new products that doesnt already exist to the stock -->

        <div align="justify">
            <form name="addProduct" action="FrontController" method="POST">
                <input type="hidden" name="command" value="addProduct">
                <h2>Tilføj et produkt</h2>
                <table style="width:30%"> 
                    <tr>
                        <td>Part Number: </td>
                        <td><input type="text" name="partNumber"></td>
                    </tr>
                    <tr>
                        <td>Navn:</td>
                        <td><input type="text" name="pName"></td>
                    </tr>
                    <tr>
                        <td>Pris:</td>
                        <td><input type="text" name="pPrice"><br></td>
                    </tr>
                    <tr>
                        <td>Længde:</td>
                        <td><input type="text" name="pLength"></td>
                    </tr>
                    <tr>
                        <td>Bredde:</td>
                        <td><input type="text" name="pWidth"></td>
                    </tr>
                    <tr>
                        <td>Højde:</td>
                        <td><input type="text" name="pHeight"></td>
                    </tr>
                </table>
                <h4><button style="height:30px;width:100px" type="submit" class="btn btn-primary">Submit</button></h4>
            </form>
        </div>
    </legend>

</body>


<%--

        


   Krav til carport
<table style="width:25%"> 
        <tr>
            <th>Type</th>
            <th>Mængde</th> 
        </tr>
        <tr>
            <td>Antal stolper: </td>
            <td><% out.print(numberOfLogs + " stk");%></td>
        </tr>
        <% if (request.getParameter("sroof").equals("false")) {
                double lenghtOfBand = (double) request.getAttribute("lenghtOfBand");%>
        <tr>
            <td>Længde af stålbånd: </td>
            <td><% out.print(lenghtOfBand + "m"); %></td>
        </tr>
        <% }  %>
        <tr>
            <td>Antal spær: </td>
            <td><% out.print(numberOfRafters + " stk"); %></td>
        </tr>
        <tr>
            <td>Areal af tag: </td>
            <td><% out.print(areaOfRoof + "m²"); %></td>
        </tr>

        <% if (request.getParameter("sroof").equals("true")) {
                double heightOfRoof = (double) request.getAttribute("heightOfRoof");
                double rafterLenght = (double) request.getAttribute("rafterLenght");
                double areaOfGable = (double) request.getAttribute("areaOfGable");

        %>
        <tr>
            <td>Højde af tag: </td>
            <td><% out.print(heightOfRoof + "m"); %></td>
        </tr>
        <tr>
            <td>Længde af spær: </td>
            <td><% out.print(rafterLenght + "m"); %></td>
        </tr>
        <tr>
            <td>Areal af gavl: </td>
            <td><% out.print(areaOfGable + "m²"); %></td>
        </tr>
        <%
            }
        %>
    </table>
--%>    

</html>
