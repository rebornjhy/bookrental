package bookrental;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Product_table")
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long productId;
    private Long productStock;
    private String devliveryStatus;

    @PostPersist
    public void onPostPersist(){
        Received received = new Received();
        BeanUtils.copyProperties(this, received);
        received.publishAfterCommit();


    }


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductStock() {
        return productStock;
    }

    public void setProductStock(Long stock) {
        this.productStock = productStock;
    }

        public String getDeliveryStatus() {
        return devliveryStatus;
    }

    public void setDeliveryStatus(String status) {
        this.devliveryStatus = devliveryStatus;
    }
}
