package model;

public class Municipio {
    private  int id, estado_id;
    private String nome, uf;

    public Municipio (){
        this.id = 0;
        this.estado_id = 0;
        this.nome = "";
        this.uf = "";
    }
    public Municipio (int id, String nome, String uf){
        this.id = id;
        this.nome = nome;
        this.uf = uf;
    }



    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstado_id() {
        return estado_id;
    }

    public void setEstado_id(int estado_id) {
        this.estado_id = estado_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
