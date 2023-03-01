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
    
    protected static void openConnection() {
        DBConnection = DatabaseConnection.getConnection();
    }

    protected static void closeConnection() {
        try {
            DBConnection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    protected abstract void openQuery(String SQLQuery);
    protected abstract void closeQuery();
}
