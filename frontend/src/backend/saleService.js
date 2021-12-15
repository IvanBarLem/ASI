import { config, appFetch } from "./appFetch";

export const findSales = (page, client, agent, onSuccess) => {
    let path = `/sales/findSales?pageNumber=${page}&clientName=${client}&agentName=${agent}`;

    appFetch(path, config("GET"), onSuccess);
};

export const blockSale = (saleId, onSuccess) => {
    let path = `/sales/freezeSale/${saleId}`;

    appFetch(path, config("POST"), onSuccess);
};

export const paySale = (saleId, onSuccess) => {
    let path = `/sales/paySale/${saleId}`;

    appFetch(path, config("POST"), onSuccess);
};
export const createSale = (sale, onSuccess, onErrors) =>
    appFetch(`/sales/create`, config("POST", sale), onSuccess, onErrors);
