package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Battle {


    Champion theChamp = new Champion(5,5,10,5);
    Monster Fox = new Monster("Fox",3,3,4, 2,2,"Forest");
    Monster Eagle = new Monster("Eagle",3,2,5, 2,2,"Forest");
    Monster Rabbit = new Monster("Rabbit",2,1,1,1,1,"Forest");
    Monster Wolf = new Monster("Wolf",7,4,3,2,3,"Forest");
    Monster Vagabond = new Monster("Vagabond",12,5,3,4,5,"Forest");

    Monster Goblin = new Monster("Goblin",14,8,8,4,5,"Goblin Caves");
    Monster HalfOrc = new Monster("Half-Orc",16,10,6,3,8,"Goblin Caves");
    Monster Slave = new Monster("Slave",10,5,5,5,4,"Goblin Caves");
    Monster GoblinWizard = new Monster("Goblin Wizard",10,6,8,15,10,"Goblin Caves");
    Monster OrcCaptain = new Monster("Orc Captain",20,13,10,8,15,"Goblin Caves");
    Monster OrcWarlord = new Monster("Orc Warlord",25,25,15,10,20,"Goblin Caves");


    String zone = "";
    ArrayList<Monster> monsterList = new ArrayList<>();
    ArrayList<Monster> goblinCavesList = new ArrayList<>();
    ArrayList<Monster> forestList = new ArrayList<>();
    ArrayList<Monster> bigMonsterList = new ArrayList<>();

    int battleTicker;
    int champTicker;
    int monsterTicker;
    boolean monsterKilled = true;
    Monster thisMonster = null;
    String textOutput = "";
    boolean changedStatus;
    int newMonsterTicker = 0;
    int regenTicker = 0;
    String currentZone = "None";
    Timeline battleTimeline = new Timeline();

    public Timeline TimelineNumber2() {
        bigMonsterList.addAll(Arrays.asList(Goblin,HalfOrc,Slave,GoblinWizard,OrcCaptain,OrcWarlord,Fox,Eagle,Rabbit,Wolf,Vagabond));
        if(!zone.equals(currentZone)){
            monsterList.clear();
            zone = currentZone;
            System.out.println(zone + " " + currentZone);
            for(Monster monster : bigMonsterList){
                if(monster.getZone().equals(zone)){
                    monsterList.add(monster);
                }
            }
        }
        zone = currentZone;
        champTicker = 0;
        monsterTicker = 0;
        goblinCavesList.addAll(Arrays.asList(Goblin,HalfOrc,Slave,GoblinWizard,OrcCaptain,OrcWarlord));
        forestList.addAll(Arrays.asList(Fox,Eagle,Rabbit,Wolf,Vagabond));
        Timeline timelineNumber2 = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            regenTicker++;
            if(currentZone.equals("Silvermere")){
                thisMonster = null;
                monsterList.clear();
            }
            if(regenTicker>=100){
                theChamp.addToHPs(theChamp.getRegen());
                regenTicker = 0;
            }
            if(monsterKilled){
                newMonsterTicker++;
            }

            double compareThis = Math.random();
            if(monsterKilled & compareThis < newMonsterTicker/1000f & !currentZone.equals("Silvermere")){
                Random randGet = new Random();
                thisMonster = monsterList.get(randGet.nextInt(monsterList.size()));
                monsterKilled = false;
                textOutput = textOutput + getHealthDisplay() + "A " + thisMonster.getMonsterName() + " appears!" + "\n";
                System.out.println("A " + thisMonster.getMonsterName() + " appears!");
                changedStatus = true;
                newMonsterTicker = 0;
            }

            battleTicker++;
            if(monsterKilled == false & !currentZone.equals("Silvermere")) {
                champTicker++;
                monsterTicker++;
                    }
                    //System.out.println(battleTicker + " " + champTicker + " " + Math.random() + " " + newMonsterTicker/1000f + " " + monsterKilled);
                    if(!monsterKilled) {
                        if (theChamp.getInitiative() <= champTicker) {
                            for(int i = 1;i<=theChamp.getAttacksPerTurn();i++) {
                                if (thisMonster.getMonsterHP() - theChamp.getAttack() <= 0) {
                                    textOutput = textOutput + getHealthDisplay() + "The Champ damaged " + thisMonster.getMonsterName() + " by " + thisMonster.getMonsterHP() + "\n";
                                    textOutput = textOutput + getHealthDisplay() + "The Champ is victorious over " + thisMonster.getMonsterName() + "\n";
                                    textOutput = textOutput + getHealthDisplay() + "The Champ gains " + thisMonster.getExp() + " experience" + "\n";
                                    System.out.println("The Champ damaged " + thisMonster.getMonsterName() + " by " + thisMonster.getMonsterHP());
                                    System.out.println("The Champ is victorious over " + thisMonster.getMonsterName());
                                    System.out.println("The Champ gains " + thisMonster.getExp() + " experience");
                                    thisMonster.setMonsterHealth(thisMonster.getMonsterHP());
                                    theChamp.addExp(thisMonster.getExp());
                                    thisMonster.reset();
                                    monsterKilled = true;
                                    monsterTicker = 0;
                                    i=theChamp.getAttacksPerTurn();
                                } else {
                                    textOutput = textOutput + getHealthDisplay() + "The Champ damaged " + thisMonster.getMonsterName() + " by " + thisMonster.getMonsterHP() + "\n";
                                    System.out.println("The Champ damaged " + thisMonster.getMonsterName() + " by " + theChamp.getAttack());
                                    thisMonster.setMonsterHealth(theChamp.getAttack());
                                }
                            }

                    champTicker = 0;
                    changedStatus = true;
                }
                if (thisMonster.getInitiative() <= monsterTicker) {
                    if (theChamp.getHP() - thisMonster.getMonsterAttack() <= 0 && thisMonster.getMonsterHP() > 0) {
                        textOutput = textOutput + getHealthDisplay() + thisMonster.getMonsterName() + " damaged The Champ by " + theChamp.getHP() + "\n";
                        textOutput = textOutput + getHealthDisplay() + thisMonster.getMonsterName() + " is victorious over The Champ \n";
                        System.out.println(thisMonster.getMonsterName() + " damaged The Champ by " + theChamp.getHP());
                        System.out.println(thisMonster.getMonsterName() + " is victorious over The Champ");
                        theChamp.setHP(theChamp.getHP());
                        currentZone = "Silvermere";
                        monsterKilled = true;
                        textOutput = textOutput + getHealthDisplay() + "By the grace of the gods you find yourself in the halls of the dead \n";
                    } else if (thisMonster.getMonsterHP() > 0) {
                        textOutput = textOutput + getHealthDisplay() + thisMonster.getMonsterName() + " damaged The Champ by " + thisMonster.getMonsterAttack() + "\n";
                        System.out.println(thisMonster.getMonsterName() + " damaged The Champ by " + thisMonster.getMonsterAttack());
                        theChamp.setHP(thisMonster.getMonsterAttack());
                    }
                    monsterTicker = 0;
                    changedStatus = true;
                }
            }
        }));
        timelineNumber2.setCycleCount(Animation.INDEFINITE);
        return timelineNumber2;
    }


    public void battleRound(){
        if(currentZone.equals("Silvermere")){

        }else {
            if (battleTimeline.getStatus().equals(Animation.Status.RUNNING)) {
                battleTimeline.stop();
                battleTimeline = TimelineNumber2();
                battleTimeline.play();
                System.out.println("We are here");
                System.out.println(battleTimeline.getStatus());
            } else {
                battleTimeline = TimelineNumber2();
                battleTimeline.play();
                System.out.println("We are there");
                System.out.println(battleTimeline.getStatus());
            }
        }
    }


    public void setCurrentZone(String currentZ){
        currentZone = currentZ;
        System.out.println(currentZ + " Is the current zone");
    }

    public String getTextOutput(){
        changedStatus = false;
        return textOutput;
    }

    public boolean getChangedStatus(){
        return changedStatus;
    }

    public String getHealthDisplay(){
        return "[HP: " + theChamp.getHP() + "/" + theChamp.getMaxHP() + "] ";
    }

    public String getStrength(){return theChamp.getStrength() + "";}
    public String getConstitution(){return theChamp.getConstitution() + "";}
    public String getDexterity(){return theChamp.getDexterity() + "";}
    public String getIntelligence(){return theChamp.getIntelligence() + "";}
    public String getHP(){return theChamp.getHP() + "";}
    public String getRegen(){return theChamp.getRegen() + "";}
    public String getInitiative(){return theChamp.getInitiative() + "";}
    public String getLevel(){return theChamp.getLevel() + "";}
    public String getExperience(){return theChamp.getExperiene() + "";}

    public String getCurrentZone(){return currentZone;}

}

