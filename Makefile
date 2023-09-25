# COLORS
GREEN  := $(shell tput -Txterm setaf 2)
YELLOW := $(shell tput -Txterm setaf 3)
WHITE  := $(shell tput -Txterm setaf 7)
RESET  := $(shell tput -Txterm sgr0)

TARGET_MAX_CHAR_NUM=20
help: ## Show help
	@echo ''
	@echo 'Usage:'
	@echo '  ${YELLOW}make${RESET} ${GREEN}<target>${RESET}'
	@echo ''
	@echo 'Targets:'
	@awk '/^[a-zA-Z\-\_0-9]+:/ { \
		helpMessage = match(lastLine, /^## (.*)/); \
		if (helpMessage) { \
			helpCommand = substr($$1, 0, index($$1, ":")-1); \
			helpMessage = substr(lastLine, RSTART + 3, RLENGTH); \
			printf "  ${YELLOW}%-$(TARGET_MAX_CHAR_NUM)s${RESET} ${GREEN}%s${RESET}\n", helpCommand, helpMessage; \
		} \
	} \
	{ lastLine = $$0 }' $(MAKEFILE_LIST)

start-db: build ## Starts application inside docker
	@docker-compose up -d db

restart: build ## Restart all containers after recompiling
	@echo "-------------- Starting Containers --------------"
	@docker-compose -f ${DOCKER_COMPOSE_FILE_PATH} down
	@docker-compose -f ${DOCKER_COMPOSE_FILE_PATH} build --no-cache
	@docker-compose -f ${DOCKER_COMPOSE_FILE_PATH} up -d

up: build ## Starts application inside docker
	@docker-compose up -d

build: ## Builds the application
	@ps -ef | grep java
	@./gradlew clean build

stop: ## Stops all containers up
	@docker-compose down
	@echo "Containers stopped!"

clean: ## Stops all containers and removes docker images, containers and networks for this stack
	@docker-compose down --rmi all
