import PropTypes from 'prop-types';
import { useState, useEffect } from 'react';
import { anuncioscreate, anunciosupdate } from '../../services/Anucio/api';
import { usuariosObtener } from '../../services/Usuario/api';

export const AnuncioForm = ({ anuncio, onClose, onSave }) => {
    const [titulo, setTitulo] = useState('');
    const [descripcion, setDescripcion] = useState('');
    const [fecha_creacion, setFechaCreacion] = useState('');
    const [id_usuario, setIdUsuario] = useState('');
    const [imagen, setImagen] = useState(null);
    const [imgPreview, setImgPreview] = useState(null);
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

        if (anuncio) {
            setTitulo(anuncio.titulo);
            setDescripcion(anuncio.descripcion);
            setFechaCreacion(formatDateForInput(anuncio.fecha_creacion));
            setIdUsuario(anuncio.id_usuario);
            setImagen(null); // Reset imagen
            setImgPreview(anuncio.imagen);
        }
    }, [anuncio]);

    const formatDateForInput = (dateString) => {
        const date = new Date(dateString);
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const day = String(date.getDate()).padStart(2, '0');
        const hours = String(date.getHours()).padStart(2, '0');
        const minutes = String(date.getMinutes()).padStart(2, '0');
        return `${year}-${month}-${day}T${hours}:${minutes}`;
    };

    const handleImageChange = (e) => {
        const file = e.target.files[0];
        setImagen(file);
        setImgPreview(URL.createObjectURL(file));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const formData = new FormData();
        formData.append('titulo', titulo);
        formData.append('descripcion', descripcion);
        formData.append('fecha_creacion', fecha_creacion);
        formData.append('id_usuario', id_usuario);
        if (imagen) {
            formData.append('imagen', imagen);
        }

        try {
            if (anuncio && anuncio.id_anuncio) {
                await anunciosupdate(formData, anuncio.id_anuncio); // Pass id_anuncio correctly
            } else {
                await anuncioscreate(formData);
            }
            onSave();
            onClose();
        } catch (error) {
            console.error(
                'Error saving anuncio:',
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
                    htmlFor="descripcion"
                >
                    Descripción
                </label>
                <textarea
                    className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    id="descripcion"
                    value={descripcion}
                    onChange={(e) => setDescripcion(e.target.value)}
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
                    htmlFor="imagen"
                >
                    Imagen
                </label>
                {imgPreview && (
                    <div className="mb-4">
                        <img
                            src={imgPreview}
                            alt="Anuncio"
                            className="h-32 w-32 object-cover"
                        />
                    </div>
                )}
                <input
                    className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    id="imagen"
                    type="file"
                    onChange={handleImageChange}
                />
            </div>
            <div className="flex items-center justify-between">
                <button
                    className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                    type="submit"
                >
                    {anuncio ? 'Actualizar' : 'Crear'}
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

AnuncioForm.propTypes = {
    anuncio: PropTypes.shape({
        id_anuncio: PropTypes.number,
        titulo: PropTypes.string,
        descripcion: PropTypes.string,
        fecha_creacion: PropTypes.string,
        id_usuario: PropTypes.number,
        imagen: PropTypes.string,
    }),
    onClose: PropTypes.func.isRequired,
    onSave: PropTypes.func.isRequired,
};
