import { config, appFetch } from "./appFetch";

export const findCompanyStatistics = (startdate, endDate, onSuccess) => {
    let path = `/statistics/company?startDate=${startdate}&endDate=${endDate}`;

    appFetch(path, config("GET"), onSuccess);
};

export const findAgentSales = (page, startdate, endDate, onSuccess) => {
    let path = `/statistics/agents?page=${page}&startDate=${startdate}&endDate=${endDate}`;

    appFetch(path, config("GET"), onSuccess);
};