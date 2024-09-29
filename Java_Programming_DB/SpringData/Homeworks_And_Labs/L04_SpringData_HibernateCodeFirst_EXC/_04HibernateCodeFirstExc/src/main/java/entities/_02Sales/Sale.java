package entities._02Sales;

import entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "sales")
public class Sale extends BaseEntity {
    @ManyToOne
    private Product product;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "store_location_id", referencedColumnName = "id")
    private StoreLocation storeLocation;

    private Date date;

    public Sale() {
    }

    public Sale(Product product, Customer customer, StoreLocation storeLocation) {
        this.product = product;
        this.customer = customer;
        this.storeLocation = storeLocation;
        this.date = Date.from(Instant.now());
  }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public StoreLocation getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(StoreLocation storeLocation) {
        this.storeLocation = storeLocation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
