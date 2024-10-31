package com.codemaster.io.service;

import com.codemaster.io.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.Map;

@Service
public class FacebookAPIService {

    @Value("${facebook.client.id}")
    private String CLIENT_ID;

    @Value("${facebook.client.secret}")
    private String CLIENT_SECRET;

    @Value("${facebook.client.redirect.uri}")
    private String REDIRECT_URI;

    private static final String TOKEN_URL = "https://graph.facebook.com/v21.0/oauth/access_token";
    private static final String USER_INFO_URL = "https://graph.facebook.com/me";
    private final RestTemplate restTemplate = new RestTemplate();

    public User getUserFrom(String code) {
        String accessToken = exchangeCodeForAccessToken(code);
        if (accessToken != null) {
            return fetchUserInfo(accessToken);
        }
        return null;
    }

    private String exchangeCodeForAccessToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", CLIENT_ID);
        params.add("client_secret", CLIENT_SECRET);
        params.add("redirect_uri", REDIRECT_URI);
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<Map> response = restTemplate.exchange(TOKEN_URL, HttpMethod.POST, request, Map.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return (String) response.getBody().get("access_token");
        }
        return null;
    }

    private User fetchUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken); // Set the access token in the Authorization header

        HttpEntity<String> entity = new HttpEntity<>(headers);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(USER_INFO_URL)
                .queryParam("fields", "name,email");

        ResponseEntity<Map> response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, entity, Map.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            Map<String, Object> userInfo = response.getBody();
            String name = (String) userInfo.get("name");
            String email = (String) userInfo.get("email");

            System.out.println("name = " + name);
            System.out.println("email = " + email);

            return User.builder()
                    .email(email)
                    .name(name)
                    .build();
        }
        return null;
    }
}

