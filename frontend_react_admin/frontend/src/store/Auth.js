import { create } from "zustand";
import { persist } from "zustand/middleware";



export const useAuthStore = create(
    persist((set) => ({
        token: "",
        isAuth: false,
        setToken: (token) => set({ token, isAuth: true }), // Actualiza el token y establece isAuth a true
        logout: () => set({ token: "", isAuth: false }), // Limpia el token y establece isAuth a false
    }), 
    { 
        name: "auth", // El estado se guardará con el nombre 'auth' en el almacenamiento local
    })
);
