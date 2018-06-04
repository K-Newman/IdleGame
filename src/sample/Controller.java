package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class Controller implements Initializable {
    public ProgressIndicator archerBar;
    public ProgressIndicator fighterBar;
    public ProgressIndicator mageBar;
    public ProgressIndicator moraleBar;

    public ProgressIndicator ftraining1PB;
    public Label ftrain1comp;
    public Label ftrain1Count;

    public ProgressIndicator atraining1PB;
    public Label atrain1comp;
    public Label atrain1Count;


    public Label archerCountLabel;
    public Label fighterCountLabel;
    public Label mageCountLabel;

    public Label siegeDefense;
    public Label siegeArchers;
    public Label siegeFighters;

    public Label siegeMages;
    public Label siegeLabel;
    public Label rebirthSiegeCount;
    public Label campaignTimer;
    public ImageView I00,I01,I02,I03,I04,I05,I06,I07,I08,I09,I0x;
    public ImageView I10,I11,I12,I13,I14,I15,I16,I17,I18,I19,I1x;
    public ImageView I20,I21,I22,I23,I24,I25,I26,I27,I28,I29,I2x;
    public ImageView I30,I31,I32,I33,I34,I35,I36,I37,I38,I39,I3x;

    public TextField countQuant;

    ArrayList<Archer> Archers = new ArrayList<>();
    ArrayList<Fighter> Fighters = new ArrayList<>();
    ArrayList<Mage> Mages = new ArrayList<>();

    ArrayList<Archer> sArchers = new ArrayList<>();
    ArrayList<Fighter> sFighters = new ArrayList<>();
    ArrayList<Mage> sMages = new ArrayList<>();

    int rebirthTotalSiegePoints = 0;
    int rebirthSiegePoints = 0;

    int archerTicker = 1;
    int aGTicker = 0;
    int aBarTicker = 0;
    int archerCount = 0;
    int archerDiscount = 1;
    int aTickPower = 0;
    int archerDamage = 10;
    int archerHealth = 10;

    int fighterTicker = 5;
    int fGTicker = 0;
    int fBarTicker = 0;
    int fighterCount = 0;
    int fighterDiscount = 1;
    int fTickPower = 0;
    int fighterDamage = 5;
    int fighterHealth = 15;

    int mageTicker = 1;
    int mGTicker = 0;
    int mBarTicker = 0;
    int mageCount = 0;
    int mageDiscount = 1;
    int mTickPower = 1;
    int mageDamage = 15;
    int mageHealth = 5;
    boolean mageUnlocked = false;

    int siegeCount = 1;
    boolean siegeBuilt = false;
    int siegeDef = 0;
    int siegeA = 1;
    int siegeF = 1;
    int siegeM = 1;
    int siegeArcherDamage = 10;

    FighterTraining FT = new FighterTraining();
    ArcherTraining AT = new ArcherTraining();


    long gameTicker = 0;
    String gameTime;

    int capFinder = 0;
    int capCounter = 0;

    public Timeline startTimeline(){

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), e->{
            gameTicker++;
            aGTicker++;
            fGTicker++;

            getTime(gameTicker);
            campaignTimer.setText("Campaign timer: " + gameTime);
            //Archer bar controls
            if(archerTicker/(archerDiscount) < aGTicker){
                aBarTicker = aBarTicker + 1 + aTickPower;
                aGTicker=0;
            }
            if(aBarTicker >=100){
                aBarTicker = aBarTicker-100;
                //Archers.add(new Archer());
                archerCount++;
            }
            //Fighter bar controls
            if(fighterTicker/(fighterDiscount) < fGTicker){
                fBarTicker = fBarTicker + 1 + fTickPower;
                fGTicker=0;
            }
            if(fBarTicker >=100){
                fBarTicker = fBarTicker-100;
                //Fighters.add(new Fighter());
                fighterCount++;
            }
            //Mage bar controls
            if(mageTicker/(mageDiscount) < mGTicker & mageUnlocked){
                mBarTicker = mBarTicker + 1 + mTickPower;
                mGTicker=0;
            }
            if(mBarTicker >=100){
                mBarTicker = mBarTicker-100;
                //Mages.add(new Mage());
                mageCount++;
            }

            //Siege logic
            //Building siege stats
            if(!siegeBuilt) {
                siegeA = siegeCount*4;
                siegeF = siegeCount*5;
                siegeM = siegeCount*2*0;  //Implement mages you lazy cow!!!
                siegeBuilt = true;
                siegeLabel.setText("Siege " + siegeCount);
            }



            archerCountLabel.setText("Archers: " + archerCount);
            archerBar.setProgress(aBarTicker/100f);

            fighterCountLabel.setText("Fighters: " + fighterCount);
            fighterBar.setProgress(fBarTicker/100f);

            mageCountLabel.setText("Magi: " + mageCount);
            mageBar.setProgress(mBarTicker/100f);

            rebirthSiegeCount.setText("Current Siege Points: " + rebirthSiegePoints);

            ftrain1Count.setText(FT.getTraining1Count());
            ftraining1PB.setProgress(FT.getTraining1Progress());
            ftrain1comp.setText(FT.getTraining1Completes() + "");

            atrain1Count.setText(AT.getTraining1Count());
            atraining1PB.setProgress(AT.getTraining1Progress());
            atrain1comp.setText(AT.getTraining1Completes() + "");

        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        return timeline;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        archerBar.setStyle(" -fx-progress-color: red;");
        theGame();
    }

    public void theGame() {
        Timeline playTheGame = startTimeline();
        playTheGame.play();
    }

    public void attackButton(){
        //First phase siege archers attack sieging fighters and archers
        if(siegeA * siegeArcherDamage>=fighterHealth*fighterCount){
            fighterCount = 0;
            if((siegeA*siegeArcherDamage) >= 2*(fighterHealth*fighterCount)){
                archerCount = ((archerHealth*archerCount) - (siegeA*siegeArcherDamage))/archerHealth;
                if(archerCount<0){archerCount=0;}
            }
        }else{
            fighterCount = ((fighterHealth * fighterCount) - (siegeA * siegeArcherDamage)) / fighterHealth;
        }
        //Second phase sieging archers attack siege archers and fighters
        if(siegeA < 1 & siegeDef < 1){
            siegeF = ((siegeF*fighterHealth)-(archerDamage*archerCount))/archerHealth;
        }
        if(archerCount * archerDamage > siegeA*archerHealth){
            siegeA = 0;
        }else {
            siegeA = ((siegeA * archerHealth*2) - (archerCount * archerDamage)) / (archerHealth*2);
        }
        //Third phase sieging fighters attack seige fighters if there is no defense
        if(siegeDef > 0){
            siegeDef = (siegeDef * 100 - (archerDamage*archerCount + fighterDamage*fighterCount + mageDamage+mageCount))/100;
        }else if((siegeF*fighterHealth)>(fighterCount*fighterHealth)){
            siegeF = ((siegeF*fighterHealth)-(fighterCount*fighterHealth))/fighterHealth;
        }else{
            siegeF = 0;
        }
        //Fourth phase siege fighters attack sieging fighters
        if(siegeDef < 1 & siegeF*fighterHealth > fighterHealth*fighterHealth){
            fighterCount = 0;
        }else if(siegeDef < 1){
            fighterCount = ((fighterHealth * fighterCount)-(siegeF * fighterHealth)) / fighterHealth;
            if(fighterCount<0){fighterCount=0;}
        }else{

        }

        //Check to see if siege was successful.  Trigger a rebuild if it was. Add a rebirth point.
        if(siegeA <= 0 & siegeF <= 0 & siegeM <= 0){
            siegeCount++;
            siegeBuilt=false;
            rebirthSiegePoints++;
        }
    }

    public void rebirthButton(){
        rebirthTotalSiegePoints = rebirthTotalSiegePoints + rebirthSiegePoints;
    }

    public String getTime(long gameTicker){

        if(gameTicker % 50 == 0){
            gameTime = String.format("%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(gameTicker*20),
                    TimeUnit.MILLISECONDS.toMinutes(gameTicker*20) -
                    TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(gameTicker*20)),
                    TimeUnit.MILLISECONDS.toSeconds(gameTicker*20) -
                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(gameTicker*20)));
        }
        return gameTime;
    }

    public void Ftraining1PlusButtonClick(){
        if(!countQuant.getText().equals("")){
            fighterCount = FT.addTraining1count(Integer.parseInt(countQuant.getText()), fighterCount);
        }

    }

    public void Ftraining1MinusButtonClick(){
        if(!countQuant.getText().equals("")){
            fighterCount = FT.subTraining1count(Integer.parseInt(countQuant.getText()), fighterCount);
        }
    }

    public void FfiftyPercent1ButtonClick(){
        countQuant.setText(fighterCount/2 + "");
        Ftraining1PlusButtonClick();
    }

    public void Ftraining1CapButtonClick(){
        if(fighterCount > 0) {
            capCounter=1;
            for (double i = FT.getTraining1Cap();  i > fighterCount+Integer.parseInt(FT.getTraining1Count()); i = FT.getTraining1Cap() / (float)capCounter) {
                capCounter++;
            }
            System.out.println(capCounter);
            if(Integer.parseInt(FT.getTraining1Count()) == (int)Math.ceil(FT.getTraining1Cap()/capCounter)){

            }else {
                fighterCount = FT.addTraining1count((int) Math.ceil((FT.getTraining1Cap() / capCounter) - Integer.parseInt(FT.getTraining1Count())), fighterCount);
            }
        }
    }

    //Archer Training
    public void Atraining1PlusButtonClick(){
        if(!countQuant.getText().equals("")){
            archerCount = AT.addTraining1count(Integer.parseInt(countQuant.getText()), archerCount);
        }
    }

    public void Atraining1MinusButtonClick(){
        if(!countQuant.getText().equals("")){
            archerCount = AT.subTraining1count(Integer.parseInt(countQuant.getText()), archerCount);
        }
    }

    public void AfiftyPercent1ButtonClick(){
        countQuant.setText(archerCount/2 + "");
        Atraining1PlusButtonClick();
    }

    public void Atraining1CapButtonClick(){
        if(archerCount > 0) {
            capCounter=1;
            for (double i = AT.getTraining1Cap();  i > archerCount+Integer.parseInt(AT.getTraining1Count()); i = AT.getTraining1Cap() / (float)capCounter) {
                capCounter++;
            }
            System.out.println(capCounter);
            if(Integer.parseInt(AT.getTraining1Count()) == (int)Math.ceil(AT.getTraining1Cap()/capCounter)){

            }else {
                archerCount = AT.addTraining1count((int) Math.ceil((AT.getTraining1Cap() / (capCounter))-Integer.parseInt(AT.getTraining1Count())), archerCount);
            }
        }
    }

}
