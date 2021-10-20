import * as actionTypes from "./actionTypes";
import backend from "../../backend";

const createPackCompleted = (pack) => ({
  type: actionTypes.CREATE_PACK_COMPLETED,
  pack,
});

export const createPack = (pack, onSuccess, onErrors) => (dispatch) =>
  backend.packService.createPack(
    pack,
    (pack) => {
      dispatch(createPackCompleted(pack));
      onSuccess();
    },
    onErrors
  );

const getTransportsCompleted = (transports) => ({
  type: actionTypes.GET_TRANSPORTS_COMPLETED,
  transports,
});

export const getTransports = (onSuccess, onErrors) => (dispatch) =>
  backend.packService.getTransports((transports) => {
    dispatch(getTransportsCompleted(transports));
    onSuccess(transports);
  }, onErrors);

const getActivitiesCompleted = (activities) => ({
  type: actionTypes.GET_ACTIVITIES_COMPLETED,
  activities,
});

export const getActivities = (onSuccess, onErrors) => (dispatch) =>
  backend.packService.getActivities((activities) => {
    dispatch(getActivitiesCompleted(activities));
    onSuccess(activities);
  }, onErrors);

const getAccomodationsCompleted = (accomodations) => ({
  type: actionTypes.GET_ACCOMODATIONS_COMPLETED,
  accomodations,
});

export const getAccomodations = (onSuccess, onErrors) => (dispatch) =>
  backend.packService.getAccomodations((accomodations) => {
    dispatch(getAccomodationsCompleted(accomodations));
    onSuccess(accomodations);
  }, onErrors);

const getTravelsCompleted = (travels) => ({
  type: actionTypes.GET_TRAVELS_COMPLETED,
  travels,
});

export const getTravels = (onSuccess, onErrors) => (dispatch) =>
  backend.packService.getTravels((travels) => {
    dispatch(getTravelsCompleted(travels));
    onSuccess(travels);
  }, onErrors);
