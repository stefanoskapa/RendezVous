package com.rendezvous.service;

import com.rendezvous.recaptcha.ReCaptchaKeys;
import com.rendezvous.recaptcha.ReCaptchaResponse;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReCaptchaService {

    private final ReCaptchaKeys reCaptchaKeys;
    private final RestTemplate restTemplate;

    @Autowired
    public ReCaptchaService(ReCaptchaKeys reCaptchaKeys, RestTemplate restTemplate) {
        this.reCaptchaKeys = reCaptchaKeys;
        this.restTemplate = restTemplate;
    }

    public ReCaptchaResponse verify(String response) {
        URI verifyURI = URI.create(String.format("https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s", reCaptchaKeys.getSecret(), response));
        ReCaptchaResponse reCaptchaResponse = restTemplate.getForObject(verifyURI, ReCaptchaResponse.class);
        System.out.println(reCaptchaResponse);
        return reCaptchaResponse;
    }

}
