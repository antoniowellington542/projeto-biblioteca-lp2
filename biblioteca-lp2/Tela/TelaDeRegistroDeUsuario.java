package Tela;

import BancoDeDados.BancoDeDados;
import Biblioteca.Biblioteca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaDeRegistroDeUsuario extends JPanel {
    public TelaDeRegistroDeUsuario() {
        setLayout(new GridBagLayout());

        GridBagConstraints constantes = new GridBagConstraints();
        constantes.insets = new Insets(5, 5, 5, 5);

        JLabel labelDeNome = new JLabel("Nome");
        constantes.gridx = 0;
        constantes.gridy = 0;
        add(labelDeNome, constantes);

        JTextField campoDeNome = new JTextField(20);
        constantes.gridx = 1;
        add(campoDeNome, constantes);

        JLabel labelDeCpf = new JLabel("Cpf");
        constantes.gridx = 0;
        constantes.gridy = 1;
        add(labelDeCpf, constantes);

        JTextField campoDeCpf = new JTextField(20);
        constantes.gridx = 1;
        add(campoDeCpf, constantes);

        JLabel labelDeSenha = new JLabel("Senha");
        constantes.gridx = 0;
        constantes.gridy = 2;
        add(labelDeSenha, constantes);

        JTextField campoDeSenha = new JPasswordField(20);
        constantes.gridx = 1;
        add(campoDeSenha, constantes);

        JButton botaoDeRegistro = new JButton();
        botaoDeRegistro.setText("Registrar");
        constantes.gridx = 0;
        constantes.gridy = 3;
        constantes.anchor = GridBagConstraints.CENTER;
        constantes.gridwidth = 2;
        botaoDeRegistro.addActionListener(action -> {
            this.registrar(campoDeNome.getText(), campoDeCpf.getText(), campoDeSenha.getText());
        });
        add(botaoDeRegistro, constantes);

        JButton botaoDeVoltar = new JButton();
        botaoDeVoltar.setText("Voltar");
        constantes.gridy = 4;
        botaoDeVoltar.addActionListener(this::voltarTela);
        add(botaoDeVoltar, constantes);
    }

    private void voltarTela(ActionEvent actionEvent) {
        GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDelayout().show(GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDePainel(), "login");
    }

    private void registrar(String nome, String cpf, String senha) {
        // Chamar metodo para registrar usuario{
        if (!nome.isEmpty() && !senha.isEmpty() && !cpf.isEmpty()) {
            //TODO fazer pesquisa de usuario e funcionario para evitar cpf repetido
            BancoDeDados.cadastrarUsuario(nome, senha, cpf);
            Biblioteca.biblioteca.adicionarUsuario(nome, senha, cpf);
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!", "Sucesso", JOptionPane.WARNING_MESSAGE);
            GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDelayout().show(GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDePainel(), "login");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário, dados incompletos", "Alerta", JOptionPane.WARNING_MESSAGE);
            System.out.println("Erro ao cadastrar usuário, dados incompletos");
        }
    }
}
