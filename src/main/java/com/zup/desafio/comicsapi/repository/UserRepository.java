package com.zup.desafio.comicsapi.repository;

import com.zup.desafio.comicsapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

     User findByEmail(String email);

    User findByCPF(String cpf);
}
