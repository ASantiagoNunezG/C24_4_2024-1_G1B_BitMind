import PropTypes from 'prop-types';
import { useState, useEffect } from 'react';
import { archivoscreate, archivosupdate } from '../../services/Archivo/api';
import { publicacionesObtener } from '../../services/Publicacion/api';

export const ArchivoForm = ({ archivo, onClose, onSave }) => {
    const [nombre, setNombre] = useState('');
    const [tipo, setTipo] = useState('');
    const [url, setUrl] = useState(null);
    const [id_publicacion, setIdPublicacion] = useState('');
    const [urlPreview, setUrlPreview] = useState(null);
    const [publicaciones, setPublicaciones] = useState([]);

    useEffect(() => {
        const fetchPublicaciones = async () => {
            try {
                const data = await publicacionesObtener();
                setPublicaciones(data);
            } catch (error) {
                console.error('Error fetching publicaciones:', error);
            }
        };

        fetchPublicaciones();

        if (archivo) {
            setNombre(archivo.nombre);
            setTipo(archivo.tipo);
            setIdPublicacion(archivo.id_publicacion);
            setUrl(null); // Reset url
            setUrlPreview(archivo.url);
        }
    }, [archivo]);

    const handleFileChange = (e) => {
        const file = e.target.files[0];
        setUrl(file);
        setUrlPreview(URL.createObjectURL(file));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const formData = new FormData();
        formData.append('nombre', nombre);
        formData.append('tipo', tipo);
        formData.append('id_publicacion', id_publicacion);
        if (url) {
            formData.append('url', url);
        }

        try {
            if (archivo && archivo.id_archivo) {
                await archivosupdate(formData, archivo.id_archivo); // Pass id_archivo correctly
            } else {
                await archivoscreate(formData);
            }
            onSave();
            onClose();
        } catch (error) {
            console.error(
                'Error saving archivo:',
                error.response ? error.response.data : error.message
            );
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <div className="mb-4">
                <label
                    className="block text-gray-700 text-sm font-bold mb-2"
                    htmlFor="nombre"
                >
                    Nombre
                </label>
                <input
                    className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    id="nombre"
                    type="text"
                    value={nombre}
                    onChange={(e) => setNombre(e.target.value)}
                />
            </div>
            <div className="mb-4">
                <label
                    className="block text-gray-700 text-sm font-bold mb-2"
                    htmlFor="tipo"
                >
                    Tipo
                </label>
                <input
                    className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    id="tipo"
                    type="text"
                    value={tipo}
                    onChange={(e) => setTipo(e.target.value)}
                />
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
            <div className="mb-4">
                <label
                    className="block text-gray-700 text-sm font-bold mb-2"
                    htmlFor="url"
                >
                    Archivo
                </label>
                {urlPreview && (
                    <div className="mb-4">
                        <a href={urlPreview} target="_blank" rel="noopener noreferrer">Ver archivo</a>
                    </div>
                )}
                <input
                    className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    id="url"
                    type="file"
                    onChange={handleFileChange}
                />
            </div>
            <div className="flex items-center justify-between">
                <button
                    className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                    type="submit"
                >
                    {archivo ? 'Actualizar' : 'Crear'}
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

ArchivoForm.propTypes = {
    archivo: PropTypes.shape({
        id_archivo: PropTypes.number,
        nombre: PropTypes.string,
        tipo: PropTypes.string,
        url: PropTypes.string,
        id_publicacion: PropTypes.number,
    }),
    onClose: PropTypes.func.isRequired,
    onSave: PropTypes.func.isRequired,
};
