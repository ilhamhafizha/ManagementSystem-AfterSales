package org.msas.managementsystemaftersales.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class WhatsappService {

    @Value("${wa.api.token}")
    private String token;

    @Value("${wa.api.url}")
    private String url;

    private final RestTemplate restTemplate = new RestTemplate();

    public boolean sendMessage(String phoneNumber, String message) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("target", phoneNumber);
        body.add("message", message);

        HttpEntity<MultiValueMap<String, String>> request =
                new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response =
                    restTemplate.postForEntity(url, request, String.class);

            log.info("✅ WA SENT to {} | response={}", phoneNumber, response.getBody());
            return true;

        } catch (Exception e) {
            log.error("❌ FAILED SEND WA to {} | {}", phoneNumber, e.getMessage());
            return false;
        }
    }
}
