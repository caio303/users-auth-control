import axios from 'axios';

export const crudApi = axios.create({
    baseURL: "http://localhost:8080",
})