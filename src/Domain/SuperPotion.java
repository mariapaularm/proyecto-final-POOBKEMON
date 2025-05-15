package Domain;

public class SuperPotion extends Item {
    private static final int PUNTOS_RECUPERACION = 50;

    public SuperPotion(String imagePath) {
        super("Superpoción",
                "Las superpociones recuperan 50 puntos...",
                imagePath);
    }

    @Override
    public boolean usar(Pokemon pokemon) {
        if (pokemon.getHp() <= 0) return false;
        pokemon.recuperarSalud(PUNTOS_RECUPERACION);
        return true;
    }
}
