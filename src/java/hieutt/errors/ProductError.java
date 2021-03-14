
package hieutt.errors;

import java.io.Serializable;

public class ProductError implements Serializable
{
    private String idIsExistedErr;
    private String priceIsNotFloatErr;
    private String priceSmallerThan0Err;
    private String quantityIsNotIntErr;
    private String quantitySmallerThan0Err;

    public ProductError() 
    {
        super();
    }

    public ProductError(String idIsExistedErr, String priceIsNotFloatErr, String priceSmallerThan0Err, String quantityIsNotIntErr, String quantitySmallerThan0Err) {
        this.idIsExistedErr = idIsExistedErr;
        this.priceIsNotFloatErr = priceIsNotFloatErr;
        this.priceSmallerThan0Err = priceSmallerThan0Err;
        this.quantityIsNotIntErr = quantityIsNotIntErr;
        this.quantitySmallerThan0Err = quantitySmallerThan0Err;
    }

    public String getIdIsExistedErr() {
        return idIsExistedErr;
    }

    public String getPriceIsNotFloatErr() {
        return priceIsNotFloatErr;
    }

    public String getPriceSmallerThan0Err() {
        return priceSmallerThan0Err;
    }

    public String getQuantityIsNotIntErr() {
        return quantityIsNotIntErr;
    }

    public String getQuantitySmallerThan0Err() {
        return quantitySmallerThan0Err;
    }

    public void setIdIsExistedErr(String idIsExistedErr) {
        this.idIsExistedErr = idIsExistedErr;
    }

    public void setPriceIsNotFloatErr(String priceIsNotFloatErr) {
        this.priceIsNotFloatErr = priceIsNotFloatErr;
    }

    public void setPriceSmallerThan0Err(String priceSmallerThan0Err) {
        this.priceSmallerThan0Err = priceSmallerThan0Err;
    }

    public void setQuantityIsNotIntErr(String quantityIsNotIntErr) {
        this.quantityIsNotIntErr = quantityIsNotIntErr;
    }

    public void setQuantitySmallerThan0Err(String quantitySmallerThan0Err) {
        this.quantitySmallerThan0Err = quantitySmallerThan0Err;
    }
    
    
}
