<%-- 
    Document   : CustomerOrder
    Created on : 15-11-2018, 09:32:45
    Author     : Esben
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <% int numberOfLogs = (int) request.getAttribute("numberOfLogs"); %>
        
        <h2>Number of logs: <% out.print(numberOfLogs); %> </h2>
    </body>
</html>
