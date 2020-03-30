import fetch from "unfetch";

export const getAllDevelopers = () => fetch('api/v1/developers');