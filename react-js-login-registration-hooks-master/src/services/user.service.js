import axios from "axios";

const API_URL = "http://localhost:8080//v1/persona/";
const API_URL_GASTOS = "http://localhost:8080/v1/gasto/";
const API_URL_EGRESO = "http://localhost:8080/v1/egreso/";
const API_URL_INGRESO = "http://localhost:8080/v1/ingreso/";
const API_URL_INVERSION = "http://localhost:8080/v1/inversion/";

const getPublicContent = () => {
  return axios.get(API_URL + "all");
};

const getlistarTiposDocumento = () => {
  return axios.get(API_URL + "listarTiposDocumento");
};

const getlistarGastos = () => {
  return axios.get(API_URL_GASTOS + "consultarTodos");
};

const registerEgreso = (data) => {
  return axios.post(API_URL_EGRESO + "crear", data);
};

const getAllEgresos = (cuenta,egreso) => {
  return axios.get(API_URL_EGRESO + "consultaEgresosAll/"+cuenta+"/"+egreso+"/0");
};

const getAllEgresosDate = (cuenta,egreso, date) => {
  return axios.get(API_URL_EGRESO + "consultaEgresosAll/"+cuenta+"/"+egreso+"/"+date);
};

const registerIngreso = (data) => {
  return axios.post(API_URL_INGRESO + "crear", data);
}; 

const getIngresosPorCuenta = (cuenta) => {
  return axios.get(API_URL_INGRESO + "consultarIdcuenta/"+cuenta+"/0");
};

const getIngresosPorCuentaFecha = (cuenta, fecha) => {
  return axios.get(API_URL_INGRESO + "consultarIdcuenta/"+cuenta+"/"+fecha);
};

const getlistarMetas = () => {
  return axios.get(API_URL_INVERSION + "consultarTodos");
};

const getUserBoard = () => {
  return axios.get(API_URL + "user");
};

const getModeratorBoard = () => {
  return axios.get(API_URL + "mod");
};

const getAdminBoard = () => {
  return axios.get(API_URL + "admin");
};

const UserService = {
  getPublicContent,
  getlistarTiposDocumento,
  getUserBoard,
  getModeratorBoard,
  getAdminBoard,
  getlistarGastos,
  registerEgreso,
  registerIngreso,
  getAllEgresos,
  getIngresosPorCuenta,
  getIngresosPorCuentaFecha,
  getAllEgresosDate,
  getlistarMetas,
}

export default UserService;
