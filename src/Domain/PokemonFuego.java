package Domain;

public class PokemonFuego extends Pokemon {

    /**
     * @param nombre Nombre del Pokémon
     * @param imagePath Ruta a la imagen del Pokémon
     * @param nivel Nivel inicial del Pokémon
     * @param hp Puntos de vida iniciales
     * @param ataque Estadística de ataque
     * @param defensa Estadística de defensa
     * @param velocidad Estadística de velocidad
     */
    public PokemonFuego(String nombre, String imagePath, int nivel, int hp, int ataque, int defensa, int velocidad) {
        super(nombre, "fuego", imagePath, nivel, hp, ataque, defensa, velocidad);
        // Cargar ataques específicos de tipo fuego
        this.cargarAtaquesFuego();
    }

    /**
     * Carga ataques específicos para Pokémon de tipo fuego
     */
    private void cargarAtaquesFuego() {
        // Limpiar ataques por defecto y agregar ataques específicos de tipo fuego
        this.getAtaques().clear();
        this.agregarAtaque(new Ataque("Ascuas", 40, "fuego"));
        this.agregarAtaque(new Ataque("Lanzallamas", 90, "fuego", "Especial"));
        this.agregarAtaque(new Ataque("Llamarada", 110, "fuego", "Especial"));
        this.agregarAtaque(new Ataque("Rueda Fuego", 60, "fuego"));
    }

    @Override
    public int atacarBasico(Pokemon objetivo) {
        // Usar un ataque básico de tipo fuego
        Ataque ataqueBasico = new Ataque("Ascuas", 40, "fuego");
        return super.atacar(ataqueBasico, objetivo);
    }

    @Override
    public int atacarEspecial(Pokemon objetivo) {
        // Usar un ataque especial de tipo fuego
        Ataque ataqueEspecial = new Ataque("Lanzallamas", 90, "fuego", "Especial");
        return super.atacar(ataqueEspecial, objetivo);
    }
}
