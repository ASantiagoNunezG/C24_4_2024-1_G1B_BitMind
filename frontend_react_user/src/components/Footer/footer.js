import React from 'react';
import './footer.css';

function Footer() {
  return (
    <footer className="bg-dark text-white text-center py-4">
    <div className="container">
      <p>&copy; 2024 BitMind. Todos los derechos reservados.</p>
      <p>
        <a href="/privacidad" className="text-white">Política de Privacidad</a> | <a href="/terminos" className="text-white">Términos de Servicio</a>
      </p>
    </div>
  </footer>
  );
}

export default Footer;
