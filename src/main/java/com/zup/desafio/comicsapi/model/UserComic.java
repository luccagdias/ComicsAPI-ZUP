package com.zup.desafio.comicsapi.model;

public class UserComic {

    private Long comicId;
    private Long userId;

    public UserComic() {
    }

    public UserComic(Long comicId, Long userId) {
        this.comicId = comicId;
        this.userId = userId;
    }

    public Long getComicId() {
        return comicId;
    }

    public void setComicId(Long comicId) {
        this.comicId = comicId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
