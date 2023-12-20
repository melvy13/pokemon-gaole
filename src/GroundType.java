public class GroundType extends Pokemon {
    public GroundType() {
        super();
        setType("Ground");
    }

    public GroundType(String name, int hp, int att, int def) {
        super(name, hp, att, def);
        setType("Ground");
    }

    public double effectiveness(String enemyType) {
        double effectiveness = 1;
        if (enemyType == "Water" || enemyType == "Grass") {
            effectiveness = 2;
        }

        else if (enemyType == "Electric") {
            effectiveness = 0;
        }

        return effectiveness;
    }
}
