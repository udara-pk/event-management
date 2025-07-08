import React from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter } from "react-router-dom";
import { Provider } from "react-redux";
import { store } from "./app/store";
import App from "./App";
import "./styles/global.scss";

ReactDOM.createRoot(document.getElementById("root")!).render(
  <Provider store={store} children={undefined}>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </Provider>
);