import PropTypes from 'prop-types';
import { useState, useEffect } from 'react';
import { foroscreate, forosupdate } from '../../services/Foro/api';
import { usuariosObtener } from '../../services/Usuario/api';
import { format } from 'date-fns';

export const ForoForm = ({ foro, onClose, onSave }) => {
    const [titulo, setTitulo] = useState('');
    const [contenido, setContenido] = useState('');
    const [fecha_creacion, setFechaCreacion] = useState('');
    const [id_usuario, setIdUsuario] = useState('');
    const [usuarios, setUsuarios] = useState([]);

    useEffect(() => {
        const fetchUsuarios = async () => {
            try {
                const data = await usuariosObtener();
                setUsuarios(data);
            } catch (error) {
                console.error('Error fetching usuarios:', error);
            }
        };

        fetchUsuarios();

        if (foro) {
            setTitulo(foro.titulo);
            setContenido(foro.contenido);
            setFechaCreacion(format(new Date(foro.fecha_creacion), "yyyy-MM-dd'T'HH:mm"));
            setIdUsuario(foro.id_usuario);
        }
    }, [foro]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        const formData = { titulo, contenido, fecha_creacion, id_usuario };

        try {
            if (foro && foro.id_foro) {
                await forosupdate(formData, foro.id_foro); // Pass id_foro correctly
            } else {
                await foroscreate(formData);
            }
            onSave();
            onClose();
        } catch (error) {
            console.error(
                'Error saving foro:',
                error.response ? error.response.data : error.message
            );
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <div className="mb-4">
                <label
                    className="block text-gray-700 text-sm font-bold mb-2"
                    htmlFor="titulo"
                >
                    Título
                </label>
                <input
                    className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    id="titulo"
                    type="text"
                    value={titulo}
                    onChange={(e) => setTitulo(e.target.value)}
                />
            </div>
            <div className="mb-4">
                <label
                    className="block text-gray-700 text-sm font-bold mb-2"
                    htmlFor="contenido"
                >
                    Contenido
                </label>
                <textarea
                    className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    id="contenido"
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
            <div className="flex items-center justify-between">
                <button
                    className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                    type="submit"
                >
                    {foro ? 'Actualizar' : 'Crear'}
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

ForoForm.propTypes = {
    foro: PropTypes.shape({
        id_foro: PropTypes.number,
        titulo: PropTypes.string,
        contenido: PropTypes.string,
        fecha_creacion: PropTypes.string,
        id_usuario: PropTypes.number,
    }),
    onClose: PropTypes.func.isRequired,
    onSave: PropTypes.func.isRequired,
};
