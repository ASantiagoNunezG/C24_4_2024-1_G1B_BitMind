// App.jsx
import { BrowserRouter, Routes, Route, Navigate, useLocation } from 'react-router-dom';
import { Login } from './pages/Login.jsx';
import { Navbar, NavbarItem } from './components/Navbar.jsx';
import { LifeBuoy, User, Medal, PersonStanding, Trophy, MessageCircle, GraduationCap, Microscope, BookMarked, Megaphone, ArchiveIcon, MessageCircleMore, HardDrive, Star } from 'lucide-react';
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
      <div className="flex items-center justify-center py-4 bg-gray-800">
        <h1 className="text-4xl font-bold text-white tracking-wider">
          PANEL DEL ADMINISTRADOR
        </h1>
      </div>

      <Navbar>

        <NavbarItem icon={<User size={20} />} text="Gestión de Usuarios" to="/Usuarios" />
        <NavbarItem icon={<Megaphone size={20} />} text="Gestión de Anuncios" to="/Anuncios" />
        <NavbarItem icon={<HardDrive size={20} />} text="Gestión de Publicaciones" to="/Publicaciones" />
        <NavbarItem icon={<ArchiveIcon size={20} />} text="Gestión de Archivos" to="/Archivos" />
        <NavbarItem icon={<Star size={20} />} text="Gestión de Valoracion" to="/Valoracion" />
        <NavbarItem icon={<MessageCircleMore size={20} />} text="Gestión de Foros" to="/Foros" />
        <NavbarItem icon={<MessageCircle size={20} />} text="Gestión de Comentarios" to="/Comentarios" />
        <NavbarItem icon={<Microscope size={20} />} text="Gestión de Cursos" to="/Cursos" />
        <NavbarItem icon={<BookMarked size={20} />} text="Gestión de Ciclos" to="/Ciclos" />
        <NavbarItem icon={<GraduationCap size={20} />} text="Gestión de Carreras" to="/Carreras" />
      </Navbar>
      <main className="content">
        <Routes>
          <Route path="/" element={<Navigate to="/login" />} />
          <Route path="/login" element={<Login />} />
          <Route path="/Usuarios" element={<Usuario />} />
          <Route path="/Comentarios" element={<Comentario />} />
          <Route path="/Publicaciones" element={<Publicacion />} />
          <Route path="/Archivos" element={<Archivo />} />
          <Route path="/Valoracion" element={<Valoracion />} />
          <Route path="/Carreras" element={<Carrera />} />
          <Route path="/Ciclos" element={<Ciclo />} />
          <Route path="/Cursos" element={<Curso />} />
          <Route path="/Foros" element={<Foro />} />
          <Route path="/Anuncios" element={<Anuncio />} />
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
