package com.zup.desafio.comicsapi.controller;

import com.zup.desafio.comicsapi.controller.exceptions.ObjectNotFoundException;
import com.zup.desafio.comicsapi.model.MarvelService;
import com.zup.desafio.comicsapi.model.UserComic;
import com.zup.desafio.comicsapi.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/comic")
public class ComicController {

    @Autowired
    private MarvelService marvelApi;

    @Autowired
    private ComicService comicService;

    @PostMapping
    public ResponseEntity<Object> getComic(@RequestBody UserComic userComic) throws ObjectNotFoundException {
        Object marvelApiResponse = marvelApi.getComic(userComic.getComicId());

        comicService.insert(marvelApiResponse, userComic.getUserId());

        return ResponseEntity.ok().build();
    }
}
