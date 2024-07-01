import PropTypes from 'prop-types';
import { useState, useEffect } from 'react';
import { carrerascreate, carrerasupdate } from '../../services/Carrera/api';

export const CarreraForm = ({ carrera, onClose, onSave }) => {
    const [nombre, setNombre] = useState('');

    useEffect(() => {
        if (carrera) {
            setNombre(carrera.nombre);
        }
    }, [carrera]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        const formData = { nombre };

        try {
            if (carrera && carrera.id_carrera) {
                await carrerasupdate(formData, carrera.id_carrera); // Pass id_carrera correctly
            } else {
                await carrerascreate(formData);
            }
            onSave();
            onClose();
        } catch (error) {
            console.error(
                'Error saving carrera:',
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
            <div className="flex items-center justify-between">
                <button
                    className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                    type="submit"
                >
                    {carrera ? 'Actualizar' : 'Crear'}
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

CarreraForm.propTypes = {
    carrera: PropTypes.shape({
        id_carrera: PropTypes.number,
        nombre: PropTypes.string,
    }),
    onClose: PropTypes.func.isRequired,
    onSave: PropTypes.func.isRequired,
};
