import axios from '../../libs/axios';

export const publicacionesObtener = async () => (await axios.get('publicaciones/')).data;

export const publicacionesObtenerid = async (id_publicacion) => (await axios.get(`publicaciones/${id_publicacion}/`)).data;

export const publicacionesEliminar = async (id_publicacion) => {
    await axios.delete(`publicaciones/${id_publicacion}/`);
};

export const publicacionescreate = async (data) => {
    await axios.post("publicaciones/", data, {
        headers: {
            "Content-Type": "multipart/form-data",
        },
    });
};

export const publicacionesupdate = async (data, id_publicacion) => {
    await axios.put(`publicaciones/${id_publicacion}/`, data, {
        headers: {
            "Content-Type": "multipart/form-data",
        },
    });
};
