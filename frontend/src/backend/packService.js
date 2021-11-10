import { config, appFetch } from "./appFetch";

export const createPack = (pack, onSuccess, onErrors) =>
  appFetch(`/packs/create`, config("POST", pack), onSuccess, onErrors);

export const findPacks = (page, onSuccess) => {
  let path = `/packs?page=${page}`;

  appFetch(path, config("GET"), onSuccess);
};

export const findAllPacks = (page, onSuccess) => {
  let path = `/packs/hidden?page=${page}`;

  appFetch(path, config("GET"), onSuccess);
};

export const hidePack = (id, onSuccess) => {
  let path = `/packs/toggleHide/${id}`;
  appFetch(path, config("PUT"), onSuccess);
};

export const outstandingPack = (id, onSuccess) => {
  let path = `/packs/toggleHighlight/${id}`;
  appFetch(path, config("PUT"), onSuccess);
};
