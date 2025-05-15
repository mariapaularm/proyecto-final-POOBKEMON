package Domain;

public class PokemonPsiquico extends Pokemon {

    /**
     * @param nombre Nombre del Pokémon
     * @param imagePath Ruta a la imagen del Pokémon
     * @param nivel Nivel inicial del Pokémon
     * @param hp Puntos de vida iniciales
     * @param ataque Estadística de ataque
     * @param defensa Estadística de defensa
     * @param velocidad Estadística de velocidad
     */
    public PokemonPsiquico(String nombre, String imagePath, int nivel, int hp, int ataque, int defensa, int velocidad) {
        super(nombre, "psíquico", imagePath, nivel, hp, ataque, defensa, velocidad);
        // Cargar ataques específicos de tipo psíquico
        this.cargarAtaquesPsiquico();
    }

    /**
     * Carga ataques específicos para Pokémon de tipo psíquico
     */
    private void cargarAtaquesPsiquico() {
        // Limpiar ataques por defecto y agregar ataques específicos de tipo psíquico
        this.getAtaques().clear();
        this.agregarAtaque(new Ataque("Confusión", 50, "psíquico", "Especial"));
        this.agregarAtaque(new Ataque("Psicorrayo", 65, "psíquico", "Especial"));
        this.agregarAtaque(new Ataque("Psíquico", 90, "psíquico", "Especial"));
        this.agregarAtaque(new Ataque("Meditación", 0, "psíquico", "Estado"));
    }

    @Override
    public int atacarBasico(Pokemon objetivo) {
        // Usar un ataque básico de tipo psíquico
        Ataque ataqueBasico = new Ataque("Confusión", 50, "psíquico", "Especial");
        return super.atacar(ataqueBasico, objetivo);
    }

    @Override
    public int atacarEspecial(Pokemon objetivo) {
        // Usar un ataque especial de tipo psíquico
        Ataque ataqueEspecial = new Ataque("Psíquico", 90, "psíquico", "Especial");
        return super.atacar(ataqueEspecial, objetivo);
    }
}

