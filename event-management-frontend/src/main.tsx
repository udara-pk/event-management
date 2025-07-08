import { Provider } from 'react-redux';
import ReactDOM from 'react-dom/client';
import store from './app/store';
import App from './App';
import React from 'react';
import { BrowserRouter } from 'react-router-dom';

const root = ReactDOM.createRoot(document.getElementById('root')!);
root.render(
  <Provider store={store}>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </Provider>
);