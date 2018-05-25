package sample;

public class Fighter {
    private int baseDamage = 5;
    private int baseHealth = 15;
    private int baseRange = 1;
    private int modDamage = 1;
    private int modHealth = 1;
    private int modRange = 1;
    private int damage = 0;
    private int health = 0;
    private int range = 0;

    public Fighter(){
        this.damage = baseDamage*modDamage;
        this.health = baseHealth*modHealth;
        this.range = baseRange*modRange;
    }

    public int getDamage(){
        return this.damage;
    }
    public int getBaseHealth(){return this.health;}
    public int getHealth(){return health;}
}
