import BancoDeDados.BancoDeDados;
import Tela.GerenciadorDeTelas;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        String nomeArquivo = "usuarios.txt"; // Nome do arquivo a ser lido
        try {
            BancoDeDados.carregarUsuarios(nomeArquivo);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de usuários não encontrado.");
        }
        GerenciadorDeTelas.getGerenciadorDeTelas().tela();
    }
}
