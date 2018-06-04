package sample;

public class ArcherTraining {

    private int training1count;
    private double training1Status = 0;
    private int training1Completes = 0;

    public double training1Cap = 5000;
    public double training2Cap = 10000;
    public double training3Cap = 15000;
    public double training4Cap = 20000;
    public double training5Cap = 25000;

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

}

