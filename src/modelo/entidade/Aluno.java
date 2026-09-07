package modelo.entidade;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Aluno implements Serializable {
    private int id;
    private String nome;
    private LocalDate dtNasc;
    private Sexo sexo;
    private double peso;
    private double altura;
    private Escola escola;
    private double imc;
    private Classificacao classificacao;
    private ArrayList<Avaliacao> avaliacao;

    public Aluno(String nome, LocalDate dtNasc, Sexo sexo, double peso, double altura, Escola escola){
        if(nome != null && !nome.isEmpty() && dtNasc != null && sexo != null
                && peso > 0 && altura > 0 && escola != null){
            this.nome = nome;
            this.dtNasc = dtNasc;
            this.sexo = sexo;
            this.peso = peso;
            this.altura = altura;
            this.avaliacao =  new ArrayList<Avaliacao>();
        }
    }

    public Aluno() {
        //@kauagames cirei essse construtor vazuo para nao mexer nos construtores principais é passar valores coringa temporários na hora de instanciar,
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
        return Period.between(dtNasc, LocalDate.now()).getYears();
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
