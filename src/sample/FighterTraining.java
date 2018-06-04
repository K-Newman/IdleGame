package sample;

public class FighterTraining {

    public int training1count;
    public double training1Status = 0;
    public int training1Completes = 0;
    public double training1Cap = 5000;
    public double training2Cap = 10000;
    public double training3Cap = 15000;
    public double training4Cap = 20000;
    public double training5Cap = 25000;
    public int tempCount = 0;

    public int addTraining1count(int countQuant,int fcount){
        if(fcount>countQuant) {
            training1count = training1count + countQuant;
            return fcount-countQuant;
        }else{
            training1count = training1count + fcount;
            return 0;
        }


    }

    public int subTraining1count(int countQuant, int fcount){
        if(training1count - countQuant > 0){
            training1count = training1count - countQuant;
            return fcount+countQuant;
        }else if(training1count - countQuant <= 0) {
            tempCount=training1count;
            training1count=0;
            return fcount + tempCount;
        }else{
            return fcount;
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
