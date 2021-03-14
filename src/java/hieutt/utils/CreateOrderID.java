
package hieutt.utils;

import java.util.Random;

/**
 *
 * @author HP
 */
public class CreateOrderID 
{
    public static String createID (String userID)
    {
        java.util.Date utilDate = new java.util.Date();
        Random r = new Random();
        int randNum = r.nextInt((100 - 1) + 1);
        String timeOrder = "" + randNum + utilDate.getYear() + utilDate.getMonth() + utilDate.getDate() + utilDate.getHours() + utilDate.getMinutes() + utilDate.getSeconds();
        
        String orderID = userID + timeOrder;
        
        return orderID;
    }
}
