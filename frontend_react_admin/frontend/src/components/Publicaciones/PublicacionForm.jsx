import PropTypes from 'prop-types';
import { useState, useEffect } from 'react';
import { publicacionescreate, publicacionesupdate } from '../../services/Publicacion/api';
import { usuariosObtener } from '../../services/Usuario/api';
import { cursosObtener } from '../../services/Curso/api';

export const PublicacionForm = ({ publicacion, onClose, onSave }) => {
    const [titulo, setTitulo] = useState('');
    const [descripcion, setDescripcion] = useState('');
    const [vistas, setVistas] = useState('');
    const [fecha_creacion, setFechaCreacion] = useState('');
    const [fecha_modificacion, setFechaModificacion] = useState('');
    const [id_usuario, setIdUsuario] = useState('');
    const [id_curso, setIdCurso] = useState('');
    const [imagen, setImagen] = useState(null);
    const [imgPreview, setImgPreview] = useState(null);
    const [usuarios, setUsuarios] = useState([]);
    const [cursos, setCursos] = useState([]);

    useEffect(() => {
        const fetchUsuarios = async () => {
            try {
                const data = await usuariosObtener();
                setUsuarios(data);
            } catch (error) {
                console.error('Error fetching usuarios:', error);
            }
        };

        const fetchCursos = async () => {
            try {
                const data = await cursosObtener();
                setCursos(data);
            } catch (error) {
                console.error('Error fetching cursos:', error);
            }
        };

        fetchUsuarios();
        fetchCursos();

        if (publicacion) {
            setTitulo(publicacion.titulo);
            setDescripcion(publicacion.descripcion);
            setVistas(publicacion.vistas);
            setFechaCreacion(publicacion.fecha_creacion ? publicacion.fecha_creacion.replace('Z', '') : '');
            setFechaModificacion(publicacion.fecha_modificacion ? publicacion.fecha_modificacion.replace('Z', '') : '');
            setIdUsuario(publicacion.id_usuario);
            setIdCurso(publicacion.id_curso);
            setImagen(null); // Reset imagen
            setImgPreview(publicacion.imagen);
        }
    }, [publicacion]);

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
        formData.append('vistas', vistas);
        formData.append('fecha_creacion', fecha_creacion);
        formData.append('fecha_modificacion', fecha_modificacion);
        formData.append('id_usuario', id_usuario);
        formData.append('id_curso', id_curso);
        if (imagen) {
            formData.append('imagen', imagen);
        }

        try {
            if (publicacion && publicacion.id_publicacion) {
                await publicacionesupdate(formData, publicacion.id_publicacion); // Pass id_publicacion correctly
            } else {
                await publicacionescreate(formData);
            }
            onSave();
            onClose();
        } catch (error) {
            console.error(
                'Error saving publicacion:',
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
                <input
                    className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    id="descripcion"
                    type="text"
                    value={descripcion}
                    onChange={(e) => setDescripcion(e.target.value)}
                />
            </div>
            <div className="mb-4">
                <label
                    className="block text-gray-700 text-sm font-bold mb-2"
                    htmlFor="vistas"
                >
                    Vistas
                </label>
                <input
                    className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    id="vistas"
                    type="number"
                    value={vistas}
                    onChange={(e) => setVistas(e.target.value)}
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
                    htmlFor="fecha_modificacion"
                >
                    Fecha de Modificación
                </label>
                <input
                    className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    id="fecha_modificacion"
                    type="datetime-local"
                    value={fecha_modificacion}
                    onChange={(e) => setFechaModificacion(e.target.value)}
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
                    htmlFor="id_curso"
                >
                    Curso
                </label>
                <select
                    className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    id="id_curso"
                    value={id_curso}
                    onChange={(e) => setIdCurso(e.target.value)}
                >
                    <option value="">Seleccione un curso</option>
                    {cursos.map((curso) => (
                        <option key={curso.id_curso} value={curso.id_curso}>
                            {curso.nombre}
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
                            alt="Publicacion"
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
                    {publicacion ? 'Actualizar' : 'Crear'}
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

PublicacionForm.propTypes = {
    publicacion: PropTypes.shape({
        id_publicacion: PropTypes.number,
        titulo: PropTypes.string,
        descripcion: PropTypes.string,
        vistas: PropTypes.number,
        fecha_creacion: PropTypes.string,
        fecha_modificacion: PropTypes.string,
        id_usuario: PropTypes.number,
        id_curso: PropTypes.number,
        imagen: PropTypes.string,
    }),
    onClose: PropTypes.func.isRequired,
    onSave: PropTypes.func.isRequired,
};
