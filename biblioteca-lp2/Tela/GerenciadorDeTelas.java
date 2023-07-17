package Tela;

import javax.swing.*;
import java.awt.*;

public class GerenciadorDeTelas {
    private static GerenciadorDeTelas gerenciadorDeTelas;
    private JPanel gerenciadorDePainel;
    private CardLayout gerenciadorDelayout;

    public static GerenciadorDeTelas getGerenciadorDeTelas() {
        if (gerenciadorDeTelas == null) {
            gerenciadorDeTelas = new GerenciadorDeTelas();
        }

        return gerenciadorDeTelas;
    }

    public JPanel getGerenciadorDePainel() {
        return gerenciadorDePainel;
    }

    public CardLayout getGerenciadorDelayout() {
        return gerenciadorDelayout;
    }

    public void tela () {
        JFrame frame = new JFrame();
        frame.setTitle("Bem vindo a biblioteca");
        frame.setVisible(true);
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);

        gerenciadorDelayout = new CardLayout();
        gerenciadorDePainel = new JPanel(gerenciadorDelayout);

        JPanel painelDeLogin = telaDeLogin();
        JPanel painelDeRegistroDeUsuario = telaDeRegistroDeUsuario();
        JPanel painelPrincipal = telaPrincipal();
        JPanel painelDeEstoque = telaDeEstoque();
        JPanel painelDeEmprestimo = telaDeEmprestimo();
        JPanel painelDeDevolucao = telaDeDevolucao();
        JPanel painelDeListarItens = telaDeListarItens();
        JPanel painelDePendencias = telaDePendencias();
        JPanel painelDeCriarItemNoEstoque = telaDeCriarItemNoEstoque();
        JPanel painelDeExcluirItemNoEstoque = telaDeExcluirItemNoEstoque();
        JPanel painelDeAtualizarItemNoEstoque = telaDeAtualizarItemNoEstoque();

        gerenciadorDePainel.add(painelDeLogin, "login");
        gerenciadorDePainel.add(painelDeRegistroDeUsuario, "registroDeUsuario");
        gerenciadorDePainel.add(painelPrincipal, "telaPrincipal");
        gerenciadorDePainel.add(painelDeEstoque, "estoque");
        gerenciadorDePainel.add(painelDeEmprestimo, "emprestimo");
        gerenciadorDePainel.add(painelDeDevolucao, "devolucaoDeItem");
        gerenciadorDePainel.add(painelDeListarItens, "listaDeItens");
        gerenciadorDePainel.add(painelDePendencias, "listaDePendencias");
        gerenciadorDePainel.add(painelDeCriarItemNoEstoque, "criarItemNoEstoque");
        gerenciadorDePainel.add(painelDeExcluirItemNoEstoque, "excluirItemNoEstoque");
        gerenciadorDePainel.add(painelDeAtualizarItemNoEstoque, "atualizarItemNoEstoque");

        frame.setContentPane(gerenciadorDePainel);
        frame.setVisible(true);
    }

    private JPanel telaDeLogin () {
        return new TelaDeLogin();
    }

    private JPanel telaDeRegistroDeUsuario () {
        return new TelaDeRegistroDeUsuario();
    }

    private JPanel telaPrincipal () {
        return new TelaPrincipal();
    }

    private JPanel telaDeEstoque () {
        return new TelaDeEstoque();
    }

    private JPanel  telaDeEmprestimo () {
        return new TelaDeEmprestimo(null);
    }

    private JPanel telaDeDevolucao () {
        return new TelaDeDevolucao();
    }
    private JPanel telaDeListarItens () {
        return new TelaDeListarItens();
    }
    private JPanel telaDePendencias () {
        return new TelaDePendencias();
    }

    private JPanel telaDeCriarItemNoEstoque () { return new TelaDeCriarItemNoEstoque(); }
    private JPanel telaDeExcluirItemNoEstoque () { return new TelaDeExcluirItemDoEstoque(); }
    private JPanel telaDeAtualizarItemNoEstoque () { return new TelaDeAtualizarQuantidadeDeItemNoEstoque(); }
}
