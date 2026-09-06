package modelo.entidade;

import java.io.Serializable;

public class Nutricionista implements Serializable {
    private int id;
    private String nome;
    private String nomeUsuario;
    private String senha;

    public Nutricionista(String nome, String nomeUsuario, String senha){
        if(nome != null && !nome.isEmpty() && nomeUsuario != null && !nomeUsuario.isEmpty()
                && senha != null && !senha.isEmpty()){
            this.nome = nome;
            this.nomeUsuario = nomeUsuario;
            this.senha = senha;
        }
    }

    public String getNome() {
        return nome;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id >= 0) {
            this.id = id;
        }
    }
}
