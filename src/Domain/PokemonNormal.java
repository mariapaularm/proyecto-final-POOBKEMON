package Domain;

public class PokemonNormal extends Pokemon {

    /**
     * @param nombre Nombre del Pokémon
     * @param imagePath Ruta a la imagen del Pokémon
     * @param nivel Nivel inicial del Pokémon
     * @param hp Puntos de vida iniciales
     * @param ataque Estadística de ataque
     * @param defensa Estadística de defensa
     * @param velocidad Estadística de velocidad
     */
    public PokemonNormal(String nombre, String imagePath, int nivel, int hp, int ataque, int defensa, int velocidad) {
        super(nombre, "normal", imagePath, nivel, hp, ataque, defensa, velocidad);
        // Cargar ataques específicos de tipo normal
        this.cargarAtaquesNormal();
    }

    /**
     * Carga ataques específicos para Pokémon de tipo normal
     */
    private void cargarAtaquesNormal() {
        // Limpiar ataques por defecto y agregar ataques específicos de tipo normal
        this.getAtaques().clear();
        this.agregarAtaque(new Ataque("Placaje", 40, "normal"));
        this.agregarAtaque(new Ataque("Golpe Cuerpo", 85, "normal"));
        this.agregarAtaque(new Ataque("Hiperrayo", 150, "normal", "Especial"));
        this.agregarAtaque(new Ataque("Agilidad", 0, "normal", "Estado"));
    }

    @Override
    public int atacarBasico(Pokemon objetivo) {
        // Usar un ataque básico de tipo normal
        Ataque ataqueBasico = new Ataque("Placaje", 40, "normal");
        return super.atacar(ataqueBasico, objetivo);
    }

    @Override
    public int atacarEspecial(Pokemon objetivo) {
        // Usar un ataque especial de tipo normal
        Ataque ataqueEspecial = new Ataque("Hiperrayo", 150, "normal", "Especial");
        return super.atacar(ataqueEspecial, objetivo);
    }
}
