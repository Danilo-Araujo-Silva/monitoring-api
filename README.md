# N26 Finance Monitoring API

This project is a RESTful API to monitor the entry of financial transactions in a certain period of time,
for example, 60 seconds.

It consists in two endpoints:

- `/transactions`:
	- Where the transactions are added and cataloged.
	- Transactions older than a given amount of time, for example 60 seconds, are not saved.
	- The transactions consist of two information, timestamp when it occurred and the amount of money.
- `/statistics`:
	- Returns a statistical summary of the transactions of a certain amount of time,  for example,
	the last 60 seconds.

## Requirements
-	Java 8
- Gradle wrapper or 4.5.1

## Installation

### Running the tests
- `./gradlew test`

### Running the application
- `./gradle bootRun`

## Testing the application

### Adding a new transaction
- `curl -i -w "\n" -d "{\"timestamp\": $(($(date +'%s * 1000 + %N / 1000000'))), \"amount\": $(echo "scale=2; $RANDOM/100" | bc)}" -H "Content-Type: application/json" -X POST http://localhost:8080/transactions`
	- This command insert a new transaction with a random amount of money.

### Retrieving the Statistics
- `curl -s -i -w "\n" -X GET http://localhost:8080/statistics`
	- This command retrieve the statistics in a certain amount of time, for example, 60 seconds.
	
## Considerations

### Technologies being used
- Java 8
- Spring Boot 2.0.2.RELEASE
- Apache Commons Lang 3
- YML

### Algorithm complexity

This project had the intention of being designed to be performative and at the same time robust when inserting and 
obtaining information.

For this was used the concept of a repository in memory, that keeps a defined size and thread-safe linked list with,
for example, 70 positions (10 positions being extra space).

For maintenance of the information, there is an automatic task that runs each unit of time, for example 1 second, 
adding a new position at the beginning of the list and removing the last one.

In addition, the insertion occurs by checking the transaction timestamp and positioning it's value in one of the 
available positions, for example from 1 to 60. Where the position that the transaction value will enter is defined 
by how long the transaction is offset from the current time, for example, a transaction of 10 seconds ago would 
enter the 10ª position.

Finally, the statistics are obtained by calculating the quantity, sum, mean, minimum value, and the maximum
value of this list in a certain moment of time, for example, the last 60 seconds.

As the insert, update, remove, and summarization always act on a fixed-length list, with 60 positions, for example, 
it is expected that the execution time of these operations will always occur in a constant period of time, O(1).


## License

This project is licensed under the [MIT](README.md) license.

## Author
[Danilo Araújo Silva (silva.danilo.araujo@gmail.com)](https://goo.gl/XW7hi3).

[LinkedIn](https://www.linkedin.com/in/daniloaraujosilva/). [Complete CV](https://goo.gl/XW7hi3). [GitHub](https://github.com/Danilo-Araujo-Silva).
