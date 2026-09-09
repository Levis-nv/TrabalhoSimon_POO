package modelo;

import java.io.Serializable;

public class Escola implements Serializable {
    private int id;
    private String nome;

    public Escola(String nome){
        if(nome != null && !nome.isEmpty()){
            this.nome = nome;
        }
    }

    public Escola() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if(id >= 0){
            this.id = id;
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome != null && !nome.isEmpty()) {
            this.nome = nome;
        }
    }
}
