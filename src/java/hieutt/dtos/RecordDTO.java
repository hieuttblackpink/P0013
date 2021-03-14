
package hieutt.dtos;

import java.io.Serializable;
import java.util.Date;

public class RecordDTO implements Serializable
{
    private String recordID;
    private String productID;
    private Date updateDate;
    private String updateUser;
    private String contentUpdate;

    public RecordDTO(String recordID, String productID, Date updateDate, String updateUser, String contentUpdate) {
        this.recordID = recordID;
        this.productID = productID;
        this.updateDate = updateDate;
        this.updateUser = updateUser;
        this.contentUpdate = contentUpdate;
    }

    public RecordDTO(String recordID, String productID, String updateUser, String contentUpdate) {
        this.recordID = recordID;
        this.productID = productID;
        this.updateUser = updateUser;
        this.contentUpdate = contentUpdate;
    }
    
    public String getRecordID() {
        return recordID;
    }

    public String getProductID() {
        return productID;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public String getContentUpdate() {
        return contentUpdate;
    }

    public void setRecordID(String recordID) {
        this.recordID = recordID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public void setContentUpdate(String contentUpdate) {
        this.contentUpdate = contentUpdate;
    }
    
}
