import React from 'react';
import {connect} from 'react-redux';
import {FormattedMessage} from 'react-intl';

import {Errors} from '../../common';
import * as actions from '../actions';

const initialState = {
    userName: '',
    password: '',
    confirmPassword: '',
    firstName: '',
    lastName: '',
    email: '',
    backendErrors: null,
    passwordsDoNotMatch: false
};

const reauthenticationCallback = (dispatch, history) => () => {
    history.push('/users/login');
    dispatch(actions.logout());
}

class SignUp extends React.Component {

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

    handleConfirmPasswordChange(event) {

        this.confirmPasswordInput.setCustomValidity('');
        this.setState({confirmPassword: event.target.value,
            passwordsDoNotMatch: false});
    
    }

    handleFirstNameChange(event) {
        this.setState({firstName: event.target.value});
    }

    handleLastNameChange(event) {
        this.setState({lastName: event.target.value});
    }

    handleEmailChange(event) {
        this.setState({email: event.target.value});
    }

    handleSubmit(event) {

        event.preventDefault();

        if (this.form.checkValidity() && this.checkConfirmPassword()) {
            this.signUp();
        } else {
            this.setBackendErrors(null);
            this.form.classList.add('was-validated');
        }

    }

    checkConfirmPassword() {

        if (this.state.password !== this.state.confirmPassword) {

            this.confirmPasswordInput.setCustomValidity('error');
            this.setState({passwordsDoNotMatch: true});

            return false;

        } else {
            return true;
        }

    }

    signUp() {

        this.props.dispatch(actions.signUp(
            {userName: this.state.userName.trim(),
            password: this.state.password,
            firstName: this.state.firstName.trim(),
            lastName: this.state.lastName.trim(),
            email: this.state.email.trim()},
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
                <Errors errors={this.state.backendErrors} onClose={() => this.handleErrorsClose()}/>
                <div className="card bg-light border-dark">
                    <h5 className="card-header">
                        <FormattedMessage id="project.users.SignUp.title"/>
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
                                <label htmlFor="confirmPassword" className="col-md-3 col-form-label">
                                    <FormattedMessage id="project.users.SignUp.fields.confirmPassword"/>
                                </label>
                                <div className="col-md-4">
                                <input ref={node => this.confirmPasswordInput = node}
                                        type="password" id="confirmPassword" className="form-control"
                                        value={this.state.confirmPassword}
                                        onChange={(e) => this.handleConfirmPasswordChange(e)}
                                        required/>
                                    <div className="invalid-feedback">
                                        {this.state.passwordsDoNotMatch ?
                                            <FormattedMessage id='project.global.validator.passwordsDoNotMatch'/> :
                                            <FormattedMessage id='project.global.validator.required'/>}
                                    </div>
                                </div>
                            </div>
                            <div className="form-group row">
                                <label htmlFor="firstName" className="col-md-3 col-form-label">
                                    <FormattedMessage id="project.global.fields.firstName"/>
                                </label>
                                <div className="col-md-4">
                                    <input type="text" id="firstName" className="form-control"
                                        value={this.state.firstName}
                                        onChange={(e) => this.handleFirstNameChange(e)}
                                        required/>
                                    <div className="invalid-feedback">
                                        <FormattedMessage id='project.global.validator.required'/>
                                    </div>
                                </div>
                            </div>
                            <div className="form-group row">
                                <label htmlFor="lastName" className="col-md-3 col-form-label">
                                    <FormattedMessage id="project.global.fields.lastName"/>
                                </label>
                                <div className="col-md-4">
                                    <input type="text" id="lastName" className="form-control"
                                        value={this.state.lastName}
                                        onChange={(e) => this.handleLastNameChange(e)}
                                        required/>
                                    <div className="invalid-feedback">
                                        <FormattedMessage id='project.global.validator.required'/>
                                    </div>
                                </div>
                            </div>
                            <div className="form-group row">
                                <label htmlFor="email" className="col-md-3 col-form-label">
                                    <FormattedMessage id="project.global.fields.email"/>
                                </label>
                                <div className="col-md-4">
                                    <input type="email" id="email" className="form-control"
                                        value={this.state.email}
                                        onChange={(e) => this.handleEmailChange(e)}
                                        required/>
                                    <div className="invalid-feedback">
                                        <FormattedMessage id='project.global.validator.email'/>
                                    </div>
                                </div>
                            </div>
                            <div className="form-group row">
                                <div className="offset-md-3 col-md-2">
                                    <button type="submit" className="btn btn-primary">
                                        <FormattedMessage id="project.users.SignUp.title"/>
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

export default connect()(SignUp);