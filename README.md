## Descrição do programa 
- O programa em Java usa Maven para ler um arquivo JSON contendo dados dos funcionários.
- Ele executa as seguintes funçoes:
   - identifica quem tem o maior salário;
   - Quem é mais velho;
   - Conta quantas pessoas moram em cada cidade.
- E depois imprime todas essas informações no terminal.
## Versões
- java 21
- Maven 3.9.1
## Como compilar e executar
- Para compilar:
````
mvn clean package
``````
- Para executar:
````
java -jar target/Json-1.0-SNAPSHOT.jar <caminho-do-arquivo-json>
```` 