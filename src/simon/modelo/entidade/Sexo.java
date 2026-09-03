package simon.modelo.entidade;

public enum Sexo {
    MASCULINO, FEMININO;

    public static Sexo selecionarSexo(int cod) {
        if(cod ==  1){
            return MASCULINO;
        } else {
            return FEMININO;
        }
    }
}
