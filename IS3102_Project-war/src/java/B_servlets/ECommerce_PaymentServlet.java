/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package B_servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Enidtingz
 */
@WebServlet(name = "ECommerce_PaymentServlet", urlPatterns = {"/ECommerce_PaymentServlet"})
public class ECommerce_PaymentServlet extends HttpServlet {

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

           DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         Date date = new Date();
         String currDate = dateFormat.format(date);
         
         Calendar c = Calendar.getInstance();
         int currentYear = c.get(Calendar.YEAR);
         int currentMonth = c.get(Calendar.MONTH);
         String name = request.getParameter("txtName");
         String cardNo = request.getParameter("txtCardNo");
         String securityCode = request.getParameter("txtSecurityCode");
         String month = request.getParameter("month");
         String year = request.getParameter("year");
         int monthVal = 0;
         
        int firstDigit = Integer.parseInt(cardNo.substring(0, 1));
         
         if(name.isEmpty() || year.isEmpty()){
            response.sendRedirect("/IS3102_Project-war/B/SG/shoppingCart.jsp?errMsg=Please do not leave empty fields.");
       
         }else if(cardNo.length()!=16){ 
              response.sendRedirect("/IS3102_Project-war/B/SG/shoppingCart.jsp?errMsg=Please enter a valid Mastercard No.");
               if (firstDigit % 2 !=0 && firstDigit == 5){
             response.sendRedirect("/IS3102_Project-war/B/SG/shoppingCart.jsp?errMsg=Please enter a valid Mastercard No.");}
               
         }             
//        }else if(cardNo.length() !=20){
//                response.sendRedirect("/IS3102_Project-war/B/SG/shoppingCart.jsp?errMsg=Please enter a valid visa No.");
//            
//        }
        else if(securityCode.length()!=3){
            response.sendRedirect("/IS3102_Project-war/B/SG/shoppingCart.jsp?errMsg=Please enter a valid CVV No.");
         }
        else if(year.length()!=4){
            response.sendRedirect("/IS3102_Project-war/B/SG/shoppingCart.jsp?errMsg=Please enter a valid year.");
        }
        else{ 
            
            response.sendRedirect("/IS3102_Project-war/B/SG/index.jsp");
            /*switch(month){
                 case "January":
                 monthVal = 1;
                 break;
                 
                 case "February":
                 monthVal = 2;
                 break;
                 
                 case "March":
                 monthVal = 3;
                 break;
                 
                 case "April":
                 monthVal = 4;
                 break;
                 
                 case "May":
                 monthVal = 5;
                 break;
                 
                 case "June":
                 monthVal = 6;
                 break;
                 
                 case "July":
                 monthVal = 7;
                 break;
                 
                 case "August":
                 monthVal = 8;
                 break;
                 
                 case "September":
                 monthVal = 9;
                 break;
                 
                 case "October":
                 monthVal = 10;
                 break;
                 
                 case "November":
                 monthVal = 11;
                 break;
                 
                 case "December":
                 monthVal = 12;
                 break;
            }*/
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
