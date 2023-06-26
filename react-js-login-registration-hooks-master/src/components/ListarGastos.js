import React, { useState, useEffect } from "react";
import Form from "react-validation/build/form";


import Select from "react-validation/build/select";
import UserService from "../services/user.service";
import AuthService from "../services/auth.service";



const ListarGastos = () => {

  const currentUser = AuthService.getCurrentUser();

  const [tipoGasto, setTipoGasto] = useState("");
  const [listaGastos, setListaGastos] = useState([]);
  const [arrayGastos, setArrayGastos] = useState([]);


  useEffect(async () => {
    const response = await UserService.getlistarGastos()
    setListaGastos(response.data.objectResponse)
  }, [])

  const getAllGastosChange = (e) => {
    const tipoGastoLocal = e.target.value;
    UserService.getAllEgresos(currentUser.cuenta, tipoGastoLocal).then(
      (response) => {
        if(response.data.statusCode == 200){
          setArrayGastos(response.data.objectResponse)
        }else{
          setArrayGastos([]);
        }        
      })


  };

  return (
    <>
      <div className="jumbotron" >
        <div className="container">
          <div className="row">
            <div className="col-3 mb-4" >
              <h3>Filtrar Gastos</h3>
              <Form>
                <Select
                  className="form-control"
                  name="tipoDocumento"
                  onChange={getAllGastosChange}
                  value={tipoGasto}>
                  <option value="0"></option>
                  {

                    listaGastos.map(obj => (
                      <option key={obj.id} value={obj.id}>{obj.descripcion}</option>
                    ))
                  }
                </Select>
              </Form>
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
                  {arrayGastos.map((listValue, index) => {
                    return (
                      <tr key={index}>
                        <td>{listValue.id}</td>
                        <td>{listValue.detalle}</td>
                        <td>{listValue.fecha}</td>
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

export default ListarGastos;
