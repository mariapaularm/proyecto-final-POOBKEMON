package Tests;

import Domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestJuegoPokemon {

    private PokemonAgua squirtle;
    private PokemonAgua dummyEnemigo;

    private Potion potion;
    private SuperPotion superPotion;
    private HyperPotion hyperPotion;
    private Revive revive;

    private Entrenador entrenador;

    @BeforeEach
    void setUp() {
        squirtle = new PokemonAgua("Squirtle", "ruta.png", 10, 100, 30, 20, 20);
        dummyEnemigo = new PokemonAgua("Dummy", "ruta.png", 5, 100, 15, 10, 10);

        potion = new Potion("potion.png");
        superPotion = new SuperPotion("superpotion.png");
        hyperPotion = new HyperPotion("hyperpotion.png");
        revive = new Revive("revive.png");

        entrenador = new Entrenador("Ash", "Entrenador joven", "ash.png", 5, "Fuego");
    }

    // -------------------- Pokemon en general --------------------

    @Test
    void testRecibirDanio() {
        boolean sigueVivo = squirtle.recibirDanio(30);
        assertEquals(70, squirtle.getHp());
        assertTrue(sigueVivo);

        sigueVivo = squirtle.recibirDanio(100);
        assertEquals(0, squirtle.getHp());
        assertFalse(sigueVivo);
    }

    @Test
    void testCurar() {
        squirtle.recibirDanio(40);
        squirtle.curar(20);
        assertEquals(80, squirtle.getHp());
    }

    @Test
    void testRecuperarSaludMaxima() {
        squirtle.recibirDanio(10);
        squirtle.recuperarSalud(20);
        assertEquals(100, squirtle.getHp()); // No supera el máximo
    }

    @Test
    void testAumentarNivel() {
        squirtle.aumentarNivel(3);
        assertEquals(13, squirtle.getNivel());
    }

    @Test
    void testAtacarBasicoYSpecial() {
        int hpInicial = dummyEnemigo.getHp();
        int danio = squirtle.atacarBasico(dummyEnemigo);
        assertTrue(danio > 0);
        assertEquals(hpInicial - danio, dummyEnemigo.getHp());

        hpInicial = dummyEnemigo.getHp();
        danio = squirtle.atacarEspecial(dummyEnemigo);
        assertTrue(danio > 0);
        assertEquals(hpInicial - danio, dummyEnemigo.getHp());
    }

    @Test
    void testAtacarConAtaqueObjeto() {
        Ataque ataque = new Ataque("Pistola Agua", 30, "Agua");
        int hpInicial = dummyEnemigo.getHp();
        int danio = squirtle.atacar(ataque, dummyEnemigo);
        assertTrue(danio > 0);
        assertEquals(hpInicial - danio, dummyEnemigo.getHp());
    }



    // -------------------- PRUEBAS ENTRENADOR --------------------

    @Test
    void testEntrenadorGettersSetters() {
        assertEquals("Ash", entrenador.getNombre());
        entrenador.setNombre("Misty");
        assertEquals("Misty", entrenador.getNombre());

        entrenador.setDescripcion("Especialista en agua");
        assertEquals("Especialista en agua", entrenador.getDescripcion());

        entrenador.setNivelInicial(8);
        assertEquals(8, entrenador.getNivel());

        entrenador.setEspecialidad("Agua");
        assertEquals("Agua", entrenador.getEspecialidad());
    }

    @Test
    void testEntrenadorToString() {
        assertTrue(entrenador.toString().contains("Entrenador"));
    }

    // -------------------- PRUEBAS ITEMS --------------------

    @Test
    void testPotion() {
        squirtle.recibirDanio(30);
        assertTrue(potion.usar(squirtle));
        assertEquals(90, squirtle.getHp());
    }

    @Test
    void testSuperPotion() {
        squirtle.recibirDanio(60);
        assertTrue(superPotion.usar(squirtle));
        assertEquals(90, squirtle.getHp());
    }

    @Test
    void testHyperPotion() {
        squirtle.recibirDanio(50);
        assertTrue(hyperPotion.usar(squirtle));
        assertEquals(100, squirtle.getHp());
    }

    @Test
    void testReviveUsar() {
        squirtle.recibirDanio(100); // HP = 0
        assertTrue(revive.usar(squirtle));
        assertEquals(50, squirtle.getHp());
    }

    @Test
    void testReviveNoSePuedeUsarSiPokemonVivo() {
        assertFalse(revive.usar(squirtle));
    }
}
