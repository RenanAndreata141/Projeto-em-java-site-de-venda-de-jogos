<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.usuario" %>
<%@ page import="java.util.List, model.jogo" %>

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
    jogo j = (jogo) request.getAttribute("j");
%>
<form name="f1" action="controle_jogos?op=ALTERAR" method="POST" enctype="multipart/form-data">
    <div><h1>Atualizar Jogo</h1></div>
    Id: <input type="text" name="txtid" value="<%= j.getId()%>"><br><br>
    Nome: <input type="text" name="txtnome" value="<%= j.getNome()%>"><br><br>
    Preço: <input type="number" step="0.01" name="txtpreco" value="<%= j.getPreco()%>"><br><br>
    Imagem: <input type="file" name="txtimagem" accept="image/*"> <br><br>
    <input type="submit" name="op" value="ALTERAR">
</form>
</body>
</html>
