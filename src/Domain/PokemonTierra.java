package Domain;

public class PokemonTierra extends Pokemon {

    /**
     * @param nombre Nombre del Pokémon
     * @param imagePath Ruta a la imagen del Pokémon
     * @param nivel Nivel inicial del Pokémon
     * @param hp Puntos de vida iniciales
     * @param ataque Estadística de ataque
     * @param defensa Estadística de defensa
     * @param velocidad Estadística de velocidad
     */
    public PokemonTierra(String nombre, String imagePath, int nivel, int hp, int ataque, int defensa, int velocidad) {
        super(nombre, "tierra", imagePath, nivel, hp, ataque, defensa, velocidad);
        // Cargar ataques específicos de tipo tierra
        this.cargarAtaquesTierra();
    }

    /**
     * Carga ataques específicos para Pokémon de tipo tierra
     */
    private void cargarAtaquesTierra() {
        // Limpiar ataques por defecto y agregar ataques específicos de tipo tierra
        this.getAtaques().clear();
        this.agregarAtaque(new Ataque("Bofetón Lodo", 55, "tierra"));
        this.agregarAtaque(new Ataque("Terremoto", 100, "tierra"));
        this.agregarAtaque(new Ataque("Fisura", 130, "tierra"));
        this.agregarAtaque(new Ataque("Excavar", 80, "tierra"));
    }

    @Override
    public int atacarBasico(Pokemon objetivo) {
        // Usar un ataque básico de tipo tierra
        Ataque ataqueBasico = new Ataque("Bofetón Lodo", 55, "tierra");
        return super.atacar(ataqueBasico, objetivo);
    }

    @Override
    public int atacarEspecial(Pokemon objetivo) {
        // Usar un ataque especial de tipo tierra
        Ataque ataqueEspecial = new Ataque("Terremoto", 100, "tierra");
        return super.atacar(ataqueEspecial, objetivo);
    }
}

