package dao;

import model.Autor;
import model.Editora;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EditoraDAO {
    private Connection conexao;

    public EditoraDAO() {
        conexao = new ConnectionFactory().getConnection();
    }


    public void inserir(Editora editora) {
        String sql = "insert into editoras (nome,site,endereco,bairro,telefone,municipio_id) values (?,?,?,?,?,?)";

        try {
            //Preparar a conexao
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, editora.getNome());
            stmt.setString(2, editora.getSite());
            stmt.setString(3, editora.getEndereco());
            stmt.setString(4, editora.getBairro());
            stmt.setString(5, editora.getTelefone());
            stmt.setInt(6, editora.getMunicipio_id());


            //executar o camando
            stmt.execute();
            System.out.println("salvou!!!!!");
            //fechar a conexao
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public List<Editora> listarTodos() {
        String sql = "SELECT editoras.id, editoras.nome, editoras.site, editoras.endereco, editoras.bairro, editoras.telefone, municipio.nome as nome_municipio FROM db_livraria.editoras, db_livraria.municipio\n" +
                "where municipio_id = municipio.id;";
        List<Editora> editoras = new ArrayList<>();

        try {
            //preparar a conexao
            PreparedStatement stmt = conexao.prepareStatement(sql);
            // Executar
            ResultSet resultados = stmt.executeQuery();

            //percorrer os resultados
            while (resultados.next()) {
               Editora editora = new Editora();
               editora.setId(resultados.getInt("id"));
               editora.setNome(resultados.getString("nome"));
                editora.setSite(resultados.getString("site"));
                editora.setNomeMunicipio(resultados.getString("nome_municipio"));
                editora.setTelefone(resultados.getString("telefone"));
                editora.setBairro(resultados.getString("bairro"));
                editora.setEndereco(resultados.getString("endereco"));
               /*autor.setId(resultados.getInt("id"));
                autor.setNome(resultados.getString("nome"));
                autor.setEmail(resultados.getString("email"));
                    */
                editoras.add(editora);


            }
            //fechar a conexao
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return editoras;

    }

    public void alterar(Editora editora) {

        String sql = "update editoras set nome = ?, site = ?,endereco = ?, bairro = ?, telefone = ?, municipio_id = ? where id = ?";

        //preparar a conexao
        try {
            //preparar a conexao
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, editora.getNome());
            stmt.setString(2, editora.getSite());
            stmt.setString(3, editora.getEndereco());
            stmt.setString(4, editora.getBairro());
            stmt.setString(5, editora.getTelefone());
            stmt.setInt(6, editora.getMunicipio_id());
            stmt.setInt(7, editora.getId());




            stmt.execute();
            System.out.println("Atualizado com sucesso!");


            //fechar a conexao
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void excluir (Editora editora){
        String sql = "Delete from editoras where id = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, editora.getId());

            stmt.execute();

            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int retornarIDEditora (String editora){
        String sql = "Select id from editoras where nome = '"+editora+"';";
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
}
