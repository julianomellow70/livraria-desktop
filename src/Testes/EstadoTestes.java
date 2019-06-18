package Testes;

import dao.EstadoDAO;
import model.Estado;

public class EstadoTestes {

    public static void main(String[] args) {
        EstadoDAO estadoDAO = new EstadoDAO();
        System.out.println(estadoDAO.retornarIDEstado("MT"));
    }
}
