<%-- 
    Document   : receipt
    Created on : 05-12-2018, 10:01:29
    Author     : caspe
--%>

<%@page import="FunctionLayer.DTO.Orderline"%>
<%@page import="FunctionLayer.DTO.Order"%>
<%@page import="FunctionLayer.partslist.Carport"%>
<%@page import="java.util.ArrayList"%>
<%@page import="FunctionLayer.LogicFacade"%>
<%@page import="FunctionLayer.DTO.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!-- This is the receipt of a chosen order, this will show information about the customer, the company and what has been ordered with a total price -->

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ordrevisning</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="https://bootswatch.com/4/flatly/bootstrap.min.css"
              rel="stylesheet" type="text/css">
        <link rel="stylesheet" type="text/css" href="css/Placing.css">
        
        
        <!-- The user is pulled from the session so that we can print out the information on the receipt. -->
        
        <!-- The order is also pulled from the session, so that we get information from the specific order that has been chosen, as price, carport type etc. -->

        <% User user = (User) request.getSession().getAttribute("user");

            Order o = (Order) request.getSession().getAttribute("currentOrder");

        %>



    </head>
    <legend>
        <div class="jumbotron text-center">
            <div class="topleft">
                <img src="images/foglogo.png" height="150">
            </div>
            <text><h1> Faktura </h1></text>
        </div>


        <br>
        <br>
        <br>
        <br>
        
        
        
        <div class="sidebar">
            <h1>
                <p style="float: left;">   
                    
                    <!-- Customer information, pulled from the current user -->
                    
                    Firma:<br>
                    Navn: <b><%out.print(user.getEmail());%></b><br>
                    Adresse:<br>
                    By og postnummer:<br>
                    Kundenummer: <b><% out.print(user.getId());%></b></p>
                
                <p style="float: right;">
                    
                   <!-- Company information -->
                    
                    <b>Johannes Fog A/S</b><br>
                    Firsskovvej 20<br>
                    2800 Lyngby<br>
                    Tlf.: 45 87 10 01<br>
                    CVR.: 16314439<br>

                    Bankoplysninger<br>

                </p>
            </h1>
        </div>
        <br>
        <br>
        <br>
        <br>
        <h2>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th scope="col">Vare</th>
                        <th scope="col">Afsendt</th>
                        <th scope="col">Pris</th>
                    </tr>
                        
                    <!-- This code is checking on what kind of choices that has been made on the order and then printed with detailed information -->
                    
                    <tr>
                        <th scope="col">Carport 
                            <%
                                if (o.getCp().isHasShed() && (o.getCp().getcSlope() > 0)) {
                                    out.print("med skur og rejsning på tag");
                                }

                                if (!o.getCp().isHasShed() && (o.getCp().getcSlope() < 0)) {
                                    out.print("uden skur og uden rejsning på tag");
                                }
                                if (o.getCp().isHasShed() && (o.getCp().getcSlope() < 0)) {
                                    out.print("med skur og uden rejsning på tag");
                                }
                                if (!o.getCp().isHasShed() && (o.getCp().getcSlope() > 0)) {
                                    out.print("uden skur, med rejsning på tag");
                                }

                            %></th>
                        
                        <!-- A check that sees if there is a valid date on the shipment. If there isnt, it is because its not shipped yet. -->
                        
                        <th scope="col"><% if (o.getdDate() != null) {
                                out.print(o.getdDate());
                            } else if (o.getdDate() == null) {
                                out.print("Ikke afsendt");
                            }


                            %>
                        </th>
                        
                        <!-- The price of the carport -->

                        <th scope="col"><% out.print(o.gettPrice());%> kr</th>
                    </tr>
                </thead>
            </table>
        </h2>      

        <table class="table table-hover">
            <tr>

            </tr>

        </table>

        <br>
        <br>
        <br>
        <br>
        <br>

        <div class="sidebar">
            <h2>
                
                <!-- The price without taxes. Its made as string because we only want two decimals -->
                
                <p style="float: right;">
                    <%
                        double udenMoms = (o.gettPrice() / (1 + 0.20));
                        String udenMomsAsString = String.format("%.2f", udenMoms);
                    %>

                    <br>Netto: <% out.print(udenMomsAsString + " " + "kr");%></br>
                    
                <!-- The amount that is tax -->

                    <%
                        double moms = (o.gettPrice() * 0.25);
                        String momsAsString = String.format("%.2f", moms);

                    %>

                    <br>Moms (25%): <% out.print(momsAsString + " " + "kr"); %><br>
                    
                    <!-- The total price with tax included -->

                    <%
                        double tPrice = (o.gettPrice());
                        String tPriceAsString = String.format("%.2f", tPrice);

                    %>

                    <br><u><b>Total: <% out.print(tPriceAsString + " " + "kr");%> </b></u><br>

                </p>
            </h2>
        </div>

    </legend>


</body>
</html>
