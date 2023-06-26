import React, { useState, useEffect } from "react";
import Form from "react-validation/build/form";
import {useNavigate} from 'react-router-dom';

import UserService from "../services/user.service";
import AccountServices from "../services/account.service";

const Home = () => {
  const navigate = useNavigate();

  const [presupuesto, setPresupuesto] = useState("");
  const [mensaje, setMensaje] = useState('')


  const handlePresupuesto = (e) => {
    e.preventDefault();
    AccountServices.crearCuenta(presupuesto)

    console.log("PRESUPUESTO")
    console.log(presupuesto)

    if(!presupuesto || presupuesto < 0) {
      navigate("/login");

    } 

  }

  return (
    <div className="card card-container text-center">
      <Form onSubmit={handlePresupuesto} className="form-group">

        <h5 className="card-title" >Definir Presupuesto</h5>
        <div className="input-group-prepend mt-2">
          <span className="input-group-text">$</span>
          <input
            className="form-control"
            type="number"
            placeholder="Añade tu Presupuesto"
            value={presupuesto}
            onChange={e => setPresupuesto(Number(e.target.value))}
          />
        </div>
        <input type="submit" className="btn btn-outline-success mt-2  btn-block" value="Añadir" />
      </Form>
    </div>
  );
};

export default Home;
