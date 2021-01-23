COMPOSE=docker-compose
EXEC=$(COMPOSE) exec
BUILD=$(COMPOSE) build
UP=$(COMPOSE) up -d
LOGS=$(COMPOSE) logs
STOP=$(COMPOSE) stop
RM=$(COMPOSE) rm
DOWN=$(COMPOSE) down
REACT=$(EXEC) react
JAVA=$(EXEC) java
DB=$(EXEC) mysql
REDIS=$(EXEC) redis

all: docker/up

docker/build: ## docker build
	$(BUILD)

docker/up: ## docker up
	$(UP)

docker/logs: ## docker logs
	$(LOGS)

docker/stop: ## docker stop
	$(STOP)

docker/rm: ## docker clean
	$(RM)

docker/down: ## docker down & docker volume prune
	$(DOWN) -v

docker/volume/prune: ### docker volume prune
	docker volume prune

react/ash: ## react container bash
	$(REACT) ash

java/bash: ## java container bash
	$(JAVA) bash

db/bash: ## db(MySQL) container bash
	$(DB) bash

db/wait: ## waiting start db(MySQL) container
	$(DB) /home/wait.sh

mysql: ## db(MySQL) container's MySQL access
	$(DB) mysql --defaults-extra-file=/home/access.cnf winter

redis/bash: ## Redis container bash
	$(REDIS) bash

help: ## Display this help screen
	@grep -E '^[a-zA-Z/_-]+:.*?## .*$$' $(MAKEFILE_LIST) | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-20s\033[0m %s\n", $$1, $$2}'
