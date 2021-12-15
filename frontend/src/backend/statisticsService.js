import { config, appFetch } from "./appFetch";

export const findCompanyStatistics = (startdate, endDate, onSuccess) => {
    let path = `/statistics/company?startDate=${startdate}&endDate=${endDate}`;

    appFetch(path, config("GET"), onSuccess);
};

export const findAgentSales = (page, startdate, endDate, onSuccess) => {
    let path = `/statistics/agents?page=${page}&startDate=${startdate}&endDate=${endDate}`;

    appFetch(path, config("GET"), onSuccess);
};

export const getSalesProducts = (page, location, category, onSuccess) =>
    appFetch(
        `/statistics/products?category=${category}&location=${location}&startDate=0001-01-01&endDate=9999-12-30&page=${page}`,
        config("GET"),
        onSuccess
    );

export const getSalesCompany = (onSuccess) =>
    appFetch(
        `/statistics/company?startDate=0001-01-01&endDate=9999-12-30`,
        config("GET"),
        onSuccess
    );
