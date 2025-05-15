package Domain;

public class PokemonVolador extends Pokemon {

    /**
     * @param nombre Nombre del Pokémon
     * @param imagePath Ruta a la imagen del Pokémon
     * @param nivel Nivel inicial del Pokémon
     * @param hp Puntos de vida iniciales
     * @param ataque Estadística de ataque
     * @param defensa Estadística de defensa
     * @param velocidad Estadística de velocidad
     */
    public PokemonVolador(String nombre, String imagePath, int nivel, int hp, int ataque, int defensa, int velocidad) {
        super(nombre, "volador", imagePath, nivel, hp, ataque, defensa, velocidad);
        // Cargar ataques específicos de tipo volador
        this.cargarAtaquesVolador();
    }

    /**
     * Carga ataques específicos para Pokémon de tipo volador
     */
    private void cargarAtaquesVolador() {
        // Limpiar ataques por defecto y agregar ataques específicos de tipo volador
        this.getAtaques().clear();
        this.agregarAtaque(new Ataque("Ataque Ala", 60, "volador"));
        this.agregarAtaque(new Ataque("Pájaro Osado", 120, "volador"));
        this.agregarAtaque(new Ataque("Tajo Aéreo", 75, "volador", "Especial"));
        this.agregarAtaque(new Ataque("Danza Pluma", 0, "volador", "Estado"));
    }

    @Override
    public int atacarBasico(Pokemon objetivo) {
        // Usar un ataque básico de tipo volador
        Ataque ataqueBasico = new Ataque("Ataque Ala", 60, "volador");
        return super.atacar(ataqueBasico, objetivo);
    }

    @Override
    public int atacarEspecial(Pokemon objetivo) {
        // Usar un ataque especial de tipo volador
        Ataque ataqueEspecial = new Ataque("Pájaro Osado", 120, "volador");
        return super.atacar(ataqueEspecial, objetivo);
    }
}
