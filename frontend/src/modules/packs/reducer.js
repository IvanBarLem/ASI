import { combineReducers } from "redux";

import * as actionTypes from "./actionTypes";

const initialState = {
  packs: null,
};

const packs = (state = initialState.packs, action) => {
  switch (action.type) {
    case actionTypes.CREATE_PACK_COMPLETED:
      return action.packs.pack;
    default:
      return state;
  }
};

const reducer = combineReducers({
  packs,
});

export default reducer;
