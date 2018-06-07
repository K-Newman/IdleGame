package sample;

public class NumberConverter {

    public String convertToSci(double someNumber){
        if(someNumber>999999) {
            return (String.format("%6.3e", someNumber));
        }else{
            return addCommasDouble(someNumber);
        }
    }

    public String addCommasDouble(double someNumber){
        return(String.format("%,.0f", someNumber));
    }

    public String addCommasInt(double someNumber){
        return(String.format("%,.0f", someNumber));
    }
}
