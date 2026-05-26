<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.cliente" %>
<%@ page import="model.jogo" %>

<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <script type="text/javascript">
        function carregarconsultartodos() {
            window.location = "/controle_jogos?op=CONSULTAR TODOS"
        }
    </script>
</head>
<header>
<section id="botaoCada">
    <a href="controle_jogos?op=REDIRECIONAR VITRINE">
      <img src="/webapp/images/logo.png" alt="logo">
    </a>
</section>
</header>
<body>
<form name="f1" action="controle_jogos?op=CADASTRAR" method="POST" enctype="multipart/form-data">
    <div><h1>Cadastro de Jogos</h1></div>
    Nome: <input type="text" name="txtnome"><br><br>
    Preço: <input type="number" step="0.01" name="txtpreco"><br><br>
    Imagem: <input type="file" name="txtimagem" accept="image/*">
    <input type="submit" name="op" value="CADASTRAR">
</form>
</body>
</html>
