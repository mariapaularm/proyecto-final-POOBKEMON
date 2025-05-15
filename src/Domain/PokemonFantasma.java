package Domain;

public class PokemonFantasma extends Pokemon {

    /**
     * @param nombre Nombre del Pokémon
     * @param imagePath Ruta a la imagen del Pokémon
     * @param nivel Nivel inicial del Pokémon
     * @param hp Puntos de vida iniciales
     * @param ataque Estadística de ataque
     * @param defensa Estadística de defensa
     * @param velocidad Estadística de velocidad
     */
    public PokemonFantasma(String nombre, String imagePath, int nivel, int hp, int ataque, int defensa, int velocidad) {
        super(nombre, "fantasma", imagePath, nivel, hp, ataque, defensa, velocidad);
        // Cargar ataques específicos de tipo fantasma
        this.cargarAtaquesFantasma();
    }

    /**
     * Carga ataques específicos para Pokémon de tipo fantasma
     */
    private void cargarAtaquesFantasma() {
        // Limpiar ataques por defecto y agregar ataques específicos de tipo fantasma
        this.getAtaques().clear();
        this.agregarAtaque(new Ataque("Puño Sombra", 60, "fantasma"));
        this.agregarAtaque(new Ataque("Bola Sombra", 80, "fantasma", "Especial"));
        this.agregarAtaque(new Ataque("Rayo Confuso", 0, "fantasma", "Estado"));
        this.agregarAtaque(new Ataque("Tinieblas", 65, "fantasma", "Especial"));
    }

    @Override
    public int atacarBasico(Pokemon objetivo) {
        // Usar un ataque básico de tipo fantasma
        Ataque ataqueBasico = new Ataque("Puño Sombra", 60, "fantasma");
        return super.atacar(ataqueBasico, objetivo);
    }

    @Override
    public int atacarEspecial(Pokemon objetivo) {
        // Usar un ataque especial de tipo fantasma
        Ataque ataqueEspecial = new Ataque("Bola Sombra", 80, "fantasma", "Especial");
        return super.atacar(ataqueEspecial, objetivo);
    }
}

