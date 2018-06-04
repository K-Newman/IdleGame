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
    public Label siegeAttack;
    public Label siegeTotalPower;

    public Label yourAttack;
    public Label yourDefence;
    public Label yourTotalPower;


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
    double morale=1;

    long yourTP = 0;
    long yourAttackPower = 0;
    long yourDefPower = 0;



    FighterTraining FT = new FighterTraining();
    ArcherTraining AT = new ArcherTraining();
    Siege siege = new Siege();


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
                siege.siegeClear();
                siegeAttack.setText("Attack: " +siege.getCurrentSiegeAttack());
                siegeDefense.setText("Defense: " + siege.getCurrentSiegeDef());
                siegeTotalPower.setText("Total Power: " + siege.getSeigeTotalPower());
                siegeLabel.setText("Siege " + siegeCount);
                siegeBuilt = siege.getSiegeStatus();
            }

            //Determine your power
            yourAttackPower = archerCount*10+fighterCount*5+(int)Math.floor(AT.getTraining1Completes()*.1*archerCount);
            yourDefPower = fighterCount*10+archerCount*2;
            yourTP=yourAttackPower+yourDefPower;



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

            yourTotalPower.setText("Total Power: " + yourTP);
            yourAttack.setText("Attack: " + yourAttackPower);
            yourDefence.setText("Defence: " + yourDefPower);

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
        if(yourTP > siege.getSeigeTotalPower()*3){
            siege.attackSiege();
            rebirthSiegePoints = rebirthSiegePoints+1;
            siegeBuilt = siege.getSiegeStatus();
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


    //Fighter Training
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
