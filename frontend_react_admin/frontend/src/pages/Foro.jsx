import { useState, useEffect } from 'react';
import { forosObtener, forosEliminar } from '../services/Foro/api';
import { Edit, Trash } from 'lucide-react';
import { ForoForm } from '../components/Foros/ForoForm';
import { format } from 'date-fns';
import { es } from 'date-fns/locale';

const formatDate = (dateString) => {
    return format(new Date(dateString), 'PPPPpp', { locale: es });
};

export const Foro = () => {
    const [foros, setForos] = useState([]);
    const [currentForo, setCurrentForo] = useState(null);
    const [showForm, setShowForm] = useState(false);
    const [searchTerm, setSearchTerm] = useState("");

    useEffect(() => {
        const fetchForos = async () => {
            try {
                const data = await forosObtener();
                setForos(data);
            } catch (error) {
                console.error('Error fetching foros:', error);
            }
        };

        fetchForos();
    }, []);

    const handleEdit = (foro) => {
        setCurrentForo(foro);
        setShowForm(true);
    };

    const handleDelete = async (id_foro) => {
        try {
            await forosEliminar(id_foro);
            setForos(foros.filter((foro) => foro.id_foro !== id_foro));
        } catch (error) {
            console.error('Error deleting foro:', error);
        }
    };

    const handleSave = async () => {
        setShowForm(false);
        setCurrentForo(null);
        const data = await forosObtener();
        setForos(data);
    };

    const handleAdd = () => {
        setCurrentForo(null);
        setShowForm(true);
    };

    const handleSearch = (event) => {
        setSearchTerm(event.target.value);
    };

    const filteredForos = foros.filter((foro) =>
        foro.titulo.toLowerCase().includes(searchTerm.toLowerCase())
    );

    return (
        <div className="container mx-auto px-4 sm:px-8">
            <div className="py-8">
                <div className="flex justify-between mb-4">
                    <h2 className="text-2xl font-semibold leading-tight">
                        Lista de Foros
                    </h2>
                    <input
                        type="text"
                        placeholder="Buscar por título"
                        value={searchTerm}
                        onChange={handleSearch}
                        className="border rounded py-2 px-4"
                    />
                    <button
                        className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
                        onClick={handleAdd}
                    >
                        Agregar Foro
                    </button>
                </div>
                <div className="overflow-x-auto relative shadow-md sm:rounded-lg">
                    <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400">
                        <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                            <tr>
                                <th scope="col" className="py-3 px-6">
                                    Título
                                </th>
                                <th scope="col" className="py-3 px-6">
                                    Contenido
                                </th>
                                <th scope="col" className="py-3 px-6">
                                    Fecha de Creación
                                </th>
                                <th scope="col" className="py-3 px-6">
                                    Usuario
                                </th>
                                <th scope="col" className="py-3 px-6">
                                    Acciones
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            {filteredForos.map((foro) => (
                                <tr
                                    key={foro.id_foro}
                                    className="bg-white border-b dark:bg-gray-800 dark:border-gray-700"
                                >
                                    <th
                                        scope="row"
                                        className="py-4 px-6 font-medium text-gray-900 whitespace-nowrap dark:text-white"
                                    >
                                        {foro.titulo}
                                    </th>
                                    <td className="py-4 px-6">{foro.contenido}</td>
                                    <td className="py-4 px-6">{formatDate(foro.fecha_creacion)}</td>
                                    <td className="py-4 px-6">{foro.usuario_nombre}</td>
                                    <td className="py-4 px-6 flex space-x-2">
                                        <button
                                            className="bg-green-500 hover:bg-green-700 text-white font-bold py-1 px-2 rounded flex items-center"
                                            onClick={() => handleEdit(foro)}
                                        >
                                            <Edit className="w-4 h-4 mr-1" /> Editar
                                        </button>
                                        <button
                                            className="bg-red-500 hover:bg-red-700 text-white font-bold py-1 px-2 rounded flex items-center"
                                            onClick={() => handleDelete(foro.id_foro)}
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
                            <ForoForm
                                foro={currentForo}
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
