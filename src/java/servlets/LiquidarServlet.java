/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Control.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Modelo.*;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.CaException;
/**
 *
 * @author JULIAN
 */
public class LiquidarServlet extends HttpServlet {
    Vehiculo v;
    Liquidacion l;
    Cilindraje c;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws util.CaException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CaException {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String Placa = request.getParameter("placa");
            String Cedula = request.getParameter("cedula");
            String Anio = request.getParameter("anio");
            
            VehiculoDAO vdao = new VehiculoDAO();
            v = vdao.getV();
            v.setK_placa(Placa);
            vdao.buscarVehiculo();
            
            
            CilindrajeDAO cdao = new CilindrajeDAO();
            c = cdao.getC();
            cdao.BuscarBaseGravable(v.getK_idCilindraje(), v.getK_idL(), v.getK_modelo());
            
            
            response.sendRedirect("liquidacion.jsp?placa="+Placa+"&cedula="+Cedula+"&anio="+Anio);
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
        try {
            processRequest(request, response);
        } catch (CaException ex) {
            Logger.getLogger(LiquidarServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (CaException ex) {
            Logger.getLogger(LiquidarServlet.class.getName()).log(Level.SEVERE, null, ex);
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
