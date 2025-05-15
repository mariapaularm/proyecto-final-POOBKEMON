package Domain;

public class Revive extends Item {
    private static final double FACTOR_RECUPERACION = 0.5;

    public Revive(String imagePath) {
        super("Revivir",
                "Los revivir solo son aplicables...",
                imagePath);
    }

    @Override
    public boolean usar(Pokemon pokemon) {
        if (pokemon.getHp() > 0) return false;
        pokemon.setHp((int)(pokemon.getHpMax() * FACTOR_RECUPERACION));
        return true;
    }
}
