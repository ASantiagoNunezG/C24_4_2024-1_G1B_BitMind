//estos accions no me pediran eso xd el header los otros si l otengo que hacer con eso  aca tpdas las que no sean jwt 
import axios from 'axios';


const axiosAuth = axios.create({
    baseURL: 'http://127.0.0.1:8000/api/',
 });


export const loginPost = async (userData ) => (await axiosAuth.post('token/', userData)).data;
export const RegisterPost = async (RegisterData) => (await axiosAuth.post('register', RegisterData)).data;
//metodo get para obtener xd

