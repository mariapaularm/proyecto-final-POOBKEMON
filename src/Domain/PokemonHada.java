package Domain;

public class PokemonHada extends Pokemon {

    /**
     * @param nombre Nombre del Pokémon
     * @param imagePath Ruta a la imagen del Pokémon
     * @param nivel Nivel inicial del Pokémon
     * @param hp Puntos de vida iniciales
     * @param ataque Estadística de ataque
     * @param defensa Estadística de defensa
     * @param velocidad Estadística de velocidad
     */
    public PokemonHada(String nombre, String imagePath, int nivel, int hp, int ataque, int defensa, int velocidad) {
        super(nombre, "hada", imagePath, nivel, hp, ataque, defensa, velocidad);
        // Cargar ataques específicos de tipo hada
        this.cargarAtaquesHada();
    }

    /**
     * Carga ataques específicos para Pokémon de tipo hada
     */
    private void cargarAtaquesHada() {
        // Limpiar ataques por defecto y agregar ataques específicos de tipo hada
        this.getAtaques().clear();
        this.agregarAtaque(new Ataque("Beso Dulce", 50, "hada"));
        this.agregarAtaque(new Ataque("Brillo Mágico", 80, "hada", "Especial"));
        this.agregarAtaque(new Ataque("Fuerza Lunar", 95, "hada", "Especial"));
        this.agregarAtaque(new Ataque("Encanto", 0, "hada", "Estado"));
    }

    @Override
    public int atacarBasico(Pokemon objetivo) {
        // Usar un ataque básico de tipo hada
        Ataque ataqueBasico = new Ataque("Beso Dulce", 50, "hada");
        return super.atacar(ataqueBasico, objetivo);
    }

    @Override
    public int atacarEspecial(Pokemon objetivo) {
        // Usar un ataque especial de tipo hada
        Ataque ataqueEspecial = new Ataque("Brillo Mágico", 80, "hada", "Especial");
        return super.atacar(ataqueEspecial, objetivo);
    }
}

