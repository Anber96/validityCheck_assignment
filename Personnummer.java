
//Inorder to use mutable arrays
import java.util.ArrayList;

import java.util.logging.*;
public class Personnummer{

    String input;
    String century;
    String year;
    String month;
    String day;
    String firstTwo;
    String firstNumbers;
    String lastThree;
    String controllNumber;

    int indexOfSeparator;

    int sumOfNumbers;
    boolean isDivided;

    Logger log;

    String calulatedControllnumber;

    public Personnummer(String input,Logger log){
        this.log = log;
        this.containsCentury(input);
        this.containsSeparator(input); 
        this.input = input;
        int lengthOfString = input.length();

        String yearDayMonth;
        if(this.isDivided){
            yearDayMonth = input.substring(0,lengthOfString-5);
        }else{
            yearDayMonth = input.substring(0,lengthOfString-4);
        }
        if(this.century==null){
            this.year = yearDayMonth.substring(0,2);
            this.month = yearDayMonth.substring(2,4);
            this.day = yearDayMonth.substring(4,6);
        }else{
            this.year = yearDayMonth.substring(2,4);
            this.month = yearDayMonth.substring(4,6);
            this.day = yearDayMonth.substring(6,8);
        }
        this.lastThree = input.substring(lengthOfString-4,lengthOfString-1);
        this.controllNumber = Character.toString(input.charAt(lengthOfString-1));
    }

    public boolean validityCheck(){

        //Check
        if(!this.checkControllNumber()){
            String failure = this.input + " has the wrong controllnumber, should be: " +  this.calulatedControllnumber;
            this.log.info(failure);
            return false;
        }

        return true;
    }

    public boolean checkControllNumber(){
        int sumOfNumbers = this.sum();
        int controllNum = Integer.parseInt(this.controllNumber);

        int res = (10 - (sumOfNumbers%10))%10;
        this.calulatedControllnumber = Integer.toString(res);
        if(res==controllNum){
            return true;
        }
        return false;
    }

    public int sum(){

        String[] tmpPersonalNumArray = (this.year + this.month + this.day + this.lastThree).split("");
        ArrayList<Integer> resNumbers = new ArrayList<Integer>(); // Create an ArrayList object

        for (int i = 0; i<tmpPersonalNumArray.length;i++) {
            String num = tmpPersonalNumArray[i];
            int val = Integer.parseInt(String.valueOf(num));
            if(i%2==0){//Even index
                val *= 2;
                num = String.valueOf(val);
                if(num.length() == 2){
                    resNumbers.add(Integer.parseInt(String.valueOf(num.charAt(0))));
                    resNumbers.add(Integer.parseInt(String.valueOf(num.charAt(1))));
                }else{
                    resNumbers.add(Integer.parseInt(num));
                }
            }else{//Uneven index
                resNumbers.add(Integer.parseInt(num));
            }
        }

        int sum = 0;
        for(int numInArray: resNumbers){
            sum += numInArray;
        }

        return sum;
    }

    // @Override
    // public String toString() {
    //     return (
    //     "personnummer: " + this.input + "\n" +
    //     "century: " + this.century + "\n" +
    //     "year: " + this.year + "\n" +
    //     "month: " + this.month + "\n" +
    //     "day: " + this.day + "\n" +
    //     "last three: " + this.lastThree + "\n" +
    //     "controllNumber: " + this.controllNumber + "\n"
    //     );
    // }

    @Override
    public String toString() {
        return (
        "personnummer: " + this.input + "\n"
        );
    }

    public void containsCentury(String input){
        firstNumbers = input.substring(0,2);
        if(firstNumbers.equals("18")|| firstNumbers.equals("19")||firstNumbers.equals("20")){
            this.century = firstNumbers;
        }
    }

    public void containsSeparator(String input){
        int seperatorIndexMinus = input.indexOf('-');
        int seperatorIndexPlus = input.indexOf('+');
        //If - seperator exists
        if(seperatorIndexMinus != -1){
            this.isDivided = true;
            this.indexOfSeparator = seperatorIndexMinus;
        //If + seperator exists
        }else if(seperatorIndexPlus!=-1){
            this.isDivided = true;
            this.indexOfSeparator = seperatorIndexPlus;

        }else{
        //If no seperator exists
            this.isDivided = false;
        }
        

    }
    
}

