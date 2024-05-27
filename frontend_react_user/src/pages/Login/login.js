// Login.js
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import './login.css'; // Asegúrate de tener este archivo CSS para los estilos del formulario

function Login() {
  const [formData, setFormData] = useState({
    email: '',
    password: ''
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Aquí puedes enviar los datos del formulario a través de una solicitud HTTP
    console.log(formData);
  };

  return (
    <div className="container">
      <div className="row justify-content-center mt-5 mb-5">
        <div className="col-md-4">
          <div className="login-container">
            <form onSubmit={handleSubmit}>
              <h3 className="text-center">Iniciar Sesión</h3>
              <div className="form-group">
                <label htmlFor="email">Correo electrónico: *</label>
                <input
                  type="email"
                  id="email"
                  name="email"
                  value={formData.email}
                  onChange={handleChange}
                  className="form-control"
                  required
                />
              </div>
              <div className="form-group">
                <label htmlFor="password">Contraseña: *</label>
                <input
                  type="password"
                  id="password"
                  name="password"
                  value={formData.password}
                  onChange={handleChange}
                  className="form-control"
                  required
                />
              </div>
              <button type="submit" className="btn btn-primary mt-3 text-center">Iniciar Sesión</button>
            </form>
            <p className='mt-3 text-center'>¿No tienes una cuenta? <Link to="/registro">Registrarse</Link></p>
            <p className='text-center'>---------- O inicia sesión con ----------</p>
          </div>
        </div>
        <div className="col-md-4">
          <div className="image-container">
            <img src="https://via.placeholder.com/400x500" alt="login-image"/>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Login;


