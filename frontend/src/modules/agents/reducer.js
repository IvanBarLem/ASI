import { combineReducers } from "redux";
import * as actionTypes from "./actionTypes";

const initialState = {
    companyStatistics: null,
    agentSalesSearch: null,
};

const companyStatistics = (state = initialState.companyStatistics, action) => {
    switch (action.type) {
        case actionTypes.FIND_COMPANY_STATISTICS_COMPLETED:
            return action.companyStatistics;
        case actionTypes.CLEAR_COMPANY_STATISTICS:
            return initialState.companyStatistics;

        default:
            return state;
    }
};

const agentSalesSearch = (state = initialState.agentSalesSearch, action) => {
    switch (action.type) {
        case actionTypes.FIND_AGENT_SALES_COMPLETED:
            return action.agentSalesSearch;
        case actionTypes.CLEAR_AGENT_SALES_SEARCH:
            return initialState.agentSalesSearch;

        default:
            return state;
    }
};

const reducer = combineReducers({
    companyStatistics,
    agentSalesSearch
});

export default reducer;
