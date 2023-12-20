public class GrassType extends Pokemon {
    public GrassType() {
        super();
        setType("Grass");
    }

    public GrassType(String name, int hp, int att, int def) {
        super(name, hp, att, def);
        setType("Grass");
    }

    public double effectiveness(String enemyType) {
        double effectiveness = 1;
        if (enemyType == "Fire") {
            effectiveness = 2;
        }

        else if (enemyType == "Water" || enemyType == "Grass" || enemyType == "Electric" || enemyType == "Ground") {
            effectiveness = 0.5;
        }

        return effectiveness;
    }
}
