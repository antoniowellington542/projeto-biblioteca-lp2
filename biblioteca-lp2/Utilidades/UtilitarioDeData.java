package Utilidades;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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

    public String convertDateFormat(Date inputDate) {
        String outputDate = "";

        try {
            DateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
            Date date = inputFormat.parse(String.valueOf(inputDate));

            DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            outputDate = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return outputDate;
    }
}
