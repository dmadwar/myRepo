Running through IDE
----------------------------------

1. Right click on project in sts and run as spring boot application

2. Request to be sent in postman as below to add shops
Longitude and latitude will be used from geo rest call.
Http Method selected - POST
URL to hit - http://localhost:8080/shops/
Header to be added - Content-type application/json

{"shopName":"Dmart","shopAddress":{"number":1,"postCode":"TW136AY"}} - London postcode
{"shopName":"MyShop","shopAddress":{"number":1,"postCode":"51403"}} - poland postcode
{"shopName":"Tesco","shopAddress":{"number":1,"postCode":"TW136DZ"}}- London postcode
{"shopName":"Walmart","shopAddress":{"number":1,"postCode":"TW136BS"}}- London postcode

3. Get the shop within the radius of 5 Km of the given longitude and latitude.
5 is configurable in application.properties.

http://localhost:8080/shops?longitude= -0.3862915&latitude=51.4394099

Response will be returned 3 shops which are in london and have nearby postcodes.


Build the application
-------------------------------------
mvn clean install

Improvements can be done and pending
-----------------------------------------
Used jdk1.8 , currently have 1.7 on the machine using
Improved testing to test more scenarios
To make production deployment ready