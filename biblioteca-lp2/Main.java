import java.io.*;
import java.util.*;

public class Main {

  private static final String ARQUIVO_USUARIOS = "usuarios.txt";

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    exibirMenuPrincipal(scanner);
  }

  private static void exibirMenuPrincipal(Scanner scanner) {
    int opcao;
    do {
      System.out.println("1. Cadastro");
      System.out.println("2. Login");
      System.out.println("3. Sair");
      System.out.print("Escolha uma opção: ");
      opcao = scanner.nextInt();

      switch (opcao) {
        case 1:
          cadastrarUsuario(scanner);
          break;
        case 2:
          realizarLogin(scanner);
          break;
        case 3:
          System.out.println("Saindo...");
          break;
        default:
          System.out.println("Opção inválida. Tente novamente.");
          break;
      }
    } while (opcao != 3);
  }

  private static void cadastrarUsuario(Scanner scanner) {
    //TODO aqui iremos inserir na nossa lista de usuários novos
    scanner.nextLine();

    System.out.print("Digite o nome de usuário: ");
    String usuario = scanner.nextLine();
    System.out.print("Digite a senha: ");
    String senha = scanner.nextLine();
    System.out.print("Digite o CPF: ");
    String cpf = scanner.nextLine();

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

  private static void realizarLogin(Scanner scanner) {
    scanner.nextLine();

    System.out.print("Digite o nome de usuário: ");
    String usuario = scanner.nextLine();
    System.out.print("Digite a senha: ");
    String senha = scanner.nextLine();

    try {
      BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_USUARIOS));
      String linha;
      boolean encontrado = false;

      while ((linha = reader.readLine()) != null) {
        String[] dadosUsuario = linha.split(",");

        if (dadosUsuario.length >= 2 && dadosUsuario[0].equals(usuario) && dadosUsuario[1].equals(senha)) {
          encontrado = true;
          break;
        }
      }

      reader.close();

      if (encontrado) {
        //TODO aqui vai mandar pro nosso segundo menu
        System.out.println("Login realizado com sucesso!");
      } else {
        System.out.println("Usuário ou senha inválidos.");
      }
    } catch (IOException e) {
      System.out.println("Erro ao realizar o login.");
      e.printStackTrace();
    }
  }
}
