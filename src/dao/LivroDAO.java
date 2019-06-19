package dao;

import model.Editora;
import model.Livro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {
    private Connection conexao;

    public LivroDAO() {
        conexao = new ConnectionFactory().getConnection();
    }

    public void inserir(Livro livro) {
        String sql = "insert into livros (titulo,data_lancamento,quantidade,preco,editora_id) values (?,?,?,?,?)";

        try {
            //Preparar a conexao
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, livro.getTitulo());
            stmt.setDate(2, Date.valueOf(livro.getData_lancamento()));
            stmt.setInt(3, livro.getQtde());
            stmt.setFloat(4, livro.getPreco());
            stmt.setInt(5, livro.getEditora_id());
            //executar o camando
            stmt.execute();
            System.out.println("salvou!!!!!");
            //fechar a conexao
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public List<Livro> listarTodos() {
        String sql = "SELECT livros.id, titulo, data_lancamento, quantidade, preco, editoras.nome FROM db_livraria.livros, editoras where livros.editora_id = editoras.id;";
        List<Livro> livros = new ArrayList<>();

        try {
            //preparar a conexao
            PreparedStatement stmt = conexao.prepareStatement(sql);
            // Executar
            ResultSet resultados = stmt.executeQuery();

            //percorrer os resultados
            while (resultados.next()) {
                Livro livro  = new Livro();
                livro.setId(resultados.getInt("id"));
                livro.setTitulo(resultados.getString("titulo"));
                livro.setData_lancamento(String.valueOf(resultados.getDate("data_lancamento")));
                livro.setQtde(resultados.getInt("quantidade"));
                livro.setPreco(resultados.getFloat("preco"));
                livro.setNome_editora(resultados.getString("nome"));

                livros.add(livro);


            }
            //fechar a conexao
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return livros;

    }

    public void alterar(Livro livro) {

        String sql = "update livros set titulo = ?, data_lancamento = ?," +
                "quantidade = ?, preco = ?, editora_id = ? where id = ?";

        //preparar a conexao
        try {
            //preparar a conexao
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, livro.getTitulo());
            stmt.setDate(2, Date.valueOf(livro.getData_lancamento()));
            stmt.setInt(3, livro.getQtde());
            stmt.setFloat(4, livro.getPreco());
            stmt.setInt(5, livro.getEditora_id());
            stmt.setInt(6,livro.getId());





            stmt.execute();
            System.out.println("Atualizado com sucesso!");


            //fechar a conexao
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void excluir (Livro livro){
        String sql = "Delete from livros where id = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, livro.getId());

            stmt.execute();

            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
