package com.zup.desafio.comicsapi.service;

import com.zup.desafio.comicsapi.controller.exceptions.ObjectNotFoundException;
import com.zup.desafio.comicsapi.model.Comic;
import com.zup.desafio.comicsapi.model.User;
import com.zup.desafio.comicsapi.model.dto.UserDTO;
import com.zup.desafio.comicsapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(Long id) throws ObjectNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuário não está registrado"));

        List<Comic> comics = user.getUserComics();

        comics.stream().forEach(comic -> {
            if (comic.isActiveDiscount()) {
                Double actualPrice = comic.getPrice();
                comic.setPrice(actualPrice - (actualPrice * 0.10));
            }
        });

        return user;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findByCPF(String cpf) {
        return userRepository.findByCPF(cpf);
    }

    public User insert(UserDTO userDTO) {
        User user = createUserFromUserDTO(userDTO);
        return userRepository.save(user);
    }

    private User createUserFromUserDTO(UserDTO userDTO) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birthDate = LocalDate.parse(userDTO.getBirthDate(), formatter);

        return new User(userDTO.getName(), userDTO.getEmail(), userDTO.getCPF(), birthDate);
    }
}
