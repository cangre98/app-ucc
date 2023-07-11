import React, { useState, useEffect } from "react";
import Form from "react-validation/build/form";
import Select from "react-validation/build/select";

import UserService from "../services/user.service";
import ModalGastos from "./ModalGastos";
import ModalIngresos from "./ModalIngresos";
import ListarGastos from "./ListarGastos";
import ListarIngresos from "./ListarIngresos";


import AccountServices from "../services/account.service";

const Analisis = () => {


  useEffect(async () => {

    const response = await AccountServices.consultarCuentaPorid();


    setCuenta(response.data.objectResponse);
    setLoading(false);

  }, [])

  const [cuenta, setCuenta] = useState([]);
  const [isLoading, setLoading] = useState(true);
  const [tipoFiltro, setipoFiltro] = useState("");


  if (isLoading) {
    return <div className="App">Loading...</div>;
  }

  const changeTipoFiltro = (e) => {
    const tipoFiltro = e.target.value;
    setipoFiltro(tipoFiltro);
  };

  const format = amount => {
    return Number(amount)
      .toFixed(2)
      .replace(/\d(?=(\d{3})+\.)/g, '$&,');
  };


  return (
    <>
      <div className="container">
        <div className="row">
          <div className="col">
            <header className="jumbotron">
              <h3><strong className="text-secondary">{cuenta.cuenta.idPersona.primerNombre} {cuenta.cuenta.idPersona.primerApellido}</strong></h3>
              <h3><strong>Disponible: </strong> $<span className="text-primary">{format(cuenta.cuenta.saldo)}</span></h3>
              <h3><strong>Ingresos Registrados:</strong> $<span className="text-success">{format(cuenta.sumIngresos.objectResponse)}</span>  </h3>
              <h3><strong>Gastos Registrados:</strong> $<span className="text-danger">{format(cuenta.sumEgresos.objectResponse)}</span> </h3>

            </header>
          </div>
          <div className="col">
            <div className="d-flex justify-content-center p-5"><ModalIngresos /></div>
            <div className="d-flex justify-content-center mb-4"><ModalGastos /></div>
            <div className="d-flex justify-content-center ">
              <Form>
                <h5>Seleccione filtro</h5>
                <Select
                  className="form-control"
                  name="tipoDocumento"
                  onChange={changeTipoFiltro}
                  value={tipoFiltro}>
                  <option value="2">Filtro Ingresos</option>
                  <option value="1">Filtro Gastos</option>
                </Select>
              </Form>
            </div>

          </div>
        </div>


        <div className="row">
          <div className="col">
            {tipoFiltro == "1" ? (<ListarGastos />) : (<ListarIngresos />)}

          </div>
        </div>
      </div>

    </>

  );
};

export default Analisis;
