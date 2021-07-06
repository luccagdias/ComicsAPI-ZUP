package com.zup.desafio.comicsapi.model.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class UserDTO {

    @NotEmpty(message = "Preenchimento obrigatório!")
    @Length(max = 100, message = "O campo deve possuir no máximo 100 caracteres.")
    private String name;

    @Email(message = "O e-mail digitado não é válido!")
    @NotEmpty(message = "Preenchimento obrigatório!")
    @Length(max = 100, message = "O campo deve possuir no máximo 100 caracteres.")
    private String email;

    @CPF
    @NotEmpty(message = "Preenchimento obrigatório!")
    @Pattern(regexp = "(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)", message = "O CPF deve estar no formato xxx.xxx.xxx-xx")
    private String CPF;

    @NotEmpty(message = "Preenchimento obrigatório!")
    private String birthDate;

    public UserDTO() {
    }

    public UserDTO(String name, String email, String CPF, String birthDate) {
        this.name = name;
        this.email = email;
        this.CPF = CPF;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
