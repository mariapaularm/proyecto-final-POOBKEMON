package Domain;

public class PokemonAgua extends Pokemon {

    /**
     * @param nombre Nombre del Pokémon
     * @param imagePath Ruta a la imagen del Pokémon
     * @param nivel Nivel inicial del Pokémon
     * @param hp Puntos de vida iniciales
     * @param ataque Estadística de ataque
     * @param defensa Estadística de defensa
     * @param velocidad Estadística de velocidad
     */
    public PokemonAgua(String nombre, String imagePath, int nivel, int hp, int ataque, int defensa, int velocidad) {
        super(nombre, "agua", imagePath, nivel, hp, ataque, defensa, velocidad);
        // Cargar ataques específicos de tipo agua
        this.cargarAtaquesAgua();
    }

    /**
     * Carga ataques específicos para Pokémon de tipo agua
     */
    private void cargarAtaquesAgua() {
        // Limpiar ataques por defecto y agregar ataques específicos de tipo agua
        this.getAtaques().clear();
        this.agregarAtaque(new Ataque("Pistola Agua", 40, "agua"));
        this.agregarAtaque(new Ataque("Hidrobomba", 110, "agua", "Especial"));
        this.agregarAtaque(new Ataque("Surf", 90, "agua", "Especial"));
        this.agregarAtaque(new Ataque("Acua Aro", 0, "agua", "Estado"));
    }

    @Override
    public int atacarBasico(Pokemon objetivo) {
        // Usar un ataque básico de tipo agua
        Ataque ataqueBasico = new Ataque("Pistola Agua", 40, "agua");
        return super.atacar(ataqueBasico, objetivo);
    }

    @Override
    public int atacarEspecial(Pokemon objetivo) {
        // Usar un ataque especial de tipo agua
        Ataque ataqueEspecial = new Ataque("Hidrobomba", 110, "agua", "Especial");
        return super.atacar(ataqueEspecial, objetivo);
    }
}
