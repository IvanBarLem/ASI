import { combineReducers } from "redux";

import * as actionTypes from "./actionTypes";

const initialState = {
  packs: [],
  packSearch: null,
  pack: null,
};

const packSearch = (state = initialState.packSearch, action) => {
  switch (action.type) {
    case actionTypes.FIND_PACKS_COMPLETED:
      return action.packSearch;
    case actionTypes.FIND_ALL_PACKS_COMPLETED:
      return action.packSearch;
    case actionTypes.CLEAR_PACK_SEARCH:
      return initialState.packSearch;

    default:
      return state;
  }
};

const pack = (state = initialState.pack, action) => {
  switch (action.type) {
    case actionTypes.CREATE_PACK_COMPLETED:
      return action.pack;
    default:
      return state;
  }
};

const reducer = combineReducers({
  packSearch,
  pack,
});

export default reducer;
