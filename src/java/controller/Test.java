/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DataBase;
import java.util.ArrayList;
import model.Product;

/**
 *
 * @author m@pc
 */
public class Test {

   
    public static void main (String []args){
     ArrayList<Product> p= DataBase.getInstance().getProductlistOfFavourite(1);
     ArrayList<Product> l= DataBase.getInstance().getProductlist();
     
     System.out.println(l);
    }
}
