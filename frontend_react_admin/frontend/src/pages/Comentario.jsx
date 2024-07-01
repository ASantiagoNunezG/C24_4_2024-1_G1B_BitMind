import { useState, useEffect } from 'react';
import { format } from 'date-fns';
import { comentariosObtener, comentariosEliminar } from '../services/Comentario/api';
import { Edit, Trash } from 'lucide-react';
import { ComentarioForm } from '../components/Comentarios/ComentarioForm';

export const Comentario = () => {
    const [comentarios, setComentarios] = useState([]);
    const [currentComentario, setCurrentComentario] = useState(null);
    const [showForm, setShowForm] = useState(false);
    const [searchTerm, setSearchTerm] = useState("");

    useEffect(() => {
        const fetchComentarios = async () => {
            try {
                const data = await comentariosObtener();
                setComentarios(data);
            } catch (error) {
                console.error('Error fetching comentarios:', error);
            }
        };

        fetchComentarios();
    }, []);

    const handleEdit = (comentario) => {
        setCurrentComentario(comentario);
        setShowForm(true);
    };

    const handleDelete = async (id_comentario) => {
        try {
            await comentariosEliminar(id_comentario);
            setComentarios(comentarios.filter((comentario) => comentario.id_comentario !== id_comentario));
        } catch (error) {
            console.error('Error deleting comentario:', error);
        }
    };

    const handleSave = async () => {
        setShowForm(false);
        setCurrentComentario(null);
        const data = await comentariosObtener();
        setComentarios(data);
    };

    const handleAdd = () => {
        setCurrentComentario(null);
        setShowForm(true);
    };

    const handleSearch = (event) => {
        setSearchTerm(event.target.value);
    };

    const filteredComentarios = comentarios.filter((comentario) =>
        comentario.contenido.toLowerCase().includes(searchTerm.toLowerCase())
    );

    return (
        <div className="container mx-auto px-4 sm:px-8">
            <div className="py-8">
                <div className="flex justify-between mb-4">
                    <h2 className="text-2xl font-semibold leading-tight">
                        Lista de Comentarios
                    </h2>
                    <input
                        type="text"
                        placeholder="Buscar por contenido"
                        value={searchTerm}
                        onChange={handleSearch}
                        className="border rounded py-2 px-4"
                    />
                    <button
                        className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
                        onClick={handleAdd}
                    >
                        Agregar Comentario
                    </button>
                </div>
                <div className="overflow-x-auto relative shadow-md sm:rounded-lg">
                    <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400">
                        <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                            <tr>
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
                                    Foro
                                </th>
                                <th scope="col" className="py-3 px-6">
                                    Acciones
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            {filteredComentarios.map((comentario) => (
                                <tr
                                    key={comentario.id_comentario}
                                    className="bg-white border-b dark:bg-gray-800 dark:border-gray-700"
                                >
                                    <th
                                        scope="row"
                                        className="py-4 px-6 font-medium text-gray-900 whitespace-nowrap dark:text-white"
                                    >
                                        {comentario.contenido}
                                    </th>
                                    <td className="py-4 px-6">{format(new Date(comentario.fecha_creacion), 'dd/MM/yyyy HH:mm')}</td>
                                    <td className="py-4 px-6">{comentario.usuario_nombre}</td>
                                    <td className="py-4 px-6">{comentario.foro_nombre}</td>
                                    <td className="py-4 px-6 flex space-x-2">
                                        <button
                                            className="bg-green-500 hover:bg-green-700 text-white font-bold py-1 px-2 rounded flex items-center"
                                            onClick={() => handleEdit(comentario)}
                                        >
                                            <Edit className="w-4 h-4 mr-1" /> Editar
                                        </button>
                                        <button
                                            className="bg-red-500 hover:bg-red-700 text-white font-bold py-1 px-2 rounded flex items-center"
                                            onClick={() => handleDelete(comentario.id_comentario)}
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
                            <ComentarioForm
                                comentario={currentComentario}
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
