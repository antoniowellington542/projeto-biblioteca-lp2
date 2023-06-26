package BancoDeDados;


import java.io.*;
import java.util.Scanner;

public class BancoDeDados {
    private static final String ARQUIVO_USUARIOS = "usuarios.txt";

    public void cadastrarUsuario (String usuario, String senha, String cpf) {
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

    public void deletarUsuario (String usuario) {}
}
