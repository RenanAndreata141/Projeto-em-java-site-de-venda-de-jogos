<%@page import="model.jogo"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultado da atualização</title>
    </head>
    <section class="botaoCada">
        <img src="/webapp/images/logo.png" alt="logo" onclick="location.href='controle_jogos?op=REDIRECIONAR_VITRINE'">
    </section>
    <body>
        <h1>Resultado Alteração do jogo</h1>
        <%
            jogo j = (jogo) request.getAttribute("j");
        %>
        <%if (j.getNome() != null) {%>
        ID..........: <%out.print(j.getId());%> <BR>
        Nome...: <%out.print(j.getNome());%> <BR>
        Preço.......: <%out.print(j.getPreco());%> <BR>
        Imagem.......: <img src="<%= request.getContextPath() %>/images/<%= j.getImagem() %>" alt="<%= j.getNome() %>"> <BR>
        <%} else {%>
        <h2>Jogo não encontrado.</h2>
        <%}%>
    </body>
</html>
