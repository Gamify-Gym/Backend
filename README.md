# Gamify Gym Backend
***

- [**Instalando o projeto**](#instalando-o-projeto)
- [**Rodando o projeto com Docker**](#-rodando-o-projeto-com-docker)
- [**Contribuindo para o projeto**](#-contribuindo-para-o-projeto)
- [**Requisições**](#requisições)

## Instalando o projeto
*** 
Primeiro, clone o repositório:
```bash
git clone https://github.com/Gamify-Gym/Backend.git
```

Navegue até a pasta do projeto:
```bash
cd desktop/backend #Talvez esse não seja o caminho exato
```

Baixe as dependências:
```bash
mvn clean install
```

Vá até o repositório de documentos e copie os seguintes arquivos -> **app.key** e **app.pub**. Depois coloque esses documentos na pasta backend/src/main/resources.

Agora você irá criar um arquivo .env na pasta backend utilizando essas variáveis de ambiente: 

```bash
DB_DATABASE=GG_Database
DB_USER=myUser
DB_PASSWORD=myPassword
DB_ROOT_PASSWORD=myRootPassword
DB_PORT=3300
DB_HOST=mysql
APP_PORT=6000
```

## <img src="https://devicon-website.vercel.app/api/docker/original.svg" alt="Docker" width="32" style="vertical-align:middle;"/> Rodando o projeto com Docker
*** 

Abra o Docker Desktop.

Depois gere as imagens e containers:
```bash
docker compose up --build
```

Caso precise finalizar os containers:
```bash
docker compose down
```

## <img src="https://devicon-website.vercel.app/api/git/original.svg" alt="Git" width="28" style="vertical-align:middle;"/> Contribuindo para o projeto
***
Logue na sua conta do github na web. 
Depois logue localmente no github: 
```bash
git config --global user.email SeuEmail

git config --global user.name SeuNome
```

Adicione suas alterações:
```bash
git add . #Caso seja algum arquivo específico apenas, troque o . pelo nome do arquivo
```

Crie um commit:
```bash
git commit -m "Sua mensagem"
```

Leve o commit para o repositório remoto:
```bash
git push origin
```

## Requisições
***

### Usuários
- POST /user/create - Cria um usuário

**Body**
```bash
{
"username": "nome do usuário",
"email": "123@gmail.com",
"password": "senha do usuário"
}
```
- POST /login - Inicia sessão do usuário

**Body**
```bash
{
"email": "123@gmail.com",
"password": "senha do usuário"
}
```
- GET /check - Verifica se o token é válido

**Auth**
```bash

```
### Amizades
- POST /friendship - Cria uma amizade
```bash
{
"friendId":id do amigo 
}
```
- PUT /friendship/status - Mostra o status atual de uma amizade
```bash
{
"code":"código",
"friendId":id do amigo,
"status":"PENDING/OK/BLOCKED/DECLINED"
}
```
- DELETE /friendship - Delete uma amizade
```bash
{
"code":"código",
"friendId":"id do amigo"
}
```
- GET /friendship - Apresenta a lista de usuários amigos do usuário logado
```bash
{
    "id": 12,
    "player": {
      "id_player": 10,
      "name": "Lucas"
    },
    "friend": {
      "id_player": 5,
      "name": "Ana"
    },
    "status": "OK",
    "createdOn": "2025-09-23",
    "sinceWhen": "2025-09-24",
    "code": "550e8400-e29b-41d4-a716-446655440000"
  }
```
