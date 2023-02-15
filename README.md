# Person Service

## 🚀 Descrição
Api construida para cadastro de pessoas e de endereços

## 🚀 Funcionalidades
Cadastro de pessoas e de endereço podendo uma pessoa conter vários endereços.
<br>
<br>
Alteração de dados de pessoas e de endereços
<br>
<br>
Consultas por ID
<br>
<br>
Listagem de todas as pessoas do banco de Dados
<br>
<br>
Criação de endereço para pessoa

## 💻 Tecnologias Utilizadas

- Java 11
- Spring Boot
- Jpa
- H2 database
- Lombok
- Mockito

## 💻 Inicialização
Primeiramente faça um fork do repositorio logo após faça
um git clone 

logo apos utilize estes payloads para testar a aplicação

### Cadastro de Pessoa:
`POST` localhost:8080/person <br>
PAYLOAD: 
```  
{
    "nome":"Lucy",
    "datNascimento":"01-01-2077",
    "enderecos":[
        {
        "cep":"00000-000",
        "endPrincipal":false,
        "rua":"Weston",
        "numero":"77",
        "cidade":"night city"
        },
        {
        "cep":"00000-000",
        "endPrincipal":true,
        "rua":"Pacifica",
        "numero":"213",
        "cidade":"night city"
        }
        
    ]
}
``` 
<br>

### Alterar Pessoa:
`POST` localhost:8080/person/{id} <br>
PAYLOAD:   

    "{
        "nome": "David",
        "datNascimento": "05-02-2077",
        "enderecos": [
            {
                "idEndereco": 1,
                "cep": "00000-000",
                "endPrincipal": true,
                "rua": "Pacifica",
                "numero": "30",
                "cidade": "City Center"
            }
        ]}

### Consulta por ID
`GET` localhost:8080/person/{id} <br>

### Busca Todas As Pessoas
`GET` localhost:8080/person

### Cria Endereço
`POST` localhost:8080/address <br>
PAYLOAD:

    [{
        "idPessoa": 1,
        "cep": "00000-000",
        "endPrincipal": true,
        "rua": "Weston",
        "numero": "99",
        "cidade": "night city"
    }]

## 🤝 [Colaborador](https://github.com/riverson98)

<table>
  <tr>
    <td align="center">
      <a href="#">
        <img src="https://avatars.githubusercontent.com/u/89596532?s=400&u=3e1bb67302d41d031d9e8d51c1866e2dfb6ffa05&v=4" width="100px;" alt="Foto do Riverson Costa no GitHub"/><br>
        <sub>
          <b>Ríverson Costa</b>
        </sub>
      </a>
    </td>
</tr>
</table>
