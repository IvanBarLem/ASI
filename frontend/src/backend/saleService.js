import { config, appFetch } from "./appFetch";

export const createSale = (sale, onSuccess, onErrors) =>
  appFetch(`/sales/create`, config("POST", sale), onSuccess, onErrors);
