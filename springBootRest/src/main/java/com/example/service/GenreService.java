package com.example.service;

import com.example.domain.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> getAll();

    Genre getByName(String name);

    Genre insert(Genre genre);

    void deleteByName(String name);

}
