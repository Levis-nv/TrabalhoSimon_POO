package modelo.entidade;

public enum Classificacao {
    MAGREZA,
    NORMAL,
    SOBREPESO,
    OBESIDADE,
    OBESIDADE_GRAVE;

    public static Classificacao gerarClassificacao(double imc){
        if (imc >= 25) {
            return MAGREZA;

        } else if (imc >= 30) {
            return NORMAL;

        } else if (imc >= 40) {
            return SOBREPESO;

        } else {
            return OBESIDADE;
        }
    }
}
