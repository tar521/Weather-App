import React from "react";
import { Route, Routes } from "react-router-dom";
import Home from "./components/Home";
import Login from "./components/Login";
import NotFound from "./components/NotFound";
import Settings from "./components/Settings";
import AuthenticatedRoute from "./components/AuthenticatedRoute";
import UnauthenticatedRoute from "./components/UnauthenticatedRoute";

export default function Links() {
  return (
    <Routes>
    <Route path="/" element={<Home />} />
    <Route path="/login" element={
      <UnauthenticatedRoute>
        <Login />
      </UnauthenticatedRoute>
    }
    />
    <Route path="/settings" element={
      <AuthenticatedRoute>
        <Settings />
      </AuthenticatedRoute>
    } 
    />
    <Route path="*" element={<NotFound />} />;

    
  </Routes>
  );
}

