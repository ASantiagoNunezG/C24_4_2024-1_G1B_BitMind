import PropTypes from 'prop-types';
import { useState, useEffect } from 'react';
import { usuarioscreate, usuariosupdate } from '../../services/Usuario/api';

export const UsuarioForm = ({ usuario, onClose, onSave }) => {
    const [nombres, setNombres] = useState('');
    const [correo, setCorreo] = useState('');
    const [clave, setClave] = useState('');
    const [imagen, setImagen] = useState(null);
    const [imgPreview, setImgPreview] = useState(null);

    useEffect(() => {
        if (usuario) {
            setNombres(usuario.nombres);
            setCorreo(usuario.correo);
            setClave(''); // No cargar la clave
            setImagen(null); // Reset imagen
            setImgPreview(usuario.imagen);
        }
    }, [usuario]);

    const handleImageChange = (e) => {
        const file = e.target.files[0];
        setImagen(file);
        setImgPreview(URL.createObjectURL(file));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const formData = new FormData();
        formData.append('nombres', nombres);
        formData.append('correo', correo);
        formData.append('clave', clave);
        if (imagen) {
            formData.append('imagen', imagen);
        }

        try {
            if (usuario && usuario.id_usuario) {
                await usuariosupdate(formData, usuario.id_usuario); // Pass id_usuario correctly
            } else {
                await usuarioscreate(formData);
            }
            onSave();
            onClose();
        } catch (error) {
            console.error(
                'Error saving usuario:',
                error.response ? error.response.data : error.message
            );
            // Aquí puedes agregar lógica para mostrar un mensaje de error al usuario
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <div className="mb-4">
                <label
                    className="block text-gray-700 text-sm font-bold mb-2"
                    htmlFor="nombres"
                >
                    Nombres
                </label>
                <input
                    className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    id="nombres"
                    type="text"
                    value={nombres}
                    onChange={(e) => setNombres(e.target.value)}
                />
            </div>
            <div className="mb-4">
                <label
                    className="block text-gray-700 text-sm font-bold mb-2"
                    htmlFor="correo"
                >
                    Correo
                </label>
                <input
                    className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    id="correo"
                    type="email"
                    value={correo}
                    onChange={(e) => setCorreo(e.target.value)}
                />
            </div>
            <div className="mb-4">
                <label
                    className="block text-gray-700 text-sm font-bold mb-2"
                    htmlFor="clave"
                >
                    Clave
                </label>
                <input
                    className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    id="clave"
                    type="password"
                    value={clave}
                    onChange={(e) => setClave(e.target.value)}
                />
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
                            alt="Usuario"
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
                    {usuario ? 'Actualizar' : 'Crear'}
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

UsuarioForm.propTypes = {
    usuario: PropTypes.shape({
        id_usuario: PropTypes.number,
        nombres: PropTypes.string,
        correo: PropTypes.string,
        clave: PropTypes.string,
        imagen: PropTypes.string,
    }),
    onClose: PropTypes.func.isRequired,
    onSave: PropTypes.func.isRequired,
};
