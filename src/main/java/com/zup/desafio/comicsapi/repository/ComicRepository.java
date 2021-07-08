package com.zup.desafio.comicsapi.repository;

import com.zup.desafio.comicsapi.model.Comic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComicRepository extends JpaRepository<Comic, Long> {

}
