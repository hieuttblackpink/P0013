
package hieutt.dtos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author HP
 */
public class OrderDTO implements Serializable
{
    private String orderID;
    private String userID;
    private Date orderDate;
    private float total;
    private String paymentMethod;
    private String name;
    private String phone;
    private String address;

    public OrderDTO() 
    {
        super();
    }

    public OrderDTO(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
    
    public OrderDTO(String orderID, String userID, Date orderDate, float total, String paymentMethod) {
        this.orderID = orderID;
        this.userID = userID;
        this.orderDate = orderDate;
        this.total = total;
        this.paymentMethod = paymentMethod;
    }

    public OrderDTO(String orderID, String userID, float total, String paymentMethod, String name, String phone, String address) {
        this.orderID = orderID;
        this.userID = userID;
        this.total = total;
        this.paymentMethod = paymentMethod;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public OrderDTO(String orderID, String userID, Date orderDate, float total, String paymentMethod, String name, String phone, String address) {
        this.orderID = orderID;
        this.userID = userID;
        this.orderDate = orderDate;
        this.total = total;
        this.paymentMethod = paymentMethod;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
    
    public String getOrderID() {
        return orderID;
    }

    public String getUserID() {
        return userID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public float getTotal() {
        return total;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
