package will.cart2.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    private static final long serialVersionUID = -2576670215015463100L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "order_date")
    private String orderDate;

    @Column(name = "customer_name")
    @Size(min = 2,message = "Name must contain at least 2 character")
    private String customerName;

    @Column(name = "customer_address")
    @NotEmpty(message = "Address must not be empty")
    private String customerAddress;

    @Column(name = "customer_email")
    @Email
    @NotBlank(message = "Email must not be empty")
    private String customerEmail;

    public Order(String orderDate, String customerName, String customerAddress, String customerEmail, String customerPhone, String orderInfo, Set<Product> products) {
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.orderInfo = orderInfo;
        this.products = products;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    @Column(name = "customer_phone", length = 128)
    @Pattern(regexp = "0[0-9]{9,10}", message = "Wrong phone number format")
    private String customerPhone;

    @Column(name = "order_info")
    private String orderInfo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    private Set<Product> products = new HashSet<>();

    public Order(String orderDate, String customerName, String customerAddress, String customerEmail, String customerPhone, Set<Product> products) {
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.products = products;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Order(String orderDate, String customerName, String customerAddress, String customerEmail, String customerPhone) {
        this.orderDate = orderDate;
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

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
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


}
