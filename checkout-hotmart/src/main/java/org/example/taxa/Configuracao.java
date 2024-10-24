package org.example.taxa;

public class Configuracao {

    private double comissaoFixa;
    private double comissaoVariada;
    private double jurosMensal;
    private double parcelamentoSemJuros;
    private double parcelamentoComJuros;

    private double calculaTaxaDeComissionamento(){
        return 1.0;
    }

    private double calculaTaxaDeJurosMensal(){
        return 1.0;
    }

}
