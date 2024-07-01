import axios from '../../libs/axios';

export const carrerasObtener = async () => (await axios.get('carreras/')).data;

export const carrerasObtenerid = async (id_carrera) => (await axios.get(`carreras/${id_carrera}/`)).data;

export const carrerasEliminar = async (id_carrera) => {
    await axios.delete(`carreras/${id_carrera}/`);
};

export const carrerascreate = async (data) => {
    await axios.post("carreras/", data, {
        headers: {
            "Content-Type": "application/json",
        },
    });
};

export const carrerasupdate = async (data, id_carrera) => {
    await axios.put(`carreras/${id_carrera}/`, data, {
        headers: {
            "Content-Type": "application/json",
        },
    });
};
