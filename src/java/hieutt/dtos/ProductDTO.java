
package hieutt.dtos;

import java.io.Serializable;
import java.util.Date;

public class ProductDTO implements Serializable
{
    private String id;
    private String name;
    private String image;
    private String description;
    private float price;
    private Date createDate;
    private String category;
    private int quantity;
    private String status;
    private int totalRecord;

    public ProductDTO(String id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public ProductDTO(String id, String name, String image, String description, float price, String category, int quantity, String status) 
    {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
        this.status = status;
    }
    
    public ProductDTO(String id, String name, String image, String description, float price, Date createDate, String category, int quantity, String status) 
    {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.createDate = createDate;
        this.category = category;
        this.quantity = quantity;
        this.status = status;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }
    
    @Override
    public String toString() {
        return "ProductDTO{" + "id=" + id + ", name=" + name + ", image=" + image + ", description=" + description + ", price=" + price + ", createDate=" + createDate + ", category=" + category + ", quantity=" + quantity + ", status=" + status + '}';
    }
    
}
