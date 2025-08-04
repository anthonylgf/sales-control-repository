package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.Funcionario;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Json {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Nenhum caminho foi fornecido, execute o programa usando: ' java -jar target\\Json-1.0-SNAPSHOT.jar <caminho-absoluto>'");
            return;
        }
        File caminho = new File(args[0]);
        if (!caminho.exists()) {
            System.out.println("Esse caminho não existe");
            return;
        }
        Map<String, Integer> habitantesPorCidade = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        Funcionario[] funcionario;

        try {
            JsonNode root = objectMapper.readTree(caminho);
            JsonNode arrayNode = root.get("funcionarios");
            funcionario = objectMapper.readValue(arrayNode.toString(), Funcionario[].class);
        } catch (IOException exception) {
            throw new RuntimeException("Erro ao ler o arquivo", exception);
        }
        BigDecimal maiorSalario = BigDecimal.ZERO;
        int maiorIdade = Integer.MIN_VALUE;
        List<Funcionario> funcionariosMaiorSalario = new ArrayList<>();
        List<Funcionario> funcionariosMaisVelhos = new ArrayList<>();
        for (Funcionario f : funcionario) {
            if (f.getSalario().compareTo(maiorSalario) > 0) {
                maiorSalario = f.getSalario();
                funcionariosMaiorSalario.clear();
                funcionariosMaiorSalario.add(f);
            } else if (f.getSalario().compareTo(maiorSalario) == 0) {
                funcionariosMaiorSalario.add(f);
            }

            if (f.getIdade() > maiorIdade) {
                maiorIdade = f.getIdade();
                funcionariosMaisVelhos.clear();
                funcionariosMaisVelhos.add(f);
            } else if (f.getIdade() == maiorIdade) {
                funcionariosMaisVelhos.add(f);
            }
            habitantesPorCidade.compute(f.getCidade(), (cidade, quantidade) -> quantidade == null ? 1 : quantidade + 1);
        }
        System.out.println(funcionariosMaiorSalario.stream().map(f -> " " + f.getNome() + " " + f.getSobrenome()).collect(Collectors.joining(", ")));
        ;
        System.out.println(funcionariosMaisVelhos.stream().map(f -> " " + f.getNome() + " " + f.getSobrenome()).collect(Collectors.joining(", ")));
        try {
            System.out.println(objectMapper.writeValueAsString(habitantesPorCidade));
        } catch (JsonProcessingException e) {
            System.err.println("Erro ao converter o mapa para JSON: " + e.getMessage());
        }
    }
}
