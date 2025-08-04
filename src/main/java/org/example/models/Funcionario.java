package org.example.models;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Funcionario {

    private int id;
    private String nome;
    private String sobrenome;
    private BigDecimal salario;
    private int idade;
    private String cidade;
}
