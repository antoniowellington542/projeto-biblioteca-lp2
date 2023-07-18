package Tela;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaUsuario extends JPanel {
    private final CardLayout cardLayout = GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDelayout();
    public TelaUsuario() {
        setLayout(new GridLayout(7, 1, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton botaoDeLogout = new JButton("Logout");
        botaoDeLogout.addActionListener(this::logout);
        add(botaoDeLogout);

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

    private void listarItens (ActionEvent e) {
        cardLayout.show(GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDePainel(), "listaDeItens");
    }

    private void verificarPendencias (ActionEvent e) {
        cardLayout.show(GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDePainel(), "listaDePendencias");
    }
}
