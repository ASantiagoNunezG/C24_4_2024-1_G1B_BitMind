import axios from '../../libs/axios';

export const archivosObtener = async () => (await axios.get('archivos/')).data;

export const archivosObtenerid = async (id_archivo) => (await axios.get(`archivos/${id_archivo}/`)).data;

export const archivosEliminar = async (id_archivo) => {
    await axios.delete(`archivos/${id_archivo}/`);
};

export const archivoscreate = async (data) => {
    await axios.post("archivos/", data, {
        headers: {
            "Content-Type": "multipart/form-data",
        },
    });
};

export const archivosupdate = async (data, id_archivo) => {
    await axios.put(`archivos/${id_archivo}/`, data, {
        headers: {
            "Content-Type": "multipart/form-data",
        },
    });
};
