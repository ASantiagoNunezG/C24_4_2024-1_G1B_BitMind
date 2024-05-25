import React from 'react';
import './inicio.css'; // Si necesitas agregar estilos personalizados

function Home() {
  return (
    <div className="home">
      <header className="bg-dark text-white text-center py-5">
        <div className="container">
          <h1 className="display-4">Bienvenido a BitMind</h1>
          <p className="lead">Aprende con ritmo y mejora tus habilidades</p>
          <a href="/registro" className="btn btn-primary btn-lg mt-3">Registrarse</a>
        </div>
      </header>
      <section className="features py-5">
        <div className="container">
          <div className="row">
            <div className="col-md-4">
              <div className="card mb-4 shadow-sm">
                <div className="card-body">
                  <h5 className="card-title">Cursos en línea</h5>
                  <p className="card-text">Accede a una variedad de cursos en línea y aprende a tu propio ritmo.</p>
                </div>
              </div>
            </div>
            <div className="col-md-4">
              <div className="card mb-4 shadow-sm">
                <div className="card-body">
                  <h5 className="card-title">Foros de discusión</h5>
                  <p className="card-text">Únete a la comunidad y discute temas interesantes con otros miembros.</p>
                </div>
              </div>
            </div>
            <div className="col-md-4">
              <div className="card mb-4 shadow-sm">
                <div className="card-body">
                  <h5 className="card-title">Recursos gratuitos</h5>
                  <p className="card-text">Aprovecha nuestros recursos gratuitos para mejorar tus habilidades.</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
      <section className="testimonials py-5 bg-light">
        <div className="container">
          <h2 className="text-center mb-4">Testimonios</h2>
          <div className="row">
            <div className="col-md-4">
              <blockquote className="blockquote">
                <p className="mb-0">"BitMind ha transformado la forma en que aprendo. ¡Es increíble!"</p><br/>
                <footer className="blockquote-footer">Juan Pérez, <cite title="Source Title">Estudiante</cite></footer>
              </blockquote>
            </div>
            <div className="col-md-4">
              <blockquote className="blockquote">
                <p className="mb-0">"Los recursos gratuitos son de gran ayuda para mejorar mis habilidades."</p><br/>
                <footer className="blockquote-footer">María López, <cite title="Source Title">Profesional</cite></footer>
              </blockquote>
            </div>
            <div className="col-md-4">
              <blockquote className="blockquote">
                <p className="mb-0">"La comunidad de BitMind es muy acogedora y colaborativa."</p><br/>
                <footer className="blockquote-footer">Carlos García, <cite title="Source Title">Desarrollador</cite></footer>
              </blockquote>
            </div>
          </div>
        </div>
      </section>
      <section className="team py-5">
        <div className="container">
          <h2 className="text-center mb-4">Nuestro Equipo</h2>
          <div className="row">
            <div className="col-md-3">
              <div className="card mb-4 shadow-sm">
                <img src="path/to/image1.jpg" className="card-img-top" alt="Member 1" />
                <div className="card-body">
                  <h5 className="card-title">Ana Martínez</h5>
                  <p className="card-text">CEO y Fundadora</p>
                </div>
              </div>
            </div>
            <div className="col-md-3">
              <div className="card mb-4 shadow-sm">
                <img src="path/to/image2.jpg" className="card-img-top" alt="Member 2" />
                <div className="card-body">
                  <h5 className="card-title">Luis Fernández</h5>
                  <p className="card-text">CTO</p>
                </div>
              </div>
            </div>
            <div className="col-md-3">
              <div className="card mb-4 shadow-sm">
                <img src="path/to/image3.jpg" className="card-img-top" alt="Member 3" />
                <div className="card-body">
                  <h5 className="card-title">Claudia Gómez</h5>
                  <p className="card-text">Directora de Marketing</p>
                </div>
              </div>
            </div>
            <div className="col-md-3">
              <div className="card mb-4 shadow-sm">
                <img src="path/to/image4.jpg" className="card-img-top" alt="Member 4" />
                <div className="card-body">
                  <h5 className="card-title">Pedro Sánchez</h5>
                  <p className="card-text">Desarrollador Principal</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
      <section className="contact py-5 bg-light">
        <div className="container">
          <h2 className="text-center mb-4">Contáctanos</h2>
          <div className="row">
            <div className="col-md-6">
              <form>
                <div className="mb-3">
                  <label htmlFor="name" className="form-label">Nombre</label>
                  <input type="text" className="form-control" id="name" />
                </div>
                <div className="mb-3">
                  <label htmlFor="email" className="form-label">Correo Electrónico</label>
                  <input type="email" className="form-control" id="email" />
                </div>
                <div className="mb-3">
                  <label htmlFor="message" className="form-label">Mensaje</label>
                  <textarea className="form-control" id="message" rows="4"></textarea>
                </div>
                <button type="submit" className="btn btn-primary">Enviar</button>
              </form>
            </div>
            <div className="col-md-6">
              <h5>Dirección:</h5>
              <p>Calle Falsa 123, Ciudad, País</p>
              <h5>Teléfono:</h5>
              <p>+123 456 7890</p>
              <h5>Email:</h5>
              <p>info@bitmind.com</p>
            </div>
          </div>
        </div>
      </section>

    </div>
  );
}

export default Home;
