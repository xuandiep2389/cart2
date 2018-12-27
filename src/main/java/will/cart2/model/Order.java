package will.cart2.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders", //
    uniqueConstraints =  {@UniqueConstraint(columnNames = "oder_num")} )
public class Order {

    private static final long serialVersionUID = -2576670215015463100L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "customer_name", length = 255, nullable = false)
    private String customerName;

    @Column(name = "customer_address", length = 255, nullable = false)
    private String customerAddress;

    @Column(name = "customer_email", length = 128, nullable = false)
    private String customerEmail;

    public Order(int id, Date orderDate, double amount, String customerName, String customerAddress, String customerEmail, String customerPhone) {
        this.id = id;
        this.orderDate = orderDate;
        this.amount = amount;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
    }

    public Order() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    @Column(name = "customer_phone", length = 128, nullable = false)
    private String customerPhone;
}
