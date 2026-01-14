# Comandos Docker Úteis

## Imagens Docker

### Listar imagens Docker disponíveis localmente
```bash
docker images
```

### Excluir uma imagem Docker
```bash
docker rmi <image_id_ou_nome>
```

## Containers Docker

### Listar todos os containers Docker (em execução e parados)
```bash
docker ps -a
```

### Iniciar um container Docker
```bash
docker start <container_id_ou_nome>
```

### Rodar container PostgreSQL na versão 16.3 em uma network personalizada
```bash
docker run --name librarydb -p 5432:5432 -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -e POSTGRES_DB=library --network library-network postgres:16.3
```

### Rodar container PgAdmin na versão 4 em uma network personalizada
```bash
docker run --name pgadmin4 -p 15432:80 -e PGADMIN_DEFAULT_EMAIL=admin@admin.com -e PGADMIN_DEFAULT_PASSWORD=admin --network library-network dpage/pgadmin4:8.9
```

### Parar um container Docker
```bash
docker stop <container_id_ou_nome>
```

### Excluir um container Docker
```bash
docker rm <container_id_ou_nome>
```

## Redes Docker

### Criar uma rede Docker personalizada
```bash
docker network create library-network
```