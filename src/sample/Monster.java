package sample;


public class Monster {
    String name = "";
    int hp = 0;
    int attack = 0;
    int def = 0;
    int constitution = 0;
    int strength = 0;
    int dexterity = 0;
    int intelligence = 0;
    double initiative = 0;
    int baseHP = 0;
    int exp;
    String zone;


    public Monster(String name, int constitution, int strength, int dexterity, int intelligence, int exp, String zone){
        this.name = name;
        constitution = constitution;
        strength = strength;
        dexterity = dexterity;
        intelligence = intelligence;
        initiative = (float)500/dexterity;
        hp = (int)Math.floor(constitution * 1.5) + (int)Math.floor(strength * .5);
        attack = (int)Math.floor(strength * 1.5) + (int)Math.floor(dexterity*.5);
        this.zone = zone;
        baseHP = hp;
        this.exp=exp;
    }

    public String getMonsterName(){
        return(name);
    }

    public int getMonsterHP(){

        return(hp);
    }

    public int getMonsterAttack(){return(this.attack);}

    public void setMonsterHealth(int damage){
        hp = hp - damage;
    }

    public double getInitiative(){
        return initiative;
    }
    public void reset(){
        hp=baseHP;
    }

    public int getExp(){return exp;}

    public String getZone(){return zone;}

}
