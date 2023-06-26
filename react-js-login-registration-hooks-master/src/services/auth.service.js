import axios from "axios";

const API_URL = "http://localhost:8080//v1/usuario/";

const register = (registro) => {
  return axios.post(API_URL + "crear", registro);
};

const login = (username, password) => {
  return axios
    .post(API_URL + "login", {
      correo: username,
      clave : password,
    })
    .then((response) => {

      if (response.data.statusCode == 200) {
        localStorage.setItem("user", JSON.stringify(response.data.objectResponse));
      }
      return response.data;
      
    });
};

const logout = () => {
  localStorage.removeItem("user");
  return axios.post(API_URL + "signout").then((response) => {
    return response.data;
  });
};

const getCurrentUser = () => {
  return JSON.parse(localStorage.getItem("user"));
};

const AuthService = {
  register,
  login,
  logout,
  getCurrentUser,
}

export default AuthService;
