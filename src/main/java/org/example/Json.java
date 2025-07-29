package org.example;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Json
{
    public static void main( String[] args ) {
        if (args.length != 1) {
            System.out.println("Nenhum caminho foi fornecido, execute o programa usando: 'java Json <caminho-absoluto>'");
            return;
        }
        File caminho = new File(args[0]);
        if (!caminho.exists()) {
            System.out.println("Esse caminho não existe");
            return;
        }
        Map<String, Integer> habitantesPorCidade = new HashMap<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Funcionarios[] funcionario = objectMapper.readValue(caminho, Funcionarios[].class);
            double maiorSalario = Double.MIN_VALUE;
            int maiorIdade = Integer.MIN_VALUE;
            Funcionarios funcionarioMaiorSalario = null;
            Funcionarios funcionarioMaisVelho = null;
            List<Funcionarios> funcionariosMaiorSalario = new ArrayList<>();
            List<Funcionarios> funcionariosMaisVelhos = new ArrayList<>();
            for (Funcionarios f : funcionario) {
                if (f.salario > maiorSalario) {
                    maiorSalario = f.salario;
                    funcionariosMaiorSalario.clear();
                    funcionariosMaiorSalario.add(f);
                }  else if (f.salario == maiorSalario) {
                    funcionariosMaiorSalario.add(f);
                }


                if (f.idade > maiorIdade) {
                    maiorIdade = f.idade;
                    funcionariosMaisVelhos.clear();
                    funcionariosMaisVelhos.add(f);
                } else if(f.idade == maiorIdade) {
                    funcionariosMaisVelhos.add(f);
                }
                habitantesPorCidade.put(f.cidade,habitantesPorCidade.getOrDefault(f.cidade, 0) + 1 );

            }
            for (Funcionarios f : funcionariosMaiorSalario) {
                System.out.println("Funcionario com o maior salário:  " + f.nome + " " + f.sobrenome + " " + f.salario);
            }
            System.out.println("Funcionarios mais velho: ");

            for (Funcionarios f : funcionariosMaisVelhos) {
                System.out.println(" " + f.nome + " " + f.sobrenome + " " + f.idade);
            }
            int contador = 0;
            int tamanho = habitantesPorCidade.size();
            System.out.println("{");
            for (Map.Entry<String, Integer> entry : habitantesPorCidade.entrySet()) {
                System.out.print("  \"" + entry.getKey() + "\": " + entry.getValue());
                contador++;
                if (contador < tamanho) {
                    System.out.println(",");
                } else {
                    System.out.println();
                }                }
            System.out.println("}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
