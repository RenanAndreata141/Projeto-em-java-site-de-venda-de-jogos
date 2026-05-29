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
        PreparedStatement imgCmd = con.prepareStatement("delete from imagens where id_jogo = ?");
        imgCmd.setInt(1, j.getId());
        imgCmd.execute();

        PreparedStatement comando = con.prepareStatement("delete from jogos where id = ?");
        comando.setInt(1,j.getId());
        comando.execute();
        con.close();
    }
    public void alterar(jogo j) throws ClassNotFoundException, SQLException{
        Connection con = conexao.getConexao();
        PreparedStatement comando = con.prepareStatement("update jogos set nome = ?, preco = ? where id = ?");
        comando.setString(1, j.getNome());
        comando.setDouble(2, j.getPreco());
        comando.setInt(3, j.getId());
        comando.execute();

        PreparedStatement verificar = con.prepareStatement("select id from imagens where id_jogo = ?");
        verificar.setInt(1, j.getId());
        ResultSet rs = verificar.executeQuery();
        if (rs.next()) {
            PreparedStatement imgCmd = con.prepareStatement("update imagens set nome_arquivo = ?, dados_imagem = ?, tipo_mime = ? where id_jogo = ?");
            imgCmd.setString(1, j.getImagem());
            imgCmd.setString(2, j.getImagem());
            imgCmd.setString(3, "image/png");
            imgCmd.setInt(4, j.getId());
            imgCmd.execute();
        } else {
            PreparedStatement imgInsert = con.prepareStatement("insert into imagens (id_jogo, nome_arquivo, dados_imagem, tipo_mime) values (?, ?, ?, ?)");
            imgInsert.setInt(1, j.getId());
            imgInsert.setString(2, j.getImagem());
            imgInsert.setString(3, j.getImagem());
            imgInsert.setString(4, "image/png");
            imgInsert.execute();
        }
        con.close();
    }
    public jogo consultarPeloId(jogo j) throws ClassNotFoundException, SQLException{
        Connection con = conexao.getConexao();
        PreparedStatement comando = con.prepareStatement("select j.id, j.nome, j.preco, i.dados_imagem from jogos j left join imagens i on i.id_jogo = j.id where j.id = ?");
        comando.setInt(1,j.getId());
        ResultSet rs = comando.executeQuery();
        jogo jogo = new jogo();
        if(rs.next()){
            jogo.setId(rs.getInt("id"));
            jogo.setNome(rs.getString("nome"));
            jogo.setPreco(rs.getDouble("preco"));
            jogo.setImagem(rs.getString("dados_imagem"));
        }
        return jogo;
    }
    public List<jogo> consultarTodos() throws ClassNotFoundException, SQLException{
        Connection con = conexao.getConexao();
        PreparedStatement comando = con.prepareStatement("select j.id, j.nome, j.preco, i.dados_imagem FROM jogos j left join imagens i on i.id_jogo = j.id");
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
