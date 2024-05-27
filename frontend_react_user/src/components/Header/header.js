import React from 'react';
import { Link } from 'react-router-dom'; // Importa Link de react-router-dom
import './header.css';

function Header() {
  return (
    <nav className="navbar navbar-expand-lg">
      <div className="container">
        <a className="navbar-brand text-white" href="#">BITMIND</a>
        <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse justify-content-end" id="navbarNav">
          <ul className="navbar-nav">
            <li className="nav-item">
              <Link className="nav-link" to="/">INICIO</Link>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="#">ARCHIVOS</a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="#">FORO</a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="#">CONTÁCTANOS</a>
            </li>
            <li className="nav-item">
            <Link className="btn btn-outline-success me-2" to="/login">Acceso</Link>
            </li>
            <li className="nav-item">
              <Link className="btn btn-outline-success" to="/registro">Registrarse</Link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
}

export default Header;
