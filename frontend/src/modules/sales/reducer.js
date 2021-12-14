import { combineReducers } from "redux";

import * as actionTypes from "./actionTypes";

const initialState = {
  sale: null,
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
});

export default reducer;
