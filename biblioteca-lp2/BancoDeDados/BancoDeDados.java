package BancoDeDados;


import Biblioteca.Biblioteca;
import Contas.Funcionario;
import Contas.Usuario;
import Item.Livro;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class BancoDeDados {
    private static final String ARQUIVO_USUARIOS = "usuarios.txt";
    private static final String ARQUIVO_LIVROS = "livros.txt";

    public static void cadastrarUsuario(String usuario, String senha, String cpf) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_USUARIOS, true));
            writer.write(usuario + "," + senha + "," + cpf);
            writer.newLine();
            writer.close();
            System.out.println("Usuário cadastrado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao cadastrar usuário.");
            e.printStackTrace();
        }
    }

    public static void cadastrarLivro(String nome, String autor, Date dataPublicacao, int quantidade) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_LIVROS, true));
            writer.write(nome + "," + autor + "," + dataPublicacao + "," + quantidade);
            writer.newLine();
            writer.close();
            System.out.println("Livro cadastrado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao cadastrar Livro.");
            e.printStackTrace();
        }
    }

    public static void removerLivro(String nomeLivro) {
        try {
            File arquivoTemporario = new File(ARQUIVO_LIVROS);
            BufferedReader leitor = new BufferedReader(new FileReader(ARQUIVO_LIVROS));
            BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivoTemporario));

            String linha;

            while ((linha = leitor.readLine()) != null) {
                String[] partes = linha.split(",");
                String nome = partes[0].trim();
                if (!nome.equalsIgnoreCase(nomeLivro)) {
                    escritor.write(linha);
                    escritor.newLine();
                }
            }

            leitor.close();
            escritor.close();

            File arquivoOriginal = new File(ARQUIVO_LIVROS);
            arquivoTemporario.renameTo(arquivoOriginal);

            System.out.println("Linha removida com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao remover a linha: " + e.getMessage());
        }
    }

    public static void carregarUsuarios() {
        Biblioteca biblioteca = Biblioteca.getBiblioteca();
        biblioteca.funcionarios.add(new Funcionario("admin", "admin", "admin", true));
        String nomeArquivo = "usuarios.txt"; // Nome do arquivo a ser lido
        try {
            File arquivo = new File(nomeArquivo);
            Scanner scanner = new Scanner(arquivo);

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] partes = linha.split(",");

                if (partes.length == 3) {
                    String usuario = partes[0].trim();
                    String senha = partes[1].trim();
                    String cpf = partes[2].trim();

                    Biblioteca.biblioteca.usuarios.add(new Usuario(senha, usuario, cpf, false));
                } else {
                    System.out.println("Usuário não pode ser carregado na linha: " + linha);
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Não foi possível carregar os usuários");
        }
    }

    public static void carregarLivros() {
        Biblioteca biblioteca = Biblioteca.getBiblioteca();
        String nomeArquivo = "livros.txt"; // Nome do arquivo a ser lido
        try {
            File arquivo = new File(nomeArquivo);
            Scanner scanner = new Scanner(arquivo);

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] partes = linha.split(",");

                if (partes.length == 4) {
                    String nome = partes[0].trim();
                    String autor = partes[1].trim();
                    String data = partes[2].trim();
                    String quantidade = partes[3].trim();
                    DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                    Date dataReal = formato.parse(data);
                    Biblioteca.biblioteca.estoque.adicionarNovoLivro(new Livro(nome, autor, dataReal, new Integer(quantidade)));
                    System.out.println("livro adicionado");
                } else {
                    System.out.println("Livro não pode ser carregado na linha: " + linha);
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Não foi possível carregar os livros");
        }
    }

    public void deletarUsuario(String usuario) {
    }
}
