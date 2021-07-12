package com.zup.desafio.comicsapi.controller;

import com.zup.desafio.comicsapi.controller.exceptions.FieldAlreadyRegisteredException;
import com.zup.desafio.comicsapi.controller.exceptions.ObjectNotFoundException;
import com.zup.desafio.comicsapi.controller.exceptions.util.FieldMessage;
import com.zup.desafio.comicsapi.model.User;
import com.zup.desafio.comicsapi.model.dto.UserDTO;
import com.zup.desafio.comicsapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/{userId}")
    public ResponseEntity<User> findById(@PathVariable("userId") Long id) throws ObjectNotFoundException {
        User user = service.findById(id);

        return ResponseEntity.ok().body(user);
    }

    @PostMapping()
    public ResponseEntity<Void> insert(@Valid @RequestBody UserDTO userDTO) throws FieldAlreadyRegisteredException {
        List<FieldMessage> err = validateUniqueFields(userDTO);
        if(err.size() != 0) {
            throw new FieldAlreadyRegisteredException(err);
        }

        User user = service.insert(userDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    private List<FieldMessage> validateUniqueFields(UserDTO userDTO) {
        List<FieldMessage> fieldErrors = new ArrayList<>();

        User userByEmail = service.findByEmail(userDTO.getEmail());
        if (userByEmail != null) {
            fieldErrors.add(new FieldMessage("email", "O email informado já está cadastrado."));
        }

        User userByCPF = service.findByCPF(userDTO.getCPF());
        if (userByCPF != null) {
            fieldErrors.add(new FieldMessage("cpf", "O CPF informado já está cadastrado."));
        }

        return fieldErrors;
    }
}