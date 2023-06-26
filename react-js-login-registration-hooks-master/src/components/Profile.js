import React from "react";
import AuthService from "../services/auth.service";

const Profile = () => {
  const currentUser = AuthService.getCurrentUser();

  return (
    <div className="container">
      <header className="jumbotron">
        <h1 className="display-4 mb-2">Hola! <strong>{currentUser.idPersona.primerNombre} {currentUser.idPersona.primerApellido}</strong></h1>
        <p class="lead">
          Bienvenido a la aplicación de control gasto e ingresos, su correo registrado es <strong>{currentUser.correo}</strong>
        </p>
      </header>
    </div>
  );
};

export default Profile;
