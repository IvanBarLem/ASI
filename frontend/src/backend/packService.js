import { config, appFetch } from "./appFetch";

export const createPack = (pack, onSuccess, onErrors) =>
  appFetch(`/packs/create`, config("POST", pack), onSuccess, onErrors);

export const findPacks = (page, onSuccess) => {
  let path = `/packs?page=${page}`;

  appFetch(path, config("GET"), onSuccess);
};
