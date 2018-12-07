<%-- 
    Document   : receipt
    Created on : 05-12-2018, 10:01:29
    Author     : caspe
--%>

<%@page import="FunctionLayer.Orderline"%>
<%@page import="FunctionLayer.Order"%>
<%@page import="FunctionLayer.partslist.Carport"%>
<%@page import="java.util.ArrayList"%>
<%@page import="FunctionLayer.LogicFacade"%>
<%@page import="FunctionLayer.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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

        <% User user = (User) request.getSession().getAttribute("user");
            
            Order o = (Order) request.getSession().getAttribute("currentOrder");
            ArrayList<Orderline> aol = o.getAol();
            LogicFacade lf = new LogicFacade();
            
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
                    Firma:<br>
                    Navn: <b><%out.print(user.getEmail());%></b><br>
                    Adresse:<br>
                    By og postnummer:<br>
                    Kundenummer: <b><% out.print(user.getId());%></b></p>
                <p style="float: right;">
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

                    <tr>
                        <th scope="col">Carport 
                            <%
                                if (o.getCp().isHasShed() && (o.getCp().getcSlope() > 0)) {
                                    out.print("med skur og rejsning på tag");
                                }
                                
                                if (o.getCp().isHasShed() == false && (o.getCp().getcSlope() < 0)) {
                                    out.print("uden skur og uden rejsning på tag");
                                }
                                if (o.getCp().isHasShed() && (o.getCp().getcSlope() < 0)) {
                                    out.print("med skur og uden rejsning på tag");
                                }
                                if (o.getCp().isHasShed() == false && (o.getCp().getcSlope() > 0)) {
                                    out.print("uden skur, med rejsning på tag");
                                }

                            %></th>
                        <th scope="col"><% if (o.getdDate() != null) {
                                out.print(o.getdDate());
                            } else if (o.getdDate() == null) {
                                out.print("Ikke afsendt");
                            }
                            

                            %>
                        </th>

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
                <p style="float: right;">
                    <%
                        double udenMoms = (o.gettPrice() / (1 + 0.20));
                        String udenMomsAsString = String.format("%.2f", udenMoms);
                    %>

                    <br>Netto: <% out.print(udenMomsAsString + " " + "kr");%></br>

                    <%
                        double moms = (o.gettPrice() * 0.25);
                        String momsAsString = String.format("%.2f", moms);

                    %>

                    <br>Moms (25%): <% out.print(momsAsString + " " + "kr"); %><br>

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
