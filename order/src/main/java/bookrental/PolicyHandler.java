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
    OrderRepository orderRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverShipped_(@Payload Shipped shipped){

        if(shipped.isMe()){
            //orderstatusupdate
            Order order = new Order();
            order.setProductId(shipped.getProductId());
            order.setProductId(shipped.getProductId());
            System.out.println("##### listener  : " + shipped.toJson());
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverShippedCanceled_(@Payload ShippedCanceled shippedCanceled){

        if(shippedCanceled.isMe()){
            System.out.println("##### listener  : " + shippedCanceled.toJson());
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderReturned_(@Payload OrderReturned orderReturned){

        if(orderReturned.isMe()){
            System.out.println("##### listener  : " + orderReturned.toJson());
        }
    }

}
