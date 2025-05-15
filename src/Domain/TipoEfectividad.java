package Domain;


public class TipoEfectividad {
    // Constantes para los multiplicadores de daño
    public static final double SUPER_EFECTIVO = 2.0;    // x2 es superefectivo
    public static final double EFECTIVO = 1.0;          // x1 es efectivo (daño normal)
    public static final double NO_EFECTIVO = 0.5;       // x0.5 es no efectivo (resistencia)
    public static final double SIN_EFECTO = 0.0;        // x0 es sin efecto (inmunidad)

    /**
     * @param tipoAtaque  Tipo del ataque utilizado
     * @param tipoPokemon Tipo del Pokémon que recibe el ataque
     * @return Multiplicador de daño según la efectividad
     */
    public static double calcularMultiplicador(String tipoAtaque, String tipoPokemon) {
        // Normalizamos los tipos a minúsculas para evitar problemas de mayúsculas/minúsculas
        tipoAtaque = tipoAtaque.toLowerCase();
        tipoPokemon = tipoPokemon.toLowerCase();

        // Implementación basada en la tabla de efectividades
        switch (tipoAtaque) {
            case "acero":
                return multiplicadorAtaqueAcero(tipoPokemon);
            case "agua":
                return multiplicadorAtaqueAgua(tipoPokemon);
            case "bicho":
                return multiplicadorAtaqueBicho(tipoPokemon);
            case "dragón":
                return multiplicadorAtaqueDragon(tipoPokemon);
            case "eléctrico":
                return multiplicadorAtaqueElectrico(tipoPokemon);
            case "fantasma":
                return multiplicadorAtaqueFantasma(tipoPokemon);
            case "fuego":
                return multiplicadorAtaqueFuego(tipoPokemon);
            case "hada":
                return multiplicadorAtaqueHada(tipoPokemon);
            case "hielo":
                return multiplicadorAtaqueHielo(tipoPokemon);
            case "lucha":
                return multiplicadorAtaqueLucha(tipoPokemon);
            case "normal":
                return multiplicadorAtaqueNormal(tipoPokemon);
            case "planta":
                return multiplicadorAtaquePlanta(tipoPokemon);
            case "psíquico":
                return multiplicadorAtaquePsiquico(tipoPokemon);
            case "roca":
                return multiplicadorAtaqueRoca(tipoPokemon);
            case "siniestro":
                return multiplicadorAtaqueSiniestro(tipoPokemon);
            case "tierra":
                return multiplicadorAtaqueTierra(tipoPokemon);
            case "veneno":
                return multiplicadorAtaqueVeneno(tipoPokemon);
            case "volador":
                return multiplicadorAtaqueVolador(tipoPokemon);
            default:
                // Si el tipo no existe o no está en la tabla, usamos efectividad normal
                return EFECTIVO;
        }
    }

    // Métodos para cada tipo de ataque
    private static double multiplicadorAtaqueAcero(String tipoPokemon) {
        switch (tipoPokemon) {
            case "acero":
            case "agua":
            case "eléctrico":
            case "fuego":
                return NO_EFECTIVO;
            case "hada":
            case "hielo":
            case "roca":
                return SUPER_EFECTIVO;
            default:
                return EFECTIVO;
        }
    }

    private static double multiplicadorAtaqueAgua(String tipoPokemon) {
        switch (tipoPokemon) {
            case "agua":
            case "dragón":
            case "planta":
                return NO_EFECTIVO;
            case "fuego":
            case "roca":
            case "tierra":
                return SUPER_EFECTIVO;
            default:
                return EFECTIVO;
        }
    }

    private static double multiplicadorAtaqueBicho(String tipoPokemon) {
        switch (tipoPokemon) {
            case "acero":
            case "fantasma":
            case "fuego":
            case "hada":
            case "lucha":
            case "veneno":
            case "volador":
                return NO_EFECTIVO;
            case "planta":
            case "psíquico":
            case "siniestro":
                return SUPER_EFECTIVO;
            default:
                return EFECTIVO;
        }
    }

    private static double multiplicadorAtaqueDragon(String tipoPokemon) {
        switch (tipoPokemon) {
            case "acero":
                return NO_EFECTIVO;
            case "dragón":
                return SUPER_EFECTIVO;
            case "hada":
                return SIN_EFECTO;
            default:
                return EFECTIVO;
        }
    }

    private static double multiplicadorAtaqueElectrico(String tipoPokemon) {
        switch (tipoPokemon) {
            case "dragón":
            case "eléctrico":
            case "planta":
                return NO_EFECTIVO;
            case "agua":
            case "volador":
                return SUPER_EFECTIVO;
            case "tierra":
                return SIN_EFECTO;
            default:
                return EFECTIVO;
        }
    }

