package GerenciadorDeArquivos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;



public class GerenciadorDeArquivos {

    private File pasta;
    private File log;

    public GerenciadorDeArquivos(String nomePasta){
        this.pasta = new File(nomePasta);
        if (!pasta.exists()) pasta.mkdir();
        this.log = new File(pasta,"log.txt");
    }

    private void registrarLog(String acao){
        try (FileWriter fw = new FileWriter(log, true)){
            String hora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
            fw.write("[" + hora + "]" + acao + "\n" );
        } catch (IOException e) {
            System.out.println("Erro ao registrar log");
        }
    }

    //métdodo da opção criaArquivo
    public void criarArquivo(Scanner sc){
        try{
            System.out.print("Digite o nome do arquivo: ");
            String nome = sc.nextLine();
            File novoArquivo = new File(pasta, nome+".txt");

            if(novoArquivo.createNewFile()){
                System.out.println("\u001B[32mArquivo criado com sucesso!\u001B[0m");
                registrarLog("Criado arquivo " + nome + ".txt");
            
                String resposta;

                do {
                    System.out.println("Deseja escrever algo nele? (s/n)");
                    resposta = sc.nextLine();
                    
                    if(!resposta.equalsIgnoreCase("s") && !resposta.equalsIgnoreCase("n")){
                        System.out.println("\u001B[31mOpção inválida! Digite apenas 's' ou 'n'.\u001B[0m");
                    }
                } while (!resposta.equalsIgnoreCase("s") && !resposta.equalsIgnoreCase("n"));

                if(resposta.equalsIgnoreCase("s")){
                    FileWriter fw = new FileWriter(novoArquivo);
                    System.out.println("Escreva o conteúdo (digite \"fim\" para terminar):");
                    String conteudo = sc.nextLine();
                    while (!(conteudo.equalsIgnoreCase("fim"))) {
                    fw.write(conteudo + "\n");
                    conteudo = sc.nextLine();
                    }
                    fw.close();
                }
            } else {
                System.out.println("Arquivo já existe!");
            }
        } catch(IOException e){
            System.out.println("Erro ao criar arquivo: " + e.getMessage());
        }
    }

    //métdodo da opção listarArquivos
    public void listarArquivos(){
        String[] arquivo = pasta.list();
        if (arquivo != null && arquivo.length > 0){
            System.out.println("\n Arquivos disponíveis:");
            for (String nome : arquivo) {
                System.out.println(" - " + nome);
            }
        } else{
            System.out.println("\n Nenhum arquivos encontrado!");
        }
        registrarLog("Listou arquivos");
    }

    //métdodo da opção lerArquivo
    public void lerArquivo(Scanner sc){
        System.out.print("Digite o nome do arquivo: ");
        String nome = sc.nextLine();
        File arquivo = new File(pasta, nome+".txt");

        if (arquivo.exists()){
            try(Scanner nomeLeitor = new Scanner(arquivo)){
                while ( nomeLeitor.hasNextLine()) {
                    System.out.println(nomeLeitor.nextLine());
                }
                registrarLog("Leu arquivo: "+ nomeLeitor);
            }catch (IOException e) {
                    System.out.println("Erro a ler aquivo: "+e.getMessage());
                }
        }else {
            System.out.println("Arquivo não encontrado!");
        }
    }

    public void renomearArquivo(Scanner sc){
        System.out.print("Digite o nome do arquivo: ");
        String nome = sc.nextLine();
        File arquivo = new File(pasta, nome+".txt");

        if (arquivo.exists()) {
            System.out.print("Novo nome: ");
            String novoNome = sc.nextLine();
            File novoArquivo = new File(pasta, novoNome+".txt");

            if(arquivo.renameTo(novoArquivo)){
                System.out.println("Arquivo renomeado.");
                registrarLog("Renomeou " + nome + " para " + novoNome);
            } else {
                System.out.println("Erro ao renomear.");
            }
        } else {
            System.out.println("Arquivo não encontrado.");
        }
    }

    public void excluirArquivo(Scanner sc){
        System.out.print("Digite o nome do arquivo: ");
        String nome = sc.nextLine();
        File arquivo = new File(pasta, nome+".txt");

        if(arquivo.exists()){
            if (arquivo.delete()){
                System.out.println("Arquivo " + arquivo + "excluído com sucesso!");
                registrarLog("Excluiu arquivo: " + nome);
            }else{
                System.out.println("Erro ao excluir arquivo");
            }
        }else{
            System.out.println("Arquivo não encontrado!");
        }
    }

    public void buscarPalavra(Scanner sc) {
        System.out.print("Nome do arquivo: ");
        String nome = sc.nextLine();
        File arq = new File(pasta, nome);

        if (arq.exists()) {
            System.out.print("Palavra-chave: ");
            String chave = sc.nextLine();

            try (Scanner leitor = new Scanner(arq)) {
                boolean encontrada = false;
                while (leitor.hasNextLine()) {
                    String linha = leitor.nextLine();
                    if (linha.toLowerCase().contains(chave.toLowerCase())) {
                        System.out.println("Encontrada: " + linha);
                        encontrada = true;
                    }
                }
                if (!encontrada) {
                    System.out.println("Palavra não encontrada.");
                }
                registrarLog("Buscou \"" + chave + "\" em " + nome);
            } catch (IOException e) {
                System.out.println("Erro ao buscar.");
            }
        } else {
            System.out.println("Arquivo não encontrado.");
        }
    }


}