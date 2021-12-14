import { combineReducers } from "redux";

import * as actionTypes from "./actionTypes";

const initialState = {
    saleSearch: null,
    sale: null,
};

const saleSearch = (state = initialState.saleSearch, action) => {
    switch (action.type) {
        case actionTypes.FIND_SALES_COMPLETED:
            return action.saleSearch;
        case actionTypes.CLEAR_SALE_SEARCH:
            return initialState.saleSearch;

        default:
            return state;
    }
};

const sale = (state = initialState.sale, action) => {
    switch (action.type) {
        case actionTypes.CREATE_SALE_COMPLETED:
            return action.sale;
        default:
            return state;
    }
};

const reducer = combineReducers({
    sale,
    saleSearch,
});

export default reducer;
