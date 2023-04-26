package kakao.kafkaproject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kakao.kafkaproject.domain.Subscriber;
import kakao.kafkaproject.repository.SubscriberRepository;
//import kakao.kafkaproject.service.KafkaConsoleConsumer;
//import kakao.kafkaproject.service.KafkaConsumert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nonapi.io.github.classgraph.json.JSONDeserializer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.spring.web.json.Json;
import springfox.documentation.spring.web.json.JsonSerializer;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class SubscriberController {

    private final SubscriberRepository subscriberRepository;

    @GetMapping("/")
    public List<Subscriber> getSubscribers() {
        return subscriberRepository.findAll();
    }

    @GetMapping("/{id}")
    public Subscriber getSubscriber(@PathVariable Integer id) {
        return subscriberRepository.findById(id).orElse(null);
    }

    @PostMapping("/")
    public Subscriber postSubscriber(@RequestBody Subscriber subscriber) {
        return subscriberRepository.save(subscriber);
    }

    @PutMapping("/")
    public Subscriber putSubscriber(@RequestBody Subscriber subscriber) {
        Subscriber oldSubscriber = subscriberRepository.findById(subscriber.getId()).orElse(null);
        oldSubscriber.setName(subscriber.getName());
        oldSubscriber.setEmail(subscriber.getEmail());
        oldSubscriber.setPassword(subscriber.getPassword());
        oldSubscriber.setLevel(subscriber.getLevel());

        return subscriberRepository.save(oldSubscriber);
    }

    @DeleteMapping("/{id}")
    public Integer DeleteSubscriber(@PathVariable Integer id) {
        subscriberRepository.deleteById(id);
        return id;
    }

    @PostMapping("/etl")
    public String toETLServerPost(@RequestBody Subscriber subscriber) throws IOException, InterruptedException {
//        Subscriber oldSubscriber = subscriberRepository.findById(subscriber.getId()).orElse(null);
        Subscriber oldSubscriber = new Subscriber();
        oldSubscriber.setName(subscriber.getName());
        oldSubscriber.setEmail(subscriber.getEmail());
        oldSubscriber.setPassword(subscriber.getPassword());
        oldSubscriber.setLevel(subscriber.getLevel());
        subscriberRepository.save(subscriber);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:3001/etl"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(oldSubscriber.toJson()))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());
        return "success: POST request";
    }

    @GetMapping("/etl/{id}")
    public Subscriber toETLServerGet(@PathVariable Integer id) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:3001/etl"))
                .header("Content-Type", "application/json")
                .GET().setHeader("id", String.valueOf(id))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());
        return subscriberRepository.findById(id).orElse(null);
    }

    @GetMapping("/etl")
    public List<Subscriber> toETLServerGetALL() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:3001/etl/all"))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());
        return subscriberRepository.findAll();
    }

    @PutMapping("/etl")
    public String toETLServerPut(@RequestBody Subscriber subscriber) throws IOException, InterruptedException {
        Subscriber oldSubscriber = subscriberRepository.findById(subscriber.getId()).orElse(null);
        oldSubscriber.setId(subscriber.getId());
        oldSubscriber.setName(subscriber.getName());
        oldSubscriber.setEmail(subscriber.getEmail());
        oldSubscriber.setPassword(subscriber.getPassword());
        oldSubscriber.setLevel(subscriber.getLevel());
        subscriberRepository.save(oldSubscriber);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:3001/etl"))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(oldSubscriber.toJson()))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());
        return "Success: PUT request";
    }

    @DeleteMapping("/etl/{id}")
    public String toETLServerDelete(@PathVariable Integer id) throws IOException, InterruptedException {
        subscriberRepository.deleteById(id);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:3001/etl"))
                .header("Content-Type", "application/json")
                .DELETE().setHeader("id", String.valueOf(id))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());
        return "Success: DELETE request";
    }
}
