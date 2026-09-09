package modelo;

public enum Sexo {
    MASCULINO,
    FEMININO;

    public static Sexo selecionar(int cod) {
        if (cod == 1) {
            return MASCULINO;
        }

        return FEMININO;
    }
}
