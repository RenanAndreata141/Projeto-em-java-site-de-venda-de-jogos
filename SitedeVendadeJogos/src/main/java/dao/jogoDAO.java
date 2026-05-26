package dao;
import model.jogo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class jogoDAO {
    public void cadastrar(jogo j) throws ClassNotFoundException, SQLException {
        Connection con = conexao.getConexao();
        PreparedStatement comando = con.prepareStatement("Insert into jogos (nome, preco) values (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
        comando.setString(1, j.getNome());
        comando.setDouble(2, j.getPreco());
        comando.execute();
        ResultSet rs = comando.getGeneratedKeys();
        if (rs.next() && j.getImagem() != null) {
            int idJogo = rs.getInt(1);
            PreparedStatement imgCmd = con.prepareStatement("insert into imagens (nome_arquivo, id_jogo, dados_imagem, tipo_mime) values (?, ?, ?, ?)");
            imgCmd.setString(1, j.getImagem());
            imgCmd.setInt(2, idJogo);
            imgCmd.setString(3, j.getImagem());
            imgCmd.setString(4, "image/png");
            imgCmd.execute();
        }
        con.close();
    }
    public void deletar(jogo j) throws ClassNotFoundException, SQLException{
        Connection con = conexao.getConexao();
        PreparedStatement comando = con.prepareStatement("delete from jogos where id = ?", PreparedStatement.RETURN_GENERATED_KEYS);
        comando.setInt(1,j.getId());
        comando.execute();
        ResultSet rs = comando.getGeneratedKeys();
        if (rs.next()) {
            int idJogo = rs.getInt(1);
            PreparedStatement imgCmd = con.prepareStatement("delete from images where id = ?");
            imgCmd.setInt(1, idJogo);
            imgCmd.execute();
        }
        con.close();
    }
    public void alterar(jogo j) throws ClassNotFoundException, SQLException{
        Connection con = conexao.getConexao();
        PreparedStatement comando = con.prepareStatement("update jogos set nome = ?, preco = ? where id = ?", PreparedStatement.RETURN_GENERATED_KEYS);
        comando.setString(1, j.getNome());
        comando.setDouble(2, j.getPreco());
        comando.setInt(4, j.getId());
        comando.execute();
        ResultSet rs = comando.getGeneratedKeys();
        if(rs.next()){
            int idJogo = rs.getInt(1);
            PreparedStatement imgCmd = con.prepareStatement("update table images set nome-arquivo = ?, id_jogo = ?, dados-imagem = ?, tipo_mime = ? where id = ?");
            imgCmd.setInt(1,idJogo);
            imgCmd.execute();
        }
        con.close();
    }
    public jogo consultarPeloId(jogo j) throws ClassNotFoundException, SQLException{
        Connection con = conexao.getConexao();
        PreparedStatement comando = con.prepareStatement("select * from jogos where id = ?");
        comando.setInt(1, j.getId());
        ResultSet rs = comando.executeQuery();
        jogo jogo = new jogo();
        if(rs.next()){
            jogo.setId(rs.getInt("id"));
            jogo.setNome("nome");
            jogo.setPreco(rs.getDouble("preco"));
        }
        return jogo;
    }
    public List<jogo> consultarTodos() throws ClassNotFoundException, SQLException{
        Connection con = conexao.getConexao();
        PreparedStatement comando = con.prepareStatement("SELECT j.id, j.nome, j.preco, i.dados_imagem FROM jogos j LEFT JOIN imagens i ON i.id_jogo = j.id");
        ResultSet rs = comando.executeQuery();
        List<jogo> ljogos = new ArrayList<jogo>();
        while(rs.next()){
            jogo jo = new jogo();
            jo.setId(rs.getInt("id"));
            jo.setNome(rs.getString("nome"));
            jo.setPreco(rs.getDouble("preco"));
            jo.setImagem(rs.getString("dados_imagem"));
            ljogos.add(jo);
        }
        return ljogos;
    }
    public jogo autenticar(String nome, double preco) throws ClassNotFoundException, SQLException {
        Connection con = conexao.getConexao();
        PreparedStatement comando = con.prepareStatement("select * from jogos where nome = ? and preco = ?");
        comando.setString(1, nome);
        comando.setDouble(2, preco);
        ResultSet rs = comando.executeQuery();
        jogo jo = null;
        if (rs.next()) {
            jo = new jogo();
            jo.setId(rs.getInt("id"));
            jo.setNome(rs.getString("nome"));
            jo.setPreco(rs.getDouble("preco"));
        }
        con.close();
        return jo;
    }
}
