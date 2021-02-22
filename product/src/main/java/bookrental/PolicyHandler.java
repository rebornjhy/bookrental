package bookrental;

import bookrental.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;



@Service
public class PolicyHandler{
    @Autowired
    ProductRepository productRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverShipped_(@Payload Shipped shipped){

        if(shipped.isMe()){
            Product product = new Product();
            product.setProductId(shipped.getProductId());
            product.setProductStock(product.getProductStock()-1);
            product.setDeliveryStatus("SHIPPED");
            productRepository.save(product);
            System.out.println("##### listener  : " + shipped.toJson());
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverShippedCanceled_(@Payload ShippedCanceled shippedCanceled){

        if(shippedCanceled.isMe()){
            Product product = new Product();
            product.setProductId(shippedCanceled.getProductId());
            product.setProductStock(product.getProductStock()+1);
            product.setDeliveryStatus("SHIPPED CANCELED");
            productRepository.save(product);
            System.out.println("##### listener  : " + shippedCanceled.toJson());
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderReturned_(@Payload OrderReturned orderReturned){

        if(orderReturned.isMe()){
            Product product = new Product();
            product.setProductId(orderReturned.getProductId());
            product.setProductStock(product.getProductStock()+1);
            product.setDeliveryStatus("RETURNED");
            productRepository.save(product);
            System.out.println("##### listener  : " + orderReturned.toJson());
        }
    }
}
