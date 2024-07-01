import axios from '../../libs/axios';

export const valoracionesObtener = async () => (await axios.get('valoraciones/')).data;

export const valoracionesObtenerid = async (id_valoracion) => (await axios.get(`valoraciones/${id_valoracion}/`)).data;

export const valoracionesEliminar = async (id_valoracion) => {
    await axios.delete(`valoraciones/${id_valoracion}/`);
};

export const valoracionescreate = async (data) => {
    await axios.post("valoraciones/", data, {
        headers: {
            "Content-Type": "multipart/form-data",
        },
    });
};

export const valoracionesupdate = async (data, id_valoracion) => {
    await axios.put(`valoraciones/${id_valoracion}/`, data, {
        headers: {
            "Content-Type": "multipart/form-data",
        },
    });
};
