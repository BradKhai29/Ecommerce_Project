/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

/**
 *
 * @author This PC
 */
public class DatabaseInformation {

    //Driver process
    private static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    /**
     * @param port
     * Có vẻ như chỉ cần nhờ có Port number thì DriverManager có thể xác định đc
     * Connection mình muốn kết nối là gì rồi
     */
    private static String port = "50988";
    private static String databaseName = "LEGO_SHOP";

    //Login process
    private static String user = "sa";
    private static String password = "123123";
    private static String encodingProperty = "useUnicode=true;characterEncoding=UTF-8;";

    //Get Connection process
    private static String url = "jdbc:sqlserver://localhost:" + port + ";databaseName=" + databaseName + ";encrypt=true;trustServerCertificate=true;" + encodingProperty;

    public static String getDriverName() {
        return driverName;
    }

    public static String getPort() {
        return port;
    }

    public static String getDatabaseName() {
        return databaseName;
    }

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }

    public static String getUrl() {
        return url;
    }
}
