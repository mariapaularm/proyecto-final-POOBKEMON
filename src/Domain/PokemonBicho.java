package Domain;

public class PokemonBicho extends Pokemon {

    /**
     * @param nombre Nombre del Pokémon
     * @param imagePath Ruta a la imagen del Pokémon
     * @param nivel Nivel inicial del Pokémon
     * @param hp Puntos de vida iniciales
     * @param ataque Estadística de ataque
     * @param defensa Estadística de defensa
     * @param velocidad Estadística de velocidad
     */
    public PokemonBicho(String nombre, String imagePath, int nivel, int hp, int ataque, int defensa, int velocidad) {
        super(nombre, "bicho", imagePath, nivel, hp, ataque, defensa, velocidad);
        // Cargar ataques específicos de tipo bicho
        this.cargarAtaquesBicho();
    }

    /**
     * Carga ataques específicos para Pokémon de tipo bicho
     */
    private void cargarAtaquesBicho() {
        // Limpiar ataques por defecto y agregar ataques específicos de tipo bicho
        this.getAtaques().clear();
        this.agregarAtaque(new Ataque("Picadura", 60, "bicho"));
        this.agregarAtaque(new Ataque("Tijera X", 80, "bicho"));
        this.agregarAtaque(new Ataque("Zumbido", 90, "bicho", "Especial"));
        this.agregarAtaque(new Ataque("Telaraña", 0, "bicho", "Estado"));
    }

    @Override
    public int atacarBasico(Pokemon objetivo) {
        // Usar un ataque básico de tipo bicho
        Ataque ataqueBasico = new Ataque("Picadura", 60, "bicho");
        return super.atacar(ataqueBasico, objetivo);
    }

    @Override
    public int atacarEspecial(Pokemon objetivo) {
        // Usar un ataque especial de tipo bicho
        Ataque ataqueEspecial = new Ataque("Zumbido", 90, "bicho", "Especial");
        return super.atacar(ataqueEspecial, objetivo);
    }
}

