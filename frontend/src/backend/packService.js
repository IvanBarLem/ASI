import { config, appFetch } from "./appFetch";

export const createPack = (pack, onSuccess, onErrors) =>
  appFetch(`/packs/create`, config("POST", pack), onSuccess, onErrors);

export const getTransports = (onSuccess, onErrors) =>
  appFetch(`/transports/`, config("GET"), onSuccess, onErrors);

export const getAccommodations = (onSuccess, onErrors) =>
  appFetch(`/accommodations/`, config("GET"), onSuccess, onErrors);

export const getActivities = (onSuccess, onErrors) =>
  appFetch(`/activities/`, config("GET"), onSuccess, onErrors);

export const getTravels = (onSuccess, onErrors) =>
  appFetch(`/travels/`, config("GET"), onSuccess, onErrors);

export const findPacks = (page, onSuccess) => {
  let path = `/packs?page=${page}`;

  appFetch(path, config("GET"), onSuccess);
};
