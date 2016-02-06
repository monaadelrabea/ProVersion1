/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Aya
 */
public class Customer {
private int id;  
private String username;
private String password;
private String address;
private String email;
private Date birthday;
private String job;
private int credit;
private String gender;
private int cart;




public Customer() {

}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
public String getUsername() {  
    return username;  
}  
public void setUsername(String username) {  
    this.username = username;  
}  
public String getPassword() {  
    return password;  
}  
public void setPassword(String password) {  
    this.password = password;  
}
public String getEmail() {  
    return email;  
}  
public void setEmail(String email) {  
    this.email = email;  
} 
public String getAddress() {  
    return address;  
}  
public void setAddress(String address) {  
    this.address = address;  
}
public String getJob() {  
    return job;  
}  
public void setJob(String job) {  
    this.job = job;  
}
public String getGender() {  
    return gender;  
}  
public void setGendr(String gender) {  
    this.gender = gender;  
}

public int getCredit() {  
    return credit;  
}  
public void setCredit(int credit) {  
    this.credit =credit;  
}

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getCart() {
        return cart;
    }

    public void setCart(int cart) {
        this.cart = cart;
    }



}
