package com.transfer.app.integration.rate.impl;
import com.transfer.app.integration.IntegrationException;
import com.transfer.app.integration.rate.MoneyRateService;
import com.transfer.app.model.RateModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class MoneyRateServiceImpl implements MoneyRateService {

    @Override
    public RateModel getRates() {
        HttpEntity<RateModel> response;
        String url = "http://data.fixer.io/api/latest";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("access_key", "61060a053f4283ca9f97d87df091dd0a")
                    .queryParam("format", "format")
                    .queryParam("symbols", "USD,KZT,EUR");

            HttpEntity<?> entity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();
            response = restTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.GET,
                    entity,
                    RateModel.class);
        }
        catch (RestClientResponseException e) {
            throw e;
        }
        catch (Exception e) {
            throw new IntegrationException(url, e.getMessage());
        }
        return response.getBody();
    }
}