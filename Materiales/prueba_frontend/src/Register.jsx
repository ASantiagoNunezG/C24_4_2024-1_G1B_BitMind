import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const Register = ({ setToken }) => {
  const [nombres, setNombres] = useState('');
  const [correo, setCorreo] = useState('');
  const [clave, setClave] = useState('');
  const navigate = useNavigate();

  const handleRegister = async (e) => {
    e.preventDefault();

    const userData = {
      nombres,
      correo,
      clave
    };

    try {
      const response = await fetch('http://localhost:8080/nuevo', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(userData)
      });

      if (response.ok) {
        const result = await response.text();
        console.log('Usuario registrado:', result);
        handleLogin();
      } else {
        console.error('Error al registrar el usuario');
      }
    } catch (error) {
      console.error('Error en la solicitud:', error);
    }
  };

  const handleLogin = async () => {
    try {
      const response = await fetch('http://localhost:8080/nuevoToken', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ correo, clave })
      });

      if (response.ok) {
        const token = await response.text();
        localStorage.setItem('token', token);
        setToken(token);
        navigate('/perfil');
        console.log('Token recibido:', token);
      } else {
        console.error('Error al obtener el token');
      }
    } catch (error) {
      console.error('Error en la solicitud:', error);
    }
  };

  return (
    <div>
      <h2>Registrar Usuario</h2>
      <form onSubmit={handleRegister}>
        <div>
          <label>Nombres:</label>
          <input
            type="text"
            value={nombres}
            onChange={(e) => setNombres(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Correo:</label>
          <input
            type="email"
            value={correo}
            onChange={(e) => setCorreo(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Clave:</label>
          <input
            type="password"
            value={clave}
            onChange={(e) => setClave(e.target.value)}
            required
          />
        </div>
        <button type="submit">Registrar</button>
      </form>
    </div>
  );
};

export default Register;
