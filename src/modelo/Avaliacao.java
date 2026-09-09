package modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Avaliacao implements Serializable {
    private int id;
    private LocalDate data;
    private int idAluno;
    private double peso;
    private double altura;
    private double imc;
    private Classificacao classificacao;

    public Avaliacao(LocalDate data, int idAluno, double peso, double altura){
        if(data != null && idAluno >= 0 && peso >= 0 && altura >= 0) {
            this.data = data;
            this.idAluno = idAluno;
            this.peso = peso;
            this.altura = altura;
            this.imc = calcularIMC(altura, peso);
            this.classificacao = Classificacao.gerarClassificacao(this.imc);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        if(id >= 0){
            this.id = id;
        }
    }

    public LocalDate getData() {
        return data;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public double getPeso() {
        return peso;
    }

    public double getAltura() {
        return altura;
    }

    public double getImc() {
        return imc;
    }

    private double calcularIMC(double altura, double peso){
        return peso/(altura * altura);
    }

    public Classificacao getClassificacao() {
        return classificacao;
    }
}
