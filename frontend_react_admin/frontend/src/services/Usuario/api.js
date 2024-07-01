import axios from '../../libs/axios';

export const usuariosObtener = async () => (await axios.get('usuarios/')).data;

export const usuariosObtenerid = async (id_usuario) => (await axios.get(`usuarios/${id_usuario}/`)).data;

export const usuariosEliminar = async (id_usuario) => {
    await axios.delete(`usuarios/${id_usuario}/`);
};

export const usuarioscreate = async (data) => {
    await axios.post("usuarios/", data, {
        headers: {
            "Content-Type": "multipart/form-data",
        },
    });
};

export const usuariosupdate = async (data, id_usuario) => {
    await axios.put(`usuarios/${id_usuario}/`, data, {
        headers: {
            "Content-Type": "multipart/form-data",
        },
    });
};
