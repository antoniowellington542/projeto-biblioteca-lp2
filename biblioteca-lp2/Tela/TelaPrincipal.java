package Tela;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaPrincipal extends JPanel {
    private final CardLayout cardLayout = GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDelayout();
    public TelaPrincipal () {
        setLayout(new GridLayout(7, 1, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton botaoDeLogout = new JButton("Logout");
        botaoDeLogout.addActionListener(this::logout);
        add(botaoDeLogout);

        JButton botaoDeEstoque = new JButton("Estoque");
        botaoDeEstoque.addActionListener(this::estoque);
        add(botaoDeEstoque);

        JButton botaoDeEmprestimo = new JButton("Empréstimo");
        botaoDeEmprestimo.addActionListener(this::emprestimo);
        add(botaoDeEmprestimo);

        JButton botaoDeDevolucao = new JButton("Devolução");
        botaoDeDevolucao.addActionListener(this::devolucao);
        add(botaoDeDevolucao);

        JButton botaoDeBuscarItem = new JButton("Buscar Item");
        botaoDeBuscarItem.addActionListener(this::buscarItem);
        add(botaoDeBuscarItem);

        JButton botaoDeListarItems = new JButton("Listar Items");
        botaoDeListarItems.addActionListener(this::listarItens);
        add(botaoDeListarItems);

        JButton botaoDeVerificarPendencias = new JButton("Verificar Pendências");
        botaoDeVerificarPendencias.addActionListener(this::verificarPendencias);
        add(botaoDeVerificarPendencias);
    }

    private void logout (ActionEvent e) {
        cardLayout.show(GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDePainel(), "login");
    }

    private void estoque (ActionEvent e) {
        cardLayout.show(GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDePainel(), "estoque");
    }

    private void emprestimo (ActionEvent e) {
        cardLayout.show(GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDePainel(), "emprestimo");
    }

    private void devolucao (ActionEvent e) {
        cardLayout.show(GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDePainel(), "devolucaoDeItem");
    }

    private void buscarItem (ActionEvent e) {
        cardLayout.show(GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDePainel(), "buscaDeItem");
    }

    private void listarItens (ActionEvent e) {
        cardLayout.show(GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDePainel(), "listaDeItens");
    }

    private void verificarPendencias (ActionEvent e) {
        cardLayout.show(GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDePainel(), "listaDePendencias");
    }
}
