<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Falha na operação</title>
    </head>
    <body>
        <%
           String msg = (String) request.getAttribute("message");
        %>
        <h1><%out.println(msg);%> não realizado</h1>
    </body>
</html>
