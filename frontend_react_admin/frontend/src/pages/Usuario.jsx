import { useState, useEffect } from 'react';
import { usuariosObtener, usuariosEliminar } from '../services/Usuario/api';
import { Edit, Trash } from 'lucide-react';
import { UsuarioForm } from '../components/Usuario/UsuarioForm';

export const Usuario = () => {
    const [usuarios, setUsuarios] = useState([]);
    const [currentUsuario, setCurrentUsuario] = useState(null);
    const [showForm, setShowForm] = useState(false);
    const [searchTerm, setSearchTerm] = useState("");

    useEffect(() => {
        const fetchUsuarios = async () => {
            try {
                const data = await usuariosObtener();
                setUsuarios(data);
            } catch (error) {
                console.error('Error fetching usuarios:', error);
            }
        };

        fetchUsuarios();
    }, []);

    const handleEdit = (usuario) => {
        setCurrentUsuario(usuario);
        setShowForm(true);
    };

    const handleDelete = async (id_usuario) => {
        try {
            await usuariosEliminar(id_usuario);
            setUsuarios(usuarios.filter((usuario) => usuario.id_usuario !== id_usuario));
        } catch (error) {
            console.error('Error deleting usuario:', error);
        }
    };

    const handleSave = async () => {
        setShowForm(false);
        setCurrentUsuario(null);
        const data = await usuariosObtener();
        setUsuarios(data);
    };

    const handleAdd = () => {
        setCurrentUsuario(null);
        setShowForm(true);
    };

    const handleSearch = (event) => {
        setSearchTerm(event.target.value);
    };

    const filteredUsuarios = usuarios.filter((usuario) =>
        usuario.nombres.toLowerCase().includes(searchTerm.toLowerCase())
    );

    return (
        <div className="container mx-auto px-4 sm:px-8">
            <div className="py-8">
                <div className="flex justify-between mb-4">
                    <h2 className="text-2xl font-semibold leading-tight">
                        Lista de Usuarios
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
                        Agregar Usuario
                    </button>
                </div>
                <div className="overflow-x-auto relative shadow-md sm:rounded-lg">
                    <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400">
                        <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                            <tr>
                                <th scope="col" className="py-3 px-6">
                                    Nombres
                                </th>
                                <th scope="col" className="py-3 px-6">
                                    Correo
                                </th>
                                <th scope="col" className="py-3 px-6">
                                    Acciones
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            {filteredUsuarios.map((usuario) => (
                                <tr
                                    key={usuario.id_usuario}
                                    className="bg-white border-b dark:bg-gray-800 dark:border-gray-700"
                                >
                                    <th
                                        scope="row"
                                        className="py-4 px-6 font-medium text-gray-900 whitespace-nowrap dark:text-white"
                                    >
                                        {usuario.nombres}
                                    </th>
                                    <td className="py-4 px-6">{usuario.correo}</td>
                                    <td className="py-4 px-6 flex space-x-2">
                                        <button
                                            className="bg-green-500 hover:bg-green-700 text-white font-bold py-1 px-2 rounded flex items-center"
                                            onClick={() => handleEdit(usuario)}
                                        >
                                            <Edit className="w-4 h-4 mr-1" /> Editar
                                        </button>
                                        <button
                                            className="bg-red-500 hover:bg-red-700 text-white font-bold py-1 px-2 rounded flex items-center"
                                            onClick={() => handleDelete(usuario.id_usuario)}
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
                            <UsuarioForm
                                usuario={currentUsuario}
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
