import axios from "axios";

const API_URL_CUENTA = "http://localhost:8080//v1/cuenta/";
const API_URL_INGRESO = "http://localhost:8080//v1/ingreso/";


const crearCuenta = (saldo) => {

  const user = JSON.parse(localStorage.getItem('user'));

  return axios
    .post(API_URL_CUENTA + "crear", {
      descripcion: "Presupuesto Sistema",
      detalle: "Presupuesto",
      idPersona: {
          id: user.idPersona.id
      },
      saldo: saldo
    })
    .then((response) => {

      console.log(response);
      return response.data;
      
    });
};


const consultarCuentaPorid = () => {
  
  const user = JSON.parse(localStorage.getItem('user'));

  return axios.get(API_URL_CUENTA + "consultarId/"+user.cuenta);
};

const crearIngreso = (valor) => {

  const user = JSON.parse(localStorage.getItem('user'));

  return axios
    .post(API_URL_INGRESO + "crear", {
      detalle: "Presupuesto",
      idCuenta: {
          id: user.cuenta
      },
      valor: valor
    })
    .then((response) => {

      console.log(response);
      return response.data;
      
    });
};

const AccountServices = {
  crearCuenta,
  crearIngreso,
  consultarCuentaPorid
}

export default AccountServices;
