package Domain;

public class HyperPotion extends Item {
    private static final int PUNTOS_RECUPERACION = 200;

    public HyperPotion(String imagePath) {
        super("Hiperpoción",
                "Las hiperpociones recuperan 200 puntos...",
                imagePath);
    }

    @Override
    public boolean usar(Pokemon pokemon) {
        if (pokemon.getHp() <= 0) return false;
        pokemon.recuperarSalud(PUNTOS_RECUPERACION);
        return true;
    }
}