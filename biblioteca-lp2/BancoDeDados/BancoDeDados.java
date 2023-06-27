package BancoDeDados;


import Biblioteca.Biblioteca;
import Contas.Usuario;

import java.io.*;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class BancoDeDados {
    private static final String ARQUIVO_USUARIOS = "usuarios.txt";

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

    public static void carregarUsuarios(String nomeArquivo) throws FileNotFoundException {
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
    }

    public void deletarUsuario(String usuario) {
    }
}
