import { combineReducers } from "redux";

import * as actionTypes from "./actionTypes";

const initialState = {
  products: [],
  statistics: null,
};

const products = (state = initialState.products, action) => {
  switch (action.type) {
    case actionTypes.GET_STATISTICS_PRODUCTS_COMPLETED:
      return action.products;
    default:
      return state;
  }
};

const statistics = (state = initialState.statistics, action) => {
  switch (action.type) {
    case actionTypes.GET_STATISTICS_COMPANY_COMPLETED:
      return action.statistics;
    default:
      return state;
  }
};

const reducer = combineReducers({
  products,
  statistics,
});

export default reducer;
