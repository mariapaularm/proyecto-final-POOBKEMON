package Domain;

public class PokemonPlanta extends Pokemon {

    /**
     * @param nombre Nombre del Pokémon
     * @param imagePath Ruta a la imagen del Pokémon
     * @param nivel Nivel inicial del Pokémon
     * @param hp Puntos de vida iniciales
     * @param ataque Estadística de ataque
     * @param defensa Estadística de defensa
     * @param velocidad Estadística de velocidad
     */
    public PokemonPlanta(String nombre, String imagePath, int nivel, int hp, int ataque, int defensa, int velocidad) {
        super(nombre, "planta", imagePath, nivel, hp, ataque, defensa, velocidad);
        // Cargar ataques específicos de tipo planta
        this.cargarAtaquesPlanta();
    }

    /**
     * Carga ataques específicos para Pokémon de tipo planta
     */
    private void cargarAtaquesPlanta() {
        // Limpiar ataques por defecto y agregar ataques específicos de tipo planta
        this.getAtaques().clear();
        this.agregarAtaque(new Ataque("Látigo Cepa", 45, "planta"));
        this.agregarAtaque(new Ataque("Hoja Afilada", 55, "planta"));
        this.agregarAtaque(new Ataque("Rayo Solar", 120, "planta", "Especial"));
        this.agregarAtaque(new Ataque("Síntesis", 0, "planta", "Estado"));
    }

    @Override
    public int atacarBasico(Pokemon objetivo) {
        // Usar un ataque básico de tipo planta
        Ataque ataqueBasico = new Ataque("Látigo Cepa", 45, "planta");
        int danio = super.atacar(ataqueBasico, objetivo);

        // 20% de probabilidad de recuperar 10% del daño causado
        if (Math.random() < 0.2) {
            this.curar(danio / 10);
        }

        return danio;
    }

    @Override
    public int atacarEspecial(Pokemon objetivo) {
        // Usar un ataque especial de tipo planta
        Ataque ataqueEspecial = new Ataque("Rayo Solar", 120, "planta", "Especial");
        int danio = super.atacar(ataqueEspecial, objetivo);

        // Recupera 15% del daño causado
        this.curar(danio / 7);

        return danio;
    }
}

