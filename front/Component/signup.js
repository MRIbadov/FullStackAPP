// src/components/Signup.js
import React, { useState } from "react";
import { registerUser } from "../services/Service";
import { useNavigate } from "react-router-dom";
import "../style.css"; // Import your CSS file

const Signup = () => {
  const [userData, setUserData] = useState({ username: "", email: "", password: "" });
  const navigate = useNavigate();

  const handleSignup = async (e) => {
    e.preventDefault();
    try {
      await registerUser(userData);
      alert("Signup successful! Please login.");
      navigate("/login");
    } catch (error) {
      alert("Signup failed!");
    }
  };

  return (
    <div className="container">
      <form onSubmit={handleSignup}>
        <h2>Signup</h2>
        <input
          type="text"
          placeholder="Username"
          onChange={(e) => setUserData({ ...userData, username: e.target.value })}
          value={userData.username}
        />
        <input
          type="email"
          placeholder="Email"
          onChange={(e) => setUserData({ ...userData, email: e.target.value })}
          value={userData.email}
        />
        <input
          type="password"
          placeholder="Password"
          onChange={(e) => setUserData({ ...userData, password: e.target.value })}
          value={userData.password}
        />
        <button type="submit">Signup</button>
      </form>
      <p>
        Already have an account? <a href="/login">Login here</a>
      </p>
    </div>
  );
};

export default Signup;
