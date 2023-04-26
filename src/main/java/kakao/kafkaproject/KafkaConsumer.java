//package kakao.kafkaproject;
//
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Primary;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
//import java.time.Duration;
//import java.util.Collections;
//import java.util.List;
//import java.util.Properties;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//@Component
//@RequiredArgsConstructor
//@Primary
//@RestController
//@RequestMapping("/kafka")
//public class KafkaConsumer {
//
//    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
//    private final Properties kafkaProperties;
//
//    @Autowired
//    private KafkaMessageHandler kafkaMessageHandler;
//
//
//    @PostConstruct
//    public void init() {
//        kafkaProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        kafkaProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
//        kafkaProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
//        kafkaProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "console-consumer");
//
//        executorService.execute(() -> {
//            KafkaConsumer consumer = new KafkaConsumer(kafkaProperties);
//            consumer.subscribe(Collections.singletonList("dbserver1.testdb.subscriber"));
//
//            while (true) {
//                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
//                if(records != null) {
//                    for (ConsumerRecord<String, String> record : records) {
//                        kafkaMessageHandler.handleMessage(record.value());
//                    }
//                }
//            }
//        });
//    }
//
//    private ConsumerRecords<String, String> poll(Duration ofMillis) {
//        return null;
//    }
//
//    private void subscribe(List<String> singletonList) {
//    }
//
////    @GetMapping("/messages")
////    public List<String> getMessages() {
////        return kafkaMessageHandler.getMessages();
////    }
////@GetMapping("/messages")
////public String getMessages() {
////    return kafkaMessageHandler.getMessages();
////}
//    @GetMapping("/messages")
//    @KafkaListener(topics = "dbserver1.testdb.subscriber")
//    public void consume(String message) {
////        logger.info(String.format("Received message: %s", message));
//        System.out.println("message: " + message);
//    }
//
////    private void processRecord(ConsumerRecord<String, String> record) {
////        System.out.println("Received message: key={}, value={}, topic={}, partition={}, offset={}"+
////                record.key(), record.value(), record.topic(), record.partition(), record.offset());
////    }
//}
