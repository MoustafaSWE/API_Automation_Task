package utilities;


import java.sql.Timestamp;
import java.util.Random;

public class RandomGeneration {

    public String generateRandomNumberString(int digits) {

        long timeStamp = new Timestamp(System.currentTimeMillis()).getTime();

        return String.valueOf(timeStamp).substring(0,digits);
    }

    public String selectRandomString(String[] arrayPool) {

        int index = new Random().nextInt(arrayPool.length);

        return arrayPool[index];
    }

}
