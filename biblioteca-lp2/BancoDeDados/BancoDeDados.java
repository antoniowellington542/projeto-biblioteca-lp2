package BancoDeDados;


import Biblioteca.Biblioteca;
import Contas.Funcionario;
import Contas.Usuario;
import Emprestimo.Emprestimo;
import Item.Item;
import Item.Livro;
import Utilidades.UtilitarioDeData;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.FileNotFoundException;

public class BancoDeDados {
    private static final String ARQUIVO_USUARIOS = "usuarios.txt";
    private static final String ARQUIVO_LIVROS = "livros.txt";

    private static final String ARQUIVO_EMPRESTIMOS = "emprestimos.txt";

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
            UtilitarioDeData utilitarioDeData = new UtilitarioDeData();
            String dataReal = utilitarioDeData.convertDateFormat(dataPublicacao);

            BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_LIVROS, true));
            writer.write(nome + "," + autor + "," + dataReal + "," + quantidade);
            writer.newLine();
            writer.close();
            System.out.println("Livro cadastrado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao cadastrar Livro.");
            e.printStackTrace();
        }
    }

    public static void MudarQuantidadeLivro(Long livroId, Integer quantidadeMudar, boolean aumentar) {
        try {
            Item livro = Biblioteca.biblioteca.estoque.getItemPorId(livroId);
            if(livro.getId() != null) {
                File arquivoTexto = new File(ARQUIVO_LIVROS);
                BufferedReader br = new BufferedReader(new FileReader(arquivoTexto));
                StringBuilder sb = new StringBuilder();
                String linha;
                while ((linha = br.readLine()) != null) {
                    String[] partes = linha.split(",");

                    if (partes.length >= 4 && partes[0].equals(livro.getNome())) {
                        int quantidade = Integer.parseInt(partes[3].trim());

                        if (aumentar) {
                            quantidade+=quantidadeMudar;
                        } else {
                            quantidade = Math.max(0, quantidade - (quantidadeMudar));
                        }

                        partes[3] = String.valueOf(quantidade);
                    }

                    sb.append(String.join(",", partes)).append(System.lineSeparator());
                }

                br.close();

                BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoTexto));
                bw.write(sb.toString());
                bw.close();

                System.out.println("Quantidade atualizada com sucesso.");
            }else{
                System.out.println("Erro ao cadastrar Livro.");
            }
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
        String nomeArquivo = ARQUIVO_USUARIOS; // Nome do arquivo a ser lido
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
        try {
            File arquivo = new File(ARQUIVO_LIVROS);
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
                    Biblioteca.biblioteca.estoque.adicionarNovoLivro(new Livro(nome, autor, dataReal, Integer.parseInt(quantidade)));
                    System.out.println("livro adicionado");
                } else {
                    System.out.println("Livro não pode ser carregado na linha: " + linha);
                }
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Não foi possível carregar os livros");
        }
    }

    public static void carregarEmprestimos() {
        Biblioteca biblioteca = Biblioteca.getBiblioteca();
        String nomeArquivo = ARQUIVO_EMPRESTIMOS; // Nome do arquivo a ser lido
        try {
            File arquivo = new File(nomeArquivo);
            Scanner scanner = new Scanner(arquivo);

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] partes = linha.split(",");

                if (partes.length == 3) {
                    String CPF = partes[0].trim();
                    String livroId = partes[1].trim();
                    String data = partes[2].trim();
                    DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                    Date dataReal = formato.parse(data);
                    Biblioteca.biblioteca.emprestimos.add(new Emprestimo(CPF, livroId, dataReal));
                    System.out.println("Empréstimo adicionado");
                } else {
                    System.out.println("Empréstimo não pode ser carregado na linha: " + linha);
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Não foi possível carregar os empréstimos");
        }
    }

    public static void cadastrarEmprestimo(String CPF, Long livroId, String data) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_EMPRESTIMOS, true));
            writer.write(CPF + "," + livroId + "," + data);
            writer.newLine();
            writer.close();
            System.out.println("Empréstimo cadastrado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao cadastrar Empréstimo.");
            e.printStackTrace();
        }
    }

    public static void removerEmprestimo(String CPF, Long livroId) {
        try {
            File arquivoTemporario = new File(ARQUIVO_EMPRESTIMOS);
            BufferedReader leitor = new BufferedReader(new FileReader(ARQUIVO_EMPRESTIMOS));
            BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivoTemporario));

            String linha;

            while ((linha = leitor.readLine()) != null) {
                String[] partes = linha.split(",");
                String cpfLinha = partes[0].trim();
                Long livroIdLinha = Long.parseLong(partes[1].trim());
                if (!cpfLinha.equalsIgnoreCase(CPF) || !livroIdLinha.equals(livroId)) {
                    escritor.write(linha);
                    escritor.newLine();
                }
            }

            leitor.close();
            escritor.close();

            File arquivoOriginal = new File(ARQUIVO_EMPRESTIMOS);
            arquivoTemporario.renameTo(arquivoOriginal);

            System.out.println("Linha removida com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao remover a linha: " + e.getMessage());
        }
    }

    public void deletarUsuario(String usuario) {
    }
}
