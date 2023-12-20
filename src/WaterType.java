public class WaterType extends Pokemon {
    public WaterType() {
        super();
        setType("Water");
    }

    public WaterType(String name, int hp, int att, int def) {
        super(name, hp, att, def);
        setType("Water");
    }

    public double effectiveness(String enemyType) {
        double effectiveness = 1;
        if (enemyType == "Grass" || enemyType == "Electric") {
            effectiveness = 2;
        }

        else if (enemyType == "Fire" || enemyType == "Water") {
            effectiveness = 0.5;
        }

        return effectiveness;
    }
}
