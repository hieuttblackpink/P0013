
package hieutt.dtos;

import java.io.Serializable;

/**
 *
 * @author HP
 */
public class OrderDetailDTO implements Serializable
{
    private String orderID;
    private String productID;
    private float price;
    private int amount;

    public OrderDetailDTO() 
    {
        super();
    }

    public OrderDetailDTO(String orderID, String productID, float price, int amount) {
        
        this.orderID = orderID;
        this.productID = productID;
        this.price = price;
        this.amount = amount;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getProductID() {
        return productID;
    }

    public float getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    
}
