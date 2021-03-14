
package hieutt.servlet;

import hieutt.daos.CategoryDAO;
import hieutt.dtos.CategoryDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
//    maxFileSize = 1024 * 1024 * 50, // 50MB
//    maxRequestSize = 1024 * 1024 * 50) // 50MB
public class DispatchServlet extends HttpServlet 
{
    private final String START_UP_CONTROLLER = "StartupServlet";
    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String LOGOUT_CONTROLLER = "LogoutServlet";
    private final String ADD_NEW_PRODUCT_CONTROLLER = "AddNewProductServlet";
    private final String SEARCH_BY_NAME_CONTROLLER = "SearchByNameSevlet";
    private final String SEARCH_BY_PRICE_CONTROLLER = "SearchByRangeOfPriceServlet";
    private final String UPDATE_PRODUCT_CONTROLLER = "UpdateProductServlet";
    private final String ADD_TO_CART_CONTROLLER = "AddToCartServlet";
    private final String REMOVE_FROM_CART_CONTROLLER = "RemoveFromCartServlet";
    private final String VIEW_BILL_CONTROLLER = "ViewBillServlet";
    private final String CHECK_OUT_CONTROLLER = "CheckOutServlet";
    private final String VIEW_HISTORY_CONTROLLER = "ViewHistoryOrderServlet";
    private final String VIEW_HISTORY_DETAIL_CONTROLLER = "ViewHistoryOrderDetailServlet";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = "";
        String button = request.getParameter("btAction");
        
        try
        {
            HttpSession session = request.getSession(true);
            CategoryDAO categoryDAO = new CategoryDAO();
            List<CategoryDTO> categoryDTOs = categoryDAO.loadCategory();
            String firstCategory = categoryDTOs.get(0).getCategoryId();
            session.setAttribute("CATEGORIES", categoryDTOs);
            session.setAttribute("CATEGORIESFIRST", firstCategory);
            
            if (button == null)
            {
                url = START_UP_CONTROLLER;
            }
            else if ("CONTINUE SHOPPING".equals(button))
            {
                url = START_UP_CONTROLLER;
            }
            else if ("LOGIN".equals(button))
            {
                url = LOGIN_CONTROLLER;
            }
            else if ("LOGOUT".equals(button))
            {
                url = LOGOUT_CONTROLLER;
            }
            else if ("ADD NEW".equals(button))
            {
                url = ADD_NEW_PRODUCT_CONTROLLER;
            }
            else if ("SEARCH".equals(button))
            {
                url = SEARCH_BY_NAME_CONTROLLER;
            }
            else if ("UPDATE".equals(button))
            {
                url = UPDATE_PRODUCT_CONTROLLER;
            }
            else if ("ADD TO CART".equals(button))
            {
                String user = (String) session.getAttribute("NAME");
                if (user != null)
                {
                    url = ADD_TO_CART_CONTROLLER;
                }
                else
                {
                    url = "login.jsp";
                }
            }
            else if ("ADD MORE".equals(button))
            {
                url = ADD_TO_CART_CONTROLLER;
            }
            else if ("DECREASE".equals(button))
            {
                url = ADD_TO_CART_CONTROLLER;
            }
            else if ("REMOVE".equals(button))
            {
                url = REMOVE_FROM_CART_CONTROLLER;
            }
            else if ("CONFIRM".equals(button))
            {
                url = VIEW_BILL_CONTROLLER;
            }
            else if ("ORDER AND PAY".equals(button))
            {
                url = CHECK_OUT_CONTROLLER;
            }
            else if ("OK".equals(button))
            {
                url = SEARCH_BY_PRICE_CONTROLLER;
            }
            else if ("VIEW HISTORY".equals(button))
            {
                url = VIEW_HISTORY_CONTROLLER;
            }
            else if ("VIEW DETAIL".equals(button))
            {
                url = VIEW_HISTORY_DETAIL_CONTROLLER;
            }
            else if ("1".equals(button))
            {
                String searchValue = request.getParameter("txtSearch");
                String searchByPrice = request.getParameter("txtSearchByPrice");
                if (searchValue != null)
                {
                    url = SEARCH_BY_NAME_CONTROLLER;
                }
                else if (searchByPrice != null)
                {
                    url = SEARCH_BY_PRICE_CONTROLLER;
                }
                else
                {
                    url = START_UP_CONTROLLER;
                }
            }
            else if ("2".equals(button))
            {
                String searchValue = request.getParameter("txtSearch");
                String searchByPrice = request.getParameter("txtSearchByPrice");
                if (searchValue != null)
                {
                    url = SEARCH_BY_NAME_CONTROLLER;
                }
                else if (searchByPrice != null)
                {
                    url = SEARCH_BY_PRICE_CONTROLLER;
                }
                else
                {
                    url = START_UP_CONTROLLER;
                }
            }
            else if ("3".equals(button))
            {
                String searchValue = request.getParameter("txtSearch");
                String searchByPrice = request.getParameter("txtSearchByPrice");
                if (searchValue != null)
                {
                    url = SEARCH_BY_NAME_CONTROLLER;
                }
                else if (searchByPrice != null)
                {
                    url = SEARCH_BY_PRICE_CONTROLLER;
                }
                else
                {
                    url = START_UP_CONTROLLER;
                }
            }
            else if ("4".equals(button))
            {
                String searchValue = request.getParameter("txtSearch");
                String searchByPrice = request.getParameter("txtSearchByPrice");
                if (searchValue != null)
                {
                    url = SEARCH_BY_NAME_CONTROLLER;
                }
                else if (searchByPrice != null)
                {
                    url = SEARCH_BY_PRICE_CONTROLLER;
                }
                else
                {
                    url = START_UP_CONTROLLER;
                }
            }
        }
        catch (NamingException ex) 
        {
            
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
