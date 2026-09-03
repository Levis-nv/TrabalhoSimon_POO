package modelo.entidade;

import java.io.Serializable;
import java.util.Date;

public class Avaliacao implements Serializable {
    private int id;
    private Date data;
    private int idAluno;
    private double peso;
    private double altura;
    private double imc;

    public Avaliacao(Date data, int idAluno, double peso, double altura){
        if(data != null && idAluno >= 0 && peso >= 0 && altura >= 0) {
            this.data = data;
            this.idAluno = idAluno;
            this.peso = peso;
            this.altura = altura;
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

    public Date getData() {
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
}
