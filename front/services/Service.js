import axios from 'axios';

const API_URL = "http://localhost:8080/api";

// Login User
export const loginUser = async (username, password) => {
  const response = await axios.post(`${API_URL}/user/login`, { username, password });
  return response.data;
};

// Register User
export const registerUser = async (userData) => {
  const response = await axios.post(`${API_URL}/user/signup`, userData);
  return response.data;
};

// Get Total Income for a User
export const getTotalIncome = async (userId) => {
  const response = await axios.get(`${API_URL}/income/user/${userId}`);
  return response.data;
};

// Add Income
export const addIncome = async (incomeData) => {
  const response = await axios.post(`${API_URL}/income`, incomeData); 
  return response.data;
};

// Get User's Income List
export const getUserIncome = async (userId) => {
  const response = await axios.get(`${API_URL}/income/user/${userId}`);
  return response.data;
};

// Add Expense
export const addExpense = async (expenseData) => {
  try {
    const response = await axios.post(`${API_URL}/expense`, expenseData);
    return response.data;
  } catch (error) {
    console.error("Error adding expense:", error);
    throw error;
  }
};

// Get All Expenses for a Specific User
export const getUserExpenses = async (userId) => {
  try {
    const response = await axios.get(`${API_URL}/expense/${userId}`);
    
    // If the response is an object (i.e., a single expense), wrap it in an array
    if (response.data && !Array.isArray(response.data)) {
      return [response.data];  // Wrap single expense in an array
    }

    return response.data;  // Return the array if it's already an array
  } catch (error) {
    console.error("Error fetching user expenses:", error);
    throw error;
  }
};

// Get Total Expenses for a Specific User
export const getTotalExpenses = async (userId) => {
  try {
    const response = await axios.get(`${API_URL}/expense/total/${userId}`);
    return response.data;
  } catch (error) {
    console.error("Error fetching total expenses:", error);
    throw error;
  }
};



// const fetchIncomeList = async () => {
//   try {
//     const response = await axios.get(`${API_URL}/expense/all`); // or wherever your backend is running
//     setIncomeList(response.data);
//   } catch (error) {
//     console.error("Error fetching income list:", error);
//   }
// };