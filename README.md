# Complementação da Prova
> Entrega "Complementação da Prova" da disciplina de "Arquitetura e Projeto de Software" no curso de "Engenharia de Software". [Repositório temporário]

## Referências

[Refactoring Guru](https://refactoring.guru/design-patterns/)\
[Repositório do professor](https://github.com/marcoaparaujo/padroes-projeto)\
[1ª Prova](https://github.com/viniciusmachadovianna/prova1-arqProj-ViniciusMachado)

---
## Diagrama

![Diagrama UML Prova](diagrama_prova.png)

---
## Comando

**Sistema de Atendimento de Clínica Veterinária**

> Uma clínica veterinária deseja um sistema simples para registrar atendimentos de animais. Cada atendimento possui tutor, animal, serviço solicitado, valor base e situação atual.

O sistema deve ser flexível para permitir novas regras e comportamentos sem grandes alterações no código existente.


#### Diagrama de classes
Construa um diagrama com as principais classes, atributos, métodos e relacionamentos.

Classes mínimas esperadas:

1. Atendimento
2. Animal
3. Tutor
4. ServicoVeterinario

Outras classes podem ser criadas conforme a solução proposta.

#### Código-fonte
Implemente em Java os seguintes comportamentos:

##### Situação do atendimento

O atendimento pode estar em uma das seguintes situações:

1. Agendado
2. EmAtendimento
3. Finalizado
4. Cancelado

Cada situação deve controlar quais mudanças são permitidas.

Exemplos:

- Um atendimento Agendado pode ir para EmAtendimento ou Cancelado;
- Um atendimento EmAtendimento pode ir para Finalizado;
- Um atendimento Finalizado não pode ser cancelado.


##### Avisos automáticos

Sempre que a situação do atendimento mudar, algumas partes devem ser avisadas automaticamente.

Exemplos:

- O tutor deve ser avisado quando o atendimento for iniciado;
- O veterinário deve ser avisado quando um atendimento for cancelado;
- A recepção deve ser avisada quando o atendimento for finalizado.
- A solução deve permitir adicionar novos interessados sem modificar diretamente a classe Atendimento.

##### Acréscimos e descontos

O valor final do atendimento pode sofrer alterações por regras opcionais:

1. Desconto para animal adotado;
2. Taxa de atendimento domiciliar;
3. Serviço de banho pós-consulta.

Essas regras podem ser combinadas em um mesmo atendimento.

#### Casos de teste
Crie os casos de teste, demonstrando:

- Mudança válida de situação;
- Tentativa de mudança inválida;
- Envio automático de aviso;
- Cálculo do valor final com mais de uma regra aplicada.
