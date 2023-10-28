import static java.lang.Math.log;
import static java.lang.Math.pow;

// as input, you need to type  bit-code string with checker bits.
public class Hamming_Code {
    private String message;
    public Hamming_Code(String message){
        this.message = message;
    }

    // method to calculate the number of checker bit's
    public  int numberOfCheckerbBits () {
        return (int) (log(message.length()) /  log(2) + 1);
    }

    // informational print to check  number of different bits in the message.
    public  void printAllBits () {
        System.out.printf("our message has %s informational bits and %s checker Bits %n",message.length()
                - numberOfCheckerbBits(),numberOfCheckerbBits());
    }

    // method to check how many bits one checker bit check's.
    public int getNumberOfControlled () {
        int positionOfMaxChecker = message.length() / 2 ;
        return message.length()- 1 - positionOfMaxChecker;
    }

    // method, that returns position of the specific checker bit
    public int getPositionOfChecker (int numberOfChecker) {
        return (int) pow(2,numberOfChecker-1);
    }

    // method, that calculates the value of concrete checker bit
    public int checkerSum(int numberOfChecker) {
        int position = getPositionOfChecker(numberOfChecker);
        int sum = 0;
        int countControlled = 0;
        int times = 1;
        while (countControlled <= getNumberOfControlled()) {
            // print's for test
//            System.out.println(sum + " base");
            for (int i = 0; i < position; i++) {
                sum += Integer.parseInt(Character.toString(message.charAt(position * times - 1 + i )));
//                System.out.println(Integer.parseInt(Character.toString(message.charAt(position * times - 1 + i ))) + "+");
//                System.out.println(position * times  + i + " position");
                countControlled++;
            }
            times+=2;
//            System.out.println(sum %2 + " result");
        }
        return sum % 2;
    }

    // method, that returns if the checker sum is legit or not.
    public int errorFinder () {
        int checkerSum = 0;
        for (int i = 1; i <= numberOfCheckerbBits();i++ ) {
            if (checkerSum(i) == 0) {
                continue;
            }
            checkerSum += getPositionOfChecker(i);
        }
        return checkerSum;
    }

    // prints the uncorrected and corrected version of the message.
    public void printCorrectedMessage () {
        StringBuilder correctedMessage = new StringBuilder(message);
        if (errorFinder() == 0) {
            System.out.println("Your message has no mistakes. congrats!");
        }
        else {
            correctedMessage.setCharAt(errorFinder() -1,switch (message.charAt(errorFinder() -1)) {
                case '1' -> '0';
                case '0' -> '1';
                default -> '8';});
            System.out.println("your message " + message + " was incorrect." + " we changed it and the corrected version of your message is " + correctedMessage);
        }
    }

    // method to get access to corrected message
    public StringBuilder correctedMessage (){
        StringBuilder correctedMessage = new StringBuilder(message);
        if (errorFinder() == 0) {
            return  correctedMessage;
        }
        else {
            correctedMessage.setCharAt(errorFinder() -1,switch (message.charAt(errorFinder() -1)) {
                case '1' -> '0';
                case '0' -> '1';
                default -> '8';});
            return  correctedMessage;
        }
    }

    // prints the corrected version of the message without checker bits
    public void printUserCode () {
        StringBuilder correctedCode = correctedMessage();
        for(int i = 1; i <= numberOfCheckerbBits();i++) {
            correctedCode.setCharAt(getPositionOfChecker(i) - 1,' ');
        }
        String finalCode = correctedCode.toString().replaceAll("\\s","");
        System.out.println("the corrected code without checker bits is " + finalCode);
    }
}