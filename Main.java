

public class Main {
    public static void main(String[] args) {
        System.out.println("enter a bit-hamming code with checker bits");
        Hamming_Code test = new Hamming_Code("0000101");
        test.printCorrectedMessage();
        test.printUserCode();
    }
}
