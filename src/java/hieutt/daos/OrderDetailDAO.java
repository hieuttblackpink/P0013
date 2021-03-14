
package hieutt.daos;

import hieutt.dtos.OrderDetailDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author HP
 */
public class OrderDetailDAO implements Serializable
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
    
    public boolean addNewOderDetail (OrderDetailDTO dto) throws NamingException, SQLException
    {
        try 
        {
            con = hieutt.utils.MyConnection.makeConnection();
        
            if (con != null)
            {
                String sql = "INSERT INTO tblOrderDetail (orderID, productID, price, amount) "
                           + "VALUES (?,?,?,?)";
                
                ps = con.prepareStatement(sql);
                
                ps.setString(1, dto.getOrderID());
                ps.setString(2, dto.getProductID());
                ps.setFloat(3, dto.getPrice());
                ps.setInt(4, dto.getAmount());

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
    
    public List<OrderDetailDTO> getOrderDetailHistory (String orderID) throws NamingException, SQLException
    {
        List<OrderDetailDTO> result = new ArrayList<>();
        
        try 
        {
            con = hieutt.utils.MyConnection.makeConnection();
        
            if (con != null)
            {
                String sql = "SELECT orderID, productID, price, amount "
                           + "FROM tblOrderDetail "
                           + "WHERE orderID=?";
                
                ps = con.prepareStatement(sql);
                
                ps.setString(1, orderID);

                rs = ps.executeQuery();
                
                while (rs.next()) 
                {
                    String productID = rs.getString("productID");
                    float total = rs.getFloat("price");
                    int amount = rs.getInt("amount");
                    
                    OrderDetailDTO dto = new OrderDetailDTO(orderID, productID, total, amount);
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
}
