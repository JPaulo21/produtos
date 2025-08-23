# Produtos-API 

## Tecnologias Utilizadas
![Maven](https://img.shields.io/badge/apachemaven-%23C71A36.svg?style=for-the-badge&logo=apachemaven&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%234479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Security](https://img.shields.io/badge/springsecurity-%236DB33F.svg?style=for-the-badge&logo=springsecurity&logoColor=white)
![JWT](https://img.shields.io/badge/jsonwebtokens-%23000000.svg?style=for-the-badge&logo=jsonwebtokens&logoColor=white)
![Swagger](https://img.shields.io/badge/swagger-%2385EA2D.svg?style=for-the-badge&logo=swagger&logoColor=white)
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)

## Documentação da API

### Autenticação
### `GET` /api/token

---
### Criar um novo produto.
### `POST` /api/produtos
Authorization: Bearer {token}
```json
{
  "id": Long,
  "nome": String,
  "descricao": String,
  "preco": Double,
  "quantidadeEstoque": Integer,
  "dataCriacao": LocalDateTime
}	
```
---
### Listar todos os produtos.
### `GET` /api/produtos
Authorization: Bearer {token}

---
### Obter detalhes de um produto por ID.
### `GET` /api/produtos/{id}
Authorization: Bearer {token}

---
### Atualizar um produto existente por ID.
### `PUT` /produtos/{id}
Authorization: Bearer {token}

---
### Remover um produto por ID.
### `DELETE` /api/produtos/{id}
Authorization: Bearer {token}

