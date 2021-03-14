
package hieutt.utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class MyConnection 
{
    public static Connection makeConnection () throws NamingException, SQLException
    {
        Context context = new InitialContext();
        Context tomcatContext = (Context) context.lookup("java:comp/env");
        DataSource data = (DataSource) tomcatContext.lookup("HANASHOP");
        Connection con = data.getConnection();
        return con;
    }
}
