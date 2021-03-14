
package hieutt.daos;

import hieutt.dtos.OrderDTO;
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
public class OrderDAO implements Serializable
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
    
    public boolean addNewOder (OrderDTO dto, String paymentMethod) throws NamingException, SQLException
    {
        try 
        {
            con = hieutt.utils.MyConnection.makeConnection();
        
            if (con != null)
            {
                String sql = "INSERT INTO tblOrder (orderID, userID, orderDate, total, paymentMethod, name, phone, address) "
                           + "VALUES (?,?,?,?,?,?,?,?)";
                
                java.util.Date utilDate = new java.util.Date();
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                
                ps = con.prepareStatement(sql);
                
                ps.setString(1, dto.getOrderID());
                ps.setString(2, dto.getUserID());
                ps.setDate(3, sqlDate);
                ps.setFloat(4, dto.getTotal());
                ps.setString(5, paymentMethod);
                ps.setString(6, dto.getName());
                ps.setString(7, dto.getPhone());
                ps.setString(8, dto.getAddress());

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
    
    public List<OrderDTO> getOrderHistory (String userID) throws NamingException, SQLException
    {
        List<OrderDTO> result = new ArrayList<>();
        
        try 
        {
            con = hieutt.utils.MyConnection.makeConnection();
        
            if (con != null)
            {
                String sql = "SELECT orderID, userID, orderDate, total, paymentMethod, name, phone, address "
                           + "FROM tblOrder "
                           + "WHERE userID=? "
                           + "ORDER BY orderDate ASC";
                
                ps = con.prepareStatement(sql);
                
                ps.setString(1, userID);

                rs = ps.executeQuery();
                
                while (rs.next()) 
                {
                    String orderID = rs.getString("orderID");
                    Date orderDate = rs.getDate("orderDate");
                    float total = rs.getFloat("total");
                    String paymentMethod = rs.getString("paymentMethod");
                    String name = rs.getString("name");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    
                    OrderDTO dto = new OrderDTO(orderID, userID, orderDate, total, paymentMethod, name, phone, address);
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
