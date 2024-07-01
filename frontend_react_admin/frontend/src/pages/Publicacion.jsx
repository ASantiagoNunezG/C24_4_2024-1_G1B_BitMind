import { useState, useEffect } from 'react';
import { publicacionesObtener, publicacionesEliminar } from '../services/Publicacion/api';
import { Edit, Trash } from 'lucide-react';
import { PublicacionForm } from '../components/Publicaciones/PublicacionForm';
import { format } from 'date-fns';

export const Publicacion = () => {
    const [publicaciones, setPublicaciones] = useState([]);
    const [currentPublicacion, setCurrentPublicacion] = useState(null);
    const [showForm, setShowForm] = useState(false);
    const [searchTerm, setSearchTerm] = useState("");

    useEffect(() => {
        const fetchPublicaciones = async () => {
            try {
                const data = await publicacionesObtener();
                setPublicaciones(data);
            } catch (error) {
                console.error('Error fetching publicaciones:', error);
            }
        };

        fetchPublicaciones();
    }, []);

    const handleEdit = (publicacion) => {
        setCurrentPublicacion(publicacion);
        setShowForm(true);
    };

    const handleDelete = async (id_publicacion) => {
        try {
            await publicacionesEliminar(id_publicacion);
            setPublicaciones(publicaciones.filter((publicacion) => publicacion.id_publicacion !== id_publicacion));
        } catch (error) {
            console.error('Error deleting publicacion:', error);
        }
    };

    const handleSave = async () => {
        setShowForm(false);
        setCurrentPublicacion(null);
        const data = await publicacionesObtener();
        setPublicaciones(data);
    };

    const handleAdd = () => {
        setCurrentPublicacion(null);
        setShowForm(true);
    };

    const handleSearch = (event) => {
        setSearchTerm(event.target.value);
    };

    const filteredPublicaciones = publicaciones.filter((publicacion) =>
        publicacion.titulo.toLowerCase().includes(searchTerm.toLowerCase())
    );

    const formatDate = (dateString) => {
        return format(new Date(dateString), 'yyyy-MM-dd HH:mm');
    };

    return (
        <div className="container mx-auto px-4 sm:px-8">
            <div className="py-8">
                <div className="flex justify-between mb-4">
                    <h2 className="text-2xl font-semibold leading-tight">
                        Lista de Publicaciones
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
                        Agregar Publicación
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
                                    Vistas
                                </th>
                                <th scope="col" className="py-3 px-6">
                                    Fecha de Creación
                                </th>
                                <th scope="col" className="py-3 px-6">
                                    Fecha de Modificación
                                </th>
                                <th scope="col" className="py-3 px-6">
                                    Usuario
                                </th>
                                <th scope="col" className="py-3 px-6">
                                    Curso
                                </th>
                                <th scope="col" className="py-3 px-6">
                                    Acciones
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            {filteredPublicaciones.map((publicacion) => (
                                <tr
                                    key={publicacion.id_publicacion}
                                    className="bg-white border-b dark:bg-gray-800 dark:border-gray-700"
                                >
                                    <th
                                        scope="row"
                                        className="py-4 px-6 font-medium text-gray-900 whitespace-nowrap dark:text-white"
                                    >
                                        {publicacion.titulo}
                                    </th>
                                    <td className="py-4 px-6">{publicacion.descripcion}</td>
                                    <td className="py-4 px-6">{publicacion.vistas}</td>
                                    <td className="py-4 px-6">{formatDate(publicacion.fecha_creacion)}</td>
                                    <td className="py-4 px-6">{formatDate(publicacion.fecha_modificacion)}</td>
                                    <td className="py-4 px-6">{publicacion.usuario_nombre}</td>
                                    <td className="py-4 px-6">{publicacion.curso_nombre}</td>
                                    <td className="py-4 px-6 flex space-x-2">
                                        <button
                                            className="bg-green-500 hover:bg-green-700 text-white font-bold py-1 px-2 rounded flex items-center"
                                            onClick={() => handleEdit(publicacion)}
                                        >
                                            <Edit className="w-4 h-4 mr-1" /> Editar
                                        </button>
                                        <button
                                            className="bg-red-500 hover:bg-red-700 text-white font-bold py-1 px-2 rounded flex items-center"
                                            onClick={() => handleDelete(publicacion.id_publicacion)}
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
                            <PublicacionForm
                                publicacion={currentPublicacion}
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
