<%@page import="model.usuario"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultado da atualização</title>
    </head>
    <section class="botaoCada">
        <img src="/webapp/images/logo.png" alt="logo" onclick="location.href='controle_usuarios?op=REDIRECIONAR_INDEX'">
    </section>
    <body>
        <h1>Resultado Alteração do jogo</h1>
        <%
            jogo j = (jogo) request.getAttribute("j");
        %>
        <%if (j.getNome() == null) {%>
        <h1>Conta deletada com sucesso</h1>
        <%} else {%>
        <h2>Erro: Conta não deletada</h2>
        <%}%>
    </body>
</html>
