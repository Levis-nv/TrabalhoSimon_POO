package simon.cliente.ui;

import simon.modelo.entidade.Aluno;
import simon.modelo.entidade.Sexo;
import simon.fachada.*;

import java.time.LocalDate;
import java.util.Scanner;

public class UIAluno {

    private Scanner scan;

    public UIAluno() {
        this.scan = new Scanner(System.in);
    }

    public void menuListar(){
        System.out.println();
    }

    public void add(){
        System.out.println("Escreva o nome do aluno:");
        String nome =  scan.next();

        System.out.println("Escreva a data de nascimento do aluno");
        System.out.print("Dia: ");
        int dia = scan.nextInt();

        System.out.print("Mês: ");
        int mes = scan.nextInt();

        System.out.println("Ano");
        int ano = scan.nextInt();

        LocalDate data = LocalDate.of(ano, mes, dia);

        System.out.println("Selecione o sexo do aluno");
        System.out.println("1- Masculino   |   2- Feminino");
        int cod = scan.nextInt();
        while (cod < 1 || cod > 2) {
            System.out.println("Escreva um valor válido");
            cod = scan.nextInt();
        }
        Sexo sexo = Sexo.selecionarSexo(cod);

        System.out.print("Escreva a altura do aluno: ");
        double altura = scan.nextDouble();

        System.out.println("Escreva o peso do aluno");
        double peso = scan.nextDouble();

        Aluno aluno = Aluno.getInstance(nome, data, sexo, altura, peso);


    }
}
