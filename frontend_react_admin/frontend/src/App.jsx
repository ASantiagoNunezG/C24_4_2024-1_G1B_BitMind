// App.jsx
import { BrowserRouter, Routes, Route, Navigate, useLocation } from 'react-router-dom';
import { Login } from './pages/Login.jsx';
import { Navbar, NavbarItem } from './components/Navbar.jsx';
import { LifeBuoy, User, Medal, PersonStanding, Trophy } from 'lucide-react';
import { Usuario } from './pages/Usuario.jsx';
import { Comentario } from './pages/Comentario.jsx';
import { Publicacion } from './pages/Publicacion.jsx';
import { Archivo } from './pages/Archivo.jsx';
import { Valoracion } from './pages/Valoracion.jsx';
import { Carrera } from './pages/Carrera.jsx';
import { Ciclo } from './pages/Ciclo.jsx';
import { Curso } from './pages/Curso.jsx';
import { Foro } from './pages/Foro.jsx';
import { Anuncio } from './pages/Anuncio.jsx';

const App = () => {
  const location = useLocation();

  if (location.pathname === '/login') {
    return <Login />;
  }

  return (
    <div className="App">
      <Navbar>
        <NavbarItem icon={<LifeBuoy size={20} />} text="Gestión de Valoracion" to="/Valoracion" />
        <NavbarItem icon={<User size={20} />} text="Gestión de Usuarios" to="/Usuarios" />
        <NavbarItem icon={<Medal size={20} />} text="Gestión de Publicaciones" to="/Publicaciones" />
        <NavbarItem icon={<PersonStanding size={20} />} text="Gestión de Foros" to="/Foros" />
        <NavbarItem icon={<Trophy size={20} />} text="Gestión de Anuncios" to="/Anuncios" />
        <NavbarItem icon={<Trophy size={20} />} text="Gestión de Archivos" to="/Archivos" />
        <NavbarItem icon={<Trophy size={20} />} text="Gestión de Carreras" to="/Carreras" />
        <NavbarItem icon={<Trophy size={20} />} text="Gestión de Ciclos" to="/Ciclos" />
        <NavbarItem icon={<Trophy size={20} />} text="Gestión de Comentarios" to="/Comentarios" />
        <NavbarItem icon={<Trophy size={20} />} text="Gestión de Cursos" to="/Cursos" />
      </Navbar>
      <main className="content">
        <Routes>
          <Route path="/" element={<Navigate to="/login" />} />
          <Route path="/login" element={<Login />} />
          <Route path="/Usuarios" element={<Usuario/>} />
          <Route path="/Comentarios" element={<Comentario/>} />
          <Route path="/Publicaciones" element={<Publicacion/>} />
          <Route path="/Archivos" element={<Archivo/>} />
          <Route path="/Valoracion" element={<Valoracion/>} />
          <Route path="/Carreras" element={<Carrera/>} />
          <Route path="/Ciclos" element={<Ciclo/>} />
          <Route path="/Cursos" element={<Curso/>} />
          <Route path="/Foros" element={<Foro/>} />
          <Route path="/Anuncios" element={<Anuncio/>} />
          {/* Agrega más rutas según tus necesidades */}
        </Routes>
      </main>
    </div>
  );
}

export const AppWrapper = () => {
  return (
    <BrowserRouter>
      <App />
    </BrowserRouter>
  );
}

export default AppWrapper;
