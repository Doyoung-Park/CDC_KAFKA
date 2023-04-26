//package kakao.kafkaproject.service;
//
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.time.Duration;
//import java.util.Collections;
//import java.util.Properties;
//
//@Component
//public class KafkaConsoleConsumer {
//
//    private final Logger logger = LoggerFactory.getLogger(KafkaConsoleConsumer.class);
//
//    private final String kafkaServer = "localhost:9092";
//    private final String kafkaTopic = "dbserver1.testdb.subscriber";
//
//    @PostConstruct
//    public void start() {
//        logger.info("Starting Kafka console consumer...");
//
//        Properties props = new Properties();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, "console-consumer");
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
//
//        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
//        consumer.subscribe(Collections.singleton(kafkaTopic));
//
//        while (true) {
//            consumer.poll(Duration.ofSeconds(1)).forEach(this::processRecord);
//        }
//    }
//
//
//    @KafkaListener(topics = "dbserver1.testdb.subscriber")
//    public void consume(String message) {
//        logger.info(String.format("Received message: %s", message));
//    }
//
//    private void processRecord(ConsumerRecord<String, String> record) {
//        logger.info("Received message: key={}, value={}, topic={}, partition={}, offset={}",
//                record.key(), record.value(), record.topic(), record.partition(), record.offset());
//    }
//
//}
