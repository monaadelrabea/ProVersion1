/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DataBase;
import dao.DataBase2;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.*;

/**
 *
 * @author m@pc
 */
public class Controller1 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         String action = request.getParameter("action");
        String name = request.getParameter("username");//username
        String pass=request.getParameter("password");
     //   int id=Integer.parseInt(request.getParameter("id"));
           
          
        switch (action) {
            case "login":
              /*  Boolean log = DataBase.getInstance().login(name, pass);
                if (log==true) {
//                    ArrayList<Product> product = DataBase.getInstance().getProductlistOfFavourite(id);
                    request.setAttribute("list", product);
                    request.setAttribute("data", "");
                    forward(request, response, "/Profile.jsp");
                } */
                 forward(request, response, "/login.jsp");

                break;
              case "vegitable":
              
                     forward(request, response, "/vegtables.jsp");
             
                break;
                   case "meal":
               String catagory2=request.getParameter("catagory");
                 ArrayList<Product> productlis = DataBase.getInstance().getProductlist1(catagory2);
                 request.setAttribute("productCat1list", productlis);
                     forward(request, response, "/meal.jsp");
             
                break;
            case "ProductList":

                ArrayList<Product> prolist = DataBase2.getInstance().getProductlist();
                request.setAttribute("prolist", prolist);
                forward(request, response, "/ProductList.jsp");
                break;
                     case "CustomerList":

                ArrayList<Customer> cuslist = DataBase2.getInstance().getCustomerlist();
                request.setAttribute("prolist", cuslist);
                forward(request, response, "/CustomerList.jsp");
                break;
            case "EditUser":
                Boolean update  = DataBase.getInstance().AddCustomer(null);
                request.setAttribute("user", update);
                forward(request, response, "/UserEdit2.jsp");

                break;
            case "Vegetables":// save only
                 forward(request, response, "vegtables.jsp");
                break;
            case "Save": // validate and then save 
               
                   


                break;
            case "ViewUser":
             

                break;
            case "DeleteOk":
              
                break;

            case "Add":// Add only
                break;
            case "addproduct"://add new product to product tale as manager
               
                break;

            case "addFafourite"://add new product to favoirite table
               
                break;
            case "addToCart":
              
               
                break;
            case "deleteFromcart": //delete from produact from cart table

        }
    }

    public void forward(HttpServletRequest request, HttpServletResponse response, String nextPage) {
        try {
            RequestDispatcher rd = getServletContext().getRequestDispatcher(nextPage);
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
