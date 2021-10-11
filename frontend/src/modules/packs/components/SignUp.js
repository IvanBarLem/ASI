import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { FormattedMessage } from "react-intl";
import { useHistory } from "react-router-dom";

import { Errors } from "../../common";
import * as actions from "../actions";

const SignUp = () => {
  const dispatch = useDispatch();
  const history = useHistory();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [backendErrors, setBackendErrors] = useState(null);
  const [passwordsDoNotMatch, setPasswordsDoNotMatch] = useState(false);
  let form;
  let confirmPasswordInput;

  const handleSubmit = (event) => {
    event.preventDefault();
    if (
      form.checkValidity() &&
      checkConfirmPassword() &&
      validateEmail(email)
    ) {
      dispatch(
        actions.signUp(
          {
            email: email.trim(),
            password: password,
            firstName: firstName.trim(),
            lastName: lastName.trim(),
          },
          () => history.push("/"),
          (errors) => setBackendErrors(errors),
          () => {
            history.push("/users/login");
            dispatch(actions.logout());
          }
        )
      );
    } else {
      setBackendErrors(null);
      form.classList.add("was-validated");
    }
  };

  const checkConfirmPassword = () => {
    if (password !== confirmPassword) {
      setPasswordsDoNotMatch(true);
      confirmPasswordInput.setCustomValidity("error");
      return false;
    } else {
      return true;
    }
  };

  const handleConfirmPasswordChange = (value) => {
    confirmPasswordInput.setCustomValidity("");
    setConfirmPassword(value);
    setPasswordsDoNotMatch(false);
  };

  const validateEmail = (email) => {
    var re = /\S+@\S+\.\S+/;
    return re.test(email) && email !== "";
  };

  return (
    <div>
      <Errors errors={backendErrors} onClose={() => setBackendErrors(null)} />
      <div className="card bg-light border-dark">
        <h5 className="card-header">
          <FormattedMessage id="project.users.SignUp.title" />
        </h5>
        <div className="card-body">
          <form
            ref={(node) => (form = node)}
            className="needs-validation"
            noValidate
            onSubmit={(e) => handleSubmit(e)}
          >
            <div className="form-group row">
              <label htmlFor="email" className="col-md-3 col-form-label">
                <FormattedMessage id="project.global.fields.email" />
              </label>
              <div className="col-md-4">
                <input
                  type="email"
                  id="email"
                  className="form-control"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  required
                />
                <div className="invalid-feedback">
                  <FormattedMessage id="project.global.validator.email" />
                </div>
              </div>
            </div>
            <div className="form-group row">
              <label htmlFor="password" className="col-md-3 col-form-label">
                <FormattedMessage id="project.global.fields.password" />
              </label>
              <div className="col-md-4">
                <input
                  type="password"
                  id="password"
                  className="form-control"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  required
                />
                <div className="invalid-feedback">
                  <FormattedMessage id="project.global.validator.required" />
                </div>
              </div>
            </div>
            <div className="form-group row">
              <label
                htmlFor="confirmPassword"
                className="col-md-3 col-form-label"
              >
                <FormattedMessage id="project.users.SignUp.fields.confirmPassword" />
              </label>
              <div className="col-md-4">
                <input
                  ref={(node) => (confirmPasswordInput = node)}
                  type="password"
                  id="confirmPassword"
                  className="form-control"
                  value={confirmPassword}
                  onChange={(e) => handleConfirmPasswordChange(e.target.value)}
                  required
                />
                <div className="invalid-feedback">
                  {passwordsDoNotMatch ? (
                    <FormattedMessage id="project.global.validator.passwordsDoNotMatch" />
                  ) : (
                    <FormattedMessage id="project.global.validator.required" />
                  )}
                </div>
              </div>
            </div>
            <div className="form-group row">
              <label htmlFor="firstName" className="col-md-3 col-form-label">
                <FormattedMessage id="project.global.fields.firstName" />
              </label>
              <div className="col-md-4">
                <input
                  type="text"
                  id="firstName"
                  className="form-control"
                  value={firstName}
                  onChange={(e) => setFirstName(e.target.value)}
                  required
                />
                <div className="invalid-feedback">
                  <FormattedMessage id="project.global.validator.required" />
                </div>
              </div>
            </div>
            <div className="form-group row">
              <label htmlFor="lastName" className="col-md-3 col-form-label">
                <FormattedMessage id="project.global.fields.lastName" />
              </label>
              <div className="col-md-4">
                <input
                  type="text"
                  id="lastName"
                  className="form-control"
                  value={lastName}
                  onChange={(e) => setLastName(e.target.value)}
                  required
                />
                <div className="invalid-feedback">
                  <FormattedMessage id="project.global.validator.required" />
                </div>
              </div>
            </div>
            <div className="form-group row">
              <div className="offset-md-3 col-md-2">
                <button type="submit" className="btn btn-primary">
                  <FormattedMessage id="project.users.SignUp.title" />
                </button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default SignUp;
