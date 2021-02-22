
package bookrental;

public class Shipped extends AbstractEvent {

    private Long productId;
    private String productStatus;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long id) {
        this.productId = productId;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }
}
