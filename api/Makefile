build: ## Buid Gradle
	sh gradlew build

run: ## Run Java program
	java -jar build/libs/api-0.0.1-SNAPSHOT.jar

help: ## Display this help screen
	@grep -E '^[a-zA-Z/_-]+:.*?## .*$$' $(MAKEFILE_LIST) | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-20s\033[0m %s\n", $$1, $$2}'
