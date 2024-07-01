import { useState, useEffect } from 'react';
import { format } from 'date-fns';
import { valoracionesObtener, valoracionesEliminar } from '../services/Valoracion/api';
import { Edit, Trash } from 'lucide-react';
import { ValoracionForm } from '../components/Valoraciones/ValoriacionForm';

export const Valoracion = () => {
    const [valoraciones, setValoraciones] = useState([]);
    const [currentValoracion, setCurrentValoracion] = useState(null);
    const [showForm, setShowForm] = useState(false);
    const [searchTerm, setSearchTerm] = useState("");

    useEffect(() => {
        const fetchValoraciones = async () => {
            try {
                const data = await valoracionesObtener();
                setValoraciones(data);
            } catch (error) {
                console.error('Error fetching valoraciones:', error);
            }
        };

        fetchValoraciones();
    }, []);

    const handleEdit = (valoracion) => {
        setCurrentValoracion(valoracion);
        setShowForm(true);
    };

    const handleDelete = async (id_valoracion) => {
        try {
            await valoracionesEliminar(id_valoracion);
            setValoraciones(valoraciones.filter((valoracion) => valoracion.id_valoracion !== id_valoracion));
        } catch (error) {
            console.error('Error deleting valoracion:', error);
        }
    };

    const handleSave = async () => {
        setShowForm(false);
        setCurrentValoracion(null);
        const data = await valoracionesObtener();
        setValoraciones(data);
    };

    const handleAdd = () => {
        setCurrentValoracion(null);
        setShowForm(true);
    };

    const handleSearch = (event) => {
        setSearchTerm(event.target.value);
    };

    const filteredValoraciones = valoraciones.filter((valoracion) =>
        valoracion.id_usuario.toString().includes(searchTerm)
    );

    return (
        <div className="container mx-auto px-4 sm:px-8">
            <div className="py-8">
                <div className="flex justify-between mb-4">
                    <h2 className="text-2xl font-semibold leading-tight">
                        Lista de Valoraciones
                    </h2>
                    <input
                        type="text"
                        placeholder="Buscar por ID Usuario"
                        value={searchTerm}
                        onChange={handleSearch}
                        className="border rounded py-2 px-4"
                    />
                    <button
                        className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
                        onClick={handleAdd}
                    >
                        Agregar Valoracion
                    </button>
                </div>
                <div className="overflow-x-auto relative shadow-md sm:rounded-lg">
                    <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400">
                        <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                            <tr>
                                <th scope="col" className="py-3 px-6">
                                    Escala
                                </th>
                                <th scope="col" className="py-3 px-6">
                                    Fecha de Creación
                                </th>
                                <th scope="col" className="py-3 px-6">
                                    Usuario
                                </th>
                                <th scope="col" className="py-3 px-6">
                                    Publicación
                                </th>
                                <th scope="col" className="py-3 px-6">
                                    Acciones
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            {filteredValoraciones.map((valoracion) => (
                                <tr
                                    key={valoracion.id_valoracion}
                                    className="bg-white border-b dark:bg-gray-800 dark:border-gray-700"
                                >
                                    <th
                                        scope="row"
                                        className="py-4 px-6 font-medium text-gray-900 whitespace-nowrap dark:text-white"
                                    >
                                        {valoracion.escala}
                                    </th>
                                    <td className="py-4 px-6">{format(new Date(valoracion.fecha_creacion), 'dd/MM/yyyy HH:mm')}</td>
                                    <td className="py-4 px-6">{valoracion.usuario_nombre}</td>
                                    <td className="py-4 px-6">{valoracion.publicacion_nombre}</td>
                                    <td className="py-4 px-6 flex space-x-2">
                                        <button
                                            className="bg-green-500 hover:bg-green-700 text-white font-bold py-1 px-2 rounded flex items-center"
                                            onClick={() => handleEdit(valoracion)}
                                        >
                                            <Edit className="w-4 h-4 mr-1" /> Editar
                                        </button>
                                        <button
                                            className="bg-red-500 hover:bg-red-700 text-white font-bold py-1 px-2 rounded flex items-center"
                                            onClick={() => handleDelete(valoracion.id_valoracion)}
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
                            <ValoracionForm
                                valoracion={currentValoracion}
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
