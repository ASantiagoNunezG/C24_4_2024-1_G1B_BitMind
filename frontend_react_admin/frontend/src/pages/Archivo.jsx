import { useState, useEffect } from 'react';
import { archivosObtener, archivosEliminar } from '../services/Archivo/api';
import { Edit, Trash } from 'lucide-react';
import { ArchivoForm } from '../components/Archivos/ArchivoForm';

export const Archivo = () => {
    const [archivos, setArchivos] = useState([]);
    const [currentArchivo, setCurrentArchivo] = useState(null);
    const [showForm, setShowForm] = useState(false);
    const [searchTerm, setSearchTerm] = useState("");

    useEffect(() => {
        const fetchArchivos = async () => {
            try {
                const data = await archivosObtener();
                setArchivos(data);
            } catch (error) {
                console.error('Error fetching archivos:', error);
            }
        };

        fetchArchivos();
    }, []);

    const handleEdit = (archivo) => {
        setCurrentArchivo(archivo);
        setShowForm(true);
    };

    const handleDelete = async (id_archivo) => {
        try {
            await archivosEliminar(id_archivo);
            setArchivos(archivos.filter((archivo) => archivo.id_archivo !== id_archivo));
        } catch (error) {
            console.error('Error deleting archivo:', error);
        }
    };

    const handleSave = async () => {
        setShowForm(false);
        setCurrentArchivo(null);
        const data = await archivosObtener();
        setArchivos(data);
    };

    const handleAdd = () => {
        setCurrentArchivo(null);
        setShowForm(true);
    };

    const handleSearch = (event) => {
        setSearchTerm(event.target.value);
    };

    const filteredArchivos = archivos.filter((archivo) =>
        archivo.nombre.toLowerCase().includes(searchTerm.toLowerCase())
    );

    return (
        <div className="container mx-auto px-4 sm:px-8">
            <div className="py-8">
                <div className="flex justify-between mb-4">
                    <h2 className="text-2xl font-semibold leading-tight">
                        Lista de Archivos
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
                        Agregar Archivo
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
                                    Tipo
                                </th>
                                <th scope="col" className="py-3 px-6">
                                    URL
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
                            {filteredArchivos.map((archivo) => (
                                <tr
                                    key={archivo.id_archivo}
                                    className="bg-white border-b dark:bg-gray-800 dark:border-gray-700"
                                >
                                    <th
                                        scope="row"
                                        className="py-4 px-6 font-medium text-gray-900 whitespace-nowrap dark:text-white"
                                    >
                                        {archivo.nombre}
                                    </th>
                                    <td className="py-4 px-6">{archivo.tipo}</td>
                                    <td className="py-4 px-6">{archivo.url}</td>
                                    <td className="py-4 px-6">{archivo.publicacion_nombre}</td>
                                    <td className="py-4 px-6 flex space-x-2">
                                        <button
                                            className="bg-green-500 hover:bg-green-700 text-white font-bold py-1 px-2 rounded flex items-center"
                                            onClick={() => handleEdit(archivo)}
                                        >
                                            <Edit className="w-4 h-4 mr-1" /> Editar
                                        </button>
                                        <button
                                            className="bg-red-500 hover:bg-red-700 text-white font-bold py-1 px-2 rounded flex items-center"
                                            onClick={() => handleDelete(archivo.id_archivo)}
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
                            <ArchivoForm
                                archivo={currentArchivo}
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
