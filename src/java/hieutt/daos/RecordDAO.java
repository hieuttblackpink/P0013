
package hieutt.daos;

import hieutt.dtos.RecordDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import javax.naming.NamingException;

public class RecordDAO implements Serializable
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
    
    public boolean recordUpdate (RecordDTO dto) throws SQLException, NamingException
    {
        try 
        {
            con = hieutt.utils.MyConnection.makeConnection();
            
            if (con != null)
            {
                String sql = "INSERT INTO tblRecord (RecordID, ProductID, UpdateDate, UpdateUser, ContentUpdate) "
                           + "VALUES (?,?,?,?,?)";
                
                java.util.Date utilDate = new java.util.Date();
                Random r = new Random();
                int randNum = r.nextInt((100 - 1) + 1);
                String timeUpdate = "" + randNum + utilDate.getYear() + utilDate.getMonth() + utilDate.getDate() + utilDate.getHours() + utilDate.getMinutes() + utilDate.getSeconds();
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                
                ps = con.prepareStatement(sql);
                
                ps.setString(1, dto.getRecordID() + timeUpdate);
                ps.setString(2, dto.getProductID());
                ps.setDate(3, sqlDate);
                ps.setString(4, dto.getUpdateUser());
                ps.setString(5, dto.getContentUpdate());
                
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
}
