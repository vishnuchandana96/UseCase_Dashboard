//package com.ibm.clusterservice.configuration;
//
//import com.ibm.clusterservice.domain.Application;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//import org.springframework.kafka.support.serializer.JsonSerializer;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class ApplicationConfiguration
//{
//        @Bean
//        public ProducerFactory<String, Application> producerFactory() {
//            Map<String, Object> config = new HashMap<>();
//            // sending records in JSON format (value)
//            config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//            config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//            config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//            return new DefaultKafkaProducerFactory<>(config);
//        }
//        @Bean
//        public KafkaTemplate<String, Application> kafkaTemplate()
//        {
//            return new KafkaTemplate<>(producerFactory());
//        }
//}
