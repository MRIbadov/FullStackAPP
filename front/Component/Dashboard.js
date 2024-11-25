import React, { useState, useEffect } from "react";
import { addIncome, getTotalIncome, getUserIncome, addExpense, getTotalExpenses, getUserExpenses } from "../services/Service";

const Dashboard = () => {
  const userId = localStorage.getItem("userId"); // Retrieve userId from localStorage
  const [incomeList, setIncomeList] = useState([]); // Initialize income list state
  const [expenseList, setExpenseList] = useState([]); // Initialize expense list state
  const [totalIncome, setTotalIncome] = useState(0);
  const [totalExpenses, setTotalExpenses] = useState(0);
  const [balance, setBalance] = useState(0); // New state for balance
  const [incomeData, setIncomeData] = useState({ amount: "", source: "", date: "" });
  const [expenseData, setExpenseData] = useState({ title: "", category: "", amount: "", local_date: "" });

  useEffect(() => {
    // Fetch income and expense data when the component mounts
    fetchIncomes();
    fetchExpenses();
    fetchTotalIncome();
    fetchTotalExpenses();
  }, [userId]);

  // Fetch all incomes for the user
  const fetchIncomes = async () => {
    try {
      const incomes = await getUserIncome(userId);
      console.log("Fetched Incomes:", incomes);  // Log the incomes to verify
      if (Array.isArray(incomes)) {
        setIncomeList(incomes); // Set income data into state
      } else {
        console.error("Incomes data is not an array:", incomes);
        setIncomeList([]); // Fallback to empty array if the response is not valid
      }
    } catch (error) {
      console.error("Error fetching incomes:", error);
    }
  };

  // Fetch all expenses for the user
  const fetchExpenses = async () => {
    try {
      const expenses = await getUserExpenses(userId);
      console.log("Fetched Expenses:", expenses);  // Log the expenses to verify
      if (Array.isArray(expenses)) {
        setExpenseList(expenses); // Set the state only if it's an array
      } else {
        console.error("Fetched expenses are not an array:", expenses);
        setExpenseList([]);  // Set empty array if the response is not valid
      }
    } catch (error) {
      console.error("Error fetching expenses:", error);
      setExpenseList([]);  // Fallback to empty array if there's an error
    }
  };

  // Fetch total income for the user
  const fetchTotalIncome = async () => {
    try {
      const total = await getTotalIncome(userId);
      setTotalIncome(total);
    } catch (error) {
      console.error("Error fetching total income:", error);
    }
  };

  // Fetch total expenses for the user
  const fetchTotalExpenses = async () => {
    try {
      const total = await getTotalExpenses(userId);
      setTotalExpenses(total);
    } catch (error) {
      console.error("Error fetching total expenses:", error);
    }
  };

  // Calculate balance (Income - Expense)
  const calculateBalance = () => {
    const balance = totalIncome - totalExpenses;
    setBalance(balance);
  };

  // Handle adding income
  const handleAddIncome = async (e) => {
    e.preventDefault();
    try {
      await addIncome({ ...incomeData, userId });
      alert("Income added!");
      fetchIncomes(); // Refresh the income list
      fetchTotalIncome(); // Refresh total income
      fetchTotalExpenses(); // Refresh total expenses
      calculateBalance(); // Recalculate balance
    } catch (error) {
      console.error("Error adding income:", error);
    }
  };

  // Handle adding expense
  const handleAddExpense = async (e) => {
    e.preventDefault();
    try {
      await addExpense({ ...expenseData, userId });
      alert("Expense added!");
      fetchExpenses(); // Refresh the expense list
      fetchTotalIncome(); // Refresh total income
      fetchTotalExpenses(); // Refresh total expenses
      calculateBalance(); // Recalculate balance
    } catch (error) {
      console.error("Error adding expense:", error);
    }
  };

  useEffect(() => {
    calculateBalance(); // Recalculate balance whenever totalIncome or totalExpenses change
  }, [totalIncome, totalExpenses]);

  // Log the current state of incomeList and expenseList whenever they change
  useEffect(() => {
    console.log("Income List State:", incomeList);
    console.log("Expense List State:", expenseList);
  }, [incomeList, expenseList]);  // Run this effect when the lists are updated

  return (
    <div>
      <h2>Income and Expense Dashboard</h2>

      <h3>Total Income: ${totalIncome}</h3>
      <h3>Total Expenses: ${totalExpenses}</h3>

      <h3>Balance: ${balance}</h3> {/* Display balance */}

      {/* Add Income Form */}
      <form onSubmit={handleAddIncome}>
        <h4>Add Income</h4>
        <input
          type="number"
          placeholder="Amount"
          value={incomeData.amount}
          onChange={(e) => setIncomeData({ ...incomeData, amount: e.target.value })}
        />
        <input
          type="text"
          placeholder="Source"
          value={incomeData.source}
          onChange={(e) => setIncomeData({ ...incomeData, source: e.target.value })}
        />
        <input
          type="date"
          value={incomeData.date}
          onChange={(e) => setIncomeData({ ...incomeData, date: e.target.value })}
        />
        <button type="submit">Add Income</button>
      </form>

      {/* Add Expense Form */}
      <form onSubmit={handleAddExpense}>
        <h4>Add Expense</h4>
        <input
          type="text"
          placeholder="Title"
          value={expenseData.title}
          onChange={(e) => setExpenseData({ ...expenseData, title: e.target.value })}
        />
        <input
          type="text"
          placeholder="Category"
          value={expenseData.category}
          onChange={(e) => setExpenseData({ ...expenseData, category: e.target.value })}
        />
        <input
          type="number"
          placeholder="Amount"
          value={expenseData.amount}
          onChange={(e) => setExpenseData({ ...expenseData, amount: e.target.value })}
        />
        <input
          type="date"
          value={expenseData.local_date}
          onChange={(e) => setExpenseData({ ...expenseData, local_date: e.target.value })}
        />
        <button type="submit">Add Expense</button>
      </form>

      <h4>Your Incomes:</h4>
      <ul>
        {incomeList.length === 0 ? (
          <li>No income records available</li>
        ) : (
          incomeList.map((income) => (
            <li key={income.id}>
              {income.source}: ${income.amount} on {new Date(income.dateReceived).toLocaleDateString()}
            </li>
          ))
        )}
      </ul>

      <h4>Your Expenses:</h4>
      <ul>
        {expenseList.length === 0 ? (
          <li>No expenses available</li>
        ) : (
          expenseList.map((expense) => (
            <li key={expense.id}>
              {expense.title}: ${expense.amount} on {new Date(expense.local_date).toLocaleDateString()}
            </li>
          ))
        )}
      </ul>
    </div>
  );
};

export default Dashboard;
