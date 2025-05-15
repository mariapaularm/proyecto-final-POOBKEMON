package Domain;

public class PokemonElectrico extends Pokemon {

    /**
     * @param nombre Nombre del Pokémon
     * @param imagePath Ruta a la imagen del Pokémon
     * @param nivel Nivel inicial del Pokémon
     * @param hp Puntos de vida iniciales
     * @param ataque Estadística de ataque
     * @param defensa Estadística de defensa
     * @param velocidad Estadística de velocidad
     */
    public PokemonElectrico(String nombre, String imagePath, int nivel, int hp, int ataque, int defensa, int velocidad) {
        super(nombre, "eléctrico", imagePath, nivel, hp, ataque, defensa, velocidad);
        // Cargar ataques específicos de tipo eléctrico
        this.cargarAtaquesElectrico();
    }

    /**
     * Carga ataques específicos para Pokémon de tipo eléctrico
     */
    private void cargarAtaquesElectrico() {
        // Limpiar ataques por defecto y agregar ataques específicos de tipo eléctrico
        this.getAtaques().clear();
        this.agregarAtaque(new Ataque("Impactrueno", 40, "eléctrico"));
        this.agregarAtaque(new Ataque("Rayo", 90, "eléctrico", "Especial"));
        this.agregarAtaque(new Ataque("Trueno", 110, "eléctrico", "Especial"));
        this.agregarAtaque(new Ataque("Onda Voltio", 60, "eléctrico"));
    }

    @Override
    public int atacarBasico(Pokemon objetivo) {
        // Usar un ataque básico de tipo eléctrico
        Ataque ataqueBasico = new Ataque("Impactrueno", 40, "eléctrico");
        return super.atacar(ataqueBasico, objetivo);
    }

    @Override
    public int atacarEspecial(Pokemon objetivo) {
        // Usar un ataque especial de tipo eléctrico
        Ataque ataqueEspecial = new Ataque("Rayo", 90, "eléctrico", "Especial");
        return super.atacar(ataqueEspecial, objetivo);
    }
}

