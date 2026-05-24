<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.cliente" %>
<html>
<head>
    <title>Home - Loja de Jogos</title>
</head>
<body>
    <%
        cliente usuario = (cliente) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            response.sendRedirect("login.html");
        }
    %>

    <h1>Bem-vindo à Loja de Jogos, <%= usuario.getNome() %>!</h1>
    <p>Seu e-mail cadastrado é: <%= usuario.getEmail() %></p>

    <a href="vitrine_jogos.jsp">Ver Jogos Disponíveis</a>
</body>
</html>