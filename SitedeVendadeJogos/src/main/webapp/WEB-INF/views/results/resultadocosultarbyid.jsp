<%@page import="model.cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultado Cosultar By ID</title>
    </head>
    <body>
        <h1>Resultado Consultar By ID</h1>
        <%
            cliente c = (cliente) request.getAttribute("c");
        %>
        <%if (c.getNome() != null) {%>
        ID..........: <%out.print(c.getId());%> <BR>
        Descrição...: <%out.print(c.getNome());%> <BR>
        Email.......: <%out.print(c.getEmail());%> <BR>
        Senha.......: <%out.print(c.getSenha());%> <BR>
        <%} else {%>
        <h2>Cliente não encontrado.</h2>
        <%}%>
    </body>
</html>
