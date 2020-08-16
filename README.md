# ApiCodingChallenge

Using the language of your choice please build your own API which calls the API at https://bpdts-test-app.herokuapp.com/, and returns people who are listed as either living in London, or whose current coordinates are within 50 miles of London.

API built has 2 endpoints /api/usersListedLivingLondon which returns just users listed as living within london. 
The second endpoint is /api/usersWithin50Miles this returns users with latitude and longitude within 50 miles of Central London calculated using the haversine formula. 
