package model;

public class Editora {
private int id, municipio_id;
private String nome;
    private String endereco;
    private String site;
    private String bairro;
    private String telefone;
    private String nomeMunicipio;
    public Editora (){

    }
    public Editora (int id, String nome,String endereco, String site, String bairro, String telefone, String nomeMunicipio ){
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.site = site;
        this.bairro = bairro;
        this.telefone = telefone;
        this.nomeMunicipio = nomeMunicipio;
    }

    public String getNomeMunicipio() {
        return nomeMunicipio;
    }

    public void setNomeMunicipio(String nomeMunicipio) {
        this.nomeMunicipio = nomeMunicipio;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMunicipio_id() {
        return municipio_id;
    }

    public void setMunicipio_id(int municipio_id) {
        this.municipio_id = municipio_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
