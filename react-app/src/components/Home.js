import React, {useState, useEffect} from 'react'
import { useAppContext } from "../lib/contextLib";

const Home = () => {

  

  const { isAuthenticated, token } = useAppContext();
  const [ response, setResponse ] = useState([]);

  function printAuth() {
    return ("" + isAuthenticated.toString() + " " + token.jwt.toString());
  }

  useEffect(()=>{

    handleButton()

  },[])

  async function fetchData() {
    return fetch('http://api.weatherapi.com/v1/forecast.json?key=96f72f7e3db14c98b6c184128231309&q=50312&days=1&aqi=no&alerts=no', {
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
      console.log(response.current.temp_c.toString())
      // return (
      //   <pre>{JSON.stringify(response, null, 2)}</pre>
      // );
    } catch (e) {
      console.log(e);
    }
  }

  return (
    <div>
      <h1>Home</h1>
      {/* <p>{printAuth()}</p> */}
      {/* <button onClick="handleButton()">Click!</button> */}
      <div>{JSON.stringify(response, null, 2)}</div>
      </div>

  )
}

export default Home