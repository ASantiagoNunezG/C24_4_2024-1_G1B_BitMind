import PropTypes from 'prop-types';
import { useState, useEffect } from 'react';
import { comentarioscreate, comentariosupdate } from '../../services/Comentario/api';
import { usuariosObtener } from '../../services/Usuario/api';
import { forosObtener } from '../../services/Foro/api';

const formatDateTimeLocal = (dateString) => {
    if (!dateString) return '';
    const date = new Date(dateString);
    const offset = date.getTimezoneOffset();
    const adjustedDate = new Date(date.getTime() - offset * 60 * 1000);
    return adjustedDate.toISOString().slice(0, 16);
};

export const ComentarioForm = ({ comentario, onClose, onSave }) => {
    const [contenido, setContenido] = useState('');
    const [fecha_creacion, setFechaCreacion] = useState('');
    const [id_usuario, setIdUsuario] = useState('');
    const [id_foro, setIdForo] = useState('');
    const [usuarios, setUsuarios] = useState([]);
    const [foros, setForos] = useState([]);

    useEffect(() => {
        const fetchUsuarios = async () => {
            try {
                const data = await usuariosObtener();
                setUsuarios(data);
            } catch (error) {
                console.error('Error fetching usuarios:', error);
            }
        };

        const fetchForos = async () => {
            try {
                const data = await forosObtener();
                setForos(data);
            } catch (error) {
                console.error('Error fetching foros:', error);
            }
        };

        fetchUsuarios();
        fetchForos();

        if (comentario) {
            setContenido(comentario.contenido);
            setFechaCreacion(formatDateTimeLocal(comentario.fecha_creacion));
            setIdUsuario(comentario.id_usuario);
            setIdForo(comentario.id_foro);
        }
    }, [comentario]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        const formData = new FormData();
        formData.append('contenido', contenido);
        formData.append('fecha_creacion', new Date(fecha_creacion).toISOString());
        formData.append('id_usuario', id_usuario);
        formData.append('id_foro', id_foro);

        try {
            if (comentario && comentario.id_comentario) {
                await comentariosupdate(formData, comentario.id_comentario);
            } else {
                await comentarioscreate(formData);
            }
            onSave();
            onClose();
        } catch (error) {
            console.error(
                'Error saving comentario:',
                error.response ? error.response.data : error.message
            );
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <div className="mb-4">
                <label
                    className="block text-gray-700 text-sm font-bold mb-2"
                    htmlFor="contenido"
                >
                    Contenido
                </label>
                <input
                    className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    id="contenido"
                    type="text"
                    value={contenido}
                    onChange={(e) => setContenido(e.target.value)}
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
                    htmlFor="id_foro"
                >
                    Foro
                </label>
                <select
                    className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    id="id_foro"
                    value={id_foro}
                    onChange={(e) => setIdForo(e.target.value)}
                >
                    <option value="">Seleccione un foro</option>
                    {foros.map((foro) => (
                        <option key={foro.id_foro} value={foro.id_foro}>
                            {foro.titulo}
                        </option>
                    ))}
                </select>
            </div>
            <div className="flex items-center justify-between">
                <button
                    className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                    type="submit"
                >
                    {comentario ? 'Actualizar' : 'Crear'}
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

ComentarioForm.propTypes = {
    comentario: PropTypes.shape({
        id_comentario: PropTypes.number,
        contenido: PropTypes.string,
        fecha_creacion: PropTypes.string,
        id_usuario: PropTypes.number,
        id_foro: PropTypes.number,
    }),
    onClose: PropTypes.func.isRequired,
    onSave: PropTypes.func.isRequired,
};
