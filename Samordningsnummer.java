import java.util.logging.*;

public class Samordningsnummer extends Personnummer{

    public Samordningsnummer(String input,Logger log) {
        super(input,log);

    }

    // public String toString() {
    //     return (
    //     "samordningssnummer: " + this.input + "\n" +
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
        int days = Integer.parseInt(this.input.substring(this.input.length()-6, this.input.length()-4));

        if(days>60){
            if(this.checkControllNumber()){
                
                return true;
            }

            failure = this.input + " has the wrong controllnumber, should be: " + this.calulatedControllnumber;
            this.log.info(failure);
            return false;
        }
        failure = this.input + " has the wrong days, should be: " + String.valueOf(days+60) ;
        this.log.info(failure);
        return false;
    }


}
