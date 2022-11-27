package com.example.service;

import com.example.domain.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestOperations;

import java.net.URI;
import java.util.Collections;
import java.util.List;

import static org.springframework.http.HttpMethod.*;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GenreServiceRest implements GenreService {

    private final RestOperations rest;
    @Value("${host-name}")
    private final String hostName;

    @Override
    @Cacheable("genre")
    public List<Genre> getAll() {
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Content-Type", Collections.singletonList("application/json"));
        RequestEntity<List<Genre>> requestEntity = new RequestEntity<>(headers, GET, URI.create(hostName + "genre/"));
        ResponseEntity<List<Genre>> responseEntity = rest.exchange(requestEntity,
                new ParameterizedTypeReference<List<Genre>>() {
                });
        return responseEntity.getBody();
    }

    @Override
    @Cacheable("genre")
    public Genre getByName(String name) {
        return rest.getForObject(hostName + "genre/" + name, Genre.class);
    }

    @Override
    @CacheEvict("genre")
    public Genre insert(Genre genre) {
        RequestEntity<Genre> requestEntity = new RequestEntity<>(genre, POST, URI.create(hostName + "genre"));
        ResponseEntity<Genre> responseEntity = rest.exchange(requestEntity, Genre.class);
        return responseEntity.getBody();
    }

    @Override
    @CacheEvict("genre")
    public void deleteByName(String name) {
        rest.delete(URI.create(hostName + "genre/" + name));
    }
}
