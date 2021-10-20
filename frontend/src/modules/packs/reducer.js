import { combineReducers } from "redux";

import * as actionTypes from "./actionTypes";

const initialState = {
  packs: [],
  activities: [],
  accomodations: [],
  travels: [],
  transorts: []
};

const packs = (state = initialState.packs, action) => {
  switch (action.type) {
    case actionTypes.CREATE_PACK_COMPLETED:
      return action.packs.packs;
    default:
      return state;
  }
};

const activities = (state = initialState.activities, action) => {
  switch (action.type) {
    case actionTypes.GET_ACTIVITIES_COMPLETED:
      return action.activities.activities;
    default:
      return state;
  }
};

const accomodations = (state = initialState.accomodations, action) => {
  switch (action.type) {
    case actionTypes.GET_ACCOMODATIONS_COMPLETED:
      return action.accomodations.accomodations;
    default:
      return state;
  }
};

const travels = (state = initialState.travels, action) => {
  switch (action.type) {
    case actionTypes.GET_TRAVELS_COMPLETED:
      return action.travels.travels;
    default:
      return state;
  }
};

const transports = (state = initialState.transports, action) => {
  switch (action.type) {
    case actionTypes.GET_TRANSPORTS_COMPLETED:
      return action.transports.transports;
    default:
      return state;
  }
};

const reducer = combineReducers({
  packs,
  activities,
  travels,
  transports,
  accomodations
});

export default reducer;
