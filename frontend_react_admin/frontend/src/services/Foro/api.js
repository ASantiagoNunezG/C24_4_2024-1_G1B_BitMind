import axios from '../../libs/axios';

export const forosObtener = async () => (await axios.get('foros/')).data;

export const forosObtenerid = async (id_foro) => (await axios.get(`foros/${id_foro}/`)).data;

export const forosEliminar = async (id_foro) => {
    await axios.delete(`foros/${id_foro}/`);
};

export const foroscreate = async (data) => {
    await axios.post("foros/", data, {
        headers: {
            "Content-Type": "application/json",
        },
    });
};

export const forosupdate = async (data, id_foro) => {
    await axios.put(`foros/${id_foro}/`, data, {
        headers: {
            "Content-Type": "application/json",
        },
    });
};
