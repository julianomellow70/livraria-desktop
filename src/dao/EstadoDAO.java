package dao;

import model.Autor;
import model.Estado;
import model.Municipio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadoDAO {
    private Connection conexao;

    public EstadoDAO() {
        conexao = new ConnectionFactory().getConnection();
    }

    public List<Estado> listar_todos_estados () {
        String sql = "SELECT * FROM estado;";
        List<Estado> estados = new ArrayList<>();

        try {
            //preparar a conexao
            PreparedStatement stmt = conexao.prepareStatement(sql);
            // Executar
            ResultSet resultados = stmt.executeQuery();

            //percorrer os resultados
            while (resultados.next()) {
               Estado estado = new Estado();
                estado.setUF(resultados.getString("uf"));
                estado.setId(resultados.getInt("id"));


                estados.add(estado);


            }
            //fechar a conexao
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return estados;
    }
    public int retornarIDEstado(String estado){
        String sql = "Select id from estado where uf = '"+estado+"';";
        int id = 0;
        try {
            //preparar a conexao
            PreparedStatement stmt = conexao.prepareStatement(sql);
            // Executar
            ResultSet resultados = stmt.executeQuery();

            //percorrer os resultados
            while (resultados.next()) {

                id = resultados.getInt("id");
            }
            //fechar a conexao
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return id;
    }

    public void inserir (Estado estado){
        String sql = "insert into estado (uf) values (?)";

        try {
            //Preparar a conexao
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, estado.getUF());


            //executar o camando
            stmt.execute();

            //fechar a conexao
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alterar(Estado estado) {

        String sql = "update estado set uf = ? where id = ?;";
        //preparar a conexao
        try {
            //preparar a conexao
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1,estado.getUF());
            stmt.setInt(2,estado.getId());

            stmt.execute();
            System.out.println("Atualizado com sucesso!");


            //fechar a conexao
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar(Estado estado) {
        String sql = "Delete from estado where id = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, estado.getId());

            stmt.execute();

            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
