package sample;

public class Siege {

    private long currentSiegeAttack = 10;
    private long nextSiegeAttack;
    private long currentSiegeDef = 11;
    private long nextSiegeDef;
    private double siegeAttackMulti=1;
    private double siegeDefMulti=1;
    private double siegeTotalPower;
    private boolean siegeStatus=false;

    public long getCurrentSiegeAttack(){return currentSiegeAttack;}

    public long getCurrentSiegeDef(){return currentSiegeDef;}

    public void siegeClear(){
        nextSiegeAttack = (long)Math.ceil(currentSiegeAttack * 11 * siegeAttackMulti);
        nextSiegeDef = (long)Math.ceil(currentSiegeDef * 11 * siegeDefMulti);
        currentSiegeAttack = nextSiegeAttack;
        currentSiegeDef = nextSiegeDef;
        siegeStatus = true;
    }

    public long getSeigeTotalPower(){
        siegeTotalPower = currentSiegeAttack + currentSiegeDef;
        return (long)Math.ceil(siegeTotalPower);
    }

    public void attackSiege(){
        siegeStatus = false;
    }

    public boolean getSiegeStatus(){
        return siegeStatus;
    }

    public void resetSiege(){
        currentSiegeAttack=10;
        currentSiegeDef=11;

    }
}
