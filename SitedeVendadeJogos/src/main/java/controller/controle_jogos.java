package controller;


import dao.jogoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.jogo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

import static java.lang.Integer.parseInt;

@WebServlet(name = "controle_jogos", urlPatterns = {"/controle_jogos"})
public class controle_jogos extends HttpServlet {
    public void processRequest(HttpServletResponse response, HttpServletRequest request)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
            String op = request.getParameter("op");
            jogoDAO jdao = new jogoDAO();
            jogo j = new jogo();

                if(op.equals("CADASTRAR")) {
                    String nome = request.getParameter("txtnome");
                    double preco = Double.parseDouble(request.getParameter("txtpreco"));
                    Part filePart = request.getPart("txtimagem");
                    if (filePart != null && filePart.getSize() > 0) {
                        String nomeArquivo = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                        String caminho = getServletContext().getRealPath("/images") + "/" + nomeArquivo;
                        filePart.write(caminho);
                        j.setImagem(nomeArquivo);
                    }else{
                        System.out.println("Imagem não selecionada");
                    }
                    if(nome != null){
                        j.setNome(nome);
                    }else{
                        System.out.println("Nome do jogo não declarado");
                    }
                    if(preco != 0){
                        j.setPreco(preco);
                    }else{
                        System.out.println("Valor inválido");
                    }
                    String msg = "Cadastrar";
                    try {
                        jdao.cadastrar(j);
                        request.setAttribute("message", "Jogo cadastrado com sucesso");
                        request.getRequestDispatcher("/WEB-INF/views/jogo_cadastro.jsp").forward(request, response);
                    } catch (SQLException | ClassNotFoundException exC) {
                        request.setAttribute("message", "Erro: " + exC.getMessage());
                        request.getRequestDispatcher("/WEB-INF/views/jogo_cadastro.jsp").forward(request, response);
                    }
                }else if(op.equals("DELETAR")) {
                    int id = parseInt(request.getParameter("id"));
                    j.setId(id);
                    String msg = "Deletar";
                    try {
                        jdao.deletar(j);
                        System.out.println("Jogo " + j.getNome() + " foi deletado com sucesso");
                        request.setAttribute("message", msg);
                        response.sendRedirect("controle_jogos?op=REDIRECIONAR_VITRINE");
                    } catch (SQLException | ClassNotFoundException exD) {
                        System.out.println("Erro ClassNotFound: " + exD.getMessage());
                        request.setAttribute("message", msg);
                        request.getRequestDispatcher("/WEB-INF/views/errors/erroJOGO.jsp").forward(request, response);
                    }
                }else if(op.equals("ALTERAR")) {
                    String nome = request.getParameter("txtnome");
                    double preco = Double.parseDouble(request.getParameter("txtpreco"));
                    int id = parseInt(request.getParameter("txtid"));
                    j.setId(id);
                    j.setNome(nome);
                    j.setPreco(preco);
                    Part filePart = request.getPart("txtimagem");
                    if (filePart != null && filePart.getSize() > 0) {
                        String nomeArquivo = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                        String caminho = getServletContext().getRealPath("/images") + "/" + nomeArquivo;
                        filePart.write(caminho);
                        j.setImagem(nomeArquivo);
                    }
                    String msg = "Alterar";
                    try {
                        jdao.alterar(j);
                        jogo jAtualizado = jdao.consultarPeloId(j);
                        if(jAtualizado == null){
                            throw new SQLException("Jogo não encontrado após atualização. ID: " + j.getId());
                        }
                        System.out.println("Informações do jogo, alteradas com sucesso");
                        request.setAttribute("j", jAtualizado);
                        request.getRequestDispatcher("/WEB-INF/views/results/resultadoATUALIZACAOJOGO.jsp").forward(request, response);
                    } catch (SQLException | ClassNotFoundException exA) {
                        request.setAttribute("message", exA.getMessage());
                        request.getRequestDispatcher("/WEB-INF/views/errors/erroJOGO.jsp").forward(request, response);
                    }
                }else if(op.equals("REDI_CADASTRO")){
                    String msg = "Redirecionamento de Cadastro";
                    try {
                        request.getRequestDispatcher("/WEB-INF/views/jogo_cadastro.jsp").forward(request, response);
                        request.setAttribute("message", msg);
                    } catch (Exception exRV) {
                        System.out.println("Erro no: " + exRV.getMessage());
                        request.setAttribute("message", "Arquivo não encontrado");
                        request.getRequestDispatcher("/WEB-INF/errors/vitrineError.jsp").forward(request, response);
                    }
                }else if(op.equals("CONSULTAR_TODOS")){
                    try {
                        List<jogo> ljogo = jdao.consultarTodos();
                        request.setAttribute("ljogo", ljogo);
                        request.getRequestDispatcher("resultadorequesttodos.jsp");
                    } catch (SQLException | ClassNotFoundException exCT) {
                        request.setAttribute("Erro: ClassNotFound: ", exCT.getMessage());
                    }
                }else if(op.equals("REDIRECIONAR_VITRINE")){
                    try {
                        List<jogo> ljogo = jdao.consultarTodos();
                        request.setAttribute("ljogo", ljogo);
                        request.getRequestDispatcher("/WEB-INF/views/vitrine_jogos.jsp").forward(request, response);
                    } catch (SQLException | ClassNotFoundException | FileNotFoundException exRV) {
                        System.out.println("Erro no: " + exRV.getMessage());
                        request.setAttribute("message", "Arquivo não encontrado");
                        request.getRequestDispatcher("/WEB-INF/errors/vitrineError.jsp").forward(request,response);
                    }
                }else if(op.equals("FORMULARIO_ALTERAR")) {
                    int id = parseInt(request.getParameter("id"));
                    j.setId(id);
                    String msg = "Redirecionar";
                    try {
                        j = jdao.consultarPeloId(j);
                        request.setAttribute("j", j);
                        request.getRequestDispatcher("/WEB-INF/views/atualizar_jogoForm.jsp").forward(request, response);
                    } catch (SQLException | ClassNotFoundException | FileNotFoundException exRV) {
                        System.out.println("Erro no: " + exRV.getMessage());
                        request.setAttribute("message", "Arquivo não encontrado");
                        request.getRequestDispatcher("/WEB-INF/errors/vitrineError.jsp").forward(request, response);
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
        processRequest(response, request);
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
        processRequest(response, request);
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