    private static double multiplicadorAtaqueFantasma(String tipoPokemon) {
        switch (tipoPokemon) {
            case "siniestro":
                return NO_EFECTIVO;
            case "fantasma":
            case "psíquico":
                return SUPER_EFECTIVO;
            case "normal":
                return SIN_EFECTO;
            default:
                return EFECTIVO;
        }
    }

    private static double multiplicadorAtaqueFuego(String tipoPokemon) {
        switch (tipoPokemon) {
            case "agua":
            case "dragón":
            case "fuego":
            case "roca":
                return NO_EFECTIVO;
            case "acero":
            case "bicho":
            case "hielo":
            case "planta":
                return SUPER_EFECTIVO;
            default:
                return EFECTIVO;
        }
    }

    private static double multiplicadorAtaqueHada(String tipoPokemon) {
        switch (tipoPokemon) {
            case "acero":
            case "fuego":
            case "veneno":
                return NO_EFECTIVO;
            case "dragón":
            case "lucha":
            case "siniestro":
                return SUPER_EFECTIVO;
            default:
                return EFECTIVO;
        }
    }

    private static double multiplicadorAtaqueHielo(String tipoPokemon) {
        switch (tipoPokemon) {
            case "acero":
            case "agua":
            case "fuego":
            case "hielo":
                return NO_EFECTIVO;
            case "dragón":
            case "planta":
            case "tierra":
            case "volador":
                return SUPER_EFECTIVO;
            default:
                return EFECTIVO;
        }
    }

    private static double multiplicadorAtaqueLucha(String tipoPokemon) {
        switch (tipoPokemon) {
            case "bicho":
            case "hada":
            case "psíquico":
            case "veneno":
            case "volador":
                return NO_EFECTIVO;
            case "acero":
            case "hielo":
            case "normal":
            case "roca":
            case "siniestro":
                return SUPER_EFECTIVO;
            case "fantasma":
                return SIN_EFECTO;
            default:
                return EFECTIVO;
        }
    }

    private static double multiplicadorAtaqueNormal(String tipoPokemon) {
        switch (tipoPokemon) {
            case "acero":
            case "roca":
                return NO_EFECTIVO;
            case "fantasma":
                return SIN_EFECTO;
            default:
                return EFECTIVO;
        }
    }

    private static double multiplicadorAtaquePlanta(String tipoPokemon) {
        switch (tipoPokemon) {
            case "acero":
            case "bicho":
            case "dragón":
            case "fuego":
            case "planta":
            case "veneno":
            case "volador":
                return NO_EFECTIVO;
            case "agua":
            case "roca":
            case "tierra":
                return SUPER_EFECTIVO;
            default:
                return EFECTIVO;
        }
    }

    private static double multiplicadorAtaquePsiquico(String tipoPokemon) {
        switch (tipoPokemon) {
            case "acero":
            case "psíquico":
                return NO_EFECTIVO;
            case "lucha":
            case "veneno":
                return SUPER_EFECTIVO;
            case "siniestro":
                return SIN_EFECTO;
            default:
                return EFECTIVO;
        }
    }

    private static double multiplicadorAtaqueRoca(String tipoPokemon) {
        switch (tipoPokemon) {
            case "acero":
            case "lucha":
            case "tierra":
                return NO_EFECTIVO;
            case "bicho":
            case "fuego":
            case "hielo":
            case "volador":
                return SUPER_EFECTIVO;
            default:
                return EFECTIVO;
        }
    }

    private static double multiplicadorAtaqueSiniestro(String tipoPokemon) {
        switch (tipoPokemon) {
            case "hada":
            case "lucha":
            case "siniestro":
                return NO_EFECTIVO;
            case "fantasma":
            case "psíquico":
                return SUPER_EFECTIVO;
            default:
                return EFECTIVO;
        }
    }

    private static double multiplicadorAtaqueTierra(String tipoPokemon) {
        switch (tipoPokemon) {
            case "bicho":
            case "planta":
                return NO_EFECTIVO;
            case "acero":
            case "eléctrico":
            case "fuego":
            case "roca":
            case "veneno":
                return SUPER_EFECTIVO;
            case "volador":
                return SIN_EFECTO;
            default:
                return EFECTIVO;
        }
    }

    private static double multiplicadorAtaqueVeneno(String tipoPokemon) {
        switch (tipoPokemon) {
            case "fantasma":
            case "roca":
            case "tierra":
            case "veneno":
                return NO_EFECTIVO;
            case "hada":
            case "planta":
                return SUPER_EFECTIVO;
            case "acero":
                return SIN_EFECTO;
            default:
                return EFECTIVO;
        }
    }

    private static double multiplicadorAtaqueVolador(String tipoPokemon) {
        switch (tipoPokemon) {
            case "acero":
            case "eléctrico":
            case "roca":
                return NO_EFECTIVO;
            case "bicho":
            case "lucha":
            case "planta":
                return SUPER_EFECTIVO;
            default:
                return EFECTIVO;
        }
    }
}