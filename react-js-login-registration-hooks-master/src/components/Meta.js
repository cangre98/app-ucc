import React, { useState, useEffect } from "react";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import Select from "react-validation/build/select";
import  Button  from "react-bootstrap/Button";





import AccountServices from "../services/account.service";
import UserService from "../services/user.service";

const Meta = () => {


  useEffect(async () => {

    const response = await AccountServices.consultarCuentaPorid();
    setCuenta(response.data.objectResponse);
    const listaMetas = await UserService.getlistarMetas();
    setListaMetas(listaMetas.data.objectResponse);
    setLoading(false);

  }, [])

  const [cuenta, setCuenta] = useState([]);
  const [isLoading, setLoading] = useState(true);
  const [tipoFiltro, setipoFiltro] = useState("");
  const [meta, setMeta] = useState("");
  const [nMeses, setNMeses] = useState("");
  const [listaMetas, setListaMetas] = useState("");


  if (isLoading) {
    return <div className="App">Loading...</div>;
  }

  const changeTipoFiltro = (e) => {
    const idfiltro = e.target.value;
    console.log(idfiltro)
    const object = listaMetas.find(obj => obj.id == idfiltro);
    setipoFiltro(idfiltro);
    setMeta(object);
  };

  const required = (value) => {
    if (!value) {
      return (
        <div className="invalid-feedback d-block">
          Campo requerido
        </div>
      );
    }
  };


  const changeNMeses = (e) => {
    const nMeses = e.target.value;
    setNMeses(nMeses);
  };

  const handleShow =() =>{
      
  }

  return (
    <>
      <div className="container">
        <div className="row">
          <div className="col">
            <header className="jumbotron">
              <h3><strong className="text-secondary">{cuenta.cuenta.idPersona.primerNombre} {cuenta.cuenta.idPersona.primerApellido}</strong></h3>
              <h3><strong>Disponible: </strong> <span className="text-primary">{cuenta.cuenta.saldo}</span></h3>
              <h3><strong>Ingresos Registrados:</strong> <span className="text-success">{cuenta.sumIngresos.objectResponse}</span>  </h3>
              <h3><strong>Gastos Registrados:</strong> <span className="text-danger">{cuenta.sumEgresos.objectResponse}</span> </h3>

            </header>
          </div>
          <div className="col jumbotron">
            <h1 className="display-6">METAS</h1>
            <Form>
              <div className="form-group col-12">
                <Select
                  className="form-control"
                  name="tipoDocumento"
                  onChange={changeTipoFiltro}
                  value={tipoFiltro}>
                  {
                    listaMetas.map(obj => (
                      <option key={obj.id} value={obj.id}>{obj.nombre}</option>
                    ))
                  }
                </Select>
              </div>
              <div className="form-group col-6">
                <label htmlFor="nDocumento">Numero Meses a Programar</label>
                <Input
                  type="number"
                  className="form-control"
                  name="nMeses"
                  value={nMeses}
                  onChange={changeNMeses}
                  validations={[required]} />
              </div>
            </Form>
            <div className="form-group col-6">
              <h3><strong>Valor:</strong> <span className="text-danger">{meta.valor}</span> </h3>
              <Button variant="success" onClick={handleShow}>
                Calcular Ahorro
              </Button>
            </div>
          </div>
        </div>


        <div className="row">
          <div className="col">
          </div>
        </div>
      </div>

    </>

  );
};

export default Meta;
