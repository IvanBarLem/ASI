import * as actionTypes from "./actionTypes";
import backend from "../../backend";

const signUpCompleted = (authenticatedUser) => ({
  type: actionTypes.SIGN_UP_COMPLETED,
  authenticatedUser,
});

export const signUp =
  (user, onSuccess, onErrors, reauthenticationCallback) => (dispatch) =>
    backend.userService.signUp(
      user,
      (authenticatedUser) => {
        dispatch(signUpCompleted(authenticatedUser));
        onSuccess();
      },
      onErrors,
      reauthenticationCallback
    );

const loginCompleted = (authenticatedUser) => ({
  type: actionTypes.LOGIN_COMPLETED,
  authenticatedUser,
});

export const login =
  (email, password, onSuccess, onErrors, reauthenticationCallback) =>
  (dispatch) =>
    backend.userService.login(
      email,
      password,
      (authenticatedUser) => {
        dispatch(loginCompleted(authenticatedUser));
        onSuccess();
      },
      onErrors,
      reauthenticationCallback
    );

export const tryLoginFromServiceToken =
  (reauthenticationCallback) => (dispatch) =>
    backend.userService.tryLoginFromServiceToken((authenticatedUser) => {
      if (authenticatedUser) {
        dispatch(loginCompleted(authenticatedUser));
      }
    }, reauthenticationCallback);

export const logout = () => {
  backend.userService.logout();

  return { type: actionTypes.LOGOUT };
};

export const updateProfileCompleted = (user) => ({
  type: actionTypes.UPDATE_PROFILE_COMPLETED,
  user,
});

export const updateProfile = (user, onSuccess, onErrors) => (dispatch) =>
  backend.userService.updateProfile(
    user,
    (user) => {
      dispatch(updateProfileCompleted(user));
      onSuccess();
    },
    onErrors
  );

export const changePassword =
  (id, oldPassword, newPassword, onSuccess, onErrors) => (dispatch) =>
    backend.userService.changePassword(
      id,
      oldPassword,
      newPassword,
      onSuccess,
      onErrors
    );

const findClientsCompleted = (clients) => ({
  type: actionTypes.FIND_CLIENTS_COMPLETED,
  clients,
});

export const findClients =
  (keywords, page, size, onSuccess, onErrors) => (dispatch) =>
    backend.userService.findClients(
      keywords,
      page,
      size,
      (clients) => {
        dispatch(findClientsCompleted(clients));
        onSuccess(clients);
      },
      onErrors
    );
