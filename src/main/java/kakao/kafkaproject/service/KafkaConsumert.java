//package kakao.kafkaproject.service;
//
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//import java.time.Duration;
//import java.util.Properties;
//import java.util.logging.Logger;
//
//@Component
//@RequiredArgsConstructor
//public class KafkaConsumert {
//
//    private final Properties kafkaProperties;
//
//    private final Logger logger = (Logger) LoggerFactory.getLogger(KafkaConsumer.class);
//
//    @PostConstruct
//    public void init() {
//        kafkaProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        kafkaProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
//        kafkaProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
//        kafkaProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "console-consumer");
//    }
//
//    @KafkaListener(topics = "dbserver1.testdb.subscriber")
//    public void consume(String data) {
//        try (KafkaConsumer consumer = new KafkaConsumer(kafkaProperties)) {
////            consumer.subscribe(Collections.singletonList("dbserver1.testdb.subscriber"));
//            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
//            for (ConsumerRecord<String, String> record : records) {
////                logger.info("Received message: {}", record.value());
//                System.out.println("Received message = " + record.value());
//            }
//        } catch (Exception e) {
//            System.out.println("Error while consuming message: = " + e.getMessage());
//        }
//        System.out.print("Received message: %s"+ data);
//        System.out.print("dataa");
//    }
//}
