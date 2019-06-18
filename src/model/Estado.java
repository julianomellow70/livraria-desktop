package model;

public class Estado {
    private int id;
    private String UF;

    public Estado (){
    }
    public Estado (int id, String UF){
        this.id = id;
        this.UF = UF;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }
}
