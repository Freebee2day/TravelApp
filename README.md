"# TravelApp" 
## Schema 
### Models
#### Post

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | user          | String   | unique id for the user|   
   | cities        | String   | name of the cities |
   | departDate    | Long     | date of depareture for flight  |
   | arriveDate    | long     | date of arrival for flight   |
   | toDo          | toDoItem | a toDoItem that contains the information of what happens on the day   |

### Networking
#### List of network requests by screen
   - Flight Search Screen
      - (Read/GET) Query all flights according to dates 
         ```java
AsyncHttpClient client = new DefaultAsyncHttpClient();
client.prepare("GET", "https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browsedates/v1.0/US/USD/en-US/SFO-sky/LAX-sky/2019-09-01?inboundpartialdate=2019-12-01")
	.setHeader("x-rapidapi-key", "7b4dfa76b8mshbfa8c1a8e2cadf6p143008jsnd94aafc4c7a7")
	.setHeader("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
	.execute()
	.toCompletableFuture()
	.thenAccept(System.out::println)
	.join();

client.close();
         ```java
      - (Create/POST) Create a new like on a post
      - (Delete) Delete existing like
      - (Create/POST) Create a new comment on a post
      - (Delete) Delete existing comment
   - Get List of places
   AsyncHttpClient client = new DefaultAsyncHttpClient();
client.prepare("GET", "https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/autosuggest/v1.0/UK/GBP/en-GB/?query=Stockholm")
	.setHeader("x-rapidapi-key", "7b4dfa76b8mshbfa8c1a8e2cadf6p143008jsnd94aafc4c7a7")
	.setHeader("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
	.execute()
	.toCompletableFuture()
	.thenAccept(System.out::println)
	.join();

client.close();


##### Skyscanner API
- Base URL - https://rapidapi.com/skyscanner/api

   HTTP Verb | Endpoint | Description
   ----------|----------|------------
    `GET`    | /cities | gets all cities
    `GET`    | /cities/byId/:id | gets specific city by :id
    `GET`    | /continents | gets all continents
    `GET`    | /continents/byId/:id | gets specific continent by :id
    `GET`    | /regions | gets all regions
    `GET`    | /regions/byId/:id | gets specific region by :id
    
    
    https://imgur.com/a/VtIK7Dw
