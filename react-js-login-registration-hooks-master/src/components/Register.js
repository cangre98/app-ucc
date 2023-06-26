import React, { useState, useRef, useEffect } from "react";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import Select from "react-validation/build/select";
import CheckButton from "react-validation/build/button";
import { isEmail } from "validator";

import AuthService from "../services/auth.service";
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

const validEmail = (value) => {
  if (!isEmail(value)) {
    return (
      <div className="invalid-feedback d-block">
        This is not a valid email.
      </div>
    );
  }
};

const vnombre = (value) => {
  if (value.length < 3 || value.length > 20) {
    return (
      <div className="invalid-feedback d-block">
        Ingrese almenos un nombre
      </div>
    );
  }
};


const vapellidos = (value) => {
  if (value.length < 3 || value.length > 20) {
    return (
      <div className="invalid-feedback d-block">
        Ingrese almenos un apellido
      </div>
    );
  }
};


const vpassword = (value) => {
  if (value.length < 6) {
    return (
      <div className="invalid-feedback d-block">
        Al menos 6 carácteres
      </div>
    );
  }
};

const Register = (props) => {
  const form = useRef();
  const checkBtn = useRef();

  const [nombres, setNombres] = useState("");
  const [apellidos, setApellidos] = useState("");
  const [tipoDocumento, setTipoDocumento] = useState("");
  const [nDocumento, setNDocumento] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [successful, setSuccessful] = useState(false);
  const [message, setMessage] = useState("");
  const [listaTiposDocumento, setListaTiposDocumento] = useState([]);


  useEffect(async () => {

    const response = await UserService.getlistarTiposDocumento()
    setListaTiposDocumento(response.data.objectResponse)
  }, [])

  const onChangeNombres = (e) => {
    const nombres = e.target.value;
    setNombres(nombres);
  };

  const onChangeApellidos = (e) => {
    const apellidos = e.target.value;
    setApellidos(apellidos);
  };

  const onChangeTipoDocumento = (e) => {
    const tipoDocumento = e.target.value;
    setTipoDocumento(tipoDocumento);
  };

  const onChangeNDocumento = (e) => {
    const nDocumento = e.target.value;
    setNDocumento(nDocumento);
  };

  const onChangeEmail = (e) => {
    const email = e.target.value;
    setEmail(email);
  };

  const onChangePassword = (e) => {
    const password = e.target.value;
    setPassword(password);
  };

  const handleRegister = (e) => {
    e.preventDefault();

    setMessage("");
    setSuccessful(false);

    form.current.validateAll();

    if (checkBtn.current.context._errors.length === 0) {
      
      const arrayNombre = nombres.split(" ");
      const arrayApellido = apellidos.split(" ");
      const registro = {
        'idPersona': {
          'idTipoDocumento': {
            'id': tipoDocumento
          },
          'numeroDocumento': nDocumento,
          'primerNombre': arrayNombre[0],
          'primerApellido': arrayApellido[0],
          'segundoNombre': arrayNombre[1],
          'segundoApellido': arrayApellido[1],
          'telefono': '11111',
          'idTipoPersona': {
            'id': 2
          }
        },
        'correo': email,
        'clave': password

      }

      AuthService.register(registro).then(
        (response) => {
          setMessage(response.data.message);
          setSuccessful(true);
        },
        (error) => {
          const resMessage =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();

          setMessage(resMessage);
          setSuccessful(false);
        }
      );
    }
  };

  return (
    <div className="col-md-12">
      <div className="card card-container">
        <Form onSubmit={handleRegister} ref={form}>
          {!successful && (
            <div>
              <div className="form-group">
                <label htmlFor="nombre">Nombres</label>
                <Input
                  type="text"
                  className="form-control"
                  name="nombre"
                  value={nombres}
                  onChange={onChangeNombres}
                  validations={[required, vnombre]}
                />
              </div>
              <div className="form-group">
                <label htmlFor="apellidos">Apellidos</label>
                <Input
                  type="text"
                  className="form-control"
                  name="apellidos"
                  value={apellidos}
                  onChange={onChangeApellidos}
                  validations={[required]}
                />
              </div>


              <div className="form-group">
                <label htmlFor="apellidos">Tipo Documento</label>
                <Select
                  className="form-control"
                  name="tipoDocumento"
                  onChange={onChangeTipoDocumento}
                  value={tipoDocumento}
                  validations={[required]}>
                    <option  value="0"></option>
                  {

                    listaTiposDocumento.map(obj => (
                      <option key={obj.id} value={obj.id}>{obj.descripcion}</option>
                    ))
                  }
                </Select>
              </div>

              <div className="form-group">
                <label htmlFor="nDocumento">Numero Documento</label>
                <Input
                  type="number"
                  className="form-control"
                  name="nDocumento"
                  value={nDocumento}
                  onChange={onChangeNDocumento}
                  validations={[required, required]}
                />
              </div>

              <div className="form-group">
                <label htmlFor="email">Email</label>
                <Input
                  type="text"
                  className="form-control"
                  name="email"
                  value={email}
                  onChange={onChangeEmail}
                  validations={[required, validEmail]}
                />
              </div>

              <div className="form-group">
                <label htmlFor="password">Contraseña</label>
                <Input
                  type="password"
                  className="form-control"
                  name="password"
                  value={password}
                  onChange={onChangePassword}
                  validations={[required, vpassword]}
                />
              </div>

              <div className="form-group">
                <button className="btn btn-outline-primary btn-block">Registrar</button>
              </div>
            </div>
          )}

          {message && (
            <div className="form-group">
              <div
                className={
                  successful ? "alert alert-success" : "alert alert-danger"
                }
                role="alert"
              >
                {message}
              </div>
            </div>
          )}
          <CheckButton style={{ display: "none" }} ref={checkBtn} />
        </Form>
      </div>
    </div>
  );
};

export default Register;
