package dao;

import model.Autor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {
    private Connection conexao;

    public AutorDAO (){
        conexao = new ConnectionFactory().getConnection();
    }

    public void inserir (Autor autor){
        String sql = "insert into autores (nome, email) values (?,?)";

     try{
         //Preparar a conexao
         PreparedStatement stmt = conexao.prepareStatement(sql);
         stmt.setString(1,autor.getNome());
         stmt.setString(2,autor.getEmail());

         //executar o camando
         stmt.execute();

         //fechar a conexao
         conexao.close();
     }catch (SQLException e){
         throw new RuntimeException(e);
     }

    }
    public List<Autor> listarTodos () {
        String sql = "SELECT * FROM autores;";
        List<Autor> autores = new ArrayList<>();

        try{
        //preparar a conexao
            PreparedStatement stmt = conexao.prepareStatement(sql);
            // Executar
            ResultSet resultados = stmt.executeQuery();

            //percorrer os resultados
            while(resultados.next()){
                Autor autor = new Autor();
                autor.setId(resultados.getInt("id"));
                autor.setNome(resultados.getString("nome"));
                autor.setEmail(resultados.getString("email"));

                autores.add(autor);


            }
            //fechar a conexao
            conexao.close();

        }catch (SQLException e){
            throw new RuntimeException();
        }

        return  autores;

    }

    public void alterar(Autor autor) {
        String sql = "update autores set nome = ?, email = ? where id = ?;";
        //preparar a conexao
       try{
           //preparar a conexao
           PreparedStatement stmt = conexao.prepareStatement(sql);
           stmt.setString(1,autor.getNome());
           stmt.setString(2,autor.getEmail());
           stmt.setInt(3,autor.getId());
           stmt.execute();
           System.out.println("Atualizado com sucesso!");

           //fechar a conexao
           conexao.close();

       }catch (SQLException e){
           throw  new RuntimeException(e);
       }
    }

    public void deletar(Autor autor) {
        String sql = "Delete from autores where id = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, autor.getId());

            stmt.execute();

            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Autor listarPorId(Autor autor) {
        String sql = "Select * from autores where id = ?";
        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1,autor.getId());

            ResultSet rs = stmt.executeQuery();
            Autor autorrs = new Autor();
            while (rs.next()){
                autorrs.setId(rs.getInt("id"));
                autorrs.setEmail(rs.getString("email"));
                autorrs.setNome(rs.getString("nome"));
            }
            return autorrs;

        }catch (SQLException e){
            throw new RuntimeException (e);

        }

    }

    public List<Autor> listar_porCampos (Autor autor){
        String sql = "Select * from autores where nome like % ? % or email like % ? %;";

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1,autor.getNome());
            stmt.setString(2,autor.getEmail());

            ResultSet rs = stmt.executeQuery();
            List<Autor> autores = new ArrayList<>();
            Autor autorrs = new Autor();

            while (rs.next()){
                autorrs.setId(rs.getInt("id"));
                autorrs.setEmail(rs.getString("email"));
                autorrs.setNome(rs.getString("nome"));
                autores.add(autorrs);
            }
            return autores;

        }catch (SQLException e){
            throw new RuntimeException (e);

        }
    }

}
