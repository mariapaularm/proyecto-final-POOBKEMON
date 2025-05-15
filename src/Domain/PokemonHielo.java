package Domain;

public class PokemonHielo extends Pokemon {

    /**
     * @param nombre Nombre del Pokémon
     * @param imagePath Ruta a la imagen del Pokémon
     * @param nivel Nivel inicial del Pokémon
     * @param hp Puntos de vida iniciales
     * @param ataque Estadística de ataque
     * @param defensa Estadística de defensa
     * @param velocidad Estadística de velocidad
     */
    public PokemonHielo(String nombre, String imagePath, int nivel, int hp, int ataque, int defensa, int velocidad) {
        super(nombre, "hielo", imagePath, nivel, hp, ataque, defensa, velocidad);
        // Cargar ataques específicos de tipo hielo
        this.cargarAtaquesHielo();
    }

    /**
     * Carga ataques específicos para Pokémon de tipo hielo
     */
    private void cargarAtaquesHielo() {
        // Limpiar ataques por defecto y agregar ataques específicos de tipo hielo
        this.getAtaques().clear();
        this.agregarAtaque(new Ataque("Viento Helado", 55, "hielo", "Especial"));
        this.agregarAtaque(new Ataque("Rayo Hielo", 90, "hielo", "Especial"));
        this.agregarAtaque(new Ataque("Ventisca", 110, "hielo", "Especial"));
        this.agregarAtaque(new Ataque("Carámbano", 65, "hielo"));
    }

    @Override
    public int atacarBasico(Pokemon objetivo) {
        // Usar un ataque básico de tipo hielo
        Ataque ataqueBasico = new Ataque("Carámbano", 65, "hielo");
        return super.atacar(ataqueBasico, objetivo);
    }

    @Override
    public int atacarEspecial(Pokemon objetivo) {
        // Usar un ataque especial de tipo hielo
        Ataque ataqueEspecial = new Ataque("Rayo Hielo", 90, "hielo", "Especial");
        return super.atacar(ataqueEspecial, objetivo);
    }
}
