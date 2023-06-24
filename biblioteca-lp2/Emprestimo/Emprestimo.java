package Emprestimo;

import Utilidades.UtilitarioDeData;

import java.util.Date;

public class Emprestimo {
    private final String id;
    private final String cpf;
    private final String itemId;
    private final Date dataSolicitacao;
    private final Date dataExpiracao;

    private static final int tempoMaximoDeEmprestimoEmMeses = 1;

    public Emprestimo(String cpf, String itemId)
    {
        this.id = String.valueOf(new UtilitarioDeData().getDataAtualEmMS());
        this.cpf = cpf;
        this.itemId = itemId;
        this.dataSolicitacao = new UtilitarioDeData().getDataAtual();
        this.dataExpiracao = new UtilitarioDeData().getDataInc(tempoMaximoDeEmprestimoEmMeses);
    }

    public String getId() {
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