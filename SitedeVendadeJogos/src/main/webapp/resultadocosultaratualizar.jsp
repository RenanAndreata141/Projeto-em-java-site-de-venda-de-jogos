<%@page import="model.Produto"%>
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
        <%if (p.getEmail() != null) {%>

        <form name="f1" action="controle_produto" method="GET">
            ID..........: <%out.print(p.getId());%> <input type="hidden" name="txtid" value="<%out.print(p.getId());%>"> <BR>
            Email...: <input type="text" name="txtemail" value="<%out.print(p.getEmail());%>">  <BR>
            Senha.......: <input type="text" name="txtsenha" value="<%out.print(p.getSenha());%>">  <BR>
            <input type="submit" name="op" value="EFETIVAR ATUALIZAÇÃO">
        </form>
        <%} else {%>
        <h2>Cliente não encontrado.</h2>
        <%}%>
    </body>
</html>
