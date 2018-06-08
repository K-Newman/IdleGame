package sample;

public class FighterTraining {

    private int training1count;
    private double training1Status = 0;
    private int training1Completes = 0;
    private int training2count;
    private double training2Status = 0;
    private int training2Completes = 0;
    private int training3count;
    private double training3Status = 0;
    private int training3Completes = 0;
    private int training4count;
    private double training4Status = 0;
    private int training4Completes = 0;
    private int training5count;
    private double training5Status = 0;
    private int training5Completes = 0;
    private int training6count;
    private double training6Status = 0;
    private int training6Completes = 0;

    public double training1Cap = 5000;
    public double training2Cap = 10000;
    public double training3Cap = 15000;
    public double training4Cap = 20000;
    public double training5Cap = 25000;
    public double training6Cap = 30000;

    private int tempCount = 0;

    public int addTraining1count(int countQuant,int acount){
        if(acount>countQuant) {
            training1count = training1count + countQuant;
            return acount-countQuant;
        }else{
            training1count = training1count + acount;
            return 0;
        }


    }
    public int subTraining1count(int countQuant, int acount){
        if(training1count - countQuant > 0){
            training1count = training1count - countQuant;
            return acount+countQuant;
        }else if(training1count - countQuant <= 0){
            tempCount = training1count;
            training1count = 0;
            return acount + tempCount;
        }else{
            return acount;
        }
    }
    public String getTraining1Count(){
        return training1count + "";
    }
    public double getTraining1Progress(){
        training1Status = training1Status + training1count/(training1Cap);
        if(training1Status >= 1){
            training1Status--;
            training1Completes++;
        }
        return training1Status;
    }
    public int getTraining1Completes(){
        return training1Completes;
    }
    public double getTraining1Cap(){ return training1Cap; }

    public int addTraining2count(int countQuant,int acount){
        if(acount>countQuant) {
            training2count = training2count + countQuant;
            return acount-countQuant;
        }else{
            training2count = training2count + acount;
            return 0;
        }


    }
    public int subTraining2count(int countQuant, int acount){
        if(training2count - countQuant > 0){
            training2count = training2count - countQuant;
            return acount+countQuant;
        }else if(training2count - countQuant <= 0){
            tempCount = training2count;
            training2count = 0;
            return acount + tempCount;
        }else{
            return acount;
        }
    }
    public String getTraining2Count(){
        return training2count + "";
    }
    public double getTraining2Progress(){
        training2Status = training2Status + training2count/(training2Cap);
        if(training2Status >= 1){
            training2Status--;
            training2Completes++;
        }
        return training2Status;
    }
    public int getTraining2Completes(){
        return training2Completes;
    }
    public double getTraining2Cap(){ return training2Cap; }

    public int addTraining3count(int countQuant,int acount){
        if(acount>countQuant) {
            training3count = training3count + countQuant;
            return acount-countQuant;
        }else{
            training3count = training3count + acount;
            return 0;
        }


    }
    public int subTraining3count(int countQuant, int acount){
        if(training3count - countQuant > 0){
            training3count = training3count - countQuant;
            return acount+countQuant;
        }else if(training3count - countQuant <= 0){
            tempCount = training3count;
            training3count = 0;
            return acount + tempCount;
        }else{
            return acount;
        }
    }
    public String getTraining3Count(){
        return training3count + "";
    }
    public double getTraining3Progress(){
        training3Status = training3Status + training3count/(training3Cap);
        if(training3Status >= 1){
            training3Status--;
            training3Completes++;
        }
        return training3Status;
    }
    public int getTraining3Completes(){
        return training3Completes;
    }
    public double getTraining3Cap(){ return training3Cap; }

    public int addTraining4count(int countQuant,int acount){
        if(acount>countQuant) {
            training4count = training4count + countQuant;
            return acount-countQuant;
        }else{
            training4count = training4count + acount;
            return 0;
        }


    }
    public int subTraining4count(int countQuant, int acount){
        if(training4count - countQuant > 0){
            training4count = training4count - countQuant;
            return acount+countQuant;
        }else if(training4count - countQuant <= 0){
            tempCount = training4count;
            training4count = 0;
            return acount + tempCount;
        }else{
            return acount;
        }
    }
    public String getTraining4Count(){
        return training4count + "";
    }
    public double getTraining4Progress(){
        training4Status = training4Status + training4count/(training4Cap);
        if(training4Status >= 1){
            training4Status--;
            training4Completes++;
        }
        return training4Status;
    }
    public int getTraining4Completes(){
        return training4Completes;
    }
    public double getTraining4Cap(){ return training4Cap; }

    public int addTraining5count(int countQuant,int acount){
        if(acount>countQuant) {
            training5count = training5count + countQuant;
            return acount-countQuant;
        }else{
            training5count = training5count + acount;
            return 0;
        }


    }
    public int subTraining5count(int countQuant, int acount){
        if(training5count - countQuant > 0){
            training5count = training5count - countQuant;
            return acount+countQuant;
        }else if(training5count - countQuant <= 0){
            tempCount = training5count;
            training5count = 0;
            return acount + tempCount;
        }else{
            return acount;
        }
    }
    public String getTraining5Count(){
        return training5count + "";
    }
    public double getTraining5Progress(){
        training5Status = training5Status + training5count/(training5Cap);
        if(training5Status >= 1){
            training5Status--;
            training5Completes++;
        }
        return training5Status;
    }
    public int getTraining5Completes(){
        return training5Completes;
    }
    public double getTraining5Cap(){ return training5Cap; }

    public int addTraining6count(int countQuant,int acount){
        if(acount>countQuant) {
            training6count = training6count + countQuant;
            return acount-countQuant;
        }else{
            training6count = training6count + acount;
            return 0;
        }


    }
    public int subTraining6count(int countQuant, int acount){
        if(training6count - countQuant > 0){
            training6count = training6count - countQuant;
            return acount+countQuant;
        }else if(training6count - countQuant <= 0){
            tempCount = training6count;
            training6count = 0;
            return acount + tempCount;
        }else{
            return acount;
        }
    }
    public String getTraining6Count(){
        return training6count + "";
    }
    public double getTraining6Progress(){
        training6Status = training6Status + training6count/(training6Cap);
        if(training6Status >= 1){
            training6Status--;
            training6Completes++;
        }
        return training6Status;
    }
    public int getTraining6Completes(){
        return training6Completes;
    }
    public double getTraining6Cap(){ return training6Cap; }

    public double getTotalTrainingCompletes(){
        return (training1Completes*.01 +
                training2Completes*.05 +
                training3Completes*.1 +
                training4Completes*.15 +
                training5Completes*.2 +
                training6Completes*.25);
    }

    public void rebirthClear(){
        training1count = 0;
        training1Status = 0;
        training1Completes = 0;
        training2count = 0;
        training2Status = 0;
        training2Completes = 0;
        training3count = 0;
        training3Status = 0;
        training3Completes = 0;
        training4count = 0;
        training4Status = 0;
        training4Completes = 0;
        training5count = 0;
        training5Status = 0;
        training5Completes = 0;
        training6count = 0;
        training6Status = 0;
        training6Completes = 0;
    }


}
