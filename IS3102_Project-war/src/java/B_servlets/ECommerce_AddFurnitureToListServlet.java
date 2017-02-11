/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package B_servlets;

import HelperClasses.ShoppingCartLineItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author SEP-23
 */
@WebServlet(name = "ECommerce_AddFurnitureToListServlet", urlPatterns = {"/ECommerce_AddFurnitureToListServlet"})
public class ECommerce_AddFurnitureToListServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            
            HttpSession session = request.getSession();
            ArrayList<ShoppingCartLineItem> shoppingCart = (ArrayList<ShoppingCartLineItem>) (session.getAttribute("shoppingCart"));
            
            String SKU = request.getParameter("SKU");
            String Name = request.getParameter("name");
            double Price = Double.parseDouble(request.getParameter("price"));
            String ImageURL = request.getParameter("imageURL");
            
            int allQty = getAllQuantity(SKU);
            if(allQty != 0){
                System.out.println("Total storage quantity: " + allQty);
                shoppingCart = new ArrayList<ShoppingCartLineItem>();
                //ArrayList<ShoppingCartLineItem> al = new ArrayList();
                ShoppingCartLineItem shoppingCartLineItem = new ShoppingCartLineItem();
                shoppingCartLineItem.setSKU(SKU);
                shoppingCartLineItem.setPrice(Price);
                shoppingCartLineItem.setName(Name);
                shoppingCartLineItem.setImageURL(ImageURL);
                shoppingCartLineItem.setQuantity(allQty);
                shoppingCart.add(shoppingCartLineItem);
                
                session.setAttribute("shoppingCart", shoppingCart);
                
                response.sendRedirect("/IS3102_Project-war/B/SG/shoppingCart.jsp?goodMsg=Item successfully added into the cart.");
            }else{
                System.out.println("No storage quantity");
                response.sendRedirect("/IS3102_Project-war/B/SG/shoppingCart.jsp?errMsg=Item not added to cart, not enough quantity available.");
            }
            
        }
    }
    public int getAllQuantity(String SKU) {
        try {
            System.out.println("getQuantity() SKU: " + SKU);
            Client client = ClientBuilder.newClient();
            WebTarget target = client
                    .target("http://localhost:8080/WebService_Student-CA4-/webresources/entity.storeentity")
                    .path("getAllQuantity")
                    .queryParam("SKU", SKU);
            Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.get();
            System.out.println("status: " + response.getStatus());
            if (response.getStatus() != 200) {
                return 0;
            }
            String result = (String) response.readEntity(String.class);
            System.out.println("Result returned from ws: " + result);
            return Integer.parseInt(result);

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
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