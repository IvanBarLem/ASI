import { combineReducers } from "redux";

import * as actionTypes from "./actionTypes";

const initialState = {
  packSearch: null,
  packs: null,
};

const packSearch = (state = initialState.packSearch, action) => {
  switch (action.type) {
    case actionTypes.FIND_PACKS_COMPLETED:
      return action.packSearch
    
    case actionTypes.CLEAR_PACK_SEARCH:
      return initialState.packSearch
    
    default:
      return state;
  }
}

const packs = (state = initialState.packs, action) => {
  switch (action.type) {
    case actionTypes.CREATE_PACK_COMPLETED:
      return action.packs.pack;

    default:
      return state;
  }
};

const reducer = combineReducers({
  packSearch,
  packs,
});

export default reducer;
