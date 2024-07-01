import axios from '../../libs/axios';

export const comentariosObtener = async () => (await axios.get('comentarios/')).data;

export const comentariosObtenerid = async (id_comentario) => (await axios.get(`comentarios/${id_comentario}/`)).data;

export const comentariosEliminar = async (id_comentario) => {
    await axios.delete(`comentarios/${id_comentario}/`);
};

export const comentarioscreate = async (data) => {
    await axios.post("comentarios/", data, {
        headers: {
            "Content-Type": "multipart/form-data",
        },
    });
};

export const comentariosupdate = async (data, id_comentario) => {
    await axios.put(`comentarios/${id_comentario}/`, data, {
        headers: {
            "Content-Type": "multipart/form-data",
        },
    });
};
