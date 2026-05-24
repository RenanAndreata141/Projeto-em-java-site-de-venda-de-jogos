<%@page import="java.util.List"%>
<%@page import="model.cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultado Consultar Todos</title>
    </head>
    <body>
        <h1>Todos os Produtos</h1>
        <%
            List<cliente> lcliente = (List<cliente>) request.getAttribute("lcliente");
        %>


        <table border="1">
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Email</th>
                <th>Senha</th>
            </tr>

            <%for (cliente c : lcliente) {%>
            <tr>
                <td><%out.print(c.getId());%></td>
                <tf><%out.print(c.getNome());%></td>
                <td><%out.print(c.getEmail());%></td>
                <td><%out.print(c.getSenha());%></td>
                <td align="center"><a href="controle_cliente?op=DELETAR&txtid=<%out.print(c.getId());%>"><img src="images/lixeira01.png" width="25" height="25"></a></td>
                <td align="center"><a href="controle_cliente?txtid=<%out.print(c.getId());%>&txtnome=<%out.print(c.getNome());%>&txtemail=<%out.print(c.getEmail());%>&txtsenha=<%out.print(c.getSenha());%>&op=ATUALIZAR" ><img src="images/editar01.png" width="25" height="25"></a></td>
            </tr>
            <%}%>

        </table>
    </body>
</html>
