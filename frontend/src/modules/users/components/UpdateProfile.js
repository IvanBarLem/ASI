import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { FormattedMessage } from "react-intl";

import { Errors } from "../../common";
import * as actions from "../actions";
import * as selectors from "../selectors";
import { useHistory } from "react-router";

const UpdateProfile = (props) => {
  const user = useSelector(selectors.getUser);
  const dispatch = useDispatch();
  const history = useHistory();

  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [backendErrors, setBackendErrors] = useState(null);
  let form;

  useEffect(() => {
    setFirstName(user.firstName);
    setLastName(user.lastName);
  }, [user]);

  function handleFirstNameChange(event) {
    setFirstName(event.target.value);
  }

  function handleLastNameChange(event) {
    setLastName(event.target.value);
  }

  function handleSubmit(event) {
    event.preventDefault();

    if (form.checkValidity()) {
      updateProfile();
    } else {
      setBackendErrors(null);
      form.classList.add("was-validated");
    }
  }

  function updateProfile() {
    dispatch(
      actions.updateProfile(
        {
          id: user.id,
          firstName: firstName.trim(),
          lastName: lastName.trim(),
        },
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
          <FormattedMessage id="project.users.UpdateProfile.title" />
        </h5>
        <div className="card-body">
          <form
            ref={(node) => (form = node)}
            className="needs-validation"
            noValidate
            onSubmit={(e) => handleSubmit(e)}
          >
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
                  onChange={(e) => handleFirstNameChange(e)}
                  autoFocus
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
                  onChange={(e) => handleLastNameChange(e)}
                  required
                />
                <div className="invalid-feedback">
                  <FormattedMessage id="project.global.validator.required" />
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

export default UpdateProfile;
