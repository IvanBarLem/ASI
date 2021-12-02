import * as actionTypes from "./actionTypes";
import backend from "../../backend";

const getDateRange = (days) => {
    var today = new Date();
    const todayStr = today.getFullYear() + "-" + today.getMonth() + "-" + today.getDay();
    today.setDate(today.getDate() - days);
    const priorStr = today.getFullYear() + "-" + today.getMonth() + "-" + today.getDay();

    return { startDate: priorStr, endDate: todayStr };
}

const findCompanyStatisticsCompleted = (companyStatistics) => ({
    type: actionTypes.FIND_COMPANY_STATISTICS_COMPLETED,
    companyStatistics,
})

export const findCompanyStatistics = () => (dispatch) => {
    const { startDate, endDate } = getDateRange(30);
    backend.statisticsService.findCompanyStatistics(startDate, endDate, (result) =>
        dispatch(findCompanyStatisticsCompleted(result))
    );
}

export const clearCompanyStatistics = () => ({
    type: actionTypes.CLEAR_COMPANY_STATISTICS,
});

const findAgentSalesCompleted = (agentSalesSearch) => ({
    type: actionTypes.FIND_AGENT_SALES_COMPLETED,
    agentSalesSearch,
});

export const findAgentSales = (page, days) => (dispatch) => {
    const { startDate, endDate } = getDateRange(days);
    backend.statisticsService.findAgentSales(page, startDate, endDate, (result) =>
        dispatch(findAgentSalesCompleted({ page, result }))
    );
};

export const previousFindAgentSalesPage = (page) => findAgentSales(page - 1);

export const nextFindAgentSalesPage = (page) => findAgentSales(page + 1);

export const clearAgentSalesSearch = () => ({
    type: actionTypes.CLEAR_AGENT_SALES_SEARCH,
});