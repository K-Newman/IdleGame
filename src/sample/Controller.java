package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

import java.net.URL;
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
    public ProgressIndicator ftraining2PB;
    public Label ftrain2comp;
    public Label ftrain2Count;
    public ProgressIndicator ftraining3PB;
    public Label ftrain3comp;
    public Label ftrain3Count;
    public ProgressIndicator ftraining4PB;
    public Label ftrain4comp;
    public Label ftrain4Count;
    public ProgressIndicator ftraining5PB;
    public Label ftrain5comp;
    public Label ftrain5Count;
    public ProgressIndicator ftraining6PB;
    public Label ftrain6comp;
    public Label ftrain6Count;

    public ProgressIndicator atraining1PB;
    public Label atrain1comp;
    public Label atrain1Count;
    public ProgressIndicator atraining2PB;
    public Label atrain2comp;
    public Label atrain2Count;
    public ProgressIndicator atraining3PB;
    public Label atrain3comp;
    public Label atrain3Count;
    public ProgressIndicator atraining4PB;
    public Label atrain4comp;
    public Label atrain4Count;
    public ProgressIndicator atraining5PB;
    public Label atrain5comp;
    public Label atrain5Count;
    public ProgressIndicator atraining6PB;
    public Label atrain6comp;
    public Label atrain6Count;

    public Label rebirthSP;
    public Label archerTrainingCurrentSpeed;
    public Label archerTrainingCurrentCount;
    public Label archerTrainingCurrentAttack;
    public Label siegePointsLabel;


    public Label archerCountLabel;
    public Label fighterCountLabel;
    public Label mageCountLabel;

    public Label siegeDefense;
    public Label siegeAttack;
    public Label siegeTotalPower;

    public Label yourAttack;
    public Label yourDefence;
    public Label yourTotalPower;


    public Label siegeLabel;
    public Label rebirthSiegeCount;
    public Label campaignTimer;
    public ImageView I00,I01,I02,I03,I04,I05,I06,I07,I08,I09,I0x;
    public ImageView I10,I11,I12,I13,I14,I15,I16,I17,I18,I19,I1x;
    public ImageView I20,I21,I22,I23,I24,I25,I26,I27,I28,I29,I2x;
    public ImageView I30,I31,I32,I33,I34,I35,I36,I37,I38,I39,I3x;

    public TextField countQuant;

    public TextArea adventureTextArea;
    public Label championLevelLabel;
    public Label championStrengthLabel;
    public Label championConstitutionLabel;
    public Label championDexterityLabel;
    public Label championIntelligenceLabel;
    public Label championHPLabel;
    public Label championRegenLabel;
    public Label championInitiativeLabel;
    public Label championExperienceLabel;



    int siegePoints = 1000;
    int rebirthSiegePoints = 0;

    int archerTicker = 1;
    int aGTicker = 0;
    double aBarTicker = 0;
    int archerCount = 0;
    double archerDiscount = 1;
    double aTickPower = 1;
    int aPower = 1;
    int archerCap = 100;
    double archerAttackPower = 10;

    int fighterTicker = 5;
    int fGTicker = 0;
    int fBarTicker = 0;
    int fighterCount = 0;
    int fighterDiscount = 1;
    int fTickPower = 0;
    int fPower = 1;
    int fighterCap = 100;
    double fighterAttackPower = 5;

    int mageTicker = 1;
    int mGTicker = 0;
    int mBarTicker = 0;
    int mageCount = 0;
    int mageDiscount = 1;
    int mTickPower = 1;
    boolean mageUnlocked = false;

    int siegeCount = 0;
    boolean siegeBuilt = false;
    double morale=1000;
    int totalSiegeCount = 0;

    long yourTP = 0;
    long yourAttackPower = 0;
    long yourDefPower = 0;



    FighterTraining FT = new FighterTraining();
    ArcherTraining AT = new ArcherTraining();
    Siege siege = new Siege();
    NumberConverter NC = new NumberConverter();
    Battle battle = new Battle();


    long gameTicker = 0;
    String gameTime;

    int capCounter = 0;

    public Timeline startTimeline(){

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), e->{
            gameTicker++;
            aGTicker++;
            fGTicker++;

            getTime(gameTicker);
            campaignTimer.setText("Campaign timer: " + gameTime);

            //Archer bar controls
            if((double)archerTicker/(archerDiscount) <= aGTicker & archerCount < archerCap){
                aBarTicker = aBarTicker + aTickPower;
                aGTicker=0;
            }

            if(archerCount>=archerCap){aBarTicker=0;}

            if(aBarTicker >=100){
                aBarTicker = aBarTicker-100;
                if(aBarTicker>=100){aBarTicker = 0;}
                archerCount += aPower;
            }
            //Fighter bar controls
            if(fighterTicker/(fighterDiscount) <= fGTicker & fighterCount < fighterCap){
                fBarTicker = fBarTicker + 1 + fTickPower;
                fGTicker=0;
            }

            if(fighterCount>=fighterCap){fBarTicker = 0;}

            if(fBarTicker >=100){
                fBarTicker = fBarTicker-100;
                if(fBarTicker>=100){fBarTicker = 0;}
                fighterCount += fPower;
            }
            //Mage bar controls
            if(mageTicker/(mageDiscount) < mGTicker & mageUnlocked & mageCount < 5){
                mBarTicker = mBarTicker + 1 + mTickPower;
                mGTicker=0;
            }
            if(mBarTicker >=100){
                mBarTicker = mBarTicker-100;
                mageCount++;
            }

            //Siege logic
            //Building siege stats
            if(!siegeBuilt) {
                siegeCount++;
                totalSiegeCount++;
                siege.siegeClear();
                if(siege.getSeigeTotalPower()>999999){
                    siegeAttack.setText("Attack: " +NC.convertToSci(siege.getCurrentSiegeAttack()));
                    siegeDefense.setText("Defense: " + NC.convertToSci(siege.getCurrentSiegeDef()));
                    siegeTotalPower.setText("Total Power: " + NC.convertToSci(siege.getSeigeTotalPower()));
                }else{
                    siegeAttack.setText("Attack: " + NC.convertToSci(siege.getCurrentSiegeAttack()));
                    siegeDefense.setText("Defense: " + NC.convertToSci(siege.getCurrentSiegeDef()));
                    siegeTotalPower.setText("Total Power: " + NC.convertToSci(siege.getSeigeTotalPower()));
                }

                siegeLabel.setText("Siege " + siegeCount);
                siegeBuilt = siege.getSiegeStatus();
                archerCap = (int)(1000*(Math.floor(Math.log(totalSiegeCount)/Math.log(2)) + 1));
                fighterCap = (int)(1000 * (Math.floor(Math.log(totalSiegeCount)/Math.log(2)) + 1));

            }


            //Determine your attack and def
            yourAttackPower = archerCount*((int)Math.round(archerAttackPower)+
                    (int)Math.floor(AT.getTotalTrainingCompletes()))+
                    fighterCount*((int)Math.floor(fighterAttackPower)+
                            (int)Math.floor(FT.getTotalTrainingCompletes()));
            yourDefPower = fighterCount*(10+(int)Math.round(FT.getTotalTrainingCompletes()))+
                    archerCount*(2+(int)Math.floor(AT.getTotalTrainingCompletes()));
            yourTP=yourAttackPower+yourDefPower;


            //Display your troop counts
            archerCountLabel.setText("Archers: " + NC.addCommasInt(archerCount));
            archerBar.setProgress(aBarTicker/100f);

            fighterCountLabel.setText("Fighters: " + NC.addCommasInt(fighterCount));
            fighterBar.setProgress(fBarTicker/100f);

            mageCountLabel.setText("Magi: " + mageCount);
            mageBar.setProgress(mBarTicker/100f);

            //Display your siege points
            rebirthSiegeCount.setText("Current Siege Points: " + NC.addCommasInt(rebirthSiegePoints));


            //Display Fighter Training
            ftrain1Count.setText(FT.getTraining1Count());
            ftraining1PB.setProgress(FT.getTraining1Progress());
            ftrain1comp.setText(FT.getTraining1Completes() + "");

            ftrain2Count.setText(FT.getTraining2Count());
            ftraining2PB.setProgress(FT.getTraining2Progress());
            ftrain2comp.setText(FT.getTraining2Completes() + "");

            ftrain3Count.setText(FT.getTraining3Count());
            ftraining3PB.setProgress(FT.getTraining3Progress());
            ftrain3comp.setText(FT.getTraining3Completes() + "");

            ftrain4Count.setText(FT.getTraining4Count());
            ftraining4PB.setProgress(FT.getTraining4Progress());
            ftrain4comp.setText(FT.getTraining4Completes() + "");

            ftrain5Count.setText(FT.getTraining5Count());
            ftraining5PB.setProgress(FT.getTraining5Progress());
            ftrain5comp.setText(FT.getTraining5Completes() + "");

            ftrain6Count.setText(FT.getTraining6Count());
            ftraining6PB.setProgress(FT.getTraining6Progress());
            ftrain6comp.setText(FT.getTraining6Completes() + "");

            //Display Archer Training
            atrain1Count.setText(AT.getTraining1Count());
            atraining1PB.setProgress(AT.getTraining1Progress());
            atrain1comp.setText(AT.getTraining1Completes() + "");

            atrain2Count.setText(AT.getTraining2Count());
            atraining2PB.setProgress(AT.getTraining2Progress());
            atrain2comp.setText(AT.getTraining2Completes() + "");

            atrain3Count.setText(AT.getTraining3Count());
            atraining3PB.setProgress(AT.getTraining3Progress());
            atrain3comp.setText(AT.getTraining3Completes() + "");

            atrain4Count.setText(AT.getTraining4Count());
            atraining4PB.setProgress(AT.getTraining4Progress());
            atrain4comp.setText(AT.getTraining4Completes() + "");

            atrain5Count.setText(AT.getTraining5Count());
            atraining5PB.setProgress(AT.getTraining5Progress());
            atrain5comp.setText(AT.getTraining5Completes() + "");

            atrain6Count.setText(AT.getTraining6Count());
            atraining6PB.setProgress(AT.getTraining6Progress());
            atrain6comp.setText(AT.getTraining6Completes() + "");

            //Display your attack and def
            yourTotalPower.setText("Total Power: " + NC.convertToSci(yourTP));
            yourAttack.setText("Attack: " + NC.convertToSci(yourAttackPower));
            yourDefence.setText("Defence: " + NC.convertToSci(yourDefPower));

            rebirthSP.setText(rebirthSiegePoints + "");
            archerTrainingCurrentSpeed.setText("Current Speed: " + (double)Math.round(aTickPower*100)/100);
            archerTrainingCurrentCount.setText("Current Count: " + aPower);
            archerTrainingCurrentAttack.setText("Current Attack: " + (double)Math.round(archerAttackPower*10)/10);
            siegePointsLabel.setText(siegePoints + "");
            if(battle.changedStatus) {
                adventureTextArea.setText(battle.getTextOutput());
                adventureTextArea.appendText("");
                championStrengthLabel.setText(battle.getStrength());
                championConstitutionLabel.setText(battle.getConstitution());
                championDexterityLabel.setText(battle.getDexterity());
                championInitiativeLabel.setText(battle.getInitiative());
                championIntelligenceLabel.setText(battle.getIntelligence());
                championHPLabel.setText(battle.getHP());
                championRegenLabel.setText(battle.getRegen());
                championLevelLabel.setText(battle.getLevel());
                championExperienceLabel.setText(battle.getExperience());
            }

        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        return timeline;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

    public void siegeButton(){

    }

    public void rebirthButton(){
        siegePoints = siegePoints + rebirthSiegePoints;
        rebirthSiegePoints = 0;
        siege.resetSiege();
        siegeBuilt=false;
        AT.rebirthClear();
        FT.rebirthClear();
        siegeCount = 0;
        archerCount = 0;
        fighterCount = 0;

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
            if(Integer.parseInt(FT.getTraining1Count()) == (int)Math.ceil(FT.getTraining1Cap()/capCounter)){

            }else {
                fighterCount = FT.addTraining1count((int) Math.ceil((FT.getTraining1Cap() / (capCounter))-Integer.parseInt(FT.getTraining1Count())), fighterCount);
            }
        }
    }

    public void Ftraining2PlusButtonClick(){
        if(!countQuant.getText().equals("")){
            fighterCount = FT.addTraining2count(Integer.parseInt(countQuant.getText()), fighterCount);
        }
    }
    public void Ftraining2MinusButtonClick(){
        if(!countQuant.getText().equals("")){
            fighterCount = FT.subTraining2count(Integer.parseInt(countQuant.getText()), fighterCount);
        }
    }
    public void FfiftyPercent2ButtonClick(){
        countQuant.setText(fighterCount/2 + "");
        Ftraining2PlusButtonClick();
    }
    public void Ftraining2CapButtonClick(){
        if(fighterCount > 0) {
            capCounter=1;
            for (double i = FT.getTraining2Cap();  i > fighterCount+Integer.parseInt(FT.getTraining2Count()); i = FT.getTraining2Cap() / (float)capCounter) {
                capCounter++;
            }
            if(Integer.parseInt(FT.getTraining2Count()) == (int)Math.ceil(FT.getTraining2Cap()/capCounter)){

            }else {
                fighterCount = FT.addTraining2count((int) Math.ceil((FT.getTraining2Cap() / (capCounter))-Integer.parseInt(FT.getTraining2Count())), fighterCount);
            }
        }
    }

    public void Ftraining3PlusButtonClick(){
        if(!countQuant.getText().equals("")){
            fighterCount = FT.addTraining3count(Integer.parseInt(countQuant.getText()), fighterCount);
        }
    }
    public void Ftraining3MinusButtonClick(){
        if(!countQuant.getText().equals("")){
            fighterCount = FT.subTraining3count(Integer.parseInt(countQuant.getText()), fighterCount);
        }
    }
    public void FfiftyPercent3ButtonClick(){
        countQuant.setText(fighterCount/3 + "");
        Ftraining3PlusButtonClick();
    }
    public void Ftraining3CapButtonClick(){
        if(fighterCount > 0) {
            capCounter=1;
            for (double i = FT.getTraining3Cap();  i > fighterCount+Integer.parseInt(FT.getTraining3Count()); i = FT.getTraining3Cap() / (float)capCounter) {
                capCounter++;
            }

            if(Integer.parseInt(FT.getTraining3Count()) == (int)Math.ceil(FT.getTraining3Cap()/capCounter)){

            }else {
                fighterCount = FT.addTraining3count((int) Math.ceil((FT.getTraining3Cap() / (capCounter))-Integer.parseInt(FT.getTraining3Count())), fighterCount);
            }
        }
    }

    public void Ftraining4PlusButtonClick(){
        if(!countQuant.getText().equals("")){
            fighterCount = FT.addTraining4count(Integer.parseInt(countQuant.getText()), fighterCount);
        }
    }
    public void Ftraining4MinusButtonClick(){
        if(!countQuant.getText().equals("")){
            fighterCount = FT.subTraining4count(Integer.parseInt(countQuant.getText()), fighterCount);
        }
    }
    public void FfiftyPercent4ButtonClick(){
        countQuant.setText(fighterCount/4 + "");
        Ftraining4PlusButtonClick();
    }
    public void Ftraining4CapButtonClick(){
        if(fighterCount > 0) {
            capCounter=1;
            for (double i = FT.getTraining4Cap();  i > fighterCount+Integer.parseInt(FT.getTraining4Count()); i = FT.getTraining4Cap() / (float)capCounter) {
                capCounter++;
            }
            if(Integer.parseInt(FT.getTraining4Count()) == (int)Math.ceil(FT.getTraining4Cap()/capCounter)){

            }else {
                fighterCount = FT.addTraining4count((int) Math.ceil((FT.getTraining4Cap() / (capCounter))-Integer.parseInt(FT.getTraining4Count())), fighterCount);
            }
        }
    }

    public void Ftraining5PlusButtonClick(){
        if(!countQuant.getText().equals("")){
            fighterCount = FT.addTraining5count(Integer.parseInt(countQuant.getText()), fighterCount);
        }
    }
    public void Ftraining5MinusButtonClick(){
        if(!countQuant.getText().equals("")){
            fighterCount = FT.subTraining5count(Integer.parseInt(countQuant.getText()), fighterCount);
        }
    }
    public void FfiftyPercent5ButtonClick(){
        countQuant.setText(fighterCount/5 + "");
        Ftraining5PlusButtonClick();
    }
    public void Ftraining5CapButtonClick(){
        if(fighterCount > 0) {
            capCounter=1;
            for (double i = FT.getTraining5Cap();  i > fighterCount+Integer.parseInt(FT.getTraining5Count()); i = FT.getTraining5Cap() / (float)capCounter) {
                capCounter++;
            }
            if(Integer.parseInt(FT.getTraining5Count()) == (int)Math.ceil(FT.getTraining5Cap()/capCounter)){

            }else {
                fighterCount = FT.addTraining5count((int) Math.ceil((FT.getTraining5Cap() / (capCounter))-Integer.parseInt(FT.getTraining5Count())), fighterCount);
            }
        }
    }

    public void Ftraining6PlusButtonClick(){
        if(!countQuant.getText().equals("")){
            fighterCount = FT.addTraining6count(Integer.parseInt(countQuant.getText()), fighterCount);
        }
    }
    public void Ftraining6MinusButtonClick(){
        if(!countQuant.getText().equals("")){
            fighterCount = FT.subTraining6count(Integer.parseInt(countQuant.getText()), fighterCount);
        }
    }
    public void FfiftyPercent6ButtonClick(){
        countQuant.setText(fighterCount/6 + "");
        Ftraining6PlusButtonClick();
    }
    public void Ftraining6CapButtonClick(){
        if(fighterCount > 0) {
            capCounter=1;
            for (double i = FT.getTraining6Cap();  i > fighterCount+Integer.parseInt(FT.getTraining6Count()); i = FT.getTraining6Cap() / (float)capCounter) {
                capCounter++;
            }
            if(Integer.parseInt(FT.getTraining6Count()) == (int)Math.ceil(FT.getTraining6Cap()/capCounter)){

            }else {
                fighterCount = FT.addTraining6count((int) Math.ceil((FT.getTraining6Cap() / (capCounter))-Integer.parseInt(FT.getTraining6Count())), fighterCount);
            }
        }
    }

    //Archer Training
    public void Atraining1PlusButtonClick(){
        if(!countQuant.getText().equals("")){archerCount = AT.addTraining1count(Integer.parseInt(countQuant.getText()), archerCount);}
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
            if(Integer.parseInt(AT.getTraining1Count()) == (int)Math.ceil(AT.getTraining1Cap()/capCounter)){

            }else {
                archerCount = AT.addTraining1count((int) Math.ceil((AT.getTraining1Cap() / (capCounter))-Integer.parseInt(AT.getTraining1Count())), archerCount);
            }
        }
    }

    public void Atraining2PlusButtonClick(){
        if(!countQuant.getText().equals("")){
            archerCount = AT.addTraining2count(Integer.parseInt(countQuant.getText()), archerCount);
        }
    }
    public void Atraining2MinusButtonClick(){
        if(!countQuant.getText().equals("")){
            archerCount = AT.subTraining2count(Integer.parseInt(countQuant.getText()), archerCount);
        }
    }
    public void AfiftyPercent2ButtonClick(){
        countQuant.setText(archerCount/2 + "");
        Atraining2PlusButtonClick();
    }
    public void Atraining2CapButtonClick(){
        if(archerCount > 0) {
            capCounter=1;
            for (double i = AT.getTraining2Cap();  i > archerCount+Integer.parseInt(AT.getTraining2Count()); i = AT.getTraining2Cap() / (float)capCounter) {
                capCounter++;
            }
            if(Integer.parseInt(AT.getTraining2Count()) == (int)Math.ceil(AT.getTraining2Cap()/capCounter)){

            }else {
                archerCount = AT.addTraining2count((int) Math.ceil((AT.getTraining2Cap() / (capCounter))-Integer.parseInt(AT.getTraining2Count())), archerCount);
            }
        }
    }

    public void Atraining3PlusButtonClick(){
        if(!countQuant.getText().equals("")){
            archerCount = AT.addTraining3count(Integer.parseInt(countQuant.getText()), archerCount);
        }
    }
    public void Atraining3MinusButtonClick(){
        if(!countQuant.getText().equals("")){
            archerCount = AT.subTraining3count(Integer.parseInt(countQuant.getText()), archerCount);
        }
    }
    public void AfiftyPercent3ButtonClick(){
        countQuant.setText(archerCount/3 + "");
        Atraining3PlusButtonClick();
    }
    public void Atraining3CapButtonClick(){
        if(archerCount > 0) {
            capCounter=1;
            for (double i = AT.getTraining3Cap();  i > archerCount+Integer.parseInt(AT.getTraining3Count()); i = AT.getTraining3Cap() / (float)capCounter) {
                capCounter++;
            }
            if(Integer.parseInt(AT.getTraining3Count()) == (int)Math.ceil(AT.getTraining3Cap()/capCounter)){

            }else {
                archerCount = AT.addTraining3count((int) Math.ceil((AT.getTraining3Cap() / (capCounter))-Integer.parseInt(AT.getTraining3Count())), archerCount);
            }
        }
    }

    public void Atraining4PlusButtonClick(){
        if(!countQuant.getText().equals("")){
            archerCount = AT.addTraining4count(Integer.parseInt(countQuant.getText()), archerCount);
        }
    }
    public void Atraining4MinusButtonClick(){
        if(!countQuant.getText().equals("")){
            archerCount = AT.subTraining4count(Integer.parseInt(countQuant.getText()), archerCount);
        }
    }
    public void AfiftyPercent4ButtonClick(){
        countQuant.setText(archerCount/4 + "");
        Atraining4PlusButtonClick();
    }
    public void Atraining4CapButtonClick(){
        if(archerCount > 0) {
            capCounter=1;
            for (double i = AT.getTraining4Cap();  i > archerCount+Integer.parseInt(AT.getTraining4Count()); i = AT.getTraining4Cap() / (float)capCounter) {
                capCounter++;
            }
            if(Integer.parseInt(AT.getTraining4Count()) == (int)Math.ceil(AT.getTraining4Cap()/capCounter)){

            }else {
                archerCount = AT.addTraining4count((int) Math.ceil((AT.getTraining4Cap() / (capCounter))-Integer.parseInt(AT.getTraining4Count())), archerCount);
            }
        }
    }

    public void Atraining5PlusButtonClick(){
        if(!countQuant.getText().equals("")){
            archerCount = AT.addTraining5count(Integer.parseInt(countQuant.getText()), archerCount);
        }
    }
    public void Atraining5MinusButtonClick(){
        if(!countQuant.getText().equals("")){
            archerCount = AT.subTraining5count(Integer.parseInt(countQuant.getText()), archerCount);
        }
    }
    public void AfiftyPercent5ButtonClick(){
        countQuant.setText(archerCount/5 + "");
        Atraining5PlusButtonClick();
    }
    public void Atraining5CapButtonClick(){
        if(archerCount > 0) {
            capCounter=1;
            for (double i = AT.getTraining5Cap();  i > archerCount+Integer.parseInt(AT.getTraining5Count()); i = AT.getTraining5Cap() / (float)capCounter) {
                capCounter++;
            }
            if(Integer.parseInt(AT.getTraining5Count()) == (int)Math.ceil(AT.getTraining5Cap()/capCounter)){

            }else {
                archerCount = AT.addTraining5count((int) Math.ceil((AT.getTraining5Cap() / (capCounter))-Integer.parseInt(AT.getTraining5Count())), archerCount);
            }
        }
    }

    public void Atraining6PlusButtonClick(){
        if(!countQuant.getText().equals("")){
            archerCount = AT.addTraining6count(Integer.parseInt(countQuant.getText()), archerCount);
        }
    }
    public void Atraining6MinusButtonClick(){
        if(!countQuant.getText().equals("")){
            archerCount = AT.subTraining6count(Integer.parseInt(countQuant.getText()), archerCount);
        }
    }
    public void AfiftyPercent6ButtonClick(){
        countQuant.setText(archerCount/6 + "");
        Atraining6PlusButtonClick();
    }
    public void Atraining6CapButtonClick(){
        if(archerCount > 0) {
            capCounter=1;
            for (double i = AT.getTraining6Cap();  i > archerCount+Integer.parseInt(AT.getTraining6Count()); i = AT.getTraining6Cap() / (float)capCounter) {
                capCounter++;
            }
            if(Integer.parseInt(AT.getTraining6Count()) == (int)Math.ceil(AT.getTraining6Cap()/capCounter)){

            }else {
                archerCount = AT.addTraining6count((int) Math.ceil((AT.getTraining6Cap() / (capCounter))-Integer.parseInt(AT.getTraining6Count())), archerCount);
            }
        }
    }

    //Spending SPs
    public void archerTrainingSpeedButton1(){
        if(siegePoints >=1){
            aTickPower = aTickPower + .1;
            siegePoints-=1;
        }
    }
    public void archerTrainingSpeedButton2(){
        if(siegePoints >=10){
            aTickPower = aTickPower + 1;
            siegePoints-=10;
        }
    }
    public void archerTrainingSpeedButton3(){
        if(siegePoints >=50){
            aTickPower = aTickPower + 5;
            siegePoints-=50;
        }
    }
    public void archerTrainingSpeedButton4(){
        if(siegePoints >=100){
            aTickPower = aTickPower + 10;
            siegePoints-=100;
        }
    }
    public void archerTrainingSpeedButton5(){
        if(siegePoints >=1000){
            aTickPower = aTickPower + 100;
            siegePoints-=1000;
        }
    }

    public void archerTrainingPowerButton1(){
        if(siegePoints >= 100){
            aPower = aPower + 1;
            siegePoints -= 100;
        }
    }
    public void archerTrainingPowerButton2(){
        if(siegePoints >= 500){
            aPower = aPower + 5;
            siegePoints -= 500;
        }
    }
    public void archerTrainingPowerButton3(){
        if(siegePoints >= 1000){
            aPower = aPower + 10;
            siegePoints -= 1000;
        }
    }
    public void archerTrainingPowerButton4(){
        if(siegePoints >= 2000){
            aPower = aPower + 20;
            siegePoints -= 2000;
        }
    }
    public void archerTrainingPowerButton5(){
        if(siegePoints >= 5000){
            aPower = aPower + 50;
            siegePoints -= 5000;
        }
    }

    public void archerTrainingAttackButton1(){
        if(siegePoints >=10){
            archerAttackPower += .1;
            siegePoints -= 10;
        }
    }
    public void archerTrainingAttackButton2(){
        if(siegePoints >=50){
            archerAttackPower += .5;
            siegePoints -= 50;
        }
    }
    public void archerTrainingAttackButton3(){
        if(siegePoints >=100){
            archerAttackPower += 1;
            siegePoints -= 100;
        }
    }
    public void archerTrainingAttackButton4(){
        if(siegePoints >=200){
            archerAttackPower += 2;
            siegePoints -= 200;
        }
    }
    public void archerTrainingAttackButton5(){
        if(siegePoints >=500){
            archerAttackPower += 5;
            siegePoints -= 500;
        }
    }

    public void TestButtonClicked(){
        battle.battleRound();
    }
}
