import React from 'react';
import {connect} from 'react-redux';
import {FormattedMessage} from 'react-intl';

import {Errors} from '../../common';
import * as actions from '../actions';
import * as selectors from '../selectors';

const initialState = {
    oldPassword: '',
    newPassword: '',
    confirmNewPassword: '',
    backendNewErrors: null,
    passwordsDoNotMatch: false
};

class ChangePassword extends React.Component {

    constructor(props) {

        super(props);

        this.state = initialState;

    }

    handleOldPasswordChange(event) {
        this.setState({oldPassword: event.target.value});
    }

    handleNewPasswordChange(event) {
        this.setState({newPassword: event.target.value});
    }

    handleConfirmNewPasswordChange(event) {

        this.confirmNewPasswordInput.setCustomValidity('');
        this.setState({confirmNewPassword: event.target.value,
            passwordsDoNotMatch: false});
    
    }

    handleSubmit(event) {

        event.preventDefault();

        if (this.form.checkValidity() && this.checkConfirmNewPassword()) {
            this.changePasword();
        } else {
            this.setBackendErrors(null);
            this.form.classList.add('was-validated');
        }

    }

    checkConfirmNewPassword() {

        if (this.state.newPassword !== this.state.confirmNewPassword) {

            this.confirmNewPasswordInput.setCustomValidity('error');
            this.setState({passwordsDoNotMatch: true});

            return false;

        } else {
            return true;
        }

    }

    changePasword() {

        this.props.changePassword(this.props.id, this.state.oldPassword,
            this.state.newPassword,
            () => this.props.history.push('/'),
            errors => this.setBackendErrors(errors));
        
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
                        <FormattedMessage id="project.users.ChangePassword.title"/>
                    </h5>
                    <div className="card-body">
                    <form ref={node => this.form = node} 
                            className="needs-validation" noValidate onSubmit={(e) => this.handleSubmit(e)}>
                            <div className="form-group row">
                                <label htmlFor="oldPassword" className="col-md-3 col-form-label">
                                    <FormattedMessage id="project.users.ChangePassword.fields.oldPassword"/>
                                </label>
                                <div className="col-md-4">
                                <input ref={node => this.confirmNewPasswordInput = node}
                                        type="password" id="confirmNewPassword" className="form-control"
                                        value={this.state.password}
                                        onChange={(e) => this.handleOldPasswordChange(e)}
                                        autoFocus
                                        required/>
                                    <div className="invalid-feedback">
                                        <FormattedMessage id='project.global.validator.required'/>
                                    </div>
                                </div>
                            </div>
                            <div className="form-group row">
                                <label htmlFor="newPassword" className="col-md-3 col-form-label">
                                    <FormattedMessage id="project.users.ChangePassword.fields.newPassword"/>
                                </label>
                                <div className="col-md-4">
                                    <input type="password" id="newPassword" className="form-control"
                                        value={this.state.password}
                                        onChange={(e) => this.handleNewPasswordChange(e)}
                                        required/>
                                    <div className="invalid-feedback">
                                        <FormattedMessage id='project.global.validator.required'/>
                                    </div>
                                </div>
                            </div>
                            <div className="form-group row">
                                <label htmlFor="confirmNewPassword" className="col-md-3 col-form-label">
                                    <FormattedMessage id="project.users.ChangePassword.fields.confirmNewPassword"/>
                                </label>
                                <div className="col-md-4">
                                    <input type="password" id="confirmNewPassword" className="form-control"
                                        value={this.state.confirmPassword}
                                        onChange={(e) => this.handleConfirmNewPasswordChange(e)}
                                        required/>
                                    <div className="invalid-feedback">
                                        {this.state.passwordsDoNotMatch ?
                                            <FormattedMessage id='project.global.validator.passwordsDoNotMatch'/> :
                                            <FormattedMessage id='project.global.validator.required'/>}
                                        
                                    </div>
                                </div>
                            </div>
                            <div className="form-group row">
                                <div className="offset-md-3 col-md-1">
                                    <button type="submit" className="btn btn-primary">
                                        <FormattedMessage id="project.global.buttons.save"/>
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

const mapStateToProps = (state) => ({
    id: selectors.getUser(state).id
});

const mapDispatchToProps = {
    changePassword: actions.changePassword
};

export default connect(mapStateToProps, mapDispatchToProps)(ChangePassword);
