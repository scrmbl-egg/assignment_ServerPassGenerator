package servidor;

import java.util.Random;

/**
 * Servicio para obtener datos de la contraseña.
 * @author Daniel N. P.
 */
public class ServicioPass {
    /**
     * Instancia de RequisitosPass.
     */
    private RequisitosPass requisitosPass;
    
    /**
     * Constructor básico.
     * @param requisitosPass Instancia de RequisitosPass (dependencia)
     */
    public ServicioPass(RequisitosPass requisitosPass) {
        this.requisitosPass = requisitosPass;
    }
    
    /**
     * Obtiene la instancia de RequisitosPass
     * @return Instancia de RequisitosPass
     */
    public RequisitosPass getRequisitosPass() {
        return requisitosPass;
    }
    
    /**
     * Genera la contraseña según los requisitos del usuario.
     * @return Cadena de caracteres aleatoria dentro de los requisitos.
     */
    public String generaPass() {
        // Inicializar Random para obtener números aleatorios.
        Random random = new Random();
        
        // Inicializar StringBuilder
        StringBuilder stringBuilder = new StringBuilder();
        
        // Añadir mayúsculas
        for (int i = 0; i < requisitosPass.getNumMayusculas(); i++) {
            int indiceAleatorio = random.nextInt(RequisitosPass.LETRAS_MAYUSCULAS.length());
            char caracter = RequisitosPass.LETRAS_MAYUSCULAS.charAt(indiceAleatorio);
            
            stringBuilder.append(caracter);
        }
        
        // Añadir minúsculas
        for (int i = 0; i < requisitosPass.getNumMinusculas(); i++) {
            int indiceAleatorio = random.nextInt(RequisitosPass.LETRAS_MINUSCULAS.length());
            char caracter = RequisitosPass.LETRAS_MINUSCULAS.charAt(indiceAleatorio);
            
            stringBuilder.append(caracter);
        }
        
        // Añadir dígitos
        for (int i = 0; i < requisitosPass.getNumDigitos(); i++) {
            int indiceAleatorio = random.nextInt(RequisitosPass.DIGITOS.length());
            char caracter = RequisitosPass.DIGITOS.charAt(indiceAleatorio);
            
            stringBuilder.append(caracter);
        }
        
        // Añadir caracteres especiales
        for (int i = 0; i < requisitosPass.getNumCaractEspeciales(); i++) {
            int indiceAleatorio = random.nextInt(RequisitosPass.CARACTERES_ESPECIALES.length());
            char caracter = RequisitosPass.CARACTERES_ESPECIALES.charAt(indiceAleatorio);
            
            stringBuilder.append(caracter);
        }
        
        return barajarString(stringBuilder.toString(), random);
    }
    
    /**
     * Obtiene la longitud de la contraseña.
     * @return Longitud de la contraseña.
     */
    public int longitudPass() {
        int sumaCaracteres = requisitosPass.getNumMayusculas()
                + requisitosPass.getNumMinusculas()
                + requisitosPass.getNumDigitos()
                + requisitosPass.getNumCaractEspeciales();
        
        return sumaCaracteres;
    }
    
    /**
     * Baraja una cadena de caracteres de forma aleatoria.
     * @param str Cadena de caracteres original.
     * @param rnd Instancia de Random.
     * @return
     */
    private String barajarString(String str, Random rnd) {
        char[] caracteres = str.toCharArray();

        // Intercambio de caracteres aleatorio.
        for (int i = caracteres.length - 1; i > 0; i--) {
            int j = rnd.nextInt(i + 1);
            char temp = caracteres[i];
            caracteres[i] = caracteres[j];
            caracteres[j] = temp;
        }

        return new String(caracteres);
    }
}
