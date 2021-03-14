
package hieutt.servlet;

import hieutt.daos.ProductDAO;
import hieutt.daos.RecordDAO;
import hieutt.dtos.ProductDTO;
import hieutt.dtos.RecordDTO;
import hieutt.errors.ProductError;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateProductServlet extends HttpServlet 
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String id = request.getParameter("txtProductIdUpdate");
        String name = request.getParameter("txtNameUpdate");
        
        String imgName = request.getParameter("txtNameImg");
        String imgTextChange = request.getParameter(imgName);
        
        String imgNameTextNotChange = request.getParameter("txtNameImgOld");
        String imgTextNotChange = request.getParameter(imgNameTextNotChange);
        
        String imgNameIsChange = request.getParameter("txtNameChangImg");
        String imgIsChange = request.getParameter(imgNameIsChange);
        
        String description = request.getParameter("txtDescriptionUpdate");
        String priceText = request.getParameter("txtPriceUpdate");
        String categoryText = request.getParameter("txtCategoryIdUpdate");
        String quantityText = request.getParameter("txtQuantityUpdate");
        String status = request.getParameter("txtStatus");
        
        String searchValue = request.getParameter("txtSearchUpdate");
        
        boolean foundErr = false;
        
        ProductError errors = new ProductError();
        
        String url = "";
        
        try 
        {
            float price = 0;
            int quantity = 0;
            
            HttpSession session = request.getSession(true);
            int currentPage = (Integer) session.getAttribute("CURRENTPAGE");
            
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
                session.setAttribute("ERRORUPDATE", errors);
                session.setAttribute("UPDATED", "FAIL");
                //request.setAttribute("ERRORUPDATE", errors);
                if (searchValue == "")
                {
                    url = "StartupServlet?txtRole=ADMIN&btAction=" + currentPage;
                }
                else
                {
                    url = "DispatchServlet?txtSearch=" + searchValue + "&txtRole=ADMIN&btAction=SEARCH";
                }
            }
            else
            {
                ProductDTO dto = null;
                
                if ("NOTCHANGE".equals(imgIsChange))
                {
                    dto = new ProductDTO(id, name, imgTextNotChange, description, price, categoryText, quantity, status);
                }
                else if ("CHANGED".equals(imgIsChange))
                {
                    dto = new ProductDTO(id, name, imgTextChange, description, price, categoryText, quantity, status);
                }
                
                ProductDAO dao = new ProductDAO();
                
                if (dao.updateProduct(dto))
                {
                    RecordDAO recordDAO = new RecordDAO();
                    RecordDTO recordDTO = null;
                    String userID = request.getParameter("txtUserID");
                    
                    if ("Active".equals(status))
                    {
                        String contentUpdate = "UPDATE PRODUCT: " + dto.getName() + " IN CATEGORY: " + dto.getCategory();
                        recordDTO = new RecordDTO("UD", id, userID, contentUpdate);
                    }
                    else if ("Inactive".equals(status))
                    {
                        String contentUpdate = "DELETE PRODUCT: " + dto.getName() + " IN CATEGORY: " + dto.getCategory();
                        recordDTO = new RecordDTO("DEL", id, userID, contentUpdate);
                    }
                     
                    if (recordDAO.recordUpdate(recordDTO))
                    {
                        if (searchValue == "")
                        {
                            url = "StartupServlet?txtRole=ADMIN&btAction=" + currentPage;
                        }
                        else
                        {
                            url = "DispatchServlet?txtSearch=" + searchValue + "&txtRole=ADMIN&btAction=SEARCH";
                        }
                        session.setAttribute("UPDATED", "UPDATE COMLETED");
                    }
                }
            }
        }
        catch (NamingException ex)
        {
             log("UpdateProductServlet _ Naming: " + ex.getMessage());
        } 
        catch (SQLException ex)
        {
            log("UpdateProductServlet _ SQL: " + ex.getMessage());
            
        }
        catch (Exception ex) 
        {
             log("UpdateProductServlet _ Ex: " + ex.getMessage());
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
