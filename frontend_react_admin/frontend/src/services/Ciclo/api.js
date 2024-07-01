import axios from '../../libs/axios';

export const ciclosObtener = async () => (await axios.get('ciclos/')).data;

export const ciclosObtenerid = async (id_ciclo) => (await axios.get(`ciclos/${id_ciclo}/`)).data;

export const ciclosEliminar = async (id_ciclo) => {
    await axios.delete(`ciclos/${id_ciclo}/`);
};

export const cicloscreate = async (data) => {
    await axios.post("ciclos/", data, {
        headers: {
            "Content-Type": "application/json",
        },
    });
};

export const ciclosupdate = async (data, id_ciclo) => {
    await axios.put(`ciclos/${id_ciclo}/`, data, {
        headers: {
            "Content-Type": "application/json",
        },
    });
};
