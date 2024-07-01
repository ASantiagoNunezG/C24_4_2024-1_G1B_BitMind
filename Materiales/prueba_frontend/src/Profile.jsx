import React, { useState, useEffect } from 'react';

const Profile = ({ token }) => {
  const [profile, setProfile] = useState(null);

  const fetchProfile = async () => {
    if (!token) {
      console.error('Token no disponible.');
      return;
    }

    try {
      const response = await fetch('http://localhost:8080/perfil', {
        method: 'GET',
        headers: {
          'Authorization': `Bearer ${token}`
        }
      });

      if (response.ok) {
        const result = await response.json();
        setProfile(result);
        console.log('Perfil obtenido:', result);
      } else {
        console.error('Error al obtener el perfil');
      }
    } catch (error) {
      console.error('Error en la solicitud:', error);
    }
  };

  useEffect(() => {
    fetchProfile();
  }, [token]);

  return (
    <div>
      <h2>Perfil del Usuario</h2>
      {profile ? (
        <div>
          <p>Nombres: {profile.nombres}</p>
          <p>Correo: {profile.correo}</p>
          {/* Agrega más campos según sea necesario */}
        </div>
      ) : (
        <p>Cargando perfil...</p>
      )}
    </div>
  );
};

export default Profile;
