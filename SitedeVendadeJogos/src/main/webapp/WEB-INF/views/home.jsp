<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.cliente" %>
<%@ page import="model.jogo" %>
<html>
<head>
    <title>Home - Loja de Jogos</title>
</head>
<header>
<section id="botaoCada">
    <img src="/webapp/images/logo.png" alt="logo">
</section>
</header>
<body>
    <%
        cliente usuario = (cliente) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/login.html");
        }
    %>

    <h1>Bem-vindo à Loja de Jogos, <%= usuario.getNome() %>!</h1>
    <p>Seu e-mail cadastrado é: <%= usuario.getEmail() %></p>

    <a href="controle_jogos?op=REDIRECIONAR VITRINE">Ver Jogos Disponíveis</a>
</body>
</html>