# Timbers API
tbd

## Example REST Requests
```
curl -X POST http://localhost:8080/players -d '{"lastName": "Chara", "firstName": "Diego", "playerNumber": 21}' -H "Content-Type: application/json" -v
curl -X GET http://localhost:8080/players/Chara/Diego -H "Accepts: application/json" -v
```