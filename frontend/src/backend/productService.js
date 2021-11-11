import { config, appFetch } from "./appFetch";

//GET MANAGER

export const getTransportsManager = (onSuccess, onErrors) =>
  appFetch(`/products/transports/hidden`, config("GET"), onSuccess, onErrors);

export const getAccommodationsManager = (onSuccess, onErrors) =>
  appFetch(
    `/products/accommodations/hidden`,
    config("GET"),
    onSuccess,
    onErrors
  );

export const getActivitiesManager = (onSuccess, onErrors) =>
  appFetch(`/products/activities/hidden`, config("GET"), onSuccess, onErrors);

export const getTravelsManager = (onSuccess, onErrors) =>
  appFetch(`/products/travels/hidden`, config("GET"), onSuccess, onErrors);

//GET AGENT

export const getTransports = (onSuccess, onErrors) =>
  appFetch(`/products/transports/`, config("GET"), onSuccess, onErrors);

export const getAccommodations = (onSuccess, onErrors) =>
  appFetch(`/products/accommodations/`, config("GET"), onSuccess, onErrors);

export const getActivities = (onSuccess, onErrors) =>
  appFetch(`/products/activities/`, config("GET"), onSuccess, onErrors);

export const getTravels = (onSuccess, onErrors) =>
  appFetch(`/products/travels/`, config("GET"), onSuccess, onErrors);

//DELETE

export const deleteTransport = (id, onSuccess, onErrors) =>
  appFetch(`/products/transports/` + id, config("DELETE"), onSuccess, onErrors);

export const deleteAccommodation = (id, onSuccess, onErrors) =>
  appFetch(
    `/products/accommodations/` + id,
    config("DELETE"),
    onSuccess,
    onErrors
  );

export const deleteActivity = (id, onSuccess, onErrors) =>
  appFetch(`/products/activities/` + id, config("DELETE"), onSuccess, onErrors);

export const deleteTravel = (id, onSuccess, onErrors) =>
  appFetch(`/products/travels/` + id, config("DELETE"), onSuccess, onErrors);

//UPDATE

export const updateTransport = (object, onSuccess, onErrors) =>
  appFetch(`/products/transports`, config("PUT", object), onSuccess, onErrors);

export const updateAccommodation = (object, onSuccess, onErrors) =>
  appFetch(
    `/products/accommodations`,
    config("PUT", object),
    onSuccess,
    onErrors
  );

export const updateActivity = (object, onSuccess, onErrors) =>
  appFetch(`/products/activities`, config("PUT", object), onSuccess, onErrors);

export const updateTravel = (object, onSuccess, onErrors) =>
  appFetch(`/products/travels`, config("PUT", object), onSuccess, onErrors);

//CREATE

export const createTransport = (object, onSuccess, onErrors) =>
  appFetch(
    `/products/transports/create`,
    config("POST", object),
    onSuccess,
    onErrors
  );

export const createAccommodation = (object, onSuccess, onErrors) =>
  appFetch(
    `/products/accommodations/create`,
    config("POST", object),
    onSuccess,
    onErrors
  );

export const createActivity = (object, onSuccess, onErrors) =>
  appFetch(
    `/products/activities/create`,
    config("POST", object),
    onSuccess,
    onErrors
  );

export const createTravel = (object, onSuccess, onErrors) =>
  appFetch(
    `/products/travels/create`,
    config("POST", object),
    onSuccess,
    onErrors
  );
