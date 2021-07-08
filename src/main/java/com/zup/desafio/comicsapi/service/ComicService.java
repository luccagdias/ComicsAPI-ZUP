package com.zup.desafio.comicsapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zup.desafio.comicsapi.model.Comic;
import com.zup.desafio.comicsapi.repository.ComicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class ComicService {

    @Autowired
    private ComicRepository comicRepository;

    public Comic insert(Object marvelApiResponse) {

        Comic comic = getComicObjectFromMarvelResponseData(marvelApiResponse);
        return comicRepository.save(comic);
    }

    private Comic getComicObjectFromMarvelResponseData(Object marvelApiResponse) {
        JsonNode resultsNodeAttributes = getComicsAttributesFromJsonResponse(marvelApiResponse);

        Long idFromMarvel = resultsNodeAttributes.get("id").asLong();
        String title = resultsNodeAttributes.get("title").asText();
        String description = resultsNodeAttributes.get("description").asText();
        String isbn = resultsNodeAttributes.get("isbn").asText();
        Double price = resultsNodeAttributes.get("prices").get(0).get("price").asDouble();
        String creators = getCreatorsFromResultsNode(resultsNodeAttributes);

        return new Comic(null, idFromMarvel, title, price, creators, isbn, description);
    }

    private JsonNode getComicsAttributesFromJsonResponse(Object marvelApiResponse) {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode responseData = objectMapper.valueToTree(marvelApiResponse);
        JsonNode resultsNode = responseData.get("data").get("results");
        JsonNode resultsNodeAttributes = objectMapper.valueToTree(resultsNode).get(0);

        return resultsNodeAttributes;
    }

    private String getCreatorsFromResultsNode(JsonNode resultsNodeAttributes) {
        Iterator<JsonNode> creatorsNode = resultsNodeAttributes.get("creators").get("items").elements();

        StringBuilder creatorsNames = new StringBuilder();
        while (creatorsNode.hasNext()) {
            JsonNode creatorItem = creatorsNode.next();
            String creatorName = creatorItem.get("name").asText();

            creatorsNames.append(creatorName + ",");
        }

        return creatorsNames.toString();
    }
}


