<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.cliente" %>
<%@ page import="java.util.List, model.jogo" %>

<!DOCTYPE html>
<html>
<head>
    <title>Registro</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script type="text/javascript">
        function carregarconsultartodos() {
            window.location = "/controle_cliente?op=CONSULTAR TODOS"
        }
        function carregarsonsultartodosjogos(){
            window.location = "/controle_jogos?op=CONSULTAR TODOS"
        }
    </script>

</head>
<header>
<section id="botaoCada">
    <img src="/webapp/images/logo.png" alt="logo" onclick="window.location.href='home.html'">
</section>
<section id = "botaoCriarJogo">
    <a href="controle_jogos?op=REDI CADASTRO">Cadastrar Jogos</a>
</section>
</header>
<body>
    <%
        cliente usuario = (cliente) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/login.html");
        }
    %>
<h1><p>JOGOS DISPONIVEIS</p></h1>
<section class="bodyVitrine">
<%
    List<jogo> lista = (List<jogo>) request.getAttribute("ljogo");
    if (lista != null) {
        for (jogo j : lista) {
%>
    <div class="card-jogo">
        <img src="<%= request.getContextPath() %>/images/<%= j.getImagem() %>" alt="<%= j.getNome() %>">
        <h2><%= j.getNome() %></h2>
        <p>R$ <%= j.getPreco() %></p>
    </div>
    </div>
<%
        }
    }
%>
</section>
</body>
</html>
