package com.zup.desafio.comicsapi.controller;

import com.zup.desafio.comicsapi.model.MarvelService;
import com.zup.desafio.comicsapi.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comic")
public class ComicController {

    @Autowired
    private MarvelService marvelApi;

    @Autowired
    private ComicService comicService;

    @GetMapping("/{comicId}")
    public ResponseEntity<Object> getComic(@PathVariable Long comicId) {
        Object marvelApiResponse = marvelApi.getComic(comicId);

        comicService.insert(marvelApiResponse);

        return ResponseEntity.ok().build();
    }
}
