package model;

public class Livro {
private int id;
    private int qtde;
    private int editora_id;
    private String titulo;
    private String data_lancamento;
    private float preco;
    private String nome_editora;


    public Livro (){
        this.id = 0;
        this.qtde = 0;
        this.editora_id = 0;
        this.titulo = "";
        this.data_lancamento = "";
        this.preco =  0;
        this.nome_editora = "";

    }
    public Livro (int id, int qtde, String titulo, String data_lancamento, float preco, String nome_editora){
        this.qtde = qtde;
        this.titulo = titulo;
        this.data_lancamento = data_lancamento;
        this.preco = preco;
        this.nome_editora = nome_editora;
        this.id = id;
    }

    public String getNome_editora() {
        return nome_editora;
    }

    public void setNome_editora(String nome_editora) {
        this.nome_editora = nome_editora;
    }

    public int getEditora_id() {
        return editora_id;
    }

    public void setEditora_id(int editora_id) {
        this.editora_id = editora_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getData_lancamento() {
        return data_lancamento;
    }

    public void setData_lancamento(String data_lancamento) {
        this.data_lancamento = data_lancamento;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
}
