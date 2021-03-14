
package hieutt.servlet;

import hieutt.daos.ProductDAO;
import hieutt.dtos.ProductDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StartupServlet extends HttpServlet 
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession(true);
        
        String button = request.getParameter("btAction");
        
        int pageNum = 1;
        
        if ("CONTINUE SHOPPING".equals(button))
        {
            pageNum = 1;
        }
        else if (button != null)
        {
            pageNum = Integer.parseInt(button);
        }
        
        int startPage = pageNum * 5 - 4;
        int endPage = pageNum * 5;
        
        String url = "home.jsp";
        
        try
        {
            ProductDAO dao = new ProductDAO();
            List<ProductDTO> first20Record = dao.getFirstProduct("ASC", startPage, endPage);
            int totalRecord = first20Record.get(0).getTotalRecord();
            int totalPage = 1;
            
            if (totalRecord%5 == 0)
            {
                totalPage = totalRecord / 5;
            }
            else
            {
                totalPage = (totalRecord / 5) + 1;
            }
            session.setAttribute("FIRSTLOAD", first20Record);
            session.setAttribute("CURRENTPAGE", pageNum);
            session.setAttribute("TOTALRECORDFIRST", totalPage);
            String txtRole = request.getParameter("txtRole");
            if ((txtRole != null) && (txtRole.equals("ADMIN")))
            {
                url = "admin.jsp";
            }
        }
        catch (SQLException ex) 
        {
            
        }
        catch (NamingException ex)
        {
            
        }       
        finally
        {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
