import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { onError } from "../lib/errorLib";

export default function Settings() {
  const nav = useNavigate();
  const [isLoading, setIsLoading] = useState(false);

//   function billUser(details) {
//     return API.post("notes", "/billing", {
//       body: details,
//     });
//   }

  return <div className="Settings"></div>;
}