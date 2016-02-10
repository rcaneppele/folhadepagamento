# Folha de Pagamento
Projeto com exemplo de Domain Model

## Tecnologias
* Java 8
* Maven
* Bootstrap
* MySQL 5
* JAVA EE 7
  * JPA 2.1/Hibernate
  * JTA 1.2
  * JSF 2.2/Mojarra(com páginas HTML5 Friendly e Stateless View)
  * CDI 1.1
  * Bean Validation 1.1

## Funcionalidades e Regras de Negócio
* Cadastro de Cargos(nome, faixa salarial(valor mínimo, valor máximo))
  * Nome do Cargo deve ser único no sistema;
  * Faixa salarial deve respeitar o sálario mínimo vigente(R$880,00);
  * Valor Máximo da Faixa Salarial deve ser maior ou igual ao Valor Mínimo;
* Cadastro de Funcionários(dados pessoais(nome, cpf, email, telefone), dados profissionais(matrícula, cargo, salário, data de admissão))
  * CPF e Matrícula devem ser únicos no sistema;
  * Sálario deve estar entre a Faixa Salarial do Cargo escolhido;
* Cadastro de Reajuste Salarial(data, valor, justificativa)
  * Valor do reajuste não pode exceder o valor de 40% do salário do funcionário;
  * Funcionário somente poderá receber o primeiro reajuste após completar o período de experiência(03 meses);
  * O intervalo entre reajustes deve ser de no mínimo 06 meses;
* Cálculo da Folha de Pagamento do Mês
  * Calcular o somatório do salário dos funcionários que receberão no mês escolhido;
  * Calcular o somatório do FGTS(08% do salário) pago aos funcionários no mês escolhido;
  * Calcular o somatório do INSS(20% do salário) pago aos funcionários no mês escolhido;
