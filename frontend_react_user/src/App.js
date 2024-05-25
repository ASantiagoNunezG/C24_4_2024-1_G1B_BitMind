import React from 'react';
import './App.css'; // Importa tus estilos CSS globales
import Header from './components/Header/header';
import Footer from './components/Footer/footer';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home/inicio';
import Login from './pages/Login/login';
import Registro from './pages/Registro/registro';

function App() {
  return (
    <Router>
      <Header />
      <div id="root">
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/registro" element={<Registro />} />
          <Route path="/" element={<Home />} />
        </Routes>
      </div>
      <Footer />
    </Router>
  );
}

export default App;

