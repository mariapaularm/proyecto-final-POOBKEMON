package Domain;

public class PokemonDragon extends Pokemon {

    /**
     * @param nombre Nombre del Pokémon
     * @param imagePath Ruta a la imagen del Pokémon
     * @param nivel Nivel inicial del Pokémon
     * @param hp Puntos de vida iniciales
     * @param ataque Estadística de ataque
     * @param defensa Estadística de defensa
     * @param velocidad Estadística de velocidad
     */
    public PokemonDragon(String nombre, String imagePath, int nivel, int hp, int ataque, int defensa, int velocidad) {
        super(nombre, "dragón", imagePath, nivel, hp, ataque, defensa, velocidad);
        // Cargar ataques específicos de tipo dragón
        this.cargarAtaquesDragon();
    }

    /**
     * Carga ataques específicos para Pokémon de tipo dragón
     */
    private void cargarAtaquesDragon() {
        // Limpiar ataques por defecto y agregar ataques específicos de tipo dragón
        this.getAtaques().clear();
        this.agregarAtaque(new Ataque("Garra Dragón", 80, "dragón"));
        this.agregarAtaque(new Ataque("Pulso Dragón", 85, "dragón", "Especial"));
        this.agregarAtaque(new Ataque("Cometa Draco", 130, "dragón", "Especial"));
        this.agregarAtaque(new Ataque("Danza Dragón", 0, "dragón", "Estado"));
    }

    @Override
    public int atacarBasico(Pokemon objetivo) {
        // Usar un ataque básico de tipo dragón
        Ataque ataqueBasico = new Ataque("Garra Dragón", 80, "dragón");
        return super.atacar(ataqueBasico, objetivo);
    }

    @Override
    public int atacarEspecial(Pokemon objetivo) {
        // Usar un ataque especial de tipo dragón
        Ataque ataqueEspecial = new Ataque("Pulso Dragón", 85, "dragón", "Especial");
        return super.atacar(ataqueEspecial, objetivo);
    }
}

