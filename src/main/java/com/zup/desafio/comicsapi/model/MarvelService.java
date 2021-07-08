package com.zup.desafio.comicsapi.model;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MARVEL-CLIENT", url = "http://gateway.marvel.com")
public interface MarvelService {

    @GetMapping("/v1/public/comics/{comicId}?ts=1&apikey=487e5171e458e6e9c7a180444394689e&hash=6862258f012bf3593c56f510fbbd8dbb")
    Object getComic(@PathVariable("comicId") Long comicId);
}
