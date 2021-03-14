
package hieutt.servlet;

import hieutt.dtos.CartObject;
import hieutt.dtos.ProductDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HP
 */
public class RemoveFromCartServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try
        {
           HttpSession session = request.getSession(false);
            if (session != null)
            {
                CartObject cart = (CartObject) session.getAttribute("YOURCART");
                if (cart != null)
                {
                    Map<ProductDTO, Integer> items = cart.getItems();
                    if (items != null)
                    {
                        String id = request.getParameter("txtProductID");
                        ProductDTO dto = cart.getItemById(id);
                        cart.removeItemFromCart(dto);
                        
                        if (cart.getItems() != null)
                        {
                            session.setAttribute("YOURCART", cart);
                        }
                        else
                        {
                            session.removeAttribute("YOURCART");
                        }
                    }
                }
            }
        }
        finally
        {
            String urlRewriting = "viewcart.jsp";
            response.sendRedirect(urlRewriting);
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
