package Emprestimo;

import Biblioteca.Biblioteca;
import Utilidades.UtilitarioDeData;

import java.util.Date;

public class Emprestimo {
    private final Long id;
    private final String cpf;
    private final String itemId;
    private final Date dataSolicitacao;
    private final Date dataExpiracao;

    private static final int tempoMaximoDeEmprestimoEmMeses = 1;

    public Emprestimo(String cpf, String itemId, Date data) {
        this.id = (long) (Biblioteca.biblioteca.emprestimos.size() + 1);
        this.cpf = cpf;
        this.itemId = itemId;
        if (data != null) {
            this.dataSolicitacao = data;
        } else {
            this.dataSolicitacao = new UtilitarioDeData().getDataAtual();
        }
        this.dataExpiracao = new UtilitarioDeData().getDataInc(tempoMaximoDeEmprestimoEmMeses);
    }

    public Emprestimo(String cpf, String itemId) {
        this.id = (long) (Biblioteca.biblioteca.emprestimos.size() + 1);
        this.cpf = cpf;
        this.itemId = itemId;
        this.dataSolicitacao = new UtilitarioDeData().getDataAtual();

        this.dataExpiracao = new UtilitarioDeData().getDataInc(tempoMaximoDeEmprestimoEmMeses);
    }

    public Long getId() {
        return id;
    }

    public Date getDataExpiracao() {
        return dataExpiracao;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public String getItemId() {
        return itemId;
    }

    public String getCpf() {
        return cpf;
    }
}