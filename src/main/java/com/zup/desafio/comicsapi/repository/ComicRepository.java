package com.zup.desafio.comicsapi.repository;

import com.zup.desafio.comicsapi.model.Comic;
import com.zup.desafio.comicsapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ComicRepository extends JpaRepository<Comic, Long> {

    Comic findByIdFromMarvel(Long marvelId);

    @Query("SELECT u FROM User u WHERE u.id = :userId")
    User findUser(@Param("userId") Long userId);
}
