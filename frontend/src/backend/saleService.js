import { config, appFetch } from "./appFetch";

export const findSales = (page, onSuccess) => {
    let path = `/sales/findSales?pageNumber=${page}`;

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