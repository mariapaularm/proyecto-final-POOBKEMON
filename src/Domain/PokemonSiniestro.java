package Domain;

public class PokemonSiniestro extends Pokemon {

    /**
     * @param nombre Nombre del Pokémon
     * @param imagePath Ruta a la imagen del Pokémon
     * @param nivel Nivel inicial del Pokémon
     * @param hp Puntos de vida iniciales
     * @param ataque Estadística de ataque
     * @param defensa Estadística de defensa
     * @param velocidad Estadística de velocidad
     */
    public PokemonSiniestro(String nombre, String imagePath, int nivel, int hp, int ataque, int defensa, int velocidad) {
        super(nombre, "siniestro", imagePath, nivel, hp, ataque, defensa, velocidad);
        // Cargar ataques específicos de tipo siniestro
        this.cargarAtaquesSiniestro();
    }

    /**
     * Carga ataques específicos para Pokémon de tipo siniestro
     */
    private void cargarAtaquesSiniestro() {
        // Limpiar ataques por defecto y agregar ataques específicos de tipo siniestro
        this.getAtaques().clear();
        this.agregarAtaque(new Ataque("Mordisco", 60, "siniestro"));
        this.agregarAtaque(new Ataque("Pulso Umbrío", 80, "siniestro", "Especial"));
        this.agregarAtaque(new Ataque("Tajo Umbrío", 70, "siniestro"));
        this.agregarAtaque(new Ataque("Mal de Ojo", 0, "siniestro", "Estado"));
    }

    @Override
    public int atacarBasico(Pokemon objetivo) {
        // Usar un ataque básico de tipo siniestro
        Ataque ataqueBasico = new Ataque("Mordisco", 60, "siniestro");
        return super.atacar(ataqueBasico, objetivo);
    }

    @Override
    public int atacarEspecial(Pokemon objetivo) {
        // Usar un ataque especial de tipo siniestro
        Ataque ataqueEspecial = new Ataque("Pulso Umbrío", 80, "siniestro", "Especial");
        return super.atacar(ataqueEspecial, objetivo);
    }
}

