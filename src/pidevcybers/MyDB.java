/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author HAYLEM SAKHRAOUI
 */
public class MyDB {
    public String url="jdbc:mysql://localhost:3306/esprit";
    public String username="root";
    public String pwd="";
    public Connection conn ;
    public Statement ste ;

    public MyDB() {
        try {
            conn = DriverManager.getConnection(url, url, pwd);
            ste = conn.createStatement();
            ResultSet resultset =ste.executeQuery("select * from personne");
            while(resultset.next()){
                System.out.println(resultset.getInt(1)+"");
                        
                        
                   
            }
                   
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
    
        }
    }
}
    