# Weather App

Everyone has apps that tell them the weather but how many apps make the weather fun? Our weather app uses real weather data to generate sometimes helpful/sometimes fun responses.

## Run This App Locally

This app has a React frontend, a Java backend, and a mySQL database so at a minimum you will need to be able to run these things. Additionally, you will need an api key for the weather api, [which you can get here](https://www.weatherapi.com/). You will need to put your api key in two places. The first is `Weather-App/src/main/resources/secrets.properties`. The second is `Weather-App/react-app/src/secrets/secrets.json`.