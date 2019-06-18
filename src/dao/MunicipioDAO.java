package dao;

import model.Autor;
import model.Municipio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MunicipioDAO {


        private Connection conexao;

        public MunicipioDAO() {
            conexao = new ConnectionFactory().getConnection();
        }

    public List<Municipio> listar_todos_municipios_tabela (){
        String sql = "SELECT municipio.id, nome, uf FROM db_livraria.estado, municipio where municipio.estado_id = estado.id ;";
        List<Municipio> municipios = new ArrayList<>();

        try {
            //preparar a conexao
            PreparedStatement stmt = conexao.prepareStatement(sql);
            // Executar
            ResultSet resultados = stmt.executeQuery();

            //percorrer os resultados
            while (resultados.next()) {
                Municipio municipio = new Municipio();
                municipio.setNome(resultados.getString("nome"));
                municipio.setId(resultados.getInt("id"));
                municipio.setUf(resultados.getString("uf"));

                municipios.add(municipio);


            }
            //fechar a conexao
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return municipios;
    }

        public List<Municipio> listar_todos_municipios (){
            String sql = "SELECT nome FROM municipio;";
            List<Municipio> municipios = new ArrayList<>();

            try {
                //preparar a conexao
                PreparedStatement stmt = conexao.prepareStatement(sql);
                // Executar
                ResultSet resultados = stmt.executeQuery();

                //percorrer os resultados
                while (resultados.next()) {
                   Municipio municipio = new Municipio();
                   municipio.setNome(resultados.getString("nome"));






                    municipios.add(municipio);


                }
                //fechar a conexao
                conexao.close();

            } catch (SQLException e) {
                throw new RuntimeException();
            }

            return municipios;
        }

    public void inserir(Municipio municipio) {
        String sql = "insert into municipio (nome, estado_id) values (?,?)";

        try {
            //Preparar a conexao
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, municipio.getNome());
            stmt.setInt(2, municipio.getEstado_id());

            //executar o camando
            stmt.execute();

            //fechar a conexao
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

        public int retornarIDMunicipio (String municipio){
            String sql = "Select id from municipio where nome = '"+municipio+"';";
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

        public void alterar (Municipio municipio){
            String sql = "update municipio set nome = ?, estado_id = ? where id = ?";

            //preparar a conexao
            try {
                //preparar a conexao
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.setString(1, municipio.getNome());
                stmt.setInt(2, municipio.getEstado_id());
                stmt.setInt(3, municipio.getId());





                stmt.execute();
                System.out.println("Atualizado com sucesso!");


                //fechar a conexao
                conexao.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public void excluir (Municipio municipio){
            String sql = "Delete from municipio where id = ?";

            try {
                PreparedStatement stmt = conexao.prepareStatement(sql);

                stmt.setInt(1, municipio.getId());

                stmt.execute();

                conexao.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        }

