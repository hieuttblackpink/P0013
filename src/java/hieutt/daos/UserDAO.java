
package hieutt.daos;

import hieutt.dtos.UserDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDAO implements Serializable
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
    
    public UserDTO checkLogin (String id, String password) throws SQLException, Exception
    {
        UserDTO dto = null;
        
        try 
        {
            con = hieutt.utils.MyConnection.makeConnection();
            if (con != null)
            {
                String firstName, lastName, role;
                String url = "SELECT userID, role, firstname, lastname "
                           + "FROM tblUser "
                           + "WHERE userID=? AND password=?";
                
                ps = con.prepareStatement(url);
                ps.setString(1, id);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next())
                {
                    firstName = rs.getString("firstname");
                    lastName = rs.getString("lastname");
                    role = rs.getString("role");
                    
                    dto = new UserDTO(id, firstName, lastName, role);
                }
            }
        }
        finally
        {
            closeConnection();
        }
        return dto;
    }
    
    public UserDTO getInfoUser (String id) throws SQLException, Exception
    {
        UserDTO dto = null;
        
        try 
        {
            con = hieutt.utils.MyConnection.makeConnection();
            if (con != null)
            {
                String userID, firstname, lastname, phone, address;
                String url = "SELECT userID, firstname, lastname, phone, address "
                           + "FROM tblUser "
                           + "WHERE userID=?";
                
                ps = con.prepareStatement(url);
                ps.setString(1, id);
                rs = ps.executeQuery();
                if (rs.next())
                {
                    userID = rs.getString("userID");
                    firstname = rs.getString("firstname");
                    lastname = rs.getString("lastname");
                    phone = rs.getString("phone");
                    address = rs.getString("address");
                    
                    dto = new UserDTO(userID, phone, address);
                    dto.setFirstName(firstname);
                    dto.setLastName(lastname);
                }
            }
        }
        finally
        {
            closeConnection();
        }
        return dto;
    }
}
