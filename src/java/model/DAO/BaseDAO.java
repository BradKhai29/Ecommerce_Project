/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.DAO;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author This PC
 */
public abstract class BaseDAO<T> implements IDao<T>{
    //DBConnection section
    protected static Connection DBConnection;
    protected static PreparedStatement query;
    
    
    /**
     * Open Connection to the Database
     */
    protected static void openConnection() {
        DBConnection = DatabaseConnection.getConnection();
    }

    /**
     * Close the current connection to the Database
     */
    protected static void closeConnection() {
        try {
            DBConnection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    /**
     * Open Connection and create a new PrepareStatement with given <span style="color:red">SQLQuery</span>
     * @param SQLQuery 
     */
    protected abstract void openQuery(String SQLQuery);
    
    /**
     * Close this Query and Connection
     * @param SQLQuery 
     */
    protected abstract void closeQuery();
}
