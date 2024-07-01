import axios from '../../libs/axios';

export const cursosObtener = async () => (await axios.get('cursos/')).data;

export const cursosObtenerid = async (id_curso) => (await axios.get(`cursos/${id_curso}/`)).data;

export const cursosEliminar = async (id_curso) => {
    await axios.delete(`cursos/${id_curso}/`);
};

export const cursoscreate = async (data) => {
    await axios.post("cursos/", data, {
        headers: {
            "Content-Type": "application/json",
        },
    });
};

export const cursosupdate = async (data, id_curso) => {
    await axios.put(`cursos/${id_curso}/`, data, {
        headers: {
            "Content-Type": "application/json",
        },
    });
};
