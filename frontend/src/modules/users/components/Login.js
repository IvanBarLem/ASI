import React, { useState } from 'react';
import { connect } from 'react-redux';
import { FormattedMessage } from 'react-intl';
import { useDispatch } from 'react-redux';
import { useHistory, Link } from 'react-router-dom';

import { Errors } from '../../common';
import * as actions from '../actions';

const Login = () => {
    const dispatch = useDispatch();
    const history = useHistory();
    const [userName, setUserName] = useState('');
    const [password, setPassword] = useState('');
    const [backendErrors, setBackendErrors] = useState(null);
    let form;

    const handleSubmit = event => {
        event.preventDefault();

        if (form.checkValidity()) {
            dispatch(actions.login(
                userName.trim(),
                password,
                () => history.push('/'),
                errors => setBackendErrors(errors),
                () => {
                    history.push('/users/login');
                    dispatch(actions.logout());
                }
            ));
        } else {
            setBackendErrors(null);
        }
    }

    // const login = () => {
    //     this.props.dispatch(actions.login(
    //         this.state.userName.trim(),
    //         this.state.password,
    //         () => this.props.history.push('/'),
    //         errors => this.setBackendErrors(errors),
    //         reauthenticationCallback(this.props.dispatch, this.props.history)
    //     ));
    // }

    return (
        <div>
            <p className="text-center">
                <Link to="/users/signup">
                    <FormattedMessage id="project.users.SignUp.title" />
                </Link>
            </p>
            <Errors errors={backendErrors} onClose={() => setBackendErrors(null)} />
            <div className="card bg-light border-dark">
                <h5 className="card-header">
                    <FormattedMessage id="project.users.Login.title" />
                </h5>
                <div className="card-body">
                    <form ref={node => form = node}
                        className="needs-validation" noValidate
                        onSubmit={(e) => handleSubmit(e)}
                    >
                        <div className="form-group row">
                            <label htmlFor="userName" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.userName" />
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="userName" className="form-control"
                                    value={userName}
                                    onChange={(e) => setUserName(e.target.value)}
                                    autoFocus
                                    required />
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required' />
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <label htmlFor="password" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.password" />
                            </label>
                            <div className="col-md-4">
                                <input type="password" id="password" className="form-control"
                                    value={password}
                                    onChange={(e) => setPassword(e.target.value)}
                                    required />
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required' />
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <div className="offset-md-3 col-md-1">
                                <button type="submit" className="btn btn-primary">
                                    <FormattedMessage id="project.users.Login.title" />
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default connect()(Login);