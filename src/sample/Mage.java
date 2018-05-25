package sample;

public class Mage {
    private int baseDamage = 15;
    private int baseHealth = 5;
    private int baseRange = 5;
    private int modDamage = 1;
    private int modHealth = 1;
    private int modRange = 1;
    private int damage = 0;
    private int health = 0;
    private int range = 0;

    public Mage(){
        this.damage = baseDamage*modDamage;
        this.health = baseHealth*modHealth;
        this.range = baseRange*modRange;
    }

    public int getDamage(){
        return this.damage;
    }

    public int getBaseHealth(){return this.health;}
}
