import java.util.logging.*;

public class Organisationsnummer extends Personnummer{

    public Organisationsnummer(String input,Logger log) {
        super(input,log);

        this.checkContainsCentury();
        if(this.century==null){
            this.year = input.substring(0,2);
            this.month = input.substring(2,4);
            this.day = input.substring(4,6);
        }else{
            this.year = input.substring(2,4);
            this.month = input.substring(4,6);
            this.day = input.substring(6,8);
        }


    }

    // public String toString() {
    //     return (
    //     "number: " + this.input + "\n" +
    //     "century: " + this.century + "\n" +
    //     "year: " + this.year + "\n" +
    //     "month: " + this.month + "\n" +
    //     "day: " + this.day + "\n" +
    //     "last three: " + this.lastThree + "\n" +
    //     "controllNumber: " + this.controllNumber + "\n"
    //     );
    // }

    public boolean validityCheck(){

        String failure;

        if(!this.checkControllNumber()){
            failure = this.input + " has the wrong controllnumber, should be: " + this.calulatedControllnumber;
            this.log.info(failure);
            return false;
        }        
        if(this.middleValue()<20){
            failure = this.input + " has the wrong middlevalue: " + this.middleValue() + ", should be atleast 20";
            this.log.info(failure);
            return false;
        }
        if(this.checkIfAmountOfNumsIs12()){

            if(this.century == null){
                failure = this.input + " has 12 numbers but doesn't contain the right century: " + this.century;
                this.log.info(failure);
                return false;
            }
        }
        return true;
    }

    public int middleValue(){
        String date;
        if(this.century != null){
            date = this.century + this.year + this.month + this.day;
        }else{
            date = this.year + this.month + this.day;
        }
        int middleIndex = date.length()/2;
        String middlleValueString = Character.toString(date.charAt(middleIndex-1))+Character.toString(date.charAt(middleIndex));
        int middleValue = Integer.parseInt(middlleValueString);
        return middleValue;
    }

    public void checkContainsCentury(){
        firstNumbers = this.input.substring(0,2);
        if(firstNumbers.equals("16")){
            this.century = firstNumbers;
        }
    }

    public boolean checkIfAmountOfNumsIs12(){
        int amountOfNumbers = this.input.replace("-", "").replace("+", "").length();
        if(amountOfNumbers==12){
            return true;
        }else{
            return false;
        }
    }

}

