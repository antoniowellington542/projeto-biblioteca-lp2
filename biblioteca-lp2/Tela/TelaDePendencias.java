package Tela;

import Biblioteca.Biblioteca;
import Emprestimo.Emprestimo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;

public class TelaDePendencias extends JPanel {
    private final CardLayout cardLayout = GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDelayout();
    private final Dialogo dialogo = new Dialogo();
    private final JPanel panel = GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDePainel();
    private final CustomTableModel tableModel;
    private final JLabel labelDeCpf;
    private final JTextField campoDeCpf;
    private final JButton botaoDeVerificarPendencias;
    private final Biblioteca biblioteca = Biblioteca.getBiblioteca();

    public TelaDePendencias() {
        setLayout(new GridBagLayout());

        tableModel = new CustomTableModel(new Object[]{"Id", "Cpf", "ItemId", "Solicitação", "Expiração"}, 0);

        JTable tabelaPendencias = new JTable(tableModel);
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

        labelDeCpf = new JLabel("CPF");
        GridBagConstraints labelDeCpfConstraints = new GridBagConstraints();
        labelDeCpfConstraints.gridx = 0;
        labelDeCpfConstraints.gridy = 2;
        labelDeCpfConstraints.anchor = GridBagConstraints.LINE_END;
        add(labelDeCpf, labelDeCpfConstraints);

        campoDeCpf = new JTextField(20);
        campoDeCpf.setMinimumSize(new Dimension(200, campoDeCpf.getPreferredSize().height));
        campoDeCpf.setPreferredSize(new Dimension(200, campoDeCpf.getPreferredSize().height));
        campoDeCpf.setMaximumSize(new Dimension(200, campoDeCpf.getPreferredSize().height));
        GridBagConstraints campoDeCpfConstraints = new GridBagConstraints();
        campoDeCpfConstraints.gridx = 1;
        campoDeCpfConstraints.gridy = 2;
        campoDeCpfConstraints.gridwidth = 1; // Ajuste o valor gridwidth conforme necessário
        campoDeCpfConstraints.anchor = GridBagConstraints.LINE_START;
        campoDeCpfConstraints.fill = GridBagConstraints.HORIZONTAL;
        campoDeCpfConstraints.weightx = 1.0;
        add(campoDeCpf, campoDeCpfConstraints);

        botaoDeVerificarPendencias = new JButton("Verificar pendências");
        GridBagConstraints botaoDeVerificarPendenciasConstraints = new GridBagConstraints();
        botaoDeVerificarPendenciasConstraints.gridx = 0;
        botaoDeVerificarPendenciasConstraints.gridy = 3;
        botaoDeVerificarPendenciasConstraints.gridwidth = 2;
        botaoDeVerificarPendenciasConstraints.fill = GridBagConstraints.HORIZONTAL;
        botaoDeVerificarPendenciasConstraints.weightx = 1.0;
        botaoDeVerificarPendenciasConstraints.weighty = 0.0;
        botaoDeVerificarPendencias.addActionListener(this::verificarPendencias);
        add(botaoDeVerificarPendencias, botaoDeVerificarPendenciasConstraints);

        JButton botaoDeLogout = new JButton("Voltar à tela principal");
        GridBagConstraints botaoDeLogoutConstraints = new GridBagConstraints();
        botaoDeLogoutConstraints.gridx = 0;
        botaoDeLogoutConstraints.gridy = 4;
        botaoDeLogoutConstraints.gridwidth = 2;
        botaoDeLogoutConstraints.fill = GridBagConstraints.HORIZONTAL;
        botaoDeLogoutConstraints.weightx = 1.0;
        botaoDeLogoutConstraints.weighty = 0.0;
        botaoDeLogout.addActionListener(this::voltarTelaPrincipal);
        add(botaoDeLogout, botaoDeLogoutConstraints);
    }

    private void voltarTelaPrincipal(ActionEvent actionEvent) {
        cardLayout.show(panel, "telaPrincipal");
    }

    private void verificarPendencias(ActionEvent actionEvent) {
        String cpf = campoDeCpf.getText();
        ArrayList<Emprestimo> emprestimos = this.verificarPendencia(cpf);
        System.out.println(emprestimos);

        tableModel.setRowCount(0); // Limpar a tabela antes de adicionar novos dados

        for (Emprestimo emprestimo : emprestimos) {
            adicionarItem(emprestimo.getId(), emprestimo.getCpf(), emprestimo.getItemId(), emprestimo.getDataSolicitacao(), emprestimo.getDataExpiracao());
        }
    }

    private ArrayList<Emprestimo> verificarPendencia(String cpf) {
        return biblioteca.getEmprestimos(cpf);
    }

    private void adicionarItem(Long id, String cpf, String itemId, Date dataSolicitacao, Date dataExpiracao) {
        Object[] rowData = {id, cpf, itemId, dataSolicitacao, dataExpiracao};
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
