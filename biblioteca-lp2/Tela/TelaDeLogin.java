package Tela;

import Biblioteca.Biblioteca;
import Contas.Funcionario;
import Contas.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaDeLogin extends JPanel {
    public TelaDeLogin() {
        setLayout(new GridBagLayout());

        GridBagConstraints constantes = new GridBagConstraints();
        constantes.insets = new Insets(5, 5, 5, 5);

        JLabel labelDeCpf = new JLabel("CPF");
        constantes.gridx = 0;
        constantes.gridy = 0;
        add(labelDeCpf, constantes);

        JTextField campoDeCpf = new JTextField(20);
        constantes.gridx = 1;
        add(campoDeCpf, constantes);

        JLabel labelDeSenha = new JLabel("Senha");
        constantes.gridx = 0;
        constantes.gridy = 1;
        add(labelDeSenha, constantes);

        JTextField campoDeSenha = new JPasswordField(20);
        constantes.gridx = 1;
        add(campoDeSenha, constantes);

        JButton botaoDeLogin = new JButton();
        botaoDeLogin.setText("Login");
        constantes.gridx = 0;
        constantes.gridy = 2;
        constantes.anchor = GridBagConstraints.CENTER;
        constantes.gridwidth = 2;
        botaoDeLogin.addActionListener(action -> {
            this.login(campoDeCpf.getText(), campoDeSenha.getText());
        });
        add(botaoDeLogin, constantes);

        JButton botaoDeRegistro = new JButton();
        botaoDeRegistro.setText("Registrar?");
        constantes.gridy = 3;
        botaoDeRegistro.addActionListener(this::registrar);
        add(botaoDeRegistro, constantes);
    }

    private void login(String cpf, String password) {
        Usuario usuario = Biblioteca.biblioteca.loginUsuario(cpf, password);
        Funcionario funcionario = Biblioteca.biblioteca.loginFuncionario(cpf, password);
        //TODO quando existir telas diferentes para usuario e funcionario, mudar o redirecionamento abaixo
        if (usuario != null) {
            GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDelayout().show(GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDePainel(), "telaPrincipal");
        } else if (funcionario != null) {
            GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDelayout().show(GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDePainel(), "telaPrincipal");
        } else {
            JOptionPane.showMessageDialog(null, "Usuário não encontrado", "Alerta", JOptionPane.WARNING_MESSAGE);
            System.out.println("Usuário não encontrado");
        }
    }

    private void registrar(ActionEvent e) {
        GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDelayout().show(GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDePainel(), "registroDeUsuario");
    }
}
