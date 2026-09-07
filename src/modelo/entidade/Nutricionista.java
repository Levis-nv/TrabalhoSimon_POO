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

    public void setNome(String nome) {
        if (nome != null && !nome.isEmpty()) {
            this.nome = nome;
        }
    }
    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        if (nomeUsuario != null && !nomeUsuario.isEmpty()) {
        this.nomeUsuario = nomeUsuario;
    }
    }
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (senha != null && !senha.isEmpty()) {
            this.senha = senha;
        }
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
