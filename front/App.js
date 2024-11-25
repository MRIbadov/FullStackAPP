// src/App.js
import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Login from "./Component/login";
import Signup from "./Component/signup";
import Dashboard from "./Component/Dashboard";

function App() {
  return (
    <Router> {/* Wrapping the whole app with Router to enable routing */}
      <div className="container">
        <header>
          <h1>Welcome to My React App</h1>
        </header>

        <Routes> {/* Define all routes here */}
          {/* Route for the Login Page */}
          <Route path="/login" element={<Login />} />

          {/* Route for the Signup Page */}
          <Route path="/signup" element={<Signup />} />

          {/* Route for the Dashboard Page */}
          <Route path="/dashboard" element={<Dashboard />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
