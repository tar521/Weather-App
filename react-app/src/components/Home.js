import React, {useState, useEffect} from 'react'
import { useAppContext } from "../lib/contextLib";
import "./Home.css";

const Home = () => {

  const { isAuthenticated, token } = useAppContext();
  const { firstLoad, setFirstLoad} = useState(true);
  const [ response, setResponse ] = useState([]);
  const [ forecast, setForcast] = useState([]);
  const [ hourly, setHourly] = useState([]);
  const [ uri, setUri] = useState("");

  useEffect(()=>{
    if (firstLoad) {
      const fjson = require('../secrets/secrets.json');
      setUri("http://api.weatherapi.com/v1/forecast.json?key=" + fjson.weather_api_key + "&q=50312&days=1&aqi=no&alerts=no")
    }
    if (isAuthenticated) {
      handleButton()
    }

  },[])

  async function fetchData() {
    return fetch(uri, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
    })
    .then(data =>  {
     if (data.ok) {
       return data.json();
     }
     throw new Error("WRONG USERNAME AND PASSWORD");
   })
    .catch((error) => {
     console.log(error);
   });
  }

  async function handleButton() {
    //event.preventDefault();

    try {
      const response1 = await fetchData();
      setResponse(response1)
      setForcast(response1.forecast.forecastday)
      setHourly(response1.forecast.forecastday[0].hour)
      console.log(response.current.temp_c.toString())
    } catch (e) {
      console.log(e);
    }
  }

  function renderLander() {
    return (
      <div className="lander">
        <h1>Weather Dashboard</h1>
        <p className="text-muted">A personalized weather application</p>
        <br/>
        <br/>
        <p>Please Login to access dashboard</p>
      </div>
    );
  }

  function renderDashboard() {
    return (
      <div>
        <div>{JSON.stringify(response, null, 2)}</div>
        <br/>
        <div>{JSON.stringify(forecast, null, 2)}</div>
        <br/>
        <div>{JSON.stringify(hourly, null, 2)}</div>
      </div>
    )
  }

  return (
    <div className="Home">
        {isAuthenticated ? renderDashboard(): renderLander()}
      </div>

  )
}

export default Home