import React from 'react';
import {connect} from 'react-redux';

import {ErrorDialog, Loader} from '../../common';
import * as actions from '../actions';
import * as selectors from '../selectors';

const errorMapStateToProps = state => ({
    error: selectors.getError(state)
});

const errorMapDispatchToProps = dispatch => ({
    onClose: () =>dispatch(actions.error(null))
});

const ConnectedErrorDialog = 
    connect(errorMapStateToProps, errorMapDispatchToProps)(ErrorDialog);

const loadingMapStateToProps = state => ({
    loading: selectors.isLoading(state)
});

const ConnectedLoader = connect(loadingMapStateToProps)(Loader);

const AppGlobalComponents = () => (

    <div>
        <ConnectedErrorDialog/>
        <ConnectedLoader/>
    </div>

);

export default AppGlobalComponents;