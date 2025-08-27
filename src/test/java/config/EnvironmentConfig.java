package config;

import org.json.JSONObject;

public class EnvironmentConfig {

     public enum EnvironmentVar {
        ENV1,
        ENV2
    }

     public static EnvironmentVar testedEnvironment = EnvironmentVar.ENV1;

     public static JSONObject setEnvironmentVariables (EnvironmentVar environmentVar) {

         String testedEnvironmentUrl = null;
         String testedEnvironmentDBConnection = null;
         String testedSecretKey = null;
         String testedActivityLogUrl = null;

         switch (environmentVar)
         {
             case ENV1:
                    testedEnvironmentUrl =  "https://fakerestapi.azurewebsites.net";
                    testedEnvironmentDBConnection =  "";
                    testedSecretKey = "";
                    testedActivityLogUrl = "";
                    break;

             case ENV2:
                    testedEnvironmentUrl =  "";
                    testedEnvironmentDBConnection =  "";
                    testedSecretKey = "";
                    testedActivityLogUrl = "";
                    break;
         }

         JSONObject environmentObjects = new JSONObject();
         environmentObjects.put("testedEnvironmentUrl", testedEnvironmentUrl);
         environmentObjects.put("testedEnvironmentDBConnection", testedEnvironmentDBConnection);
         environmentObjects.put("testedSecretKey" , testedSecretKey);
         environmentObjects.put("testedActivityLogUrl" , testedActivityLogUrl);

         return environmentObjects;
     }

    public static String testedEnvironmentBaseUrl = setEnvironmentVariables(testedEnvironment).getString("testedEnvironmentUrl");
    public static String testedDbConnectionUrl = setEnvironmentVariables(testedEnvironment).getString("testedEnvironmentDBConnection");

    public static String testedActivityLogBaseUrl = setEnvironmentVariables(testedEnvironment).getString("testedActivityLogUrl");

    public static String microsoftSqlClassName = "";
    public static String dbUserName = "";
    public static String dbPassword = "";
    public static String testedSecretKey = setEnvironmentVariables(testedEnvironment).getString("testedSecretKey");

}
