package utilities;

import java.io.File;

public class FileUtilities {

    public static void deleteFile(String fileName) {
        File fileLocation = new File(System.getProperty("user.dir").concat("/" + fileName));
        fileLocation.delete();
    }

}
