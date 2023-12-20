public class FireType extends Pokemon {
    public FireType() {
        super();
        setType("Fire");
    }

    public FireType(String name, int hp, int att, int def) {
        super(name, hp, att, def);
        setType("Fire");
    }

    public double effectiveness(String enemyType) {
        double effectiveness = 1;
        if (enemyType == "Water" || enemyType == "Ground") {
            effectiveness = 2;
        }

        else if (enemyType == "Fire" || enemyType == "Grass" ) {
            effectiveness = 0.5;
        }

        return effectiveness;
    }
}
