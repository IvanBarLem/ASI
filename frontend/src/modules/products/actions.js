import * as actionTypes from "./actionTypes";
import backend from "../../backend";

const getTransportsCompleted = (transports) => ({
  type: actionTypes.GET_TRANSPORTS_COMPLETED,
  transports,
});

export const getTransports = (onSuccess, onErrors) => (dispatch) =>
  backend.productService.getTransports((transports) => {
    dispatch(getTransportsCompleted(transports));
    onSuccess(transports);
  }, onErrors);

const getActivitiesCompleted = (activities) => ({
  type: actionTypes.GET_ACTIVITIES_COMPLETED,
  activities,
});

export const getActivities = (onSuccess, onErrors) => (dispatch) =>
  backend.productService.getActivities((activities) => {
    dispatch(getActivitiesCompleted(activities));
    onSuccess(activities);
  }, onErrors);

const getAccommodationsCompleted = (accommodations) => ({
  type: actionTypes.GET_ACCOMMODATIONS_COMPLETED,
  accommodations,
});

export const getAccommodations = (onSuccess, onErrors) => (dispatch) =>
  backend.productService.getAccommodations((accommodations) => {
    dispatch(getAccommodationsCompleted(accommodations));
    onSuccess(accommodations);
  }, onErrors);

const getTravelsCompleted = (travels) => ({
  type: actionTypes.GET_TRAVELS_COMPLETED,
  travels,
});

export const getTravels = (onSuccess, onErrors) => (dispatch) =>
  backend.productService.getTravels((travels) => {
    dispatch(getTravelsCompleted(travels));
    onSuccess(travels);
  }, onErrors);

const deleteTravelCompleted = () => ({
  type: actionTypes.DELETE_TRAVEL_COMPLETED,
});

export const deleteTravel = (id, onSuccess, onErrors) => (dispatch) =>
  backend.productService.deleteTravel(
    id,
    () => {
      dispatch(deleteTravelCompleted());
      onSuccess();
    },
    onErrors
  );

const deleteActivityCompleted = () => ({
  type: actionTypes.DELETE_ACTIVITY_COMPLETED,
});

export const deleteActivity = (id, onSuccess, onErrors) => (dispatch) =>
  backend.productService.deleteActivity(
    id,
    () => {
      dispatch(deleteActivityCompleted());
      onSuccess();
    },
    onErrors
  );

const deleteTransportCompleted = () => ({
  type: actionTypes.DELETE_TRANSPORT_COMPLETED,
});

export const deleteTransport = (id, onSuccess, onErrors) => (dispatch) =>
  backend.productService.deleteTransport(
    id,
    () => {
      dispatch(deleteTransportCompleted());
      onSuccess();
    },
    onErrors
  );

const deleteAccommodationCompleted = () => ({
  type: actionTypes.DELETE_ACCOMMODATION_COMPLETED,
});

export const deleteAccommodation = (id, onSuccess, onErrors) => (dispatch) =>
  backend.productService.deleteAccommodation(
    id,
    () => {
      dispatch(deleteAccommodationCompleted());
      onSuccess();
    },
    onErrors
  );
