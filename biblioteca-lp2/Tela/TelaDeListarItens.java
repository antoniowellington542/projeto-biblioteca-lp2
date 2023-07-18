package Tela;

import BancoDeDados.Session;
import Biblioteca.Biblioteca;
import Item.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class TelaDeListarItens extends JPanel {
    private final CardLayout cardLayout = GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDelayout();
    private final Dialogo dialogo = new Dialogo();
    private final JPanel panel = GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDePainel();
    private final CustomTableModel tableModel;
    private final JLabel labelDeNome;
    private final JTextField campoDeNome;
    private final JButton botaoDeBuscarLivro;
    private final JButton botaoEnviarItem;
    private final JTable tabelaPendencias;
    private final Biblioteca biblioteca = Biblioteca.getBiblioteca();

    public TelaDeListarItens() {
        setLayout(new GridBagLayout());

        tableModel = new CustomTableModel(new Object[]{"Id", "Nome", "Autor", "Quantidade"}, 0);

        this.listarLivros();

        tabelaPendencias = new JTable(tableModel);
        tabelaPendencias.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(tabelaPendencias);
        GridBagConstraints scrollPaneConstraints = new GridBagConstraints();
        scrollPaneConstraints.gridx = 0;
        scrollPaneConstraints.gridy = 0;
        scrollPaneConstraints.gridwidth = 2;
        scrollPaneConstraints.fill = GridBagConstraints.BOTH;
        scrollPaneConstraints.weightx = 1.0;
        scrollPaneConstraints.weighty = 1.0;
        add(scrollPane, scrollPaneConstraints);

        labelDeNome = new JLabel("Nome");
        GridBagConstraints labelDeNomeConstraints = new GridBagConstraints();
        labelDeNomeConstraints.gridx = 0;
        labelDeNomeConstraints.gridy = 2;
        labelDeNomeConstraints.anchor = GridBagConstraints.LINE_END;
        add(labelDeNome, labelDeNomeConstraints);

        campoDeNome = new JTextField(20);
        campoDeNome.setMinimumSize(new Dimension(200, campoDeNome.getPreferredSize().height));
        campoDeNome.setPreferredSize(new Dimension(200, campoDeNome.getPreferredSize().height));
        campoDeNome.setMaximumSize(new Dimension(200, campoDeNome.getPreferredSize().height));
        GridBagConstraints campoDeCpfConstraints = new GridBagConstraints();
        campoDeCpfConstraints.gridx = 1;
        campoDeCpfConstraints.gridy = 2;
        campoDeCpfConstraints.gridwidth = 1; // Ajuste o valor gridwidth conforme necessário
        campoDeCpfConstraints.anchor = GridBagConstraints.LINE_START;
        campoDeCpfConstraints.fill = GridBagConstraints.HORIZONTAL;
        campoDeCpfConstraints.weightx = 1.0;
        add(campoDeNome, campoDeCpfConstraints);

        botaoDeBuscarLivro = new JButton("Buscar livros");
        GridBagConstraints botaoDeVerificarPendenciasConstraints = new GridBagConstraints();
        botaoDeVerificarPendenciasConstraints.gridx = 0;
        botaoDeVerificarPendenciasConstraints.gridy = 3;
        botaoDeVerificarPendenciasConstraints.gridwidth = 2;
        botaoDeVerificarPendenciasConstraints.fill = GridBagConstraints.HORIZONTAL;
        botaoDeVerificarPendenciasConstraints.weightx = 1.0;
        botaoDeVerificarPendenciasConstraints.weighty = 0.0;
        botaoDeBuscarLivro.addActionListener(this::buscarLivro);
        add(botaoDeBuscarLivro, botaoDeVerificarPendenciasConstraints);

        JButton botaoDeLogout = new JButton("Voltar à tela principal");
        GridBagConstraints botaoDeLogoutConstraints = new GridBagConstraints();
        botaoDeLogoutConstraints.gridx = 0;
        botaoDeLogoutConstraints.gridy = 5;
        botaoDeLogoutConstraints.gridwidth = 2;
        botaoDeLogoutConstraints.fill = GridBagConstraints.HORIZONTAL;
        botaoDeLogoutConstraints.weightx = 1.0;
        botaoDeLogoutConstraints.weighty = 0.0;
        botaoDeLogout.addActionListener(this::voltarTelaPrincipal);
        add(botaoDeLogout, botaoDeLogoutConstraints);

        botaoEnviarItem = new JButton("Realizar empréstimo");
        GridBagConstraints botaoEnviarItemConstraints = new GridBagConstraints();
        botaoEnviarItemConstraints.gridx = 0;
        botaoEnviarItemConstraints.gridy = 4;
        botaoEnviarItemConstraints.gridwidth = 2;
        botaoEnviarItemConstraints.fill = GridBagConstraints.HORIZONTAL;
        botaoEnviarItemConstraints.weightx = 1.0;
        botaoEnviarItemConstraints.weighty = 0.0;
        botaoEnviarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarItemSelecionado();
            }
        });
        add(botaoEnviarItem, botaoEnviarItemConstraints);
    }

    private void voltarTelaPrincipal(ActionEvent actionEvent) {
        if(Session.admin)
            cardLayout.show(panel, "telaPrincipal");
        else cardLayout.show(panel, "telaUsuario");
    }

    private void buscarLivro(ActionEvent actionEvent) {
        String nome = campoDeNome.getText();

        if (nome.isEmpty()) {
            tableModel.setRowCount(0);

            this.listarLivros();
        } else {
            ArrayList<Item> item = this.buscarLivro(nome);

            tableModel.setRowCount(0);

            Livro livro = (Livro) item.get(0);

            adicionarItem(livro.getId(), livro.getNome(), livro.getAutor(), livro.getQuantidade());
        }
    }

    private void enviarItemSelecionado() {
        if(Session.admin) {
            int selectedRow = tabelaPendencias.getSelectedRow();

            if (selectedRow != -1) {
                Long id = (Long) tableModel.getValueAt(selectedRow, 0);
                String nome = (String) tableModel.getValueAt(selectedRow, 1);
                String autor = (String) tableModel.getValueAt(selectedRow, 2);
                int quantidade = (int) tableModel.getValueAt(selectedRow, 3);

                HashMap<String, Object> itemMap = new HashMap<>();
                itemMap.put("id", id);
                itemMap.put("nome", nome);
                itemMap.put("autor", autor);
                itemMap.put("quantidade", quantidade);

                TelaDeEmprestimo.receberInfoItem(itemMap);
                cardLayout.show(panel, "emprestimo");
            } else {
                dialogo.mostrarMensagemDeAlerta("Escolha um item");
            }
        } else{
            dialogo.mostrarMensagemDeAlerta("Funcionalidade disponível apenas para funcionários.");
        }
    }

    private void listarLivros() {
        ArrayList<Item> items = biblioteca.getTodosItensDoEstoque();

        for (Item item : items) {
            Livro livro = (Livro) item ;
            adicionarItem(livro.getId(), livro.getNome(), livro.getAutor(), livro.getQuantidade());
        }
    }

    private ArrayList<Item> buscarLivro(String nome) {
        return biblioteca.getLivroPeloNome(nome);
    }

    private void voltarATelaPrincipal(ActionEvent actionEvent) {
        if(Session.admin)
            cardLayout.show(panel, "telaPrincipal");
        else cardLayout.show(panel, "telaUsuario");
    }

    private void adicionarItem(Long id, String nome, String autor, int quantidade) {
        Object[] rowData = {id, nome, autor, quantidade};
        tableModel.addRow(rowData);
    }

    private static class CustomTableModel extends DefaultTableModel {
        CustomTableModel(Object[] columnNames, int rowCount) {
            super(columnNames, rowCount);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Torna todas as células não editáveis
        }
    }
}
