package simon.modelo.entidade;

import java.io.Serializable;

public class Escola implements Serializable {
    private int id;
    private String nome;

    public Escola(String nome){
        if(nome != null && !nome.isEmpty()){
            this.nome = nome;
        }
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
}
