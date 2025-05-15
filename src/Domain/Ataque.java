package Domain;

public class Ataque {
    private String nombre;
    private int potencia;
    private String tipo;
    private String categoria; // "Físico", "Especial" o "Estado"

    /**
     * @param nombre Nombre del ataque
     * @param potencia Potencia base del ataque
     * @param tipo Tipo elemental del ataque
     */
    public Ataque(String nombre, int potencia, String tipo) {
        this.nombre = nombre;
        this.potencia = potencia;
        this.tipo = tipo;
        // Por defecto, si la potencia es 0, es un ataque de estado
        // si no, asumimos que es un ataque físico
        this.categoria = potencia == 0 ? "Estado" : "Físico";
    }

    /**
     *
     * @param nombre Nombre del ataque
     * @param potencia Potencia base del ataque
     * @param tipo Tipo elemental del ataque
     * @param categoria Categoría del ataque (Físico, Especial o Estado)
     */
    public Ataque(String nombre, int potencia, String tipo, String categoria) {
        this.nombre = nombre;
        this.potencia = potencia;
        this.tipo = tipo;
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPotencia() {
        return potencia;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return nombre + " (" + tipo + ", " + potencia + " pts)";
    }
}