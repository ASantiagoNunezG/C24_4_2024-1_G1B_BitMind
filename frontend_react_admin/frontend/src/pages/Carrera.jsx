import { useState, useEffect } from 'react';
import { carrerasObtener, carrerasEliminar } from '../services/Carrera/api';
import { Edit, Trash } from 'lucide-react';
import { CarreraForm } from '../components/Carreras/CarreraForm';

export const Carrera = () => {
    const [carreras, setCarreras] = useState([]);
    const [currentCarrera, setCurrentCarrera] = useState(null);
    const [showForm, setShowForm] = useState(false);
    const [searchTerm, setSearchTerm] = useState("");

    useEffect(() => {
        const fetchCarreras = async () => {
            try {
                const data = await carrerasObtener();
                setCarreras(data);
            } catch (error) {
                console.error('Error fetching carreras:', error);
            }
        };

        fetchCarreras();
    }, []);

    const handleEdit = (carrera) => {
        setCurrentCarrera(carrera);
        setShowForm(true);
    };

    const handleDelete = async (id_carrera) => {
        try {
            await carrerasEliminar(id_carrera);
            setCarreras(carreras.filter((carrera) => carrera.id_carrera !== id_carrera));
        } catch (error) {
            console.error('Error deleting carrera:', error);
        }
    };

    const handleSave = async () => {
        setShowForm(false);
        setCurrentCarrera(null);
        const data = await carrerasObtener();
        setCarreras(data);
    };

    const handleAdd = () => {
        setCurrentCarrera(null);
        setShowForm(true);
    };

    const handleSearch = (event) => {
        setSearchTerm(event.target.value);
    };

    const filteredCarreras = carreras.filter((carrera) =>
        carrera.nombre.toLowerCase().includes(searchTerm.toLowerCase())
    );

    return (
        <div className="container mx-auto px-4 sm:px-8">
            <div className="py-8">
                <div className="flex justify-between mb-4">
                    <h2 className="text-2xl font-semibold leading-tight">
                        Lista de Carreras
                    </h2>
                    <input
                        type="text"
                        placeholder="Buscar por nombre"
                        value={searchTerm}
                        onChange={handleSearch}
                        className="border rounded py-2 px-4"
                    />
                    <button
                        className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
                        onClick={handleAdd}
                    >
                        Agregar Carrera
                    </button>
                </div>
                <div className="overflow-x-auto relative shadow-md sm:rounded-lg">
                    <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400">
                        <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                            <tr>
                                <th scope="col" className="py-3 px-6">
                                    Nombre
                                </th>
                                <th scope="col" className="py-3 px-6">
                                    Acciones
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            {filteredCarreras.map((carrera) => (
                                <tr
                                    key={carrera.id_carrera}
                                    className="bg-white border-b dark:bg-gray-800 dark:border-gray-700"
                                >
                                    <th
                                        scope="row"
                                        className="py-4 px-6 font-medium text-gray-900 whitespace-nowrap dark:text-white"
                                    >
                                        {carrera.nombre}
                                    </th>
                                    <td className="py-4 px-6 flex space-x-2">
                                        <button
                                            className="bg-green-500 hover:bg-green-700 text-white font-bold py-1 px-2 rounded flex items-center"
                                            onClick={() => handleEdit(carrera)}
                                        >
                                            <Edit className="w-4 h-4 mr-1" /> Editar
                                        </button>
                                        <button
                                            className="bg-red-500 hover:bg-red-700 text-white font-bold py-1 px-2 rounded flex items-center"
                                            onClick={() => handleDelete(carrera.id_carrera)}
                                        >
                                            <Trash className="w-4 h-4 mr-1" /> Eliminar
                                        </button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
                {showForm && (
                    <div className="fixed inset-0 bg-gray-600 bg-opacity-50 overflow-y-auto h-full w-full">
                        <div className="relative top-20 mx-auto p-5 border w-96 shadow-lg rounded-md bg-white">
                            <CarreraForm
                                carrera={currentCarrera}
                                onSave={handleSave}
                                onClose={() => setShowForm(false)}
                            />
                        </div>
                    </div>
                )}
            </div>
        </div>
    );
};
