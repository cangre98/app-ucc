import React, { useState, useEffect } from "react";
import Form from "react-validation/build/form";


import Select from "react-validation/build/select";
import UserService from "../services/user.service";
import AuthService from "../services/auth.service";



const ListarIngresos = () => {

  const currentUser = AuthService.getCurrentUser();


  const [arrayIngresos, setArrayIngresos] = useState([]);


  useEffect(async () => {
    const response = await UserService.getIngresosPorCuenta(currentUser.cuenta)
    setArrayIngresos(response.data.objectResponse)
  }, [])

  return (
    <>
      <div className="jumbotron" >
        <div className="container">
          <div className="row">
            <div className="col-6 mb-4" >
              <h3>Historico Ingresos</h3>              
            </div>
          </div>
          <div className="row">
            <div className="col">
              <table className="table">
                <thead className="thead-dark">
                  <tr>
                    <th scope="col">#</th>
                    <th scope="col">Detalle</th>
                    <th scope="col">Fecha</th>
                    <th scope="col">Valor</th>
                  </tr>
                </thead>
                <tbody>
                  {arrayIngresos.map((listValue, index) => {
                    return (
                      <tr key={index}>
                        <td>{listValue.id}</td>
                        <td>{listValue.detalle}</td>
                        <td>{listValue.fechaIngreso}</td>
                        <td>{listValue.valor}</td>
                      </tr>
                    );
                  })}
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default ListarIngresos;
