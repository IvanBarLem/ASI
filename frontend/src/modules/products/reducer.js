import { combineReducers } from "redux";

import * as actionTypes from "./actionTypes";

const initialState = {
  activities: [],
  accommodations: [],
  travels: [],
  transports: [],
};

const activities = (state = initialState.activities, action) => {
  switch (action.type) {
    case actionTypes.GET_ACTIVITIES_COMPLETED:
      return action.activities;
    default:
      return state;
  }
};

const accommodations = (state = initialState.accommodations, action) => {
  switch (action.type) {
    case actionTypes.GET_ACCOMMODATIONS_COMPLETED:
      return action.accommodations;
    default:
      return state;
  }
};

const travels = (state = initialState.travels, action) => {
  switch (action.type) {
    case actionTypes.GET_TRAVELS_COMPLETED:
      return action.travels;
    default:
      return state;
  }
};

const transports = (state = initialState.transports, action) => {
  switch (action.type) {
    case actionTypes.GET_TRANSPORTS_COMPLETED:
      return action.transports;
    default:
      return state;
  }
};

const reducer = combineReducers({
  activities,
  travels,
  transports,
  accommodations,
});

export default reducer;
