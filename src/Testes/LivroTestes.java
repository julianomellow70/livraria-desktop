package Testes;

import dao.LivroDAO;
import model.Livro;

import java.util.List;

public class LivroTestes {
    public static void main(String[] args) {
       Livro l = new Livro() ;
        l.setData_lancamento("2018-02-10");
        l.setEditora_id(1);
        l.setPreco((float) 10.50);
        l.setQtde(10);
        l.setTitulo("Narnia");
        l.setId(2);

        LivroDAO ld = new LivroDAO();
        ld.alterar(l);

       /* LivroDAO livrodao = new LivroDAO();
       List<Livro> l = livrodao.listarTodos();
        for (Livro livro : l){
            System.out.println(livro.getQtde());
        }
        */

    }
}
