package Utilidades;

import java.util.Calendar;
import java.util.Date;

public class UtilitarioDeData {
    public long getDataAtualEmMS () {
        return new Date().getTime();
    }

    public Date getDataAtual () {
        return new Date();
    }

    public Date getDataInc (int tempoIncrementandoEmMeses) {
        Date dataAtual = this.getDataAtual();

        Calendar calendario = Calendar.getInstance();
        calendario.setTime(dataAtual);

        calendario.add(Calendar.MONTH, tempoIncrementandoEmMeses);

        Date dataIncrementada = calendario.getTime();

        return dataIncrementada;
    }
}
