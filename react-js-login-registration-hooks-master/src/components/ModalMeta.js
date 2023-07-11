import React, { useState, useRef,useEffect } from "react";

import  Button  from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import Select from "react-validation/build/select";
import CheckButton from "react-validation/build/button";

import UserService from "../services/user.service";


const required = (value) => {
  if (!value) {
    return (
      <div className="invalid-feedback d-block">
        Campo requerido
      </div>
    );
  }
};


const ModalMeta = () => {

  const form = useRef();
  const checkBtn = useRef();

  const [show, setShow] = useState(false);

  const handleCloseSave = () =>{
    form.current.validateAll();
    if (checkBtn.current.context._errors.length === 0) {
      setShow(false);
      const user = JSON.parse(localStorage.getItem('user'));

      

      const data = {
        idCuenta: {
          id: user.cuenta
        },
        detalle: detalleIngreso,
        fechaIngreso :fecha,
        valor: valor
      }

      UserService.registerIngreso(data).then(
        (response) => {
         console.log("response");
         console.log(response);
         window.location.reload(false);
        },
        (error) => {
          const resMessage =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();
        }
      );
    }   
  } 

  const handleClose = () =>{
    setShow(false);
  }

  const handleShow = () => setShow(true);

  const [valor, setValor] = useState("");
  const [fecha, setFecha] = useState("");
  const [detalleIngreso, setDetalleIngreso] = useState("");
  

  const onChangeValor = (e) => {
    const valor = e.target.value;
    setValor(valor);
  };

  const onChangeFecha = (e) => {    
    const fecha = e.target.value;
    setFecha(fecha);
  };

  const onChangeDetalleIngreso= (e) => {
    const detalleIngreso = e.target.value;
    setDetalleIngreso(detalleIngreso);
  };
  

  return (
    <>
    <Button variant="secondary"  onClick={handleShow}>
       Agregar Meta
      </Button>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Agregar Meta</Modal.Title>
        </Modal.Header>
        <Modal.Body>
         <Form  ref={form}>      
          
            <label>Detalle de la Meta</label>
            <Input 
                  type="text"
                  className="form-control"
                  name="detalle"
                  value={detalleIngreso}
                  onChange={onChangeDetalleIngreso}
                  validations={[required]}/>
            
            <label>Valor Meta</label>
            <Input 
                  type="text"
                  className="form-control"
                  name="valor"
                  value={valor}
                  onChange={onChangeValor}
                  validations={[required]}/>
            
            <CheckButton style={{ display: "none" }} ref={checkBtn} />
         </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Cerrar
          </Button>
          <Button variant="primary" onClick={handleCloseSave}>
            Guardar
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
};

export default ModalMeta;
