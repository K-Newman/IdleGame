package sample;

public class Champion {

    int hp = 0;
    int attack = 0;
    int def = 0;
    int constitution = 0;
    int strength = 0;
    int dexterity = 0;
    int intelligence = 0;
    int initiative = 0;

    public Champion(int constitution, int strength, int dexterity, int intelligence){
        initiative = intelligence * 15 + dexterity * 15;
        hp = (int)Math.floor(constitution * 1.5) + (int)Math.floor(strength * .5);
        attack = (int)Math.floor(strength * 1.5) + (int)Math.floor(dexterity*.5);
    }

    public int getHP(){return hp;}

    public void setHP(int damage){
        hp=hp-damage;
    }

    public int getInitiative(){return initiative;}

    public int getAttack(){return attack;}
}
