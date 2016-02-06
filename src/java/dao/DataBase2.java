/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;
import model.Product;

/**
 *
 * @author m@pc
 */
public class DataBase2 {
      private static DataBase2 instance = null;
    private Connection conn;
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/food";
   Product p=new Product();
   Customer c=new Customer();
     private DataBase2 (){
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, "root","");

        } catch (Exception ex) {
            Logger.getLogger(DataBase2.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
   public static synchronized DataBase2 getInstance() {
        if (instance == null) {
            instance = new DataBase2();
        }
        return instance;
    }
   
   
   public  boolean login(String userName,String Password){
       try{
        PreparedStatement pStm = conn.prepareStatement("select name , password   from Chat.Users where name= ?  and password= ?");
            pStm.setString(1,userName);
               pStm.setString(2, Password);
                ResultSet result = pStm.executeQuery();
	            if (result.next()) {
                String username = result.getString(1);         
               String userPass = result.getString(2);
               if(userName.equals(username) && Password.equals(userPass)){
                   return true;
               }     
            }
                      
	}
	catch (Exception e)  {
   	  Logger.getLogger(DataBase2.class.getName()).log(Level.SEVERE, null, e);
	}
	return false;
}
   public ArrayList<Product> getProductlist() {
        ArrayList<Product> productlist = new ArrayList<Product>();
        try {
            PreparedStatement pStm = conn.prepareStatement("SELECT * FROM food.product");

            ResultSet rs = pStm.executeQuery();

            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setImage(rs.getString(3));
                p.setPrice(rs.getInt(4));
                p.setDescription(rs.getString(5));
                p.setQuantity(rs.getInt(6));
                productlist.add(p);
            }

            rs.close();
            pStm.close();
        } catch (Exception ex) {
            Logger.getLogger(DataBase2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productlist;
    }
    public ArrayList<Customer> getCustomerlist() {
        ArrayList<Customer> Customerlist = new ArrayList<Customer>();
        try {
            PreparedStatement pStm = conn.prepareStatement("SELECT * FROM food.product");

            ResultSet rs = pStm.executeQuery();

            while (rs.next()) {
                c.setId(rs.getInt(1));
                c.setUsername(rs.getString(2));
                c.setPassword(rs.getString(3));
                c.setEmail(rs.getString(4));
                 c.setAddress(rs.getString(5));
                c.setBirthday(rs.getDate(6));
                c.setJob(rs.getString(7));
                c.setCredit(rs.getInt(8));
                c.setGendr(rs.getString(9));
                c.setCart(rs.getInt(10));
               Customerlist.add(c);
            }

            rs.close();
            pStm.close();
        } catch (Exception ex) {
            Logger.getLogger(DataBase2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Customerlist;
    }
    public boolean removeProduct(int id) {
        boolean result = true;
        try {
            PreparedStatement pStm = conn.prepareStatement("delete  from  food.product where id =?  ");
            pStm.setInt(1, id);
            pStm.execute();
            if (pStm.execute()) {
                result = true;
            }
            pStm.close();

        } catch (Exception ex) {
            Logger.getLogger(DataBase2 .class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public boolean removeCustomer(int id) {
        boolean result = true;
        try {
            PreparedStatement pStm = conn.prepareStatement("delete  from  food.customer where id =?  ");
            pStm.setInt(1, id);
            pStm.execute();
            if (pStm.execute()) {
                result = true;
            }
            pStm.close();

        } catch (Exception ex) {
            Logger.getLogger(DataBase2 .class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public int max_id_pro(){
         int max=0;
      try{
           PreparedStatement pStm = conn.prepareStatement("SELECT max(id) FROM food.product");
          ResultSet rs = pStm.executeQuery();
          if(rs.next()) 
            max=rs.getInt(1) ; 
          pStm.close();
          
             }
      catch (SQLException ex) {   
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }   
        return max;
     }
     public boolean getProduct(Product p) {
             boolean result =false;
            int id=max_id_pro()+1;
        try {
           PreparedStatement pstm =conn.prepareStatement("insert into food.product values(?,?,?,?,?,?,?)");
             pstm .setInt(1,id);
              pstm.setString(2, p.getName());
              pstm.setString(3, p.getImage());
              pstm.setInt(4, p.getPrice());
             pstm.setString(5, p.getDescription());
              pstm.setInt(6, p.getQuantity());
                pstm.setString(7, p.getCatagory());
          
              int update =pstm.executeUpdate();
              
              if(update>0){
                  result = true ;
              }
          pstm.close();
        } catch (Exception ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
     public boolean updateProduct(Product p,int id){
            boolean result =false;
            try{
         PreparedStatement pstm =conn.prepareStatement("update food.product set name=?,image=?,price=?,description=?,quantity=?,catagory=? where id=? ");
         
       
              pstm.setString(1, p.getName());
              pstm.setString(2, p.getImage());
              pstm.setInt(3, p.getPrice());
             pstm.setString(4, p.getDescription());
              pstm.setInt(5, p.getQuantity());
                pstm.setString(6, p.getCatagory());
            pstm .setInt(7,id);
         
         
         
         
         
         
       int update =pstm.executeUpdate();
              
              if(update>0){
                  result = true ;
              }
          pstm.close();
        } catch (Exception ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }      
   
    
    
    
}
