/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.cakes;

import java.io.Serializable;

/**
 *
 * @author antru
 */
public class AddCakeErrors implements Serializable{
    private String nameLengthErrors;
    private String createdDateErrors;
    private String expirationDateErrors;
    private String imageErrors;
    private String priceErrors;
    private String quantityErrors;

    public AddCakeErrors() {
    }

    public AddCakeErrors(String nameLengthErrors, String createdDateErrors, String expirationDateErrors, String imageErrors, String priceErrors, String quantityErrors) {
        this.nameLengthErrors = nameLengthErrors;
        this.createdDateErrors = createdDateErrors;
        this.expirationDateErrors = expirationDateErrors;
        this.imageErrors = imageErrors;
        this.priceErrors = priceErrors;
        this.quantityErrors = quantityErrors;
    }

    public String getNameLengthErrors() {
        return nameLengthErrors;
    }

    public void setNameLengthErrors(String nameLengthErrors) {
        this.nameLengthErrors = nameLengthErrors;
    }

    public String getCreatedDateErrors() {
        return createdDateErrors;
    }

    public void setCreatedDateErrors(String createdDateErrors) {
        this.createdDateErrors = createdDateErrors;
    }

    public String getExpirationDateErrors() {
        return expirationDateErrors;
    }

    public void setExpirationDateErrors(String expirationDateErrors) {
        this.expirationDateErrors = expirationDateErrors;
    }

    public String getImageErrors() {
        return imageErrors;
    }

    public void setImageErrors(String imageErrors) {
        this.imageErrors = imageErrors;
    }

    public String getPriceErrors() {
        return priceErrors;
    }

    public void setPriceErrors(String priceErrors) {
        this.priceErrors = priceErrors;
    }

    public String getQuantityErrors() {
        return quantityErrors;
    }

    public void setQuantityErrors(String quantityErrors) {
        this.quantityErrors = quantityErrors;
    }
    
    
}
