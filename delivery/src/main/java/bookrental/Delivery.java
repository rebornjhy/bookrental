package bookrental;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Delivery_table")
public class Delivery {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long orderId;
    private Integer statusCode;

    @PostUpdate
    public void onPostUpdate(){
        Shipped shipped = new Shipped();
        BeanUtils.copyProperties(this, shipped);
        shipped.publishAfterCommit();


        OrderReturned orderReturned = new OrderReturned();
        BeanUtils.copyProperties(this, orderReturned);
        orderReturned.publishAfterCommit();


        ShippedCanceled shippedCanceled = new ShippedCanceled();
        BeanUtils.copyProperties(this, shippedCanceled);
        shippedCanceled.publishAfterCommit();


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }




}
