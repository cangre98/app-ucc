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


const ModalGastos = () => {

  const form = useRef();
  const checkBtn = useRef();

  const [show, setShow] = useState(false);

  const handleCloseSave = () =>{
    form.current.validateAll();
    if (checkBtn.current.context._errors.length === 0) {
      setShow(false);
      const user = JSON.parse(localStorage.getItem('user'));

      const data = {
        cuenta: {
          id: user.cuenta
        },
        gasto: {
            id: tipoGasto
        },
        detalle: detalleGasto,
        fecha: fecha,
        valor: valor
      }

      UserService.registerEgreso(data).then(
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

  const [tipoGasto, setTipoGasto] = useState("");
  const [valor, setValor] = useState("");
  const [fecha, setFecha] = useState("");
  const [detalleGasto, setDetalleGasto] = useState("");
  const [listaGastos, setListaGastos] = useState([]);


  useEffect(async () => {
    const response = await UserService.getlistarGastos()
    setListaGastos(response.data.objectResponse)
  }, [])
  

  const onChangeTipoGasto = (e) => {
    const tipoGasto = e.target.value;
    setTipoGasto(tipoGasto);
  };

  const onChangeFecha = (e) => {    
    const fecha = e.target.value;
    setFecha(fecha);
  };

  const onChangeValor = (e) => {
    const valor = e.target.value;
    setValor(valor);
  };

  const onChangeDetalleGasto = (e) => {
    const detalleGasto = e.target.value;
    setDetalleGasto(detalleGasto);
  };
  

  return (
    <>
    <Button variant="danger"  onClick={handleShow}>
       Agregar Gasto
      </Button>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Agregar Gasto</Modal.Title>
        </Modal.Header>
        <Modal.Body>
         <Form  ref={form}>
            <label>Tipo de Gasto</label>
            <Select
                  className="form-control"
                  name="tipoDocumento"   
                  onChange={onChangeTipoGasto}
                  value={tipoGasto}
                  validations={[required]}>
                  {
                       listaGastos.map(obj => (
                      <option key={obj.id} value={obj.id}>{obj.descripcion}</option>
                    ))
                  }
            </Select>
          
            <label>Detalle del Gasto</label>
            <Input 
                  type="text"
                  className="form-control"
                  name="detalle"
                  value={detalleGasto}
                  onChange={onChangeDetalleGasto}
                  validations={[required]}/>
            <label>Fecha del Gasto</label>
            <Input  
                  type="date"
                  className="form-control"
                  value={fecha}
                  onChange={onChangeFecha}
                  validations={[required]}/>
            <label>Valor Gasto</label>
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

export default ModalGastos;
