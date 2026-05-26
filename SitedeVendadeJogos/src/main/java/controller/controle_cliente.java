package controller;

import dao.clienteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.cliente;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;


@WebServlet(name = "controle_cliente", urlPatterns = {"/controle_cliente"})
public class controle_cliente extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String op = request.getParameter("op");
            clienteDAO cdao = new clienteDAO();
            cliente c = new cliente();

            if (op.equals("CADASTRAR")) {
                String nome = request.getParameter("txtnome");
                String email = request.getParameter("txtemail");
                String senha = request.getParameter("txtsenha");
                c.setNome(nome);
                c.setEmail(email);
                c.setSenha(senha);
                String msg = "Cadastrar";
                try {
                    cdao.cadastrar(c);
                    System.out.println("Cadastrado com sucesso");
                    request.setAttribute("message", msg);
                    request.getRequestDispatcher("resultado.jsp").forward(request, response);
                } catch (SQLException | ClassNotFoundException ex) {
                    System.out.println("Erro ClassNotFound: " + ex.getMessage());
                    request.setAttribute("message", msg);
                    request.getRequestDispatcher("/WEB-INF/views/errors/erro.jsp").forward(request, response);

                }
            } else if (op.equals("DELETAR")) {
                int id = Integer.parseInt(request.getParameter("txtid"));
                c.setId(id);
                String msgD = "Deletar";
                try {
                    cdao.deletar(c);
                    List<cliente> lcliente = cdao.consultarTodos();
                    request.setAttribute("lcliente", lcliente);
                    request.getRequestDispatcher("resultadoconsultartodos.jsp").forward(request, response);
                } catch (ClassNotFoundException | SQLException exD) {
                    System.out.println("Erro ClassNotFound: " + exD.getMessage());
                    request.setAttribute("message", msgD);
                    request.getRequestDispatcher("/WEB-INF/views/errors/erro.jsp").forward(request, response);
                }
            } else if (op.equals("CONSULTAR BY ID")) {
                int id = Integer.parseInt(request.getParameter("txtid"));
                c.setId(id);
                try {
                    c = cdao.consultarPeloId(c);
                    request.setAttribute("c", c);
                    request.getRequestDispatcher("/WEB-INF/views/results/resultadoconsultabyid.jsp").forward(request, response);
                } catch (ClassNotFoundException | SQLException exCoI) {
                    System.out.println("Erro ClassNotFound: " + exCoI.getMessage());
                }

            } else if (op.equals("CONSULTAR TODOS")) {
                try {
                    List<cliente> lcliente = cdao.consultarTodos();
                    request.setAttribute("lcliente", lcliente);
                    request.getRequestDispatcher("/WEB-INF/views/results/resultadoconsultartodos.jsp").forward(request, response);
                } catch (ClassNotFoundException | SQLException exCoT) {
                    System.out.println("Erro ClassNotFound: " + exCoT.getMessage());
                }
            } else if (op.equals("ATUALIZAR")) {
                int id = Integer.parseInt(request.getParameter("txtid"));
                c.setId(id);
                try {
                    c = cdao.consultarPeloId(c);
                    request.setAttribute("c", c);
                    request.getRequestDispatcher("/WEB-INF/views/results/resultadoconsultaratualizar.jsp").forward(request, response);
                } catch (ClassNotFoundException | SQLException exCoA) {
                    System.out.println("Erro ClassNotFound: " + exCoA.getMessage());
                }
            } else if (op.equals("EFETIVAR ATUALIZAÇÂO")) {
                int id = Integer.parseInt(request.getParameter("txtid"));
                String nome = request.getParameter("txtnome");
                String email = request.getParameter("txtemail");
                String senha = request.getParameter("txtsenha");
                c.setId(id);
                c.setEmail(email);
                c.setSenha(senha);
                String msgAtu = "Atualizar";
                try {
                    cdao.alterar(c);
                    System.out.println("Atualizado com sucesso");
                    request.setAttribute("message", msgAtu);
                    request.getRequestDispatcher("/WEB-INF/views/results/resultado.jsp").forward(request, response);
                } catch (ClassNotFoundException | SQLException exATU) {
                    System.out.println("Erro ClassNotFound: " + exATU.getMessage());
                    request.setAttribute("message", msgAtu);
                    request.getRequestDispatcher("/WEB-INF/views/errors/erro.jsp").forward(request, response);
                }
            }else if (op.equals("LOGIN")) {
                String email = request.getParameter("txtemail");
                String senha = request.getParameter("txtsenha");

                try {
                    cliente clienteLogado = cdao.autenticar(email, senha);

                    if (clienteLogado != null) {
                        jakarta.servlet.http.HttpSession sessao = request.getSession();
                        sessao.setAttribute("usuarioLogado", clienteLogado);

                        request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
                    } else {
                        request.setAttribute("message", "Email ou senha incorretos.");
                        request.getRequestDispatcher("/WEB-INF/views/errors/erro.jsp").forward(request, response);
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    System.out.println("Erro na autenticação: " + ex.getMessage());
                    request.setAttribute("message", "Erro interno no servidor.");
                    request.getRequestDispatcher("/WEB-INF/views/errors/erro.jsp").forward(request, response);
                }
            }
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
/**
 * Handles the HTTP <code>GET</code> method.
 *
 * @param request servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException if an I/O error occurs
 */
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    processRequest(request, response);
}

/**
 * Handles the HTTP <code>POST</code> method.
 *
 * @param request servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException if an I/O error occurs
 */
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    processRequest(request, response);
}

/**
 * Returns a short description of the servlet.
 *
 * @return a String containing servlet description
 */
@Override
public String getServletInfo() {
    return "Short description";
}// </editor-fold>

}