
package hieutt.servlet;

import hieutt.daos.OrderDAO;
import hieutt.daos.OrderDetailDAO;
import hieutt.daos.ProductDAO;
import hieutt.dtos.CartObject;
import hieutt.dtos.OrderDTO;
import hieutt.dtos.OrderDetailDTO;
import hieutt.dtos.ProductDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HP
 */
public class CheckOutServlet extends HttpServlet 
{
    private final String SUCCESS = "payment.jsp";
    private final String FAIL = "viewbill.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession(true);
        
        CartObject cart = (CartObject) session.getAttribute("YOURCART");
        
        Map<ProductDTO, Integer> items = cart.getItems();
        
        String txtTotalBill = request.getParameter("txtTotalBill");
        
        String url = FAIL;
        
        try
        {
            float totalBill = Float.parseFloat(txtTotalBill);
            String userName = request.getParameter("txtUsername");
            String phone = request.getParameter("txtPhone");
            String txtAddress = request.getParameter("txtAddress");
            String paymentMethod = request.getParameter("txtPaymemt");
            
            String orderID = hieutt.utils.CreateOrderID.createID(cart.getUserID());
            
            OrderDTO orderDTO = new OrderDTO(orderID, cart.getUserID(), totalBill, paymentMethod, userName, phone, txtAddress);
            OrderDAO orderDAO = new OrderDAO();
            
            if (orderDAO.addNewOder(orderDTO, paymentMethod))
            {
                for (Map.Entry<ProductDTO, Integer> entry : items.entrySet()) 
                {
                    ProductDTO productDTO = entry.getKey();
                    Integer amount = entry.getValue();
                    
                    OrderDetailDTO orderDetailDTO = new OrderDetailDTO(orderID, productDTO.getId(), productDTO.getPrice(), amount);
                    OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                    
                    if (orderDetailDAO.addNewOderDetail(orderDetailDTO))
                    {
                        productDTO.setQuantity(productDTO.getQuantity() - amount);
                        ProductDAO productDAO = new ProductDAO();
                        productDAO.updateProduct(productDTO);
                        
                        url = SUCCESS;
                    }
                }
                session.removeAttribute("YOURCART");
            }
        }
        catch (NamingException ex) 
        {
            System.out.println(ex);
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex);
        }        
        finally
        {
            response.sendRedirect(url);
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
