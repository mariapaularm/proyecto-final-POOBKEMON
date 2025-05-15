package Domain;

public class PokemonVeneno extends Pokemon {

    /**
     * @param nombre Nombre del Pokémon
     * @param imagePath Ruta a la imagen del Pokémon
     * @param nivel Nivel inicial del Pokémon
     * @param hp Puntos de vida iniciales
     * @param ataque Estadística de ataque
     * @param defensa Estadística de defensa
     * @param velocidad Estadística de velocidad
     */
    public PokemonVeneno(String nombre, String imagePath, int nivel, int hp, int ataque, int defensa, int velocidad) {
        super(nombre, "veneno", imagePath, nivel, hp, ataque, defensa, velocidad);
        // Cargar ataques específicos de tipo veneno
        this.cargarAtaquesVeneno();
    }

    /**
     * Carga ataques específicos para Pokémon de tipo veneno
     */
    private void cargarAtaquesVeneno() {
        // Limpiar ataques por defecto y agregar ataques específicos de tipo veneno
        this.getAtaques().clear();
        this.agregarAtaque(new Ataque("Picotazo Venenoso", 40, "veneno"));
        this.agregarAtaque(new Ataque("Bomba Lodo", 90, "veneno", "Especial"));
        this.agregarAtaque(new Ataque("Residuos", 65, "veneno"));
        this.agregarAtaque(new Ataque("Tóxico", 0, "veneno", "Estado"));
    }

    @Override
    public int atacarBasico(Pokemon objetivo) {
        // Usar un ataque básico de tipo veneno
        Ataque ataqueBasico = new Ataque("Picotazo Venenoso", 40, "veneno");
        return super.atacar(ataqueBasico, objetivo);
    }

    @Override
    public int atacarEspecial(Pokemon objetivo) {
        // Usar un ataque especial de tipo veneno
        Ataque ataqueEspecial = new Ataque("Bomba Lodo", 90, "veneno", "Especial");
        return super.atacar(ataqueEspecial, objetivo);
    }
}

