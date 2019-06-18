package Testes;

import dao.MunicipioDAO;
import model.Municipio;

import java.util.List;

public class MunicipioTeste {
    public static void main(String[] args) {
        MunicipioDAO municipioDAO = new MunicipioDAO();
        List<Municipio> municipios = municipioDAO.listar_todos_municipios_tabela();

       for(Municipio mun : municipios){
            System.out.println(mun.getNome()+mun.getUf());
        }


    }
}
