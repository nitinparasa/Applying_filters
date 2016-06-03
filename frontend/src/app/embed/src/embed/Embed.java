/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package embed;

import java.sql.SQLException;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
/**
 *
 * @author mahith
 */
public class Embed {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            Class.forName("org.h2.Driver");
            Connection conn=DriverManager.getConnection("jdbc:h2:~/test","Mahith","2108");
            Statement st = conn.createStatement();
            // st.execute("insert into new values(10,12)");
            ResultSet rs = st.executeQuery("select * from new");
             ResultSetMetaData rsmd = rs.getMetaData();
             int x=rsmd.getColumnCount();
             for(int i=1;i<=x;i++){
             //System.out.print(x);
             String s = rsmd.getColumnName(i);
             System.out.print(s + "\t");
             }
             while(rs.next()) {
             System.out.println("");
             for(int j=1;j<=x;j++){
             System.out.print(rs.getString(j)+"\t");
             }
            }
        conn.close();
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Embed.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Embed.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
}
