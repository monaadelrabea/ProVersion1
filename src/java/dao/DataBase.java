/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;
import model.Product;

/**
 *
 * @author m@pc
 */
public class DataBase {
    private static DataBase instance = null;
    private Connection conn;
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/food";
   Product p=new Product();
     private DataBase (){
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, "root","");

        } catch (Exception ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
   public static synchronized DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }
   
   
   public  boolean login(String userName,String Password){
       try{
        PreparedStatement pStm = conn.prepareStatement("select email , password   from Chat.Users where email= ?  and password= ?");
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
   	  Logger.getLogger(DataBase .class.getName()).log(Level.SEVERE, null, e);
	}
	return false;
}
    public boolean AddCustomer(Customer c){
         boolean result =false;
          try{
              int id=max_id()+1;
               int cart=max_id_cart()+1;
              PreparedStatement pstm =conn.prepareStatement("insert into food.customer values(?,?,?,?,?,?,?,?,?,?)");
              pstm.setInt(1, id);
              pstm.setString(2, c.getUsername());
              pstm.setString(3, c.getPassword());
              pstm.setString(4, c.getEmail());
             pstm.setString(5, c.getAddress());
              pstm.setDate(6, c.getBirthday());
              pstm.setString(7, c.getJob());
                pstm.setInt(8, c.getCredit());
                pstm.setString(9, c.getGender()); 
                 pstm.setInt(10, cart);
              int update =pstm.executeUpdate();
              
              if(update>0){
                  result = true ;
              }
             pstm.close();
          } catch (Exception ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
          return result ;  
     }
         public int max_id(){
         int max=0;
      try{
           PreparedStatement pStm = conn.prepareStatement("SELECT max(id) FROM food.customer");
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
    public int max_id_cart(){
         int max=0;
      try{
           PreparedStatement pStm = conn.prepareStatement("SELECT max(cart_id) FROM food.customer");
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
        public int max_id_c(){
         int max=0;
      try{
           PreparedStatement pStm = conn.prepareStatement("SELECT max(id) FROM food.cart");
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
           public int max_id_f(){
         int max=0;
      try{
           PreparedStatement pStm = conn.prepareStatement("SELECT max(id) FROM food.favorites");
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
            public int max_id_h(){
         int max=0;
      try{
           PreparedStatement pStm = conn.prepareStatement("SELECT max(id) FROM food.history");
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
       public int id_cart(String uname){
         int cart_id=0;
         
      try{
           PreparedStatement pStm = conn.prepareStatement("SELECT cart_id FROM food.customer where username=?");
              pStm.setString(1, uname);
          ResultSet rs = pStm.executeQuery();
          if(rs.next()) 
            cart_id=rs.getInt(1) ; 
          
          pStm.close();
          
             }
      catch (SQLException ex) {   
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }   
        return cart_id;
     }
         public int id_product(String pname){
         int id=0;
         
      try{
           PreparedStatement pStm = conn.prepareStatement("SELECT id FROM food.product where name=?");
              pStm.setString(1, pname);
          ResultSet rs = pStm.executeQuery();
          if(rs.next()) 
            id=rs.getInt(1) ; 
       
          pStm.close();
          
             }
      catch (SQLException ex) {   
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }   
        return id;
     }
   
   
   
     public ArrayList<Product> getProductlist() {
        ArrayList<Product> productlist = new ArrayList<Product>();
        try {
            PreparedStatement pStm = conn.prepareStatement("SELECT * FROM food.product");

            ResultSet rs = pStm.executeQuery();

            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setImage( rs.getString(3));
                p.setPrice(rs.getInt(4));
                p.setDescription(rs.getString(5));
                p.setQuantity(rs.getInt(6));
                productlist.add(p);
            }

            rs.close();
            pStm.close();
        } catch (Exception ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productlist;
    }
     public ArrayList<Product> getProductlist1(String Catag) {
        ArrayList<Product> productlist = new ArrayList<Product>();
        try {
            PreparedStatement pStm = conn.prepareStatement("SELECT * FROM food.product where catagory=?");

            ResultSet rs = pStm.executeQuery();

            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setImage( rs.getString(3));
                p.setPrice(rs.getInt(4));
                 p.setDescription(rs.getString(5));
                p.setQuantity(rs.getInt(6));
                productlist.add(p);
            }

            rs.close();
            pStm.close();
        } catch (Exception ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productlist;
    }
        public Product getProduct(String pname) {
            
        try {
            PreparedStatement pStm = conn.prepareStatement("SELECT * FROM food.product where name=? ");
            pStm.setString(1, pname);
            ResultSet rs = pStm.executeQuery();

            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setImage(rs.getString(3));
                p.setPrice(rs.getInt(4));
                 p.setDescription(rs.getString(5));
                p.setQuantity(rs.getInt(6));
               
            }

            rs.close();
            pStm.close();
        } catch (Exception ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
        public Product getProduct(String pname ,String catg) {
            
        try {
            PreparedStatement pStm = conn.prepareStatement("SELECT * FROM food.product where name=? and catagory=?");
            pStm.setString(1, pname);
            pStm.setString(2, catg);
            ResultSet rs = pStm.executeQuery();

            while (rs.next()) {
               p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setImage(rs.getString(3));
                p.setPrice(rs.getInt(4));
                 p.setDescription(rs.getString(5));
                p.setQuantity(rs.getInt(6));
               
            }

            rs.close();
            pStm.close();
        } catch (Exception ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
      public  ArrayList<Product> getProduct1(int price) {
           ArrayList<Product> productlist = new ArrayList<Product>();
        try {
            PreparedStatement pStm = conn.prepareStatement("SELECT * FROM food.product where price=?");
             pStm.setInt(1, price);
            ResultSet rs = pStm.executeQuery();

            while (rs.next()) {
               // p.setId(rs.getInt(1));
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
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productlist;
    }
     
      public boolean buyProduct(String username, String productname,int quantity) {
  
        boolean result = true;
        try {
            PreparedStatement pStm = conn.prepareStatement("insert into  food.cart values (?,?,?,?)");
            int id = max_id_c()+ 1;
            int pro = id_product(productname);
            int cart = id_cart(username);
            pStm.setInt(1, id);
            pStm.setInt(2, pro);
            pStm.setInt(3, cart);
            pStm.setInt(4, quantity);
             pStm.executeUpdate();
            if (pStm.executeUpdate() > 0) {
                result = true;
            }
            pStm.close();

        } catch (Exception ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;

    }
       public boolean removeProduct(String username) {
        boolean result = true;
        try {
            PreparedStatement pStm = conn.prepareStatement("delete  from  food.cart where cart_id =?  ");
            pStm.setInt(1, id_cart(username));
            pStm.execute();
            if (pStm.execute()) {
                result = true;
            }
            pStm.close();

        } catch (Exception ex) {
            Logger.getLogger(DataBase .class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
        public boolean removeProduct(String username,String pname) {
        boolean result = true;
        try {
            PreparedStatement pStm = conn.prepareStatement("delete  from  food.cart where cart_id =? and product_id= ? ");
            pStm.setInt(1, id_cart(username));
            pStm.setInt(2, id_product(pname));
            pStm.execute();
            if (pStm.execute()) {
                result = true;
            }
            pStm.close();

        } catch (Exception ex) {
            Logger.getLogger(DataBase .class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
 public boolean favouriteProduct(String username, String productname) {
  
        boolean result = true;
        try {
            PreparedStatement pStm = conn.prepareStatement("insert into  food. favorites values (?,?,?)");
            int id = max_id_f()+ 1;
            int pro = id_product(productname);
            int cart = id_cart(username);
            pStm.setInt(1, id);
            pStm.setInt(2, pro);
            pStm.setInt(3, cart);
             pStm.executeUpdate();
            if (pStm.executeUpdate() > 0) {
                result = true;
            }
            pStm.close();

        } catch (Exception ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;

    }
  public boolean removeProduct_f(String username,String pname) {
        boolean result = true;
        try {
            PreparedStatement pStm = conn.prepareStatement("delete  from  food.favorites where customer_id =? and product_id= ? ");
            pStm.setInt(1, id_cart(username));
            pStm.setInt(2, id_product(pname));
            pStm.execute();
            if (pStm.execute()) {
                result = true;
            }
            pStm.close();

        } catch (Exception ex) {
            Logger.getLogger(DataBase .class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
  
  
  
   public boolean historyProduct(String username, String productname) {
  
        boolean result = true;
        try {
            PreparedStatement pStm = conn.prepareStatement("insert into  food. history values (?,?,?)");
            int id = max_id_h()+ 1;
           
            pStm.setInt(1, id);
            pStm.setString(2, username);
            pStm.setString(3, productname);
         
             pStm.executeUpdate();
            if (pStm.executeUpdate() > 0) {
                result = true;
            }
            pStm.close();

        } catch (Exception ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;

    }
  
    
    
   
   
        
        
        
        
        
        
        
    }
     
     
     
     
     
    

