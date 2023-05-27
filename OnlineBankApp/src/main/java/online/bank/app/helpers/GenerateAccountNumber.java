package online.bank.app.helpers;
import java.util.Random;

public class GenerateAccountNumber {

    public static int generateAccountNumber(){
        int accountNumber;
        Random random = new Random();
        int bound = 1000;
        accountNumber = bound * random.nextInt(bound);
        return accountNumber;
    }

}
