public class Pokemon {
    protected String name, type;
    protected int hp, att, def;

    // Constructors
    public Pokemon() {
        name = "Noname";
        hp = 100;
        att = 100;
        def = 100;
    }

    public Pokemon(String name, int hp, int att, int def) {
        this.name = name;
        this.hp = hp;
        this.att = att;
        this.def = def;
    }

    // Getters & Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public int getAtt() {
        return att;
    }
    public void setAtt(int att) {
        this.att = att;
    }
    public int getDef() {
        return def;
    }
    public void setDef(int def) {
        this.def = def;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public double effectiveness(String enemyType) {
        double effectiveness = 1;
        return effectiveness;
    }
    
    @Override
    public String toString() {
        return String.format("%s [%s] HP: %d Attack: %d Defense: %d", name, type, hp, att, def);
    }
}
