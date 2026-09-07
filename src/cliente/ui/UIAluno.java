package cliente.ui;

import modelo.entidade.Aluno;
import modelo.entidade.Classificacao;
import modelo.entidade.Escola;
import modelo.entidade.Sexo;
import cliente.fachada.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class UIAluno {

    private Scanner scan;
    private FachadaCliente fachada;

    public UIAluno(FachadaCliente fachada) {
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
            System.out.println("5 - Listar alunos por categoria");
            System.out.println("6 - Listar alunos por escola");
            System.out.println("7 - Listar alunos na faixa de risco");
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
                    try {
                        listarAluno(fachada.listarAluno());
                    } catch (IOException e) {
                        System.out.println("Erro na comunicação com o servidor");

                    } catch (ClassNotFoundException e) {
                        System.out.println("Erro ao receber a resposta do servidor");

                    }
                    break;

                case 5:
                    try {
                        listarAluno(fachada.listarAlunoCategoria(listarAlunoPorCategoria()));
                    } catch (IOException e) {
                        System.out.println("Erro na comunicação com o servidor");

                    } catch (ClassNotFoundException e) {
                        System.out.println("Erro ao receber a resposta do servidor");

                    }
                    break;

                case 6:
                    try {
                        listarAluno(fachada.listarAlunoEscola(listarAlunoPorEscola()));
                    } catch (IOException e) {
                        System.out.println("Erro na comunicação com o servidor");

                    } catch (ClassNotFoundException e) {
                        System.out.println("Erro ao receber a resposta do servidor");

                    }
                    break;

                case 7:
                    try {
                        listarAluno(fachada.listarAlunoRisco());
                    } catch (IOException e) {
                        System.out.println("Erro na comunicação com o servidor");

                    } catch (ClassNotFoundException e) {
                        System.out.println("Erro ao receber a resposta do servidor");

                    }
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

        Sexo sexo = Sexo.selecionar(cod);

        System.out.print("Escreva a altura do aluno: ");
        double altura = scan.nextDouble();

        System.out.println("Escreva o peso do aluno");
        double peso = scan.nextDouble();

        Escola escola = null;

        try{
            listarEscola(fachada.listarEscola());

            while (escola == null) {
                System.out.println("Digite o código da escola");
                int codigo = scan.nextInt();
                escola = fachada.buscarEscola(codigo);
                if (escola == null) {
                    System.out.println("A escola não foi encontrada");
                }
            }
        } catch (IOException e) {
            System.out.println("Erro na comunicação com o servidor");
            return;

        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao receber a resposta do servidor");
            return;

        }

        Aluno aluno = new Aluno(nome, data, sexo, altura, peso, escola);

        try {
            String resposta = fachada.cadastrarAluno(aluno);

            System.out.println(resposta);
        } catch (IOException e) {
            System.out.println("Erro na comunicação com o servidor");

        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao receber a resposta do servidor");

        }

    }

    public void alterar(){
        try {
            listarAluno(fachada.listarAluno());
        } catch (IOException e) {
            System.out.println("Erro na comunicação com o servidor");
            return;

        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao receber a resposta do servidor");
            return;

        }

        Aluno alunoAlterado = null;
        try {
            while (alunoAlterado == null) {
                System.out.println("Escolha um aluno");
                int cod = scan.nextInt();
                alunoAlterado = fachada.buscarAluno(cod);
                if (alunoAlterado == null) {
                    System.out.println("O aluno não foi encontrado!");
                }
            }
        } catch (IOException e) {
            System.out.println("Erro na comunicação com o servidor");
            return;

        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao receber a resposta do servidor");
            return;

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
                    try{
                        listarEscola(fachada.listarEscola());

                        while (escola == null) {
                            System.out.println("Digite o código da escola");
                            int codigo = scan.nextInt();
                            escola = fachada.buscarEscola(codigo);
                            if (escola == null) {
                                System.out.println("A escola não foi encontrada");
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Erro na comunicação com o servidor");
                        return;

                    } catch (ClassNotFoundException e) {
                        System.out.println("Erro ao receber a resposta do servidor");
                        return;

                    }

                    alunoAlterado.setEscola(escola);
                    break;

                case 3:
                    alunoAlterado.setAltura(scan.nextInt());
                    break;

                case 4:
                    alunoAlterado.setPeso(scan.nextInt());
                    break;


                default:
                    System.out.println("Escreva um valor válido");
            }
        } while (op < 1 || op > 4);

        try {
            String resposta = fachada.alterarAluno(alunoAlterado);
            System.out.println(resposta);

        } catch (IOException e) {
            System.out.println("Erro na comunicação com o servidor");
            return;

        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao receber a resposta do servidor");
            return;

        }


    }

    public void excluir(){
        try {
            listarAluno(fachada.listarAluno());
        } catch (IOException e) {
            System.out.println("Erro na comunicação com o servidor");
            return;

        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao receber a resposta do servidor");
            return;

        }

        Aluno aluno = null;
        try {
            while (aluno == null) {
                System.out.println("Escolha um aluno");
                int cod = scan.nextInt();
                aluno = fachada.buscarAluno(cod);
                if (aluno == null) {
                    System.out.println("O aluno não foi encontrado!");
                }
            }
        } catch (IOException e) {
            System.out.println("Erro na comunicação com o servidor");
            return;

        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao receber a resposta do servidor");
            return;

        }

        try {
            String resposta = fachada.excluirAluno(aluno.getId());

            System.out.println(resposta);
        } catch (IOException e) {
            System.out.println("Erro na comunicação com o servidor");

        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao receber a resposta do servidor");

        }
    }

    public void listarAluno(ArrayList<Aluno> arr){
        System.out.printf("%-8s %-15s %-20s %-15s %-10s %-10s %-10s %-15s\n",
                "Código", "Nome", "Escola", "Sexo","Altura", "Peso", "IMC", "Classificação");
        for (Aluno a: arr) {
            System.out.printf("%-8d %-15s %-20s %-15s %-6s %-6s %-10s %-15s\n", a.getId(), a.getNome(), a.getEscola().getNome(),
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
        try{
            listarEscola(fachada.listarEscola());

            while (escola == null) {
                System.out.println("Digite o código da escola");
                int codigo = scan.nextInt();
                escola = fachada.buscarEscola(codigo);
                if (escola == null) {
                    System.out.println("A escola não foi encontrada");
                }
            }
        } catch (IOException e) {
            System.out.println("Erro na comunicação com o servidor");
            return -1;

        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao receber a resposta do servidor");
            return -1;

        }

        try{
            listarEscola(fachada.listarEscola());

            while (escola == null) {
                System.out.println("Digite o código da escola");
                int codigo = scan.nextInt();
                escola = fachada.buscarEscola(codigo);
                if (escola == null) {
                    System.out.println("A escola não foi encontrada");
                }
            }
        } catch (IOException e) {
            System.out.println("Erro na comunicação com o servidor");
            return -1;

        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao receber a resposta do servidor");
            return -1;

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
}
