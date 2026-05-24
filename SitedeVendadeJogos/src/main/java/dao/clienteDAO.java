package dao;

import model.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class clienteDAO {
    public void cadastrar(cliente c) throws ClassNotFoundException, SQLException{
        Connection con = conexao.getConexao();
        PreparedStatement comando = con.prepareStatement("Insert into usuarios (nome, email, senha) values (?, ? ,md5(?))");
        comando.setString(1, c.getNome());
        comando.setString(2, c.getEmail());
        comando.setString(3, c.getSenha());
        comando.execute();
        con.close();
    }
    public void deletar(cliente c) throws ClassNotFoundException, SQLException{
        Connection con = conexao.getConexao();
        PreparedStatement comando = con.prepareStatement("delete from usuarios where id = ?");
        comando.setInt(1,c.getId());
        comando.execute();
        con.close();
    }
    public void alterar(cliente c) throws ClassNotFoundException, SQLException{
        Connection con = conexao.getConexao();
        PreparedStatement comando = con.prepareStatement("update clientes set nome = ?, email = ?, senha = ? where id = ?");
        comando.setString(1, c.getNome());
        comando.setString(2, c.getEmail());
        comando.setString(3, c.getSenha());
        comando.setInt(4, c.getId());
        comando.execute();
        con.close();
    }
    public cliente consultarPeloId(cliente c) throws ClassNotFoundException, SQLException{
        Connection con = conexao.getConexao();
        PreparedStatement comando = con.prepareStatement("select * from usuarios where id = ?");
        comando.setInt(1, c.getId());
        ResultSet rs = comando.executeQuery();
        cliente cli = new cliente();
        if(rs.next()){
            cli.setId(rs.getInt("id"));
            cli.setNome("nome");
            cli.setEmail("email");
            cli.setSenha("senha");
        }
        return cli;
    }
    public List<cliente> consultarTodos() throws ClassNotFoundException, SQLException{
        Connection con = conexao.getConexao();
        PreparedStatement comando = con.prepareStatement("select * from usuarios");
        ResultSet rs = comando.executeQuery();
        List<cliente> lcliente = new ArrayList<cliente>();
        while(rs.next()){
            cliente cli = new cliente();
            cli.setId(rs.getInt("id"));
            cli.setNome(rs.getString("nome"));
            cli.setEmail(rs.getString("email"));
            cli.setSenha((rs.getString("senha")));
            lcliente.add(cli);
        }
        return lcliente;
    }
    public cliente autenticar(String email, String senha) throws ClassNotFoundException, SQLException {
        Connection con = conexao.getConexao();
        PreparedStatement comando = con.prepareStatement("SELECT * FROM usuarios WHERE email = ? AND senha = md5(?)");
        comando.setString(1, email);
        comando.setString(2, senha);
        ResultSet rs = comando.executeQuery();
        cliente cli = null;
        if (rs.next()) {
            cli = new cliente();
            cli.setId(rs.getInt("id"));
            cli.setNome(rs.getString("nome"));
            cli.setEmail(rs.getString("email"));
        }
        con.close();
        return cli;
    }
}