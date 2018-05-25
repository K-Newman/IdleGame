package sample;

public class Archer {
    private int baseDamage = 10;
    private int baseHealth = 5;
    private int baseRange = 10;
    private int modDamage = 1;
    private int modHealth = 1;
    private int modRange = 1;
    private int damage = 0;
    private int health = 0;
    private int range = 0;

    public Archer(){
        this.damage = baseDamage*modDamage;
        this.health = baseHealth*modHealth;
        this.range = baseRange*modRange;
    }

    public int getDamage(){
        return this.damage;
    }
    public int getBaseHealth(){return this.health;}
}
