
package hieutt.dtos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CartObject implements Serializable
{
    protected String userID;
    protected Map<ProductDTO, Integer> items = null;

    public Map<ProductDTO, Integer> getItems() 
    {
        return this.items;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    public int getAmount (ProductDTO dto)
    {
        if (this.items == null)
        {
            return -1;
        }
        if (this.items.containsKey(dto))
        {
            return this.items.get(dto);
        }
        return -1;
    }
    
    public void addItemToCart (ProductDTO dto, int amount)
    {
        if (this.items == null)
        {
            this.items = new HashMap<>();
        }
        int quantity = amount;
        if (this.items.containsKey(dto))
        {
            quantity = amount;
        }
        
        this.items.put(dto, quantity);
    }
    
    public void updateItemCart(ProductDTO dto, int amount) 
    {
        if (this.items == null) 
        {
            this.items = new HashMap<>();
        }
        if (this.items.containsKey(dto)) 
        {
            this.items.put(dto, amount);
        }
    }
    
    public void removeItemFromCart (ProductDTO dto)
    {
        if (this.items == null)
        {
            return;
        }
        if (this.items.containsKey(dto))
        {
            this.items.remove(dto);
            if (this.items.isEmpty())
            {
                this.items = null;
            }
        }
    }
    
    public ProductDTO getItemById (String id) 
    {
        ProductDTO dto = null;
        if (this.items == null)
        {
            return null;
        }
        
        for (Map.Entry<ProductDTO, Integer> entry : this.items.entrySet()) 
        {
            if (entry.getKey().getId().equals(id))
            {
                dto = entry.getKey();
                break;
            }
        }
        return dto;
    }
    
    public int getAmountById (String id) 
    {
        int currentAmount = 0;
        if (this.items == null)
        {
            return currentAmount;
        }
        
        for (Map.Entry<ProductDTO, Integer> entry : this.items.entrySet()) 
        {
            if (entry.getKey().getId().equals(id))
            {
                currentAmount = entry.getValue();
                break;
            }
        }
        return currentAmount;
    }
}
