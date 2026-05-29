<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.usuario" %>
<%@ page import="java.util.List, model.jogo" %>

<!DOCTYPE html>
<html>
<head>
    <title>Lista de Usuários</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script type="text/javascript">
        function carregarconsultartodos() {
            window.location = "/controle_usuarios?op=CONSULTAR_TODOS"
        }
        function carregarsonsultartodosjogos(){
            window.location = "/controle_jogos?op=CONSULTAR_TODOS"
        }
    </script>

</head>
<header>
    <section class="botaoCada">
        <img src="/webapp/images/logo.png" alt="logo" onclick="location.href='controle_jogos?op=REDIRECIONAR_VITRINE'">
    </section>
</header>
<body>
<h1><p>Usuário Cadastrado</p></h1>
<section class="bodyUsu">
<%
        usuario c = (usuario) session.getAttribute("usuarioLogado");
        if (c == null) {
            response.sendRedirect(request.getContextPath() + "/login.html");
            return;
        }
%>
    <div class="card-usuario">
        <h2>Nome: <%= c.getNome() %></h2>
        <p>Email: <%= c.getEmail() %></p>
        <p><button onclick="location.href='controle_usuarios?op=FORMULARIO_ALTERAR&id=<%= c.getId()%>'">Editar</button></p>
        <p><button onclick="location.href='controle_usuarios?op=DELETAR&id=<%= c.getId()%>'">Deletar</button></p>
    </div>
</section>
</body>
</html>
