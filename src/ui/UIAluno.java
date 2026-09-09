package ui;

import modelo.*;
import negocio.Fachada;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class UIAluno {

    private Scanner scan;
    private Fachada fachada;

    public UIAluno(Fachada fachada) {
        this.scan = new Scanner(System.in);
        this.fachada = fachada;
    }

    public void exibir() {

        int op;

        do {
            System.out.println("\n--- ALUNOS ---");
            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Alterar aluno");
            System.out.println("3 - Excluir aluno");
            System.out.println("4 - Listar alunos");
            System.out.println();
            System.out.println("5 - Listar alunos por categoria");
            System.out.println("6 - Listar alunos por escola");
            System.out.println("7 - Listar alunos na faixa de risco");
            System.out.println();
            System.out.println("8 - Realizar avaliação");
            System.out.println("0 - Voltar");

            op = scan.nextInt();

            switch (op) {

                case 1:
                    add();
                    break;

                case 2:
                    alterar();
                    break;

                case 3:
                    excluir();
                    break;

                case 4:
                    listarAluno(fachada.listarAlunos());
                    break;

                case 5:
                    listarAluno(fachada.listarPorCategoria(listarAlunoPorCategoria()));
                    break;

                case 6:
                    listarAluno(fachada.listarPorEscola(listarAlunoPorEscola()));
                    break;

                case 7:
                    listarAluno(fachada.listarFaixaDeRisco());
                    break;

                case 8:
                    avaliacao();
                    break;

                case 0:
                    System.out.println("Voltando...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (op != 0);
    }

    public void add(){
        System.out.println("Escreva o nome do aluno:");
        String nome = scan.next();

        System.out.println("Escreva a data de nascimento do aluno");
        System.out.print("Dia: ");
        int dia = scan.nextInt();
        scan.nextLine();

        System.out.print("Mês: ");
        int mes = scan.nextInt();
        scan.nextLine();

        System.out.println("Ano");
        int ano = scan.nextInt();
        scan.nextLine();

        LocalDate data = LocalDate.of(ano, mes, dia);

        System.out.println("Selecione o sexo do aluno");
        System.out.println("1- Masculino   |   2- Feminino");
        int cod = scan.nextInt();
        scan.nextLine();

        while (cod < 1 || cod > 2) {
            System.out.println("Escreva um valor válido");
            cod = scan.nextInt();
        }

        Sexo sexo = Sexo.selecionar(cod);

        System.out.print("Escreva a altura do aluno: ");
        double altura = scan.nextDouble();
        scan.nextLine();

        System.out.println("Escreva o peso do aluno");
        double peso = scan.nextDouble();
        scan.nextLine();

        Escola escola = null;

        listarEscola(fachada.listarEscola());

        while (escola == null) {
            System.out.println("Digite o código da escola");
            int codigo = scan.nextInt();
            scan.nextLine();
            escola = fachada.buscarEscola(codigo);
            if (escola == null) {
                System.out.println("A escola não foi encontrada");
            }
        }

        Aluno aluno = new Aluno(nome, data, sexo, peso, altura, escola);

        if (fachada.cadastrarAluno(aluno)){
            Avaliacao avaliacao = new Avaliacao(data, aluno.getId(), peso, altura);
            if (fachada.avaliacao(avaliacao));
            {
                System.out.println("Cadastro realizado com sucesso");
            }
        } else {
            System.out.println("Erro ao realizar o cadastro");
        }
    }

    public void alterar(){
        listarAluno(fachada.listarAlunos());

        Aluno alunoAlterado = null;
        while (alunoAlterado == null) {
            System.out.println("Escolha um aluno");
            int cod = scan.nextInt();
            alunoAlterado = fachada.buscarAluno(cod);
            if (alunoAlterado == null) {
                System.out.println("O aluno não foi encontrado!");
            }
        }

        int op;
        System.out.println("Escolha o que você deseja alterar");
        System.out.println("1- Nome do aluno");
        System.out.println("2- Escola do aluno");
        System.out.println("3- Altura do aluno");
        System.out.println("4- Peso");

        do {
            op = scan.nextInt();
            switch (op) {
                case 1:
                    alunoAlterado.setNome(scan.next());

                    break;

                case 2:
                    Escola escola = null;
                        listarEscola(fachada.listarEscola());

                        while (escola == null) {
                            System.out.println("Digite o código da escola");
                            int codigo = scan.nextInt();
                            escola = fachada.buscarEscola(codigo);
                            if (escola == null) {
                                System.out.println("A escola não foi encontrada");
                            }
                        }

                    alunoAlterado.setEscola(escola);
                    break;

                case 3:
                    alunoAlterado.setAltura(scan.nextDouble());
                    break;

                case 4:
                    alunoAlterado.setPeso(scan.nextDouble());
                    break;

                default:
                    System.out.println("Escreva um valor válido");
            }
        } while (op < 1 || op > 4);

            if (fachada.alterarAluno(alunoAlterado)) {
                System.out.println("Alteração realizada com sucesso");
            } else {
                System.out.println("Erro ao alterar");
            }


    }

    public void excluir(){
            listarAluno(fachada.listarAlunos());

        Aluno aluno = null;
            while (aluno == null) {
                System.out.println("Escolha um aluno");
                int cod = scan.nextInt();
                aluno = fachada.buscarAluno(cod);
                if (aluno == null) {
                    System.out.println("O aluno não foi encontrado!");
                }
            }

            if (!fachada.removerAluno(aluno)) {
                System.out.println("Aluno excluido com sucesso");
            } else {
                System.out.println("Erro ao excluir");
            }
    }

    public void listarAluno(ArrayList<Aluno> arr){
        if (arr == null || arr.isEmpty()) {
            System.out.println("Não existem alunos cadastrados");
            return;
        }

        System.out.printf("%-8s %-15s %-20s %-15s %-10s %-10s %-10s %-15s\n",
                "Código", "Nome", "Escola", "Sexo","Altura", "Peso", "IMC", "Classificação");
        for (Aluno a: arr) {
            System.out.printf("%-8d %-15s %-20s %-15s %-10s %-10s %-10s %-15s\n", a.getId(), a.getNome(), a.getEscola().getNome(),
            a.getNome() ,a.getAltura(), a.getPeso(), a.getImc(), a.getClassificacao());
        }
    }

    public Classificacao listarAlunoPorCategoria(){
        System.out.println("Escolha a categoria");
        System.out.println("1- Magreza");
        System.out.println("2- Normal");
        System.out.println("3- Sobrepeso");
        System.out.println("4- Obesidade");
        System.out.println("5- Obesidade grave");

        int op;

        do {
            op = scan.nextInt();
            switch (op) {
                case 1:
                    return Classificacao.MAGREZA;

                case 2:
                    return Classificacao.NORMAL;

                case 3:
                    return Classificacao.SOBREPESO;

                case 4:
                    return Classificacao.OBESIDADE;

                case 5:
                    return Classificacao.OBESIDADE_GRAVE;



                default:
                    System.out.println("Escreva um valor válido");
            }
        } while (true);
    }

    public int listarAlunoPorEscola(){
        Escola escola = null;
            listarEscola(fachada.listarEscola());

            while (escola == null) {
                System.out.println("Digite o código da escola");
                int codigo = scan.nextInt();
                escola = fachada.buscarEscola(codigo);
                if (escola == null) {
                    System.out.println("A escola não foi encontrada");
                }
            }

        return escola.getId();
    }

    public void listarEscola(ArrayList<Escola> arr){
        System.out.printf("%-8s %-15s \n",
                "Código", "Nome");
        for (Escola e: arr) {
            System.out.printf("%-8d %-15s \n", e.getId(), e.getNome());
        }

    }

    public void avaliacao(){
        System.out.println("Escreva a data da avaliação");
        System.out.print("Dia: ");
        int dia = scan.nextInt();
        scan.nextLine();

        System.out.print("Mês: ");
        int mes = scan.nextInt();
        scan.nextLine();

        System.out.println("Ano");
        int ano = scan.nextInt();
        scan.nextLine();

        LocalDate data = LocalDate.of(ano, mes, dia);

        Aluno aluno = null;

        listarAluno(fachada.listarAlunos());

        while (aluno == null) {
            System.out.println("Digite o código da aluno");
            int codigo = scan.nextInt();
            scan.nextLine();
            aluno = fachada.buscarAluno(codigo);
            if (aluno == null) {
                System.out.println("O aluno não foi encontrado");
            }
        }

        System.out.println("Escreva o peso atual do aluno");
        double peso = scan.nextDouble();
        scan.nextLine();

        System.out.println("Escreva a altura atual do aluno");
        double altura = scan.nextDouble();
        scan.nextLine();

        Avaliacao avaliacao = new Avaliacao(data, aluno.getId(), peso, altura);

        if (fachada.avaliacao(avaliacao)){
            System.out.println("Avaliação registrada!");
        } else {
            System.out.println("Erro ao registrar avaliação!");
        }
    }
}
