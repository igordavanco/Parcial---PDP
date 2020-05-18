/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author igor_
 */
public class ConnectionFactory {
    private static Connection instance;
    private static final String URL = "jdbc:sqlite:banco_de_dados/db_projeto.db";
    
    public static Connection getConnection(){
        try{
            if(instance == null){
                instance = DriverManager.getConnection(URL);
            }
        }catch(SQLException e){
            throw new RuntimeException("Ops!! Erro ao conectar com o banco de dados!", e);
        }
        return instance;
    }
    
    public static void closeConnection(){
        try{
            if(instance.isClosed() == false){
                instance.close();
                instance = null;
            }
        }catch(SQLException e){
            throw new RuntimeException("Ops!! Erro ao fechar o banco de dados!", e);
        }
    }
}
