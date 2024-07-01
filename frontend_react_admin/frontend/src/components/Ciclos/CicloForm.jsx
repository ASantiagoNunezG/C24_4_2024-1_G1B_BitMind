import PropTypes from 'prop-types';
import { useState, useEffect } from 'react';
import { cicloscreate, ciclosupdate } from '../../services/Ciclo/api';

export const CicloForm = ({ ciclo, onClose, onSave }) => {
    const [nombre, setNombre] = useState('');

    useEffect(() => {
        if (ciclo) {
            setNombre(ciclo.nombre);
        }
    }, [ciclo]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        const formData = { nombre };

        try {
            if (ciclo && ciclo.id_ciclo) {
                await ciclosupdate(formData, ciclo.id_ciclo); // Pass id_ciclo correctly
            } else {
                await cicloscreate(formData);
            }
            onSave();
            onClose();
        } catch (error) {
            console.error(
                'Error saving ciclo:',
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
                    {ciclo ? 'Actualizar' : 'Crear'}
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

CicloForm.propTypes = {
    ciclo: PropTypes.shape({
        id_ciclo: PropTypes.number,
        nombre: PropTypes.string,
    }),
    onClose: PropTypes.func.isRequired,
    onSave: PropTypes.func.isRequired,
};
