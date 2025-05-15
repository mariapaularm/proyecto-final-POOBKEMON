package Domain;

public class PokemonAcero extends Pokemon {

    /**
     * @param nombre Nombre del Pokémon
     * @param imagePath Ruta a la imagen del Pokémon
     * @param nivel Nivel inicial del Pokémon
     * @param hp Puntos de vida iniciales
     * @param ataque Estadística de ataque
     * @param defensa Estadística de defensa
     * @param velocidad Estadística de velocidad
     */
    public PokemonAcero(String nombre, String imagePath, int nivel, int hp, int ataque, int defensa, int velocidad) {
        super(nombre, "acero", imagePath, nivel, hp, ataque, defensa, velocidad);
        // Cargar ataques específicos de tipo acero
        this.cargarAtaquesAcero();
    }

    /**
     * Carga ataques específicos para Pokémon de tipo acero
     */
    private void cargarAtaquesAcero() {
        // Limpiar ataques por defecto y agregar ataques específicos de tipo acero
        this.getAtaques().clear();
        this.agregarAtaque(new Ataque("Garra Metal", 50, "acero"));
        this.agregarAtaque(new Ataque("Cabeza de Hierro", 80, "acero"));
        this.agregarAtaque(new Ataque("Puño Bala", 40, "acero"));
        this.agregarAtaque(new Ataque("Defensa Férrea", 0, "acero", "Estado"));
    }

    @Override
    public int atacarBasico(Pokemon objetivo) {
        // Usar un ataque básico de tipo acero
        Ataque ataqueBasico = new Ataque("Garra Metal", 50, "acero");
        return super.atacar(ataqueBasico, objetivo);
    }

    @Override
    public int atacarEspecial(Pokemon objetivo) {
        // Usar un ataque especial de tipo acero
        Ataque ataqueEspecial = new Ataque("Cabeza de Hierro", 80, "acero", "Especial");
        return super.atacar(ataqueEspecial, objetivo);
    }
}


