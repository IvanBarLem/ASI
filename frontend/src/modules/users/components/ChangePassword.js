import React, { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { FormattedMessage } from "react-intl";

import { Errors } from "../../common";
import * as actions from "../actions";
import * as selectors from "../selectors";
import { useHistory } from "react-router";

const ChangePassword = () => {
  const user = useSelector(selectors.getUser);
  const dispatch = useDispatch();
  const history = useHistory();
  const [oldPassword, setOldPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [confirmNewPassword, setConfirmNewPassword] = useState("");
  const [backendErrors, setBackendErrors] = useState(null);
  const [passwordsDoNotMatch, setPasswordsDoNotMatch] = useState(false);
  let form;
  let confirmNewPasswordInput;

  function handleOldPasswordChange(event) {
    setOldPassword(event.target.value);
  }

  function handleNewPasswordChange(event) {
    setNewPassword(event.target.value);
  }

  function handleConfirmNewPasswordChange(event) {
    confirmNewPasswordInput.setCustomValidity("");
    setConfirmNewPassword(event.target.value);
    setPasswordsDoNotMatch(false);
  }

  function handleSubmit(event) {
    event.preventDefault();

    if (form.checkValidity() && checkConfirmNewPassword()) {
      changePassword();
    } else {
      setBackendErrors(null);
      form.classList.add("was-validated");
    }
  }

  function checkConfirmNewPassword() {
    if (newPassword !== confirmNewPassword) {
      setPasswordsDoNotMatch(true);
      confirmNewPasswordInput.setCustomValidity("error");
      return false;
    } else {
      return true;
    }
  }

  function changePassword() {
    dispatch(
      actions.changePassword(
        user.id,
        oldPassword,
        newPassword,
        () => history.push("/"),
        (errors) => setBackendErrors(errors)
      )
    );
  }

  function handleErrorsClose() {
    setBackendErrors(null);
  }

  return (
    <div>
      <Errors errors={backendErrors} onClose={() => handleErrorsClose()} />
      <div className="card bg-light border-dark">
        <h5 className="card-header">
          <FormattedMessage id="project.users.ChangePassword.title" />
        </h5>
        <div className="card-body">
          <form
            ref={(node) => (form = node)}
            className="needs-validation"
            noValidate
            onSubmit={(e) => handleSubmit(e)}
          >
            <div className="form-group row">
              <label htmlFor="oldPassword" className="col-md-3 col-form-label">
                <FormattedMessage id="project.users.ChangePassword.fields.oldPassword" />
              </label>
              <div className="col-md-4">
                <input
                  type="password"
                  id="confirmNewPassword"
                  className="form-control"
                  value={oldPassword}
                  onChange={(e) => handleOldPasswordChange(e)}
                  autoFocus
                  required
                />
                <div className="invalid-feedback">
                  <FormattedMessage id="project.global.validator.required" />
                </div>
              </div>
            </div>
            <div className="form-group row">
              <label htmlFor="newPassword" className="col-md-3 col-form-label">
                <FormattedMessage id="project.users.ChangePassword.fields.newPassword" />
              </label>
              <div className="col-md-4">
                <input
                  type="password"
                  id="newPassword"
                  className="form-control"
                  value={newPassword}
                  onChange={(e) => handleNewPasswordChange(e)}
                  required
                />
                <div className="invalid-feedback">
                  <FormattedMessage id="project.global.validator.required" />
                </div>
              </div>
            </div>
            <div className="form-group row">
              <label
                htmlFor="confirmNewPassword"
                className="col-md-3 col-form-label"
              >
                <FormattedMessage id="project.users.ChangePassword.fields.confirmNewPassword" />
              </label>
              <div className="col-md-4">
                <input
                  ref={(node) => (confirmNewPasswordInput = node)}
                  type="password"
                  id="confirmNewPassword"
                  className="form-control"
                  value={confirmNewPassword}
                  onChange={(e) => handleConfirmNewPasswordChange(e)}
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
              <div className="offset-md-3 col-md-1">
                <button type="submit" className="btn btn-primary">
                  <FormattedMessage id="project.global.buttons.save" />
                </button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default ChangePassword;
