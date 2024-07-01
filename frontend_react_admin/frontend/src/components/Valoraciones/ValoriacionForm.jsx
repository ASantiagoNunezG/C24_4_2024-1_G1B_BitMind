import PropTypes from 'prop-types';
import { useState, useEffect } from 'react';
import { valoracionescreate, valoracionesupdate } from '../../services/Valoracion/api';
import { usuariosObtener } from '../../services/Usuario/api';
import { publicacionesObtener } from '../../services/Publicacion/api';

export const ValoracionForm = ({ valoracion, onClose, onSave }) => {
    const [escala, setEscala] = useState('');
    const [fecha_creacion, setFechaCreacion] = useState('');
    const [id_usuario, setIdUsuario] = useState('');
    const [id_publicacion, setIdPublicacion] = useState('');
    const [usuarios, setUsuarios] = useState([]);
    const [publicaciones, setPublicaciones] = useState([]);

    useEffect(() => {
        const fetchUsuarios = async () => {
            try {
                const data = await usuariosObtener();
                setUsuarios(data);
            } catch (error) {
                console.error('Error fetching usuarios:', error);
            }
        };

        const fetchPublicaciones = async () => {
            try {
                const data = await publicacionesObtener();
                setPublicaciones(data);
            } catch (error) {
                console.error('Error fetching publicaciones:', error);
            }
        };

        fetchUsuarios();
        fetchPublicaciones();

        if (valoracion) {
            setEscala(valoracion.escala);
            setFechaCreacion(valoracion.fecha_creacion ? valoracion.fecha_creacion.substring(0, 16) : '');
            setIdUsuario(valoracion.id_usuario);
            setIdPublicacion(valoracion.id_publicacion);
        }
    }, [valoracion]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        const formData = {
            escala,
            fecha_creacion: fecha_creacion + ':00Z',  // Formato de fecha adecuado
            id_usuario,
            id_publicacion
        };

        try {
            if (valoracion && valoracion.id_valoracion) {
                await valoracionesupdate(formData, valoracion.id_valoracion);
            } else {
                await valoracionescreate(formData);
            }
            onSave();
            onClose();
        } catch (error) {
            console.error(
                'Error saving valoracion:',
                error.response ? error.response.data : error.message
            );
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <div className="mb-4">
                <label
                    className="block text-gray-700 text-sm font-bold mb-2"
                    htmlFor="escala"
                >
                    Escala
                </label>
                <input
                    className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    id="escala"
                    type="number"
                    value={escala}
                    onChange={(e) => setEscala(e.target.value)}
                />
            </div>
            <div className="mb-4">
                <label
                    className="block text-gray-700 text-sm font-bold mb-2"
                    htmlFor="fecha_creacion"
                >
                    Fecha de Creación
                </label>
                <input
                    className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    id="fecha_creacion"
                    type="datetime-local"
                    value={fecha_creacion}
                    onChange={(e) => setFechaCreacion(e.target.value)}
                />
            </div>
            <div className="mb-4">
                <label
                    className="block text-gray-700 text-sm font-bold mb-2"
                    htmlFor="id_usuario"
                >
                    Usuario
                </label>
                <select
                    className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    id="id_usuario"
                    value={id_usuario}
                    onChange={(e) => setIdUsuario(e.target.value)}
                >
                    <option value="">Seleccione un usuario</option>
                    {usuarios.map((usuario) => (
                        <option key={usuario.id_usuario} value={usuario.id_usuario}>
                            {usuario.nombres}
                        </option>
                    ))}
                </select>
            </div>
            <div className="mb-4">
                <label
                    className="block text-gray-700 text-sm font-bold mb-2"
                    htmlFor="id_publicacion"
                >
                    Publicación
                </label>
                <select
                    className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    id="id_publicacion"
                    value={id_publicacion}
                    onChange={(e) => setIdPublicacion(e.target.value)}
                >
                    <option value="">Seleccione una publicación</option>
                    {publicaciones.map((publicacion) => (
                        <option key={publicacion.id_publicacion} value={publicacion.id_publicacion}>
                            {publicacion.titulo}
                        </option>
                    ))}
                </select>
            </div>
            <div className="flex items-center justify-between">
                <button
                    className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                    type="submit"
                >
                    {valoracion ? 'Actualizar' : 'Crear'}
                </button>
                <button
                    className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                    type="button"
                    onClick={onClose}
                >
                    Cancelar
                </button>
            </div>
        </form>
    );
};

ValoracionForm.propTypes = {
    valoracion: PropTypes.shape({
        id_valoracion: PropTypes.number,
        escala: PropTypes.number,
        fecha_creacion: PropTypes.string,
        id_usuario: PropTypes.number,
        id_publicacion: PropTypes.number,
    }),
    onClose: PropTypes.func.isRequired,
    onSave: PropTypes.func.isRequired,
};
