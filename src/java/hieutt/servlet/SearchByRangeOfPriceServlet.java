
package hieutt.servlet;

import hieutt.daos.ProductDAO;
import hieutt.dtos.ProductDTO;
import hieutt.errors.ProductError;
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

/**
 *
 * @author HP
 */
public class SearchByRangeOfPriceServlet extends HttpServlet
{
    private final String USER_PAGE = "home.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String searchValue = request.getParameter("txtSearch");
        String txtMinPrice = request.getParameter("txtMinPrice");
        String txtMaxPrice = request.getParameter("txtMaxPrice");
        
        request.setAttribute("MINPRICE", txtMinPrice);
        request.setAttribute("MAXPRICE", txtMaxPrice);
        
        String button = request.getParameter("btAction");
        
        int pageNum = 1;
        
        if (button != null)
        {
            try 
            {
                pageNum = Integer.parseInt(button);
            }
            catch (NumberFormatException ex) 
            {
                pageNum = 1;
            }
        }
        
        int startPage = pageNum * 5 - 4;
        int endPage = pageNum * 5;
        
        String url = "";
        
        try 
        {
            float minPrice = Float.parseFloat(txtMinPrice);
            float maxPrice = Float.parseFloat(txtMaxPrice);
            System.out.println(minPrice + " " + maxPrice);
            ProductDAO dao = new ProductDAO();
            List<ProductDTO> result = dao.searchProductByPrice(minPrice, maxPrice, startPage, endPage);
            
            if (!result.isEmpty())
            {
                int totalRecord = result.get(0).getTotalRecord();
                int totalPage = 1;

                if (totalRecord%5 == 0)
                {
                    totalPage = totalRecord / 5;
                }
                else
                {
                    totalPage = (totalRecord / 5) + 1;
                }
                request.setAttribute("TOTALRECORD", totalPage);
                request.setAttribute("SEARCHBYPRICE", "MATCHED");
            }
            else
            {
                request.setAttribute("NORESULT", "SORRY NO PRODUCT SUITABLE WITH YOUR SELECTION");
            }
            
            request.setAttribute("RESULTSEARCH", result);
            
            url = USER_PAGE;
        }
        catch (NamingException ex) 
        {
            log("" + ex);
        }
        catch (SQLException ex) 
        {
            log("" + ex);
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
