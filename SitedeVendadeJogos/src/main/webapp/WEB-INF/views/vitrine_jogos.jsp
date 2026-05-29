<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.usuario" %>
<%@ page import="java.util.List, model.jogo" %>

<!DOCTYPE html>
<html>
<head>
    <title>Vitrine de jogos</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script type="text/javascript">
        function carregarconsultartodos() {
            window.location = "/controle_cliente?op=CONSULTAR_TODOS"
        }
        function carregarsonsultartodosjogos(){
            window.location = "/controle_jogos?op=CONSULTAR_TODOS"
        }
    </script>

</head>
<header>
    <section class="botaoCada">
        <img src="/webapp/images/logo.png" alt="logo" onclick="location.href='controle_jogos?op=REDIRECIONAR_HOME'">
    </section>
<section id = "botaoCriarJogo">
    <button onclick="location.href='controle_jogos?op=REDI_CADASTRO'">Cadastrar Jogos</a>
</section>
</header>
<body>
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
        <p><button onclick="location.href='controle_jogos?op=FORMULARIO_ALTERAR&id=<%= j.getId()%>'">Editar</button></p>
        <p><button onclick="location.href='controle_jogos?op=DELETAR&id=<%= j.getId()%>'">Deletar</button></p>
    </div>
<%
        }
    }
%>
</section>
</body>
</html>
