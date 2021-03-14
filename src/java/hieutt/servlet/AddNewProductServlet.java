
package hieutt.servlet;

import hieutt.daos.ProductDAO;
import hieutt.dtos.ProductDTO;
import hieutt.errors.ProductError;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

//@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
//    maxFileSize = 1024 * 1024 * 50, // 50MB
//    maxRequestSize = 1024 * 1024 * 50) // 50MB
public class AddNewProductServlet extends HttpServlet
{
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String id = request.getParameter("txtIDProduct");
        String name = request.getParameter("txtName");
        String imgText = request.getParameter("img");
        String description = request.getParameter("txtDescription");
        String priceText = request.getParameter("txtPrice");
        String categoryText = request.getParameter("txtCategoryId");
        String quantityText = request.getParameter("txtQuantity");
        
        System.out.println(imgText);
        
        String url = "admin.jsp";
        
        boolean foundErr = false;
        
        ProductError errors = new ProductError();
        
        try
        {
            float price = 0;
            int quantity = 0;
            
            try 
            {
                price = Float.parseFloat(priceText);
            } 
            catch (NumberFormatException e) 
            {
                errors.setPriceIsNotFloatErr("PRICE MUST BE A NUMBER");
                foundErr = true;
            }
            
            try 
            {
                quantity = Integer.parseInt(quantityText);
            } 
            catch (NumberFormatException e) 
            {
                errors.setQuantityIsNotIntErr("QUANTITY MUST BE A NUMBER");
                foundErr = true;
            }
            
            if (price < 0)
            {
                foundErr = true;
                errors.setPriceSmallerThan0Err("PRICE MUST BE GRATER THAN 0");
            }
            
            if (quantity < 0)
            {
                foundErr = true;
                errors.setQuantitySmallerThan0Err("QUANTITY MUST BE GRATER THAN 0");
            }
            
            if (foundErr)
            {
                request.setAttribute("ERRORADD", errors);
                
            }
            else
            {
                ProductDTO dto = new ProductDTO("P" + id, name, imgText, description, price, categoryText, quantity, name);
                ProductDAO dao = new ProductDAO();

                if (dao.insertProduct(dto))
                {
                    url = "StartupServlet?txtRole=ADMIN&btAction=" + 1;
                    request.setAttribute("ADDED", "ADDED COMLETED");
                }
                
            }
        }
        catch (NamingException ex)
        {
            System.out.println(ex);
        } 
        catch (SQLException ex)
        {
            String err = ex.getMessage();
            log("AddNewProductServlet _ SQL: " + ex.getMessage());
            if (err.contains("duplicate"))
            {
                errors.setIdIsExistedErr("THIS ID: " + id + " IS EXSITED!!!");
                request.setAttribute("ERRORADD", errors);
            }
        }
        catch (IOException ex)
        {
            request.setAttribute("ADDED", "ADDED COMLETED");
        }
        catch (Exception ex) 
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
