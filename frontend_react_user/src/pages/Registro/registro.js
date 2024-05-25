import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import './registro.css'; // Asegúrate de tener este archivo CSS para los estilos del formulario

function Registro() {
  const [formData, setFormData] = useState({
    username: '',
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
      <div className="row justify-content-center mt-5 mb-5 ">
      <div className="col-md-4 ">
          <div className="image-container">
            <img src="https://via.placeholder.com/400x500" alt="learning--v1"/>
          </div>
        </div>
        <div className="col-md-4">
          
          <div className="registro-container">
            <form onSubmit={handleSubmit}>
              <h3 className="text-center">Registrarse</h3>
              <div className="form-group">
                <label htmlFor="username">Usuario: *</label>
                <input
                  type="text"
                  id="username"
                  name="username"
                  value={formData.username}
                  onChange={handleChange}
                  className="form-control"
                  required
                />
              </div>
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
              <button type="submit" className="btn btn-primary mt-3 text-center">Registrarse</button>
            </form>
            <p className='mt-3 text-center'>¿Ya tienes una cuenta? <Link to="/login">Acceso</Link></p>
            <p className='text-center'>---------- O registrate con ----------</p>
          </div>
        </div>

      </div>
    </div>
  );
}

export default Registro;
