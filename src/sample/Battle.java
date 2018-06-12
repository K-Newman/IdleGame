package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Battle {


    Champion theChamp = new Champion(5,5,5,5);
    Monster Fox = new Monster("Fox",2,3,4, 2,2,"Forest");
    Monster Eagle = new Monster("Eagle",2,2,5, 2,2,"Forest");
    Monster Rabbit = new Monster("Rabbit",1,1,1,1,1,"Forest");
    Monster Wolf = new Monster("Wolf",5,4,3,2,3,"Forest");
    Monster Vagabond = new Monster("Vagabond",10,5,3,4,5,"Forest");

    String zone = "";
    ArrayList<Monster> monsterList = new ArrayList<>();
    int battleTicker;
    int champTicker;
    int monsterTicker;
    boolean monsterKilled = true;
    Monster thisMonster = null;
    public Timeline TimelineNumber2() {
        zone = getCurrentZone();
        champTicker = 0;
        monsterTicker = 0;


        Timeline timelineNumber2 = new Timeline(new KeyFrame(Duration.millis(100), e -> {

            if(monsterKilled){
                Random randGet = new Random();
                monsterList.addAll(Arrays.asList(Fox,Eagle,Rabbit,Wolf,Vagabond));
                thisMonster = monsterList.get(randGet.nextInt(monsterList.size()));
                monsterKilled = false;
                System.out.println("A " + thisMonster.getMonsterName() + " appears!");
            }

            battleTicker++;
            champTicker++;
            monsterTicker++;
            //System.out.println(battleTicker + " " + thisMonster.getMonsterName() + " " + champTicker);
            if(theChamp.getInitiative()<=champTicker){
                if (thisMonster.getMonsterHP() - theChamp.getAttack() <= 0) {
                    System.out.println("The Champ damaged " + thisMonster.getMonsterName() + " by " + thisMonster.getMonsterHP());
                    System.out.println("The Champ is victorious over " + thisMonster.getMonsterName());
                    System.out.println("The Champ gains " + thisMonster.getExp() + " experience");
                    thisMonster.setMonsterHealth(thisMonster.getMonsterHP());
                    theChamp.addExp(thisMonster.getExp());
                    thisMonster.reset();
                    monsterKilled=true;
                    monsterTicker = 0;
                } else {
                    System.out.println("The Champ damaged " + thisMonster.getMonsterName() + " by " + theChamp.getAttack());
                    thisMonster.setMonsterHealth(theChamp.getAttack());
                }
                champTicker = 0;
            }
            if(thisMonster.getInitiative() <= monsterTicker){
                if (theChamp.getHP() - thisMonster.getMonsterAttack() <= 0 && thisMonster.getMonsterHP() > 0) {
                    System.out.println(thisMonster.getMonsterName() + " damaged " + "The Champ by " + theChamp.getHP());
                    System.out.println(thisMonster.getMonsterName() + " is victorious over The Champ");
                    theChamp.setHP(theChamp.getHP());
                } else if (thisMonster.getMonsterHP() > 0) {
                    System.out.println(thisMonster.getMonsterName() + " damaged The Champ by " + thisMonster.getMonsterAttack());
                    theChamp.setHP(thisMonster.getMonsterAttack());
                }
                monsterTicker=0;

            }
        }));
        timelineNumber2.setCycleCount(Animation.INDEFINITE);
        return timelineNumber2;
    }


    public void battleRound(){
        zone = getCurrentZone();
        Timeline battleTimeline = TimelineNumber2();
        System.out.println("Here");
        battleTimeline.play();
        System.out.println("There");
    }


    public String getCurrentZone(){
        return "Forest";
    }
}

