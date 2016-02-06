/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.mysql.jdbc.Clob;

/**
 *
 * @author Aya
 */
public class Product {
    
private int id;    
private String name;
private String image;
private String description;
private int price;
private int quantity;
private String catagory;




public Product() {

}

public int getId() {  
    return id;  
}  
public void setId(int id) {  
    this.id = id;  
}   
public String getName() {  
    return name;  
}  
public void setName(String name) {  
    this.name = name;  
}  

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

  
public int getPrice() {  
    return price;  
}  
public void setPrice(int price) {  
    this.price= price;  
} 
public String getDescription() {  
    return description;  
}  
public void setDescription(String description) {  
    this.description = description;  
}


public int getQuantity() {  
    return quantity;  
}  
public void setQuantity(int quantity) {  
    this.quantity =quantity;  
}

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    
}
