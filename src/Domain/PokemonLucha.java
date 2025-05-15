package Domain;

public class PokemonLucha extends Pokemon {

    /**
     * @param nombre Nombre del Pokémon
     * @param imagePath Ruta a la imagen del Pokémon
     * @param nivel Nivel inicial del Pokémon
     * @param hp Puntos de vida iniciales
     * @param ataque Estadística de ataque
     * @param defensa Estadística de defensa
     * @param velocidad Estadística de velocidad
     */
    public PokemonLucha(String nombre, String imagePath, int nivel, int hp, int ataque, int defensa, int velocidad) {
        super(nombre, "lucha", imagePath, nivel, hp, ataque, defensa, velocidad);
        // Cargar ataques específicos de tipo lucha
        this.cargarAtaquesLucha();
    }

    /**
     * Carga ataques específicos para Pokémon de tipo lucha
     */
    private void cargarAtaquesLucha() {
        // Limpiar ataques por defecto y agregar ataques específicos de tipo lucha
        this.getAtaques().clear();
        this.agregarAtaque(new Ataque("Golpe Kárate", 50, "lucha"));
        this.agregarAtaque(new Ataque("Puño Dinámico", 100, "lucha"));
        this.agregarAtaque(new Ataque("A Bocajarro", 120, "lucha"));
        this.agregarAtaque(new Ataque("Corpulencia", 0, "lucha", "Estado"));
    }

    @Override
    public int atacarBasico(Pokemon objetivo) {
        // Usar un ataque básico de tipo lucha
        Ataque ataqueBasico = new Ataque("Golpe Kárate", 50, "lucha");
        return super.atacar(ataqueBasico, objetivo);
    }

    @Override
    public int atacarEspecial(Pokemon objetivo) {
        // Usar un ataque especial de tipo lucha
        Ataque ataqueEspecial = new Ataque("Puño Dinámico", 100, "lucha");
        return super.atacar(ataqueEspecial, objetivo);
    }
}
