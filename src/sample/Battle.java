package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Battle {


    Champion theChamp = new Champion(5,5,5,5);
    Monster Fox = new Monster("Fox",2,3,4, 2,"Forest");
    Monster Eagle = new Monster("Eagle",2,2,5, 2,"Forest");
    Monster Rabbit = new Monster("Rabbit",1,1,1,1,"Forest");
    Monster Wolf = new Monster("Wolf",5,4,3,2,"Forest");
    Monster Vagabond = new Monster("Vagabond",10,5,3,4,"Forest");

    String zone = "";
    ArrayList<Monster> monsterList = new ArrayList<>();

    public void battleInit(){
        monsterList.addAll(Arrays.asList(Fox,Eagle,Rabbit,Wolf,Vagabond));
    }

    public void battleRound(){
        battleInit();
        System.out.println(theChamp.getHP());
        Random randGet = new Random();
        zone = getCurrentZone();
        Monster thisMonster = monsterList.get(randGet.nextInt(monsterList.size()));


        while(thisMonster.getMonsterHP() > 0 && theChamp.getHP() > 0) {

            if(theChamp.getInitiative() > thisMonster.getInitiative()) {
                if (thisMonster.getMonsterHP() - theChamp.getAttack() <= 0) {
                    System.out.println("The Champ damaged " + thisMonster.getMonsterName() + " by " + thisMonster.getMonsterHP());
                    System.out.println("The Champ is victorious over " + thisMonster.getMonsterName());
                    thisMonster.setMonsterHealth(thisMonster.getMonsterHP());
                } else {
                    System.out.println("The Champ damaged " + thisMonster.getMonsterName() + " by " + theChamp.getAttack());
                    thisMonster.setMonsterHealth(theChamp.getAttack());
                }

                if (theChamp.getHP() - thisMonster.getMonsterAttack() <= 0 && thisMonster.getMonsterHP() > 0) {
                    System.out.println(thisMonster.getMonsterName() + " damaged " + "The Champ by " + theChamp.getHP());
                    System.out.println(thisMonster.getMonsterName() + " is victorious over The Champ");
                    theChamp.setHP(theChamp.getHP());
                } else if (thisMonster.getMonsterHP() > 0) {
                    System.out.println(thisMonster.getMonsterName() + " damaged The Champ by " + thisMonster.getMonsterAttack());
                    theChamp.setHP(thisMonster.getMonsterAttack());
                }

            } else if(thisMonster.getInitiative() <= theChamp.getInitiative()){
                if (theChamp.getHP() - thisMonster.getMonsterAttack() <= 0 && thisMonster.getMonsterHP() > 0) {
                    System.out.println(thisMonster.getMonsterName() + " damaged The Champ by " + theChamp.getHP());
                    System.out.println(thisMonster.getMonsterName() + " is victorious over The Champ");
                    theChamp.setHP(theChamp.getHP());
                } else {
                    System.out.println(thisMonster.getMonsterName() + " damaged The Champ by " + thisMonster.getMonsterAttack());
                    theChamp.setHP(thisMonster.getMonsterAttack());
                }
                if (thisMonster.getMonsterHP() - theChamp.getAttack() <= 0) {
                    System.out.println("The Champ damaged " + thisMonster.getMonsterName() + " by " + thisMonster.getMonsterHP());
                    System.out.println("The Champ is victorious over " + thisMonster.getMonsterName());
                    thisMonster.setMonsterHealth(thisMonster.getMonsterHP());
                } else if (theChamp.getHP() > 0){
                    System.out.println("The Champ damaged " + thisMonster.getMonsterName() + " by " + theChamp.getAttack());
                    thisMonster.setMonsterHealth(theChamp.getAttack());
                }
            }
        }
        thisMonster.reset();
    }


    public String getCurrentZone(){
        return "Forest";
    }
}

