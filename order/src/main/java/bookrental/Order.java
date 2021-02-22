package bookrental;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

import bookrental.external.Delivery;
import bookrental.external.DeliveryService;

import java.util.List;

@Entity
@Table(name="Order_table")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long orderId;
    private Long productId;
    private String orderStatus;

    @PrePersist
    public void onPrePersist(){
        Ordered ordered = new Ordered();
        BeanUtils.copyProperties(this, ordered);
        ordered.publishAfterCommit();


    }

    @PreUpdate
    public void onPreUpdate(){
        OrderCanceled orderCanceled = new OrderCanceled();
        BeanUtils.copyProperties(this, orderCanceled);
        orderCanceled.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        Delivery delivery = new Delivery();
        // mappings goes here
        OrderApplication.applicationContext.getBean(DeliveryService.class)
            .cancelShip(delivery);


        Returned returned = new Returned();
        BeanUtils.copyProperties(this, returned);
        returned.publishAfterCommit();


    }


    public Long getOrderId() {
        return orderId;
    }

    public void setId(Long orderId) {
        this.orderId = orderId;
    }
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setStatusCode(String orderStatus) {
        this.orderStatus = orderStatus;
    }




}
