
package hieutt.daos;

import hieutt.dtos.ProductDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class ProductDAO implements Serializable
{
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    private void closeConnection () throws SQLException
    {
        if (rs != null)
        {
            rs.close();
        }
        if (ps != null)
        {
            ps.close();
        }
        if (con != null)
        {
            con.close();
        }
    }
    
    public List<ProductDTO> getFirstProduct (String orderBy, int startRecord, int endRecord) throws SQLException, NamingException
    {
        List<ProductDTO> first20Product = new ArrayList<>();
        
        try 
        {
            con = hieutt.utils.MyConnection.makeConnection();
            
            if (con != null)
            {
                String sql = "SELECT t.ID, t.Name, t.Image, t.Description, t.Price, t.CreateDate, t.CategoryID, t.Quantity, t2.total "
                           + "FROM (SELECT ROW_NUMBER() OVER (ORDER BY CreateDate ASC) AS RowNum, ID, Name, Image, Description, Price, CreateDate, CategoryID, Quantity "
                                 + "FROM tblProduct " 
                                 + "WHERE Status='Active' AND Quantity>0) AS t, "
                                + "(SELECT COUNT(ID) AS total " 
                                + "FROM tblProduct " 
                                + "WHERE Status='Active' AND Quantity>0) AS t2 "
                           + "WHERE RowNum >= ? AND RowNum <= ? "
                           + "ORDER BY RowNum";
                
                ps = con.prepareStatement(sql);
                ps.setInt(1, startRecord);
                ps.setInt(2, endRecord);
                
                rs = ps.executeQuery();
                
                while (rs.next()) 
                {                    
                    String id = rs.getString("ID");
                    String name = rs.getString("Name");
                    String img = rs.getString("Image");
                    String description = rs.getString("Description");
                    float price = rs.getFloat("Price");
                    Date date = rs.getDate("CreateDate");
                    String categoryId = rs.getString("CategoryID");
                    int qunatity = rs.getInt("Quantity");
                    int totalRecord = rs.getInt("total");
                    
                    ProductDTO dto = new ProductDTO(id, name, img, description, price, date, categoryId, qunatity, "Active");
                    dto.setTotalRecord(totalRecord);
                    first20Product.add(dto);
                }
                
            }
        }
        finally
        {
            closeConnection();
            return first20Product;
        }
    }
    
    public List<ProductDTO> searchProductByName (String searchValue, int startRecord, int endRecord) throws SQLException, NamingException
    {
        List<ProductDTO> result = new ArrayList<>();
        
        try 
        {
            con = hieutt.utils.MyConnection.makeConnection();
            
            if (con != null)
            {
                String sql = "SELECT t.ID, t.Name, t.Image, t.Description, t.Price, t.CreateDate, t.CategoryID, t.Quantity, t2.total "
                           + "FROM (SELECT ROW_NUMBER() OVER (ORDER BY CreateDate ASC) AS RowNum, ID, Name, Image, Description, Price, CreateDate, CategoryID, Quantity "
                                 + "FROM tblProduct " 
                                 + "WHERE Name LIKE ? AND Status='Active' AND Quantity>0) AS t, "
                                + "(SELECT COUNT(ID) AS total " 
                                + "FROM tblProduct " 
                                + "WHERE Name LIKE ? AND Status='Active' AND Quantity>0) AS t2 "
                           + "WHERE RowNum >= ? AND RowNum <= ? "
                           + "ORDER BY RowNum";

                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + searchValue + "%");
                ps.setString(2, "%" + searchValue + "%");
                ps.setInt(3, startRecord);
                ps.setInt(4, endRecord);
                
                rs = ps.executeQuery();
                
                while (rs.next()) 
                {                    
                    String id = rs.getString("ID");
                    String name = rs.getString("Name");
                    String img = rs.getString("Image");
                    String description = rs.getString("Description");
                    float price = rs.getFloat("Price");
                    Date date = rs.getDate("CreateDate");
                    String categoryId = rs.getString("CategoryID");
                    int qunatity = rs.getInt("Quantity");
                    int totalRecord = rs.getInt("total");
                    
                    ProductDTO dto = new ProductDTO(id, name, img, description, price, date, categoryId, qunatity, "Active");
                    dto.setTotalRecord(totalRecord);
                    result.add(dto);
                }
                
            }
        }
        finally
        {
            closeConnection();
            return result;
        }
    }
    
    public List<ProductDTO> searchProductByPrice (float minPrice, float maxPrice, int startRecord, int endRecord) throws SQLException, NamingException
    {
        List<ProductDTO> result = new ArrayList<>();
        
        try 
        {
            con = hieutt.utils.MyConnection.makeConnection();
            
            if (con != null)
            {
                String sql = "SELECT t.ID, t.Name, t.Image, t.Description, t.Price, t.CreateDate, t.CategoryID, t.Quantity, t2.total "
                           + "FROM (SELECT ROW_NUMBER() OVER (ORDER BY CreateDate ASC) AS RowNum, ID, Name, Image, Description, Price, CreateDate, CategoryID, Quantity "
                                 + "FROM tblProduct " 
                                 + "WHERE Price >= ? AND Price <= ? AND Status='Active' AND Quantity>0) AS t, "
                                + "(SELECT COUNT(ID) AS total " 
                                + "FROM tblProduct " 
                                + "WHERE Price >= ? AND Price <= ? AND Status='Active' AND Quantity>0) AS t2 "
                           + "WHERE RowNum >= ? AND RowNum <= ? "
                           + "ORDER BY RowNum";

                ps = con.prepareStatement(sql);
                ps.setFloat(1, minPrice);
                ps.setFloat(2, maxPrice);
                ps.setFloat(3, minPrice);
                ps.setFloat(4, maxPrice);
                ps.setInt(5, startRecord);
                ps.setInt(6, endRecord);
                
                rs = ps.executeQuery();
                
                while (rs.next()) 
                {                    
                    String id = rs.getString("ID");
                    String name = rs.getString("Name");
                    String img = rs.getString("Image");
                    String description = rs.getString("Description");
                    float price = rs.getFloat("Price");
                    Date date = rs.getDate("CreateDate");
                    String categoryId = rs.getString("CategoryID");
                    int qunatity = rs.getInt("Quantity");
                    int totalRecord = rs.getInt("total");
                    
                    ProductDTO dto = new ProductDTO(id, name, img, description, price, date, categoryId, qunatity, "Active");
                    dto.setTotalRecord(totalRecord);
                    result.add(dto);
                }
                
            }
        }
        finally
        {
            closeConnection();
            return result;
        }
    }
    
    public List<ProductDTO> searchProductByCategory (String categorySearch, int startRecord, int endRecord) throws SQLException, NamingException
    {
        List<ProductDTO> result = new ArrayList<>();
        
        try 
        {
            con = hieutt.utils.MyConnection.makeConnection();
            
            if (con != null)
            {
                String sql = "SELECT ID, Name, Image, Description, Price, CreateDate, CategoryID, Quantity "
                           + "FROM (SELECT ROW_NUMBER() OVER (ORDER BY CreateDate ASC) AS RowNum, ID, Name, Image, Description, Price, CreateDate, CategoryID, Quantity "
                                 + "FROM tblProduct " 
                                 + "WHERE CategoryID = ? AND Status='Active' AND Quantity>0) AS t " 
                           + "WHERE RowNum >= ? AND RowNum <= ? "
                           + "ORDER BY RowNum";

                ps = con.prepareStatement(sql);
                ps.setString(1, categorySearch);
                ps.setInt(2, startRecord);
                ps.setInt(3, endRecord);
                
                rs = ps.executeQuery();
                
                while (rs.next()) 
                {                    
                    String id = rs.getString("ID");
                    String name = rs.getString("Name");
                    String img = rs.getString("Image");
                    String description = rs.getString("Description");
                    float price = rs.getFloat("Price");
                    Date date = rs.getDate("CreateDate");
                    String categoryId = rs.getString("CategoryID");
                    int qunatity = rs.getInt("Quantity");
                    
                    ProductDTO dto = new ProductDTO(id, name, img, description, price, date, categoryId, qunatity, "Active");
                    result.add(dto);
                }
                
            }
        }
        finally
        {
            closeConnection();
            return result;
        }
    }
    
    public boolean insertProduct (ProductDTO dto) throws SQLException, NamingException, Exception
    {
        try 
        {
            con = hieutt.utils.MyConnection.makeConnection();
            
            if (con != null)
            {
                String sql = "INSERT INTO tblProduct (ID, Name, Image, Description, Price, CreateDate, CategoryID, Quantity, Status) "
                           + "VALUES (?,?,?,?,?,?,?,?,?)";
                
                java.util.Date utilDate = new java.util.Date();
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                
                ps = con.prepareStatement(sql);
                
                ps.setString(1, dto.getId());
                ps.setString(2, dto.getName());
                ps.setString(3, dto.getImage());
                ps.setString(4, dto.getDescription());
                ps.setFloat(5, dto.getPrice());
                ps.setDate(6, sqlDate);
                ps.setString(7, dto.getCategory());
                ps.setInt(8, dto.getQuantity());
                ps.setString(9, "Active");
                
                int row = -1;
                
                row = ps.executeUpdate();
                
                if (row > 0)
                {
                    return true;
                }
            }
        }
        finally
        {
            closeConnection();
        }
        return false;
    }
    
    public boolean updateProduct (ProductDTO dto) throws SQLException, NamingException
    {
        try 
        {
            con = hieutt.utils.MyConnection.makeConnection();
            if (con != null)
            {
                String sql = "UPDATE tblProduct "
                           + "SET Name=?, Image=?, Description=?, Price=?, CategoryID=?, Quantity=?, Status=? "
                           + "WHERE ID=?";
                
                ps = con.prepareStatement(sql);
                ps.setString(1, dto.getName());
                ps.setString(2, dto.getImage());
                ps.setString(3, dto.getDescription());
                ps.setFloat(4, dto.getPrice());
                ps.setString(5, dto.getCategory());
                ps.setInt(6, dto.getQuantity());
                ps.setString(7, dto.getStatus());
                
                ps.setString(8, dto.getId());
                
                int row = ps.executeUpdate();
                
                if (row > 0)
                {
                    return true;
                }
            }
        }
        finally
        {
            closeConnection();
        }
        
        return false;
    }
    
     public boolean removeProduct (String id) throws SQLException, NamingException
    {
        try
        {
            con = hieutt.utils.MyConnection.makeConnection();
            if (con != null)
            {
                String sql = "UPDATE tblProduct "
                           + "SET Status=? "
                           + "WHERE ID=?";
                
                ps = con.prepareStatement(sql);
                ps.setString(1, "Inactive");
                ps.setString(2, id);
                int row = ps.executeUpdate();
                
                if (row > 0)
                {
                    return true;
                }
            }
        }
        finally
        {
            closeConnection();
        }
        return false;
    }
    
     public ProductDTO getProductByID (String productID) throws SQLException, NamingException
    {
        ProductDTO dto = null;
        
        try 
        {
            con = hieutt.utils.MyConnection.makeConnection();
            
            if (con != null)
            {
                String sql = "SELECT ID, Name, Image, Description, Price, CreateDate, CategoryID, Quantity "
                           + "FROM tblProduct " 
                           + "WHERE ID=?";
                
                ps = con.prepareStatement(sql);
                
                ps.setString(1, productID);
                
                rs = ps.executeQuery();
                
                while (rs.next()) 
                {                    
                    String id = rs.getString("ID");
                    String name = rs.getString("Name");
                    String img = rs.getString("Image");
                    String description = rs.getString("Description");
                    float price = rs.getFloat("Price");
                    Date date = rs.getDate("CreateDate");
                    String categoryId = rs.getString("CategoryID");
                    int qunatity = rs.getInt("Quantity");
                    
                    dto = new ProductDTO(id, name, img, description, price, date, categoryId, qunatity, "Active");
                }
                
            }
        }
        finally
        {
            closeConnection();
            return dto;
        }
    }
}
