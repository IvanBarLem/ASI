import * as actionTypes from "./actionTypes";
import backend from "../../backend";

//GETS MANAGER

const getTransportsManagerCompleted = (transports) => ({
  type: actionTypes.GET_TRANSPORTS_MANAGER_COMPLETED,
  transports,
});

export const getTransportsManager = (onSuccess, onErrors) => (dispatch) =>
  backend.productService.getTransportsManager((transports) => {
    dispatch(getTransportsManagerCompleted(transports));
    onSuccess(transports);
  }, onErrors);

const getActivitiesManagerCompleted = (activities) => ({
  type: actionTypes.GET_ACTIVITIES_MANAGER_COMPLETED,
  activities,
});

export const getActivitiesManager = (onSuccess, onErrors) => (dispatch) =>
  backend.productService.getActivitiesManager((activities) => {
    dispatch(getActivitiesManagerCompleted(activities));
    onSuccess(activities);
  }, onErrors);

const getAccommodationsManagerCompleted = (accommodations) => ({
  type: actionTypes.GET_ACCOMMODATIONS_MANAGER_COMPLETED,
  accommodations,
});

export const getAccommodationsManager = (onSuccess, onErrors) => (dispatch) =>
  backend.productService.getAccommodationsManager((accommodations) => {
    dispatch(getAccommodationsManagerCompleted(accommodations));
    onSuccess(accommodations);
  }, onErrors);

const getTravelsManagerCompleted = (travels) => ({
  type: actionTypes.GET_TRAVELS_MANAGER_COMPLETED,
  travels,
});

export const getTravelsManager = (onSuccess, onErrors) => (dispatch) =>
  backend.productService.getTravelsManager((travels) => {
    dispatch(getTravelsManagerCompleted(travels));
    onSuccess(travels);
  }, onErrors);

//GETS AGENT

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

//DELETES

const deleteTravelCompleted = () => ({
  type: actionTypes.UPDATE_TRAVEL_COMPLETED,
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
  type: actionTypes.UPDATE_ACTIVITY_COMPLETED,
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
  type: actionTypes.UPDATE_TRANSPORT_COMPLETED,
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

//UPDATES

const updateTravelCompleted = () => ({
  type: actionTypes.UPDATE_TRAVEL_COMPLETED,
});

export const updateTravel = (object, onSuccess, onErrors) => (dispatch) =>
  backend.productService.updateTravel(
    object,
    () => {
      dispatch(updateTravelCompleted());
      onSuccess();
    },
    onErrors
  );

const updateActivityCompleted = () => ({
  type: actionTypes.UPDATE_ACTIVITY_COMPLETED,
});

export const updateActivity = (object, onSuccess, onErrors) => (dispatch) =>
  backend.productService.updateActivity(
    object,
    (_) => {
      dispatch(updateActivityCompleted());
      onSuccess();
    },
    onErrors
  );

const updateTransportCompleted = () => ({
  type: actionTypes.UPDATE_TRANSPORT_COMPLETED,
});

export const updateTransport = (object, onSuccess, onErrors) => (dispatch) =>
  backend.productService.updateTransport(
    object,
    (_) => {
      dispatch(updateTransportCompleted());
      onSuccess();
    },
    onErrors
  );

const updateAccommodationCompleted = () => ({
  type: actionTypes.UPDATE_ACCOMMODATION_COMPLETED,
});

export const updateAccommodation =
  (object, onSuccess, onErrors) => (dispatch) =>
    backend.productService.updateAccommodation(
      object,
      (_) => {
        dispatch(updateAccommodationCompleted());
        onSuccess();
      },
      onErrors
    );

//CREATE

const createTravelCompleted = () => ({
  type: actionTypes.CREATE_TRAVEL_COMPLETED,
});

export const createTravel = (object, onSuccess, onErrors) => (dispatch) =>
  backend.productService.createTravel(
    object,
    () => {
      dispatch(createTravelCompleted());
      onSuccess();
    },
    onErrors
  );

const createActivityCompleted = () => ({
  type: actionTypes.CREATE_ACTIVITY_COMPLETED,
});

export const createActivity = (object, onSuccess, onErrors) => (dispatch) =>
  backend.productService.createActivity(
    object,
    (_) => {
      dispatch(createActivityCompleted());
      onSuccess();
    },
    onErrors
  );

const createTransportCompleted = () => ({
  type: actionTypes.CREATE_TRANSPORT_COMPLETED,
});

export const createTransport = (object, onSuccess, onErrors) => (dispatch) =>
  backend.productService.createTransport(
    object,
    (_) => {
      dispatch(createTransportCompleted());
      onSuccess();
    },
    onErrors
  );

const createAccommodationCompleted = () => ({
  type: actionTypes.CREATE_ACCOMMODATION_COMPLETED,
});

export const createAccommodation =
  (object, onSuccess, onErrors) => (dispatch) =>
    backend.productService.createAccommodation(
      object,
      (_) => {
        dispatch(createAccommodationCompleted());
        onSuccess();
      },
      onErrors
    );
