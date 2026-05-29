<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, model.usuario" %>

<!DOCTYPE html>
<html>
<head>
    <title>Registro</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>
<header>
<section class="botaoCada">
    <a href="controle_jogos?op=REDIRECIONAR_VITRINE">
      <img src="/webapp/images/logo.png" alt="logo">
    </a>
</section>
</header>
<body>
<%
    usuario c = (usuario) request.getAttribute("c");
%>
<form name="f1" action="controle_usuarios?op=ATUALIZAR" method="POST">
    <div><h1>Atualizar Usuário</h1></div>
    Id: <input type="text" name="txtid" value="<%= c.getId()%>"><br><br>
    Nome: <input type="text" name="txtnome" value="<%= c.getNome()%>"><br><br>
    Email: <input type="text" name="txtemail" value="<%= c.getEmail()%>"><br><br>
    Senha: <input type="text" name="txtsenha" value="<%= c.getSenha()%>"> <br><br>
    <input type="submit" name="op" value="ATUALIZAR">
</form>
</body>
</html>
