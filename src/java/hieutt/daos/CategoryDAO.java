
package hieutt.daos;

import hieutt.dtos.CategoryDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class CategoryDAO implements Serializable
{
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    private void closeConnection () throws Exception
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
    
    public List<CategoryDTO> loadCategory () throws SQLException, NamingException, Exception
    {
        List<CategoryDTO> listCategory = new ArrayList<CategoryDTO>();
        
        try 
        {
            con = hieutt.utils.MyConnection.makeConnection();
            
            if (con != null)
            {
                String sql = "SELECT CategoryID, CategoryName "
                           + "FROM tblCategory";
                
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                
                while (rs.next()) 
                {
                    String categoryID = rs.getString("CategoryID");
                    String categoryName = rs.getString("CategoryName");
                    
                    CategoryDTO dto = new CategoryDTO(categoryID, categoryName);
                    listCategory.add(dto);
                }
            }
        }
        finally
        {
            closeConnection();
            return listCategory;
        }
    }
}
