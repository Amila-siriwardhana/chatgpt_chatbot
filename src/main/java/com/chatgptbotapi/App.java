package com.chatgptbotapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@RestController
public class App {
    String CHATGPT_ENDPOINT = "https://api.openai.com/v1/chat/completions";
    String CHATGPT_AUTH_TOKEN = "";

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @PostMapping("/chatbot")
    public String chat(@RequestBody String message) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(CHATGPT_AUTH_TOKEN);

        String requestBody = "{\"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"user\", \"content\": \""
                + message + "\"}], \"temperature\": 0.7}";
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(CHATGPT_ENDPOINT, entity, String.class);
        return response.getBody();
    }

}
