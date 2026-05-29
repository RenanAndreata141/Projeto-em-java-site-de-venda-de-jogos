<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.usuario" %>
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
        usuario c = (usuario) session.getAttribute("usuarioLogado");

        if (c == null) {
            response.sendRedirect(request.getContextPath() + "/login.html");
        }
    %>
<body>
    <h1>Bem-vindo à sua Loja de Jogos, <%= c.getNome() %>!</h1>
    <p>Verifique os jogos e usuarios abaixo</p>
    <button onclick="location.href='controle_jogos?op=REDIRECIONAR_VITRINE'">Ver Jogos Disponíveis</button>
    <button onclick="location.href='controle_usuarios?op=REDIRECIONAR_USUARIOS'">Ver Lista de Usuários</button>
</body>
</html>