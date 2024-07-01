import PropTypes from 'prop-types';
import { useState, useEffect } from 'react';
import { cursoscreate, cursosupdate } from '../../services/Curso/api';
import { ciclosObtener } from '../../services/Ciclo/api';
import { carrerasObtener } from '../../services/Carrera/api';

export const CursoForm = ({ curso, onClose, onSave }) => {
    const [nombre, setNombre] = useState('');
    const [id_ciclo, setIdCiclo] = useState('');
    const [id_carrera, setIdCarrera] = useState('');
    const [ciclos, setCiclos] = useState([]);
    const [carreras, setCarreras] = useState([]);

    useEffect(() => {
        const fetchCiclos = async () => {
            try {
                const data = await ciclosObtener();
                setCiclos(data);
            } catch (error) {
                console.error('Error fetching ciclos:', error);
            }
        };

        const fetchCarreras = async () => {
            try {
                const data = await carrerasObtener();
                setCarreras(data);
            } catch (error) {
                console.error('Error fetching carreras:', error);
            }
        };

        fetchCiclos();
        fetchCarreras();

        if (curso) {
            setNombre(curso.nombre);
            setIdCiclo(curso.id_ciclo);
            setIdCarrera(curso.id_carrera);
        }
    }, [curso]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        const formData = { nombre, id_ciclo, id_carrera };

        try {
            if (curso && curso.id_curso) {
                await cursosupdate(formData, curso.id_curso); // Pass id_curso correctly
            } else {
                await cursoscreate(formData);
            }
            onSave();
            onClose();
        } catch (error) {
            console.error(
                'Error saving curso:',
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
                    htmlFor="id_ciclo"
                >
                    Ciclo
                </label>
                <select
                    className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    id="id_ciclo"
                    value={id_ciclo}
                    onChange={(e) => setIdCiclo(e.target.value)}
                >
                    <option value="">Seleccione un ciclo</option>
                    {ciclos.map((ciclo) => (
                        <option key={ciclo.id_ciclo} value={ciclo.id_ciclo}>
                            {ciclo.nombre}
                        </option>
                    ))}
                </select>
            </div>
            <div className="mb-4">
                <label
                    className="block text-gray-700 text-sm font-bold mb-2"
                    htmlFor="id_carrera"
                >
                    Carrera
                </label>
                <select
                    className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    id="id_carrera"
                    value={id_carrera}
                    onChange={(e) => setIdCarrera(e.target.value)}
                >
                    <option value="">Seleccione una carrera</option>
                    {carreras.map((carrera) => (
                        <option key={carrera.id_carrera} value={carrera.id_carrera}>
                            {carrera.nombre}
                        </option>
                    ))}
                </select>
            </div>
            <div className="flex items-center justify-between">
                <button
                    className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                    type="submit"
                >
                    {curso ? 'Actualizar' : 'Crear'}
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

CursoForm.propTypes = {
    curso: PropTypes.shape({
        id_curso: PropTypes.number,
        nombre: PropTypes.string,
        id_ciclo: PropTypes.number,
        id_carrera: PropTypes.number,
    }),
    onClose: PropTypes.func.isRequired,
    onSave: PropTypes.func.isRequired,
};
