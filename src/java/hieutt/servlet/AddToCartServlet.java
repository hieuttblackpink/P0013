
package hieutt.servlet;

import hieutt.dtos.CartObject;
import hieutt.dtos.ProductDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddToCartServlet extends HttpServlet
{
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String urlRewriting = "";
        
        String button = request.getParameter("btAction");
        
        try
        {
            
            HttpSession session = request.getSession(true);
            
            CartObject cart = (CartObject) session.getAttribute("YOURCART");
            if (cart == null)
            {
                cart = new CartObject();
            }
            
            String userID = request.getParameter("txtUserID");
            String productID = request.getParameter("txtProductID");
            String txtAmount = request.getParameter("txtAmount");
            int amount = Integer.parseInt(txtAmount);
            ProductDTO dto = cart.getItemById(productID);
            int currentAmount = cart.getAmountById(productID);
            String searchValue = request.getParameter("txtSearch");
            
            if (dto != null)
            {
                if ((button != null) && ("ADD MORE".equals(button)))
                {
                    cart.addItemToCart(dto, amount);
                }
                if ((button != null) && ("DECREASE".equals(button)))
                {
                    cart.addItemToCart(dto, amount);
                }
                if ((button != null) && ("ADD TO CART".equals(button)))
                {
                    cart.addItemToCart(dto, currentAmount + amount);
                }
            }
            else
            {
                String productName = request.getParameter("txtProductName");
                String productImage = request.getParameter("txtImage");
                String productDescription = request.getParameter("txtDescription");
                String productCategory = request.getParameter("txtCategory");
                String productQuantity = request.getParameter("txtQuantity");
                int quantityProduct = Integer.parseInt(productQuantity);
                String txtProductPrice = request.getParameter("priceOfProduct");
                float productPrice = Float.parseFloat(txtProductPrice);

                String txtTotal = request.getParameter("txtTotalPrice");
                float total = Float.parseFloat(txtTotal);

                dto = new ProductDTO(productID, productName, productImage, productDescription, productPrice, productCategory, quantityProduct, "Active");

                cart.setUserID(userID);
                cart.addItemToCart(dto, amount);
                
                urlRewriting = "DispatchServlet?txtSearch=" + searchValue + "&role=USER&btAction=SEARCH";
            }
            
            session.setAttribute("YOURCART", cart);
            
            if ((button != null) && ("ADD MORE".equals(button)))
            {
                urlRewriting = "viewcart.jsp";
            }
            if ((button != null) && ("DECREASE".equals(button)))
            {
                urlRewriting = "viewcart.jsp";
            }
            if ((button != null) && ("ADD TO CART".equals(button)))
            {
                urlRewriting = "DispatchServlet?txtSearch=" + searchValue + "&role=USER&btAction=SEARCH";
            }
        }       
        finally
        {
//            RequestDispatcher rd = request.getRequestDispatcher(urlRewriting);
//            rd.forward(request, response);
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
