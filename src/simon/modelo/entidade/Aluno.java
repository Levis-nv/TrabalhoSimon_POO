package simon.modelo.entidade;

import java.io.Serializable;
import java.time.LocalDate;
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

    public Aluno(String nome, LocalDate dtNasc, Sexo sexo, double altura, double peso){
        if(nome != null && !nome.isEmpty() && dtNasc != null && sexo != null && peso > 0 && altura > 0){
            this.nome = nome;
            this.dtNasc = dtNasc;
            this.sexo = sexo;
            this.peso = peso;
            this.altura = altura;
            this.avaliacao =  new ArrayList<Avaliacao>();
        }
    }

    public static Aluno getInstance(String nome, LocalDate dtNasc, Sexo sexo, double altura, double peso) {

        if(nome != null && !nome.isEmpty() && dtNasc != null && sexo != null && altura > 0 && peso > 0){
            return new Aluno(nome, dtNasc, sexo, altura, peso);
        }

        return null;
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

    public LocalDate getDtNasc(){
        return this.dtNasc;
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
