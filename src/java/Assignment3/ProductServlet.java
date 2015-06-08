/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Assignment3;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author c0652674
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/ProductServlet"})
public class ProductServlet extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        try {
            PrintWriter out=response.getWriter();
            if(!request.getParameterNames().hasMoreElements()){
                DB_work d=new DB_work();
                List<Product> products=d.selectAllProducts();
                for(Product p:products){
                    System.out.println(p.toJSON());
                }
            }else{
                DB_work d=new DB_work();
                Product p=d.selectById(2);
                System.out.println(p.toJSON());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
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
        Set<String> set = request.getParameterMap().keySet();
        try {
            PrintWriter pw=response.getWriter();
            if(set.contains("pId")&& set.contains("pName")&&set.contains("pDescription")&&set.contains("pQuantity")){
                int pId=Integer.parseInt(request.getParameter("pId"));
                String pName=request.getParameter("pName");
                String pDescription=request.getParameter("pDescription");
                int pQuantity=Integer.parseInt(request.getParameter("pQuantity"));
                Product p=new Product(pId,pName,pDescription,pQuantity);
                DB_work d=new DB_work();
                d.insertProduct(p);
            }else{
                response.setStatus(500);
            }
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {

        Set<String> keySet = request.getParameterMap().keySet();
        try (PrintWriter out = response.getWriter()) {
            if (keySet.contains("ProductID") && keySet.contains("name") && keySet.contains("description") && keySet.contains("quantity")) {
                int pId=Integer.parseInt(request.getParameter("pId"));
                String pName=request.getParameter("pName");
                String pDescription=request.getParameter("pDescription");
                int pQuantity=Integer.parseInt(request.getParameter("pQuantity"));
                DB_work d=new DB_work();
                d.updateById(pId);
            }else{
                System.out.println("Error");
            }
        } catch (IOException ex) {
            response.setStatus(500);
            System.out.println("Error in writing output: " + ex.getMessage());
        }
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Set<String> keySet = request.getParameterMap().keySet();
        try {
            PrintWriter pw=response.getWriter();
            if(!request.getParameterNames().hasMoreElements()){
                int pId=Integer.parseInt(request.getParameter("pId"));                
                DB_work d=new DB_work();
                d.deleteById(pId);
            }else{
                response.setStatus(500);
            }
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
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
