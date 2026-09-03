package simon.modelo.entidade;

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
        return this.nome;
    }

    public int getIdade(){
        return this.idade;
    }

    public Sexo getSexo() {
        return this.sexo;
    }

    public double getPeso() {
        return this.peso;
    }

    public double getAltura() {
        return this.altura;
    }

    public Escola getEscola() {
        return escola;
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
