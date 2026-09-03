package modelo.entidade;

import java.io.Serializable;
import java.util.ArrayList;

public class Aluno implements Serializable {
    private int id;
    private String nome;
    private int idade;
    private Sexo sexo;
    private double peso;
    private double altura;
    private Escola escola;
    private double imc;
    private Classificacao classificacao;
    private ArrayList<Avaliacao> avaliacao;

    public Aluno(String nome, int idade, Sexo sexo, double peso, double altura){
        if(nome != null && !nome.isEmpty() && idade > 0 && sexo != null && peso > 0 && altura > 0){
            this.nome = nome;
            this.idade = idade;
            this.sexo = sexo;
            this.peso = peso;
            this.altura = altura;
            this.avaliacao =  new ArrayList<Avaliacao>();
        }
    }

    public int getId() {
        return this.id;
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

    public int getIdade() {
        return this.idade;
    }

    public void setIdade(int idade) {
        if (idade > 0) {
            this.idade = idade;
        }
    }

    public Sexo getSexo() {
        return this.sexo;
    }

    public void setSexo(Sexo sexo) {
        if (sexo != null) {
            this.sexo = sexo;
        }
    }

    public double getPeso() {
        return this.peso;
    }

    public void setPeso(double peso) {
        if (peso > 0) {
            this.peso = peso;
        }
    }

    public double getAltura() {
        return this.altura;
    }

    public void setAltura(double altura) {
        if (altura > 0) {
            this.altura = altura;
        }
    }

    public Escola getEscola() {
        return this.escola;
    }

    public void setEscola(Escola escola) {
        if (escola != null) {
            this.escola = escola;
        }
    }

    public double getImc() {
        return this.imc;
    }

    public void setImc(double imc) {
        if(imc > 0) {
            this.imc = imc;
        }
    }

    public Classificacao getClassificacao() {
        return this.classificacao;
    }

    public void setClassificacao(Classificacao classificacao) {
        if(classificacao != null) {
            this.classificacao = classificacao;
        }
    }

    public ArrayList<Avaliacao> getAvaliacao() {
        return new ArrayList<>(this.avaliacao);
    }

}
