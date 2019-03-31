To build & run the application follow below steps.

Download the git repository.
Go to vrs folder
Run below commands:
mvn clean install
java -server -jar ./vacation-rental-app/target/vacation-rental-app-0.0.1-SNAPSHOT.jar server ./config/vrs.yml


This will start the application.
For list of APIs find below details.
Postman link - https://www.getpostman.com/collections/dd7d0b1d67202eaa1628

POST    /vrs/listings (com.expedia.vrs.resource.VRSResource)
DELETE  /vrs/listings/{propertyId} (com.expedia.vrs.resource.VRSResource)
GET     /vrs/listings/{propertyId} (com.expedia.vrs.resource.VRSResource)
PUT     /vrs/listings/{propertyId} (com.expedia.vrs.resource.VRSResource)

curl -X POST \
  http://localhost:8080/vrs/listings \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 2da7d578-39c3-09f2-69ef-2706ec21e9cf' \
  -d '{
    "contact": {
      "phone": "15126841100",
      "formattedPhone": "+1 512-684-1100"
    },
    "address": {
      "address": "1011 W 5th St",
      "postalCode": "1011",
      "countryCode": "US",
      "city": "Austin",
      "state": "TX",
      "country": "United States"
    },
    "location": {
      "lat": 40.4255485534668,
      "lng": -3.7075681686401367
    }
  }
'


curl -X GET \
  http://localhost:8080/vrs/listings/e5d20ab1-f757-46ce-a947-0dd8084c0adb \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: d2d77d2f-477b-6be0-b467-cfcea3f6fc49'


curl -X PUT \
  http://localhost:8080/vrs/listings/e5d20ab1-f757-46ce-a947-0dd8084c0adb \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 1832fac1-b60f-f466-1433-dae9012e875f' \
  -d '{
    "contact": {
        "phone": "9028827214",
        "formattedPhone": "+1 512-684-1100"
    },
    "address": {
        "address": "1011 W 5th St",
        "postalCode": "1011",
        "countryCode": "US",
        "city": "Austin",
        "state": "TX",
        "country": "United States"
    },
    "location": {
        "lat": 40.4255485534668,
        "lng": -3.7075681686401367
    }
}'


curl -X DELETE \
  http://localhost:8080/vrs/listings/e5d20ab1-f757-46ce-a947-0dd8084c0adb \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: 345d8716-a85b-0746-d955-2c7192da3d75'