import { config, appFetch } from "./appFetch";

export const getTransports = (onSuccess, onErrors) =>
  appFetch(`/products/transports/`, config("GET"), onSuccess, onErrors);

export const getAccommodations = (onSuccess, onErrors) =>
  appFetch(`/products/accommodations/`, config("GET"), onSuccess, onErrors);

export const getActivities = (onSuccess, onErrors) =>
  appFetch(`/products/activities/`, config("GET"), onSuccess, onErrors);

export const getTravels = (onSuccess, onErrors) =>
  appFetch(`/products/travels/`, config("GET"), onSuccess, onErrors);

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
