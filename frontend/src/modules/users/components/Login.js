import React from 'react';
import {connect} from 'react-redux';
import {Link} from 'react-router-dom';
import {FormattedMessage} from 'react-intl';

import {Errors} from '../../common';
import * as actions from '../actions';

const initialState = {
    userName: '',
    password: '',
    backendErrors: null
};

const reauthenticationCallback = (dispatch, history) => () => {
    history.push('/users/login');
    dispatch(actions.logout());
}

class Login extends React.Component {

    constructor(props) {

        super(props);

        this.state = initialState;

    }

    handleUserNameChange(event) {
        this.setState({userName: event.target.value});
    }

    handlePasswordChange(event) {
        this.setState({password: event.target.value});
    }

    handleSubmit(event) {

        event.preventDefault();

        if (this.form.checkValidity()) {
            this.login();
        } else {
            this.setBackendErrors(null);
            this.form.classList.add('was-validated');
        }

    }

    login() {

        this.props.dispatch(actions.login(
            this.state.userName.trim(),
            this.state.password,
            () => this.props.history.push('/'),
            errors => this.setBackendErrors(errors),
            reauthenticationCallback(this.props.dispatch, this.props.history)
        ));
        
    }

    setBackendErrors(backendErrors) {
        this.setState({backendErrors});
    }

    handleErrorsClose() {
        this.setState({backendErrors: null});
    }

    render() {

        return (
            <div>
                <p className="text-center">
                    <Link to="/users/signup">
                        <FormattedMessage id="project.users.SignUp.title"/>
                    </Link>
                </p>
                <Errors errors={this.state.backendErrors} onClose={() => this.handleErrorsClose()}/>
                <div className="card bg-light border-dark">
                    <h5 className="card-header">
                        <FormattedMessage id="project.users.Login.title"/>
                    </h5>
                    <div className="card-body">
                    <form ref={node => this.form = node} 
                            className="needs-validation" noValidate 
                            onSubmit={(e) => this.handleSubmit(e)}>
                            <div className="form-group row">
                                <label htmlFor="userName" className="col-md-3 col-form-label">
                                    <FormattedMessage id="project.global.fields.userName"/>
                                </label>
                                <div className="col-md-4">
                                    <input type="text" id="userName" className="form-control"
                                        value={this.state.userName}
                                        onChange={(e) => this.handleUserNameChange(e)}
                                        autoFocus
                                        required/>
                                    <div className="invalid-feedback">
                                        <FormattedMessage id='project.global.validator.required'/>
                                    </div>
                                </div>
                            </div>
                            <div className="form-group row">
                                <label htmlFor="password" className="col-md-3 col-form-label">
                                    <FormattedMessage id="project.global.fields.password"/>
                                </label>
                                <div className="col-md-4">
                                    <input type="password" id="password" className="form-control"
                                        value={this.state.password}
                                        onChange={(e) => this.handlePasswordChange(e)}
                                        required/>
                                    <div className="invalid-feedback">
                                        <FormattedMessage id='project.global.validator.required'/>
                                    </div>
                                </div>
                            </div>
                            <div className="form-group row">
                                <div className="offset-md-3 col-md-1">
                                    <button type="submit" className="btn btn-primary">
                                        <FormattedMessage id="project.users.Login.title"/>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        );

    }

}

export default connect()(Login);