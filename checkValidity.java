//Reading file imports
import java.io.BufferedReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
//Handling input
import java.nio.charset.Charset;

//
import java.io.InputStreamReader;

//Inorder to use mutable arrays
import java.util.ArrayList;
import java.util.Scanner;
//Handeling exceptions
import java.io.IOException;



import java.util.logging.*;

public class checkValidity {

    ArrayList<Object> valid = new ArrayList<Object>(); // Create an ArrayList object
    ArrayList<Organisationsnummer> validON = new ArrayList<Organisationsnummer>(); // Create an ArrayList object
    ArrayList<Samordningsnummer> validSN = new ArrayList<Samordningsnummer>(); // Create an ArrayList object

    static Logger l = Logger.getLogger("LoggerForFailure");

	public static void main(String args[]) throws IOException{

        //Kollar vilken typ av nummer det ?r, enligt f?ljande regler
        //Om dagarna ?r ?ver 60 -> samordningsnummer
        //Om mittersta sifferparet ?r minst 20 -> organisationsnummer
        //Om ingen ovan -> personnummer
        checkValidity controller = new checkValidity();

        String number = args[0];
        String type = args[1];
        System.out.println("Number chosen:" + number);
        System.out.println("type of Number chosen:" + type);
        
        String success = "valid";
        switch(type){
            case "PN":
                Personnummer PN = new Personnummer(number,l);
                if(PN.validityCheck()){
                    l.info(success);
                    controller.valid.add(PN);
                }
                break;
            case "ON":
                Organisationsnummer ON = new Organisationsnummer(number,l);
                if(ON.validityCheck()){
                    l.info(success);
                    controller.valid.add(ON);
                }
            break;
            case "SN":
                Samordningsnummer SN = new Samordningsnummer(number,l);
                if(SN.validityCheck()){
                    l.info(success);
                    controller.valid.add(SN);
                }
            break;
        }

        for(int i = 0; i<controller.valid.size();i++){
            System.out.println(controller.valid.get(i));
        }    
    }

}

