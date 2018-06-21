package sample;

public class Champion {

    int hp;
    int maxHP;
    int attack;
    int constitution;
    int strength;
    int dexterity;
    int intelligence;
    double initiative;
    int exp = 0;
    int level = 1;
    int regen;
    int attacksPerTurn;

    public Champion(int constitution, int strength, int dexterity, int intelligence){
        this.constitution = constitution;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        initiative = (float)500/dexterity;
        hp = (int)Math.floor(constitution * 1.5) + (int)Math.floor(strength * .5);
        maxHP = hp;
        attack = (int)Math.floor(strength * 1.5) + (int)Math.floor(dexterity*.5);
        regen = Math.round(maxHP/6f);
        attacksPerTurn = (int)Math.floor(dexterity/5);
    }

    public int getMaxHP(){return maxHP;}

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
        levelUPChampionStats();
        System.out.println("The Champ gained a level");
    }

    public void levelUPChampionStats(){
        initiative = (float)500/dexterity;
        maxHP = (int)Math.floor(constitution * 1.5) + (int)Math.floor(strength * .5);
        attack = (int)Math.floor(strength * 1.5) + (int)Math.floor(dexterity*.5);
        regen = Math.round(maxHP/6f);
        attacksPerTurn = (int)Math.floor(dexterity/5);
    }

    public void addToHPs(int moreHP){
        if(hp+moreHP>maxHP){
            hp=maxHP;
        } else {
            hp = hp + moreHP;
        }
    }

    public int getStrength(){return strength;}
    public int getConstitution(){return constitution;}
    public int getDexterity(){return dexterity;}
    public int getRegen(){return regen;}
    public int getIntelligence(){return intelligence;}
    public int getLevel(){return level;}
    public int getExperiene(){return exp;}
    public int getAttacksPerTurn(){return attacksPerTurn;}

}
