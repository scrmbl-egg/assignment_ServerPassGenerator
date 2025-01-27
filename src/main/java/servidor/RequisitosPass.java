package servidor;

/**
 * Objeto que contiene los requisitos del ServicioPass.
 * @see ServicioPass
 * @author Daniel N. P.
 */
public class RequisitosPass {
    /**
     * Cadena de caracteres de todas las letras minúsculas.
     */
    public static final String LETRAS_MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
    
    /**
     * Cadena de caracteres de todas las letras mayúsculas.
     */
    public static final String LETRAS_MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    /**
     * Cadena de caracteres de todos los dígitos.
     */
    public static final String DIGITOS = "0123456789";
    
    /**
     * Cadena de caracteres de todos los caracteres especiales.
     */
    public static final String CARACTERES_ESPECIALES = "!@#$%^&*()_-+=.:?";
    
    /**
     * Mínima cantidad requerida de cada tipo de caracter.
     */
    public static final int MINIMO_INCLUSIVO = 0;
    
    /**
     * Máxima cantidad permitida de cada tipo de caracter.
     */
    public static final int MAXIMO_INCLUSIVO = 9;
    
    /**
     * Número de letras mayúsculas.
     */
    private int numMayusculas;
    
    /**
     * Número de letras minúsculas.
     */
    private int numMinusculas;
    
    /**
     * Número de dígitos numéricos.
     */
    private int numDigitos;
    
    /**
     * Número de caracteres especiales.
     * Los caracteres especiales reconocidos son los siguientes <code>!@#$%^&*()_-+=.:?</code>.
     */
    private int numCaractEspeciales;
    
    /**
     * Constructor avanzado.
     * @param numMayusculas Número de letras mayúsculas.
     * @param numMinusculas Número de letras minúsculas.
     * @param numDigitos Número de dígitos numéricos.
     * @param numCaractEspeciales Número de caracteres especiales.
     */
    public RequisitosPass(
            int numMayusculas, 
            int numMinusculas, 
            int numDigitos, 
            int numCaractEspeciales) {
        this.numMayusculas = numMayusculas;
        this.numMinusculas = numMinusculas;
        this.numDigitos = numDigitos;
        this.numCaractEspeciales = numCaractEspeciales;
    }
    
    /**
     * Obtiene el número de letras mayúsculas.
     * @return Número de letras mayúsculas
     */
    public int getNumMayusculas() {
        return numMayusculas;
    }
    
    /**
     * Obtiene el número de letras minúsculas.
     * @return Número de letras minúsculas
     */
    public int getNumMinusculas() {
        return numMinusculas;
    }
    
    /**
     * Obtiene el número de dígitos numéricos.
     * @return Número de dígitos numéricos
     */
    public int getNumDigitos() {
        return numDigitos;
    }
    
    /**
     * Obtiene el número de caracteres especiales..
     * @return Número de caracteres especiales.
     */
    public int getNumCaractEspeciales() {
        return numCaractEspeciales;
    }
    
    /**
     * Modifica el número de letras mayúsculas.
     * @param numMayusculas Número de mayúsculas.
     */
    public void setNumMayusculas(int numMayusculas) {
        this.numMayusculas = numMayusculas;
    }
    
    /**
     * Modifica el número de letras minúsculas.
     * @param numMinusculas Número de minúsculas.
     */
    public void setNumMinusculas(int numMinusculas) {
        this.numMinusculas = numMinusculas;
    }
    
    /**
     * Modifica el número de dígitos numéricos.
     * @param numDigitos Número de dígitos numéricos.
     */
    public void setNumDigitos(int numDigitos) {
        this.numDigitos = numDigitos;
    }
    
    /**
     * Modifica el número de caracteres especiales.
     * @param numCaractEspeciales Número de caracteres especiales.
     */
    public void setNumCaractEspeciales(int numCaractEspeciales) {
        this.numCaractEspeciales = numCaractEspeciales;
    }

    @Override
    public String toString() {
        return "RequisitosPass {\n"
            + "\tnumMayusculas = " + numMayusculas + ",\n"
            + "\tnumMinusculas = " + numMinusculas + ",\n"
            + "\tnumDigitos = " + numDigitos + ",\n"
            + "\tnumCaractEspeciales = " + numCaractEspeciales + ",\n"
            + "}";
    }
}
