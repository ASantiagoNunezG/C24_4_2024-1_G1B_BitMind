import React from 'react';
import { Link } from 'react-router-dom';

const Navbar = ({ token, onLogout }) => {
  return (
    <nav>
      <ul>
        <li><Link to="/">Inicio</Link></li>
        {token ? (
          <>
            <li><Link to="/perfil">Perfil</Link></li>
            <li><button onClick={onLogout}>Cerrar Sesión</button></li>
          </>
        ) : (
          <>
            <li><Link to="/login">Iniciar Sesión</Link></li>
            <li><Link to="/register">Registrar</Link></li>
          </>
        )}
      </ul>
    </nav>
  );
};

export default Navbar;
