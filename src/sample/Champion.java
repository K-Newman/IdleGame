package sample;

public class Champion {

    int hp;
    int attack;
    int constitution;
    int strength;
    int dexterity;
    int intelligence;
    double initiative;
    int exp = 0;
    int level = 1;

    public Champion(int constitution, int strength, int dexterity, int intelligence){
        this.constitution = constitution;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        initiative = (float)500/dexterity;
        hp = (int)Math.floor(constitution * 1.5) + (int)Math.floor(strength * .5);
        attack = (int)Math.floor(strength * 1.5) + (int)Math.floor(dexterity*.5);
    }

    public int getHP(){return hp;}

    public void setHP(int damage){
        hp=hp-damage;
    }

    public double getInitiative(){return initiative;}

    public int getAttack(){return attack;}

    public void addExp(int expAdded){
        this.exp = this.exp + expAdded;
        while(Math.pow(2,level)<this.exp){
            levelUP();
        }
    }

    public void levelUP(){
        level++;
        intelligence++;
        constitution++;
        strength++;
        dexterity++;
        resetChampion();
        System.out.println("The Champ gained a level");
    }

    public void resetChampion(){
        initiative = (float)500/dexterity;
        hp = (int)Math.floor(constitution * 1.5) + (int)Math.floor(strength * .5);
        attack = (int)Math.floor(strength * 1.5) + (int)Math.floor(dexterity*.5);
    }
}
