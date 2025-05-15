package Domain;

public class PokemonRoca extends Pokemon {

    /**
     * @param nombre Nombre del Pokémon
     * @param imagePath Ruta a la imagen del Pokémon
     * @param nivel Nivel inicial del Pokémon
     * @param hp Puntos de vida iniciales
     * @param ataque Estadística de ataque
     * @param defensa Estadística de defensa
     * @param velocidad Estadística de velocidad
     */
    public PokemonRoca(String nombre, String imagePath, int nivel, int hp, int ataque, int defensa, int velocidad) {
        super(nombre, "roca", imagePath, nivel, hp, ataque, defensa, velocidad);
        // Cargar ataques específicos de tipo roca
        this.cargarAtaquesRoca();
    }

    /**
     * Carga ataques específicos para Pokémon de tipo roca
     */
    private void cargarAtaquesRoca() {
        // Limpiar ataques por defecto y agregar ataques específicos de tipo roca
        this.getAtaques().clear();
        this.agregarAtaque(new Ataque("Lanzarrocas", 50, "roca"));
        this.agregarAtaque(new Ataque("Avalancha", 75, "roca"));
        this.agregarAtaque(new Ataque("Roca Afilada", 100, "roca"));
        this.agregarAtaque(new Ataque("Fortaleza", 0, "roca", "Estado"));
    }

    @Override
    public int atacarBasico(Pokemon objetivo) {
        // Usar un ataque básico de tipo roca
        Ataque ataqueBasico = new Ataque("Lanzarrocas", 50, "roca");
        return super.atacar(ataqueBasico, objetivo);
    }

    @Override
    public int atacarEspecial(Pokemon objetivo) {
        // Usar un ataque especial de tipo roca
        Ataque ataqueEspecial = new Ataque("Avalancha", 75, "roca");
        return super.atacar(ataqueEspecial, objetivo);
    }
}
