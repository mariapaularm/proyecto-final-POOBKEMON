package Domain;

public class Potion extends Item {
    private static final int PUNTOS_RECUPERACION = 20;

    public Potion(String imagePath) {
        super("Poción",
                "Las pociones recuperan 20 puntos de salud...",
                imagePath);
    }

    @Override
    public boolean usar(Pokemon pokemon) {
        if (pokemon.getHp() <= 0) return false;
        pokemon.recuperarSalud(PUNTOS_RECUPERACION);
        return true;
    }
}