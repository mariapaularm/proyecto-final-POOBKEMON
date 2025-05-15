package Domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public abstract class Pokemon {
    private String nombre;
    private String tipo;
    private String imagePath;
    private int nivel;
    private int hp;
    private int ataque;
    private int defensa;
    private int velocidad;
    private int hpMax;
    private String spriteKey;
    private List<Ataque> ataques = new ArrayList<>();
    private Random random = new Random();

    /**

     *
     * @param nombre Nombre del Pokémon
     * @param tipo Tipo elemental del Pokémon (Fuego, Agua, etc.)
     * @param imagePath Ruta a la imagen del Pokémon
     * @param nivel Nivel actual del Pokémon
     * @param hp Puntos de vida del Pokémon
     * @param ataque Estadística de ataque
     * @param defensa Estadística de defensa
     * @param velocidad Estadística de velocidad
     */
    public Pokemon(String nombre, String tipo, String imagePath, int nivel, int hp, int ataque, int defensa, int velocidad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.imagePath = imagePath;
        this.nivel = nivel;
        this.hp = hp;
        this.ataque = ataque;
        this.defensa = defensa;
        this.velocidad = velocidad;
        this.hpMax = hp;
        this.spriteKey = nombre.replace(" ", "").replace("á", "a").replace("é", "e").replace("í", "i")
                .replace("ó", "o").replace("ú", "u").replace("ñ", "n"); // simplificar para evitar errores
        cargarAtaquesPorDefecto();
    }

    /**

     * @param objetivo El Pokémon que recibe el ataque
     * @return La cantidad de daño causado
     */
    public abstract int atacarBasico(Pokemon objetivo);

    /**
     * @param objetivo El Pokémon que recibe el ataque
     * @return La cantidad de daño causado
     */
    public abstract int atacarEspecial(Pokemon objetivo);

    /**
     * Calcula la efectividad de un tipo de ataque contra este Pokémon
     * @param tipoAtaque El tipo del ataque que recibe el Pokémon
     * @return El multiplicador de efectividad (0.0, 0.5, 1.0, 2.0)
     */
    public double calcularEfectividad(String tipoAtaque) {
        return TipoEfectividad.calcularMultiplicador(tipoAtaque, this.tipo);
    }

    /**
     * @param ataque El ataque a realizar
     * @param objetivo El Pokémon que recibe el ataque
     * @return La cantidad de daño causado
     */
    public int atacar(Ataque ataque, Pokemon objetivo) {
        // Si es un ataque de estado, manejarlo diferente
        if (ataque.getCategoria().equals("Estado")) {
            return manejarAtaqueEstado(ataque, objetivo);
        }

        // Calcular daño base según fórmula de los juegos Pokémon
        // Usamos la fórmula simplificada: ((2 * nivel / 5 + 2) * potencia * ataque / defensa) / 50 + 2
        double nivelFactor = (2.0 * this.nivel / 5.0 + 2.0);
        int estadisticaAtaque = (ataque.getCategoria().equals("Físico")) ? this.ataque : this.ataque; // Podríamos usar estadísticas separadas para ataque especial
        int estadisticaDefensa = (ataque.getCategoria().equals("Físico")) ? objetivo.defensa : objetivo.defensa; // Podríamos usar estadísticas separadas para defensa especial

        double danioBase = (nivelFactor * ataque.getPotencia() * estadisticaAtaque / estadisticaDefensa) / 50.0 + 2.0;


        double efectividad = objetivo.calcularEfectividad(ataque.getTipo());


        double stab = (this.tipo.equalsIgnoreCase(ataque.getTipo())) ? 1.5 : 1.0;

        //  variación aleatoria (entre 0.85 y 1.0)
        double variacion = 0.85 + (random.nextDouble() * 0.15);

        int danioFinal = (int) Math.round(danioBase * efectividad * stab * variacion);

        objetivo.recibirDanio(danioFinal);

        mostrarMensajeEfectividad(efectividad);

        return danioFinal;
    }

    /**
     * @param ataque El ataque de estado
     * @param objetivo El Pokémon objetivo
     * @return 0 ya que los ataques de estado no causan daño directamente
     */
    private int manejarAtaqueEstado(Ataque ataque, Pokemon objetivo) {
        String nombreAtaque = ataque.getNombre().toLowerCase();


        switch (nombreAtaque) {
            case "defensa ardiente":
                this.aumentarDefensa(5);
                System.out.println(this.nombre + " aumentó su defensa usando " + ataque.getNombre() + "!");
                break;
            case "esquivar":

                System.out.println(this.nombre + " se prepara para esquivar el próximo ataque!");
                break;

            default:
                System.out.println(this.nombre + " usó " + ataque.getNombre() + "!");
                break;
        }

        return 0; // Los ataques de estado no causan daño directo
    }

    /**
     * @param efectividad El multiplicador de efectividad
     */
    private void mostrarMensajeEfectividad(double efectividad) {
        if (efectividad == TipoEfectividad.SUPER_EFECTIVO) {
            System.out.println("¡Es superefectivo!");
        } else if (efectividad == TipoEfectividad.NO_EFECTIVO) {
            System.out.println("No es muy efectivo...");
        } else if (efectividad == TipoEfectividad.SIN_EFECTO) {
            System.out.println("No afecta al Pokémon enemigo...");
        }
    }

    /**
     * @param cantidad Cantidad de daño recibido
     * @return true si el Pokémon sigue con vida, false si ha sido derrotado
     */
    public boolean recibirDanio(int cantidad) {
        this.hp -= cantidad;
        if (this.hp < 0) this.hp = 0;
        return this.hp > 0;
    }

    public String getSpriteKey() {
        return spriteKey;
    }

    /**
     * @param cantidad Cantidad de HP a curar
     */
    public void curar(int cantidad) {
        this.hp = Math.min(this.hp + cantidad, this.hpMax);
    }

    /**
     * @param cantidad Cantidad de niveles a aumentar
     */
    public void aumentarNivel(int cantidad) {
        this.nivel += cantidad;
        this.hpMax += (cantidad * 2);
        this.ataque += cantidad;
        this.defensa += cantidad;
        this.velocidad += cantidad;
        this.hp = this.hpMax; // Recuperamos toda la salud al subir de nivel
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getHp() {
        return hp;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    @Override
    public String toString() {
        return nombre + " (Tipo: " + tipo + ", Nivel: " + nivel + ")";
    }

    /**
     * @param cantidad Cantidad de salud a recuperar
     */
    public void recuperarSalud(int cantidad) {
        this.hp = Math.min(this.hp + cantidad, this.hpMax);
    }

    public void setHp(int nuevoHp) {
        this.hp = nuevoHp;
    }

    public List<Ataque> getAtaques() {
        return ataques;
    }

    /**
     * @param ataque El ataque a agregar
     */
    public void agregarAtaque(Ataque ataque) {
        ataques.add(ataque);
    }


    public void cargarAtaquesPorDefecto() {
        if (this.ataques.isEmpty()) {
            this.ataques.add(new Ataque("Placaje", 20, "Normal"));

            // Agregar un ataque típico según el tipo del Pokémon
            switch (this.tipo.toLowerCase()) {
                case "fuego":
                    this.ataques.add(new Ataque("Lanzallamas", 40, "Fuego"));
                    break;
                case "agua":
                    this.ataques.add(new Ataque("Hidrobomba", 40, "Agua"));
                    break;
                case "planta":
                    this.ataques.add(new Ataque("Latigazo", 40, "Planta"));
                    break;
                case "eléctrico":
                    this.ataques.add(new Ataque("Impactrueno", 40, "Eléctrico"));
                    break;
                default:
                    // Ataque genérico para otros tipos
                    this.ataques.add(new Ataque("Ataque Rápido", 40, this.tipo));
                    break;
            }

            // Agregar ataques de estado comunes
            this.ataques.add(new Ataque("Defensa Ardiente", 0, "Defensa", "Estado"));
            this.ataques.add(new Ataque("Esquivar", 0, "Defensa", "Estado"));
        }
    }

    public int getHpMax() {
        return this.hpMax;
    }

    /**
     * @param cantidad Cantidad a aumentar
     */
    public void aumentarDefensa(int cantidad) {
        this.defensa += cantidad;
    }

    /**
     * Obtiene la URL del recurso de imagen
     * @return Ruta de la imagen para el recurso
     */
    private String getImageResource() {
        return imagePath;
    }
}
