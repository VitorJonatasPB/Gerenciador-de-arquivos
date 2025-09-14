package GerenciadorDeArquivos;

import java.util.Scanner;

public class Main{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GerenciadorDeArquivos ga = new GerenciadorDeArquivos("Gerenciador");

        int op;
        do {
            System.out.println("\u001B[32m==== Gerenciador de Arquivos ====\u001B[0m");
            System.out.println("1) Criar Arquivos");
            System.out.println("2) Listar Aquivo");
            System.out.println("3) Ler Arquivo");
            System.out.println("4) Renomear Arquivo");
            System.out.println("5) Excluir Arquivo");
            System.out.println("6) Pesquisar em Arquivo");
            System.out.println("0) Sair");
            
            System.out.print("Escolha a opção desejada: ");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> ga.criarArquivo(sc);
                case 2 -> ga.listarArquivos();
                case 3 -> ga.lerArquivo(sc);
                case 4 -> ga.renomearArquivo(sc);
                case 5 -> ga.excluirArquivo(sc);
                case 6 -> ga.buscarPalavra(sc);
                case 0 -> System.out.println("Encerrando programa...");
                default -> System.out.println("Opção inválida!");
            }

        } while (op != 0);

        sc.close();
    }

}
