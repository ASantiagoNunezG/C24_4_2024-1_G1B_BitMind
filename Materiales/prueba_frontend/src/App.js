import React, { useState, useEffect } from 'react';
import { Route, Routes, useNavigate } from 'react-router-dom';
import Register from './Register';
import Login from './Login';
import Profile from './Profile';
import Navbar from './Navbar';

const App = () => {
  const [token, setToken] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    const storedToken = localStorage.getItem('token');
    if (storedToken) {
      setToken(storedToken);
    }
  }, []);

  const handleLogout = () => {
    localStorage.removeItem('token');
    setToken('');
    navigate('/login');
  };

  return (
    <>
      <Navbar token={token} onLogout={handleLogout} />
      <Routes>
        <Route path="/" element={<h1>Inicio</h1>} />
        <Route path="/register" element={<Register setToken={setToken} />} />
        <Route path="/login" element={<Login setToken={setToken} />} />
        <Route path="/perfil" element={<Profile token={token} />} />
      </Routes>
    </>
  );
};

export default App;
