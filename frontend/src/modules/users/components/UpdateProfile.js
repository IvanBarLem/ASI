import React from 'react';
import {connect} from 'react-redux';
import {FormattedMessage} from 'react-intl';

import {Errors} from '../../common';
import * as actions from '../actions';
import * as selectors from '../selectors';

class UpdateProfile extends React.Component {

    constructor(props) {

        super(props);

        this.state = {
            firstName: props.user.firstName,
            lastName: props.user.lastName,
            email: props.user.email,
            backendErrors: null,
            passwordsDoNotMatch: false
        };

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

        if (this.form.checkValidity()) {
            this.updateProfile();
        } else {
            this.setBackendErrors(null);
            this.form.classList.add('was-validated');
        }

    }

    updateProfile() {

        this.props.updateProfile(
            {id: this.props.user.id,
            firstName: this.state.firstName.trim(),
            lastName: this.state.lastName.trim(),
            email: this.state.email.trim()},
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
                        <FormattedMessage id="project.users.UpdateProfile.title"/>
                    </h5>
                    <div className="card-body">
                    <form ref={node => this.form = node} 
                            className="needs-validation" noValidate onSubmit={(e) => this.handleSubmit(e)}>
                            <div className="form-group row">
                                <label htmlFor="firstName" className="col-md-3 col-form-label">
                                    <FormattedMessage id="project.global.fields.firstName"/>
                                </label>
                                <div className="col-md-4">
                                    <input type="text" id="firstName" className="form-control"
                                        value={this.state.firstName}
                                        onChange={(e) => this.handleFirstNameChange(e)}
                                        autoFocus
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
    user: selectors.getUser(state)
});

const mapDispatchToProps = {
    updateProfile: actions.updateProfile
};

export default connect(mapStateToProps, mapDispatchToProps)(UpdateProfile);