import { useState, useEffect } from 'react';
import { anunciosObtener, anunciosEliminar } from '../services/Anucio/api';
import { Edit, Trash } from 'lucide-react';
import { AnuncioForm } from '../components/Anuncios/AnuncioForm';
import { format } from 'date-fns';

export const Anuncio = () => {
    const [anuncios, setAnuncios] = useState([]);
    const [currentAnuncio, setCurrentAnuncio] = useState(null);
    const [showForm, setShowForm] = useState(false);
    const [searchTerm, setSearchTerm] = useState("");

    useEffect(() => {
        const fetchAnuncios = async () => {
            try {
                const data = await anunciosObtener();
                setAnuncios(data);
            } catch (error) {
                console.error('Error fetching anuncios:', error);
            }
        };

        fetchAnuncios();
    }, []);

    const handleEdit = (anuncio) => {
        setCurrentAnuncio(anuncio);
        setShowForm(true);
    };

    const handleDelete = async (id_anuncio) => {
        try {
            await anunciosEliminar(id_anuncio);
            setAnuncios(anuncios.filter((anuncio) => anuncio.id_anuncio !== id_anuncio));
        } catch (error) {
            console.error('Error deleting anuncio:', error);
        }
    };

    const handleSave = async () => {
        setShowForm(false);
        setCurrentAnuncio(null);
        const data = await anunciosObtener();
        setAnuncios(data);
    };

    const handleAdd = () => {
        setCurrentAnuncio(null);
        setShowForm(true);
    };

    const handleSearch = (event) => {
        setSearchTerm(event.target.value);
    };

    const filteredAnuncios = anuncios.filter((anuncio) =>
        anuncio.titulo.toLowerCase().includes(searchTerm.toLowerCase())
    );

    return (
        <div className="container mx-auto px-4 sm:px-8">
            <div className="py-8">
                <div className="flex justify-between mb-4">
                    <h2 className="text-2xl font-semibold leading-tight">
                        Lista de Anuncios
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
                        Agregar Anuncio
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
                                    Descripción
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
                            {filteredAnuncios.map((anuncio) => (
                                <tr
                                    key={anuncio.id_anuncio}
                                    className="bg-white border-b dark:bg-gray-800 dark:border-gray-700"
                                >
                                    <th
                                        scope="row"
                                        className="py-4 px-6 font-medium text-gray-900 whitespace-nowrap dark:text-white"
                                    >
                                        {anuncio.titulo}
                                    </th>
                                    <td className="py-4 px-6">{anuncio.descripcion}</td>
                                    <td className="py-4 px-6">
                                        {format(new Date(anuncio.fecha_creacion), 'dd/MM/yyyy HH:mm')}
                                    </td>
                                    <td className="py-4 px-6">{anuncio.usuario_nombre}</td>
                                    <td className="py-4 px-6 flex space-x-2">
                                        <button
                                            className="bg-green-500 hover:bg-green-700 text-white font-bold py-1 px-2 rounded flex items-center"
                                            onClick={() => handleEdit(anuncio)}
                                        >
                                            <Edit className="w-4 h-4 mr-1" /> Editar
                                        </button>
                                        <button
                                            className="bg-red-500 hover:bg-red-700 text-white font-bold py-1 px-2 rounded flex items-center"
                                            onClick={() => handleDelete(anuncio.id_anuncio)}
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
                            <AnuncioForm
                                anuncio={currentAnuncio}
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
