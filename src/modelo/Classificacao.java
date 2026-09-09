package modelo;

public enum Classificacao {
    MAGREZA,
    NORMAL,
    SOBREPESO,
    OBESIDADE,
    OBESIDADE_GRAVE;

    public static Classificacao gerarClassificacao(double imc){
        if (imc < 18) {
            return MAGREZA;

        } else if (imc < 25) {
            return NORMAL;

        } else if (imc < 35) {
            return SOBREPESO;

        } else {
            return OBESIDADE;
        }
    }
}
