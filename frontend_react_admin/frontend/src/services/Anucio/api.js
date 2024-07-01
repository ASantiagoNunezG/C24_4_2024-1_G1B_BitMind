import axios from '../../libs/axios';

export const anunciosObtener = async () => (await axios.get('anuncios/')).data;

export const anunciosObtenerid = async (id_anuncio) => (await axios.get(`anuncios/${id_anuncio}/`)).data;

export const anunciosEliminar = async (id_anuncio) => {
    await axios.delete(`anuncios/${id_anuncio}/`);
};

export const anuncioscreate = async (data) => {
    await axios.post("anuncios/", data, {
        headers: {
            "Content-Type": "multipart/form-data",
        },
    });
};

export const anunciosupdate = async (data, id_anuncio) => {
    await axios.put(`anuncios/${id_anuncio}/`, data, {
        headers: {
            "Content-Type": "multipart/form-data",
        },
    });
};
