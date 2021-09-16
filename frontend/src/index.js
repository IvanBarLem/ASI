import React from 'react';
import ReactDOM from 'react-dom';
import {Provider} from 'react-redux';
import {IntlProvider} from 'react-intl';

import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap';
import '@fortawesome/fontawesome-free-webfonts/css/fontawesome.css';
import '@fortawesome/fontawesome-free-webfonts/css/fa-solid.css';

import registerServiceWorker from './registerServiceWorker';
import './polyfills';
import configureStore from './store';
import {App} from './modules/app';
import backend from './backend';
import {NetworkError} from './backend';
import app from './modules/app';
import {initReactIntl} from './i18n';

/* Configure store. */
const store = configureStore();

/* Configure backend proxy. */
backend.init(error => store.dispatch(app.actions.error(new NetworkError())));

/* Configure i18n. */
const {locale, messages} = initReactIntl();

/* Render application. */
ReactDOM.render(
    <Provider store={store}>
        <IntlProvider locale={locale} messages={messages}>
            <App/>
        </IntlProvider>
    </Provider>, 
    document.getElementById('root'));

registerServiceWorker();
