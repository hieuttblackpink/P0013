
package hieutt.servlet;

import hieutt.daos.CategoryDAO;
import hieutt.daos.UserDAO;
import hieutt.dtos.CategoryDTO;
import hieutt.dtos.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet 
{
    private final String LOGIN_SUCCESS_USER = "home.jsp";
    private final String LOGIN_SUCCESS_ADMIN = "admin.jsp";
    private final String LOGIN_FAIL = "login.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("txtID");
        String password = request.getParameter("txtPassword");
        String url = LOGIN_FAIL;
        try
        {
            UserDAO dao = new UserDAO();
            UserDTO dto = dao.checkLogin(id, password);
            if (dto != null)
            {
                HttpSession session = request.getSession(true);
                session.setAttribute("NAME", dto.getLastName());
                session.setAttribute("USERID", dto.getUserID());
                
                if (dto.getRole().equals("Admin"))
                {
                    url = LOGIN_SUCCESS_ADMIN;
                }
                else
                {
                    url = LOGIN_SUCCESS_USER;
                }
            }
            else
            {
                request.setAttribute("LOGINFAIL", "WRONG ID OR PASSWORD. PLEASE TRY AGAIN");
            }
        }
        catch (Exception ex) 
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
