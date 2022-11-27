package com.example.service;

import com.example.domain.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DemoServiceImpl implements DemoService{

    private final GenreService genreService;

    @Override
    public void demo() {
        System.out.println("=============================ВСЕ ЖАНРЫ ИЗНАЧАЛЬНО=============================");
        System.out.println(genreService.getAll());
        System.out.println("=============================ПОИСК ЖАНРОВ ПО ИМЕНИ=============================");
        System.out.println(genreService.getByName("adventure"));
        System.out.println("=============================УДАЛЕНИЕ ЖАНРА ПО ИМЕНИ=============================");
        System.out.println("*ДО УДАЛЕНИЯ*");
        System.out.println(genreService.getAll());
        genreService.deleteByName("fantastic");
        System.out.println("*ПОСЛЕ УДАЛЕНИЯ*");
        System.out.println(genreService.getAll());
        System.out.println("=============================ДОБАВЛЕНИЕ ЖАНРА=============================");
        System.out.println("*ДО ДОБАВЛЕНИЯ*");
        System.out.println(genreService.getAll());
        genreService.insert(new Genre(genreService.getAll().size(), "fantastic"));
        System.out.println("*ПОСЛЕ ДОБАВЛЕНИЯ*");
        System.out.println(genreService.getAll());
    }
}
