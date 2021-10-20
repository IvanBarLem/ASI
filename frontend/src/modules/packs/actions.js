import * as actionTypes from "./actionTypes";
import backend from "../../backend";

const findPacksCompleted = (packSearch) => ({
  type: actionTypes.FIND_PACKS_COMPLETED,
  packSearch,
});

export const findPacks = (page) => (dispatch) => {
  backend.packService.findPacks(page, (result) =>
    dispatch(findPacksCompleted({ page, result }))
  );
};

export const previousFindPacksPage = (page) => findPacks(page - 1);

export const nextFindPacksPage = (page) => findPacks(page + 1);

export const clearPackSearch = () => ({
  type: actionTypes.CLEAR_PACK_SEARCH,
});

const createPackCompleted = (pack) => ({
  type: actionTypes.CREATE_PACK_COMPLETED,
  pack,
});

export const createPack = (pack, onSuccess, onErrors) => (dispatch) =>
  backend.packService.createPack(
    pack,
    (pack) => {
      dispatch(createPackCompleted(pack));
      onSuccess(pack);
    },
    onErrors
  );

const getTransportsCompleted = (transports) => ({
  type: actionTypes.GET_TRANSPORTS_COMPLETED,
  transports,
});

export const getTransports = (onSuccess, onErrors) => (dispatch) =>
  backend.packService.getTransports((transports) => {
    dispatch(getTransportsCompleted(transports));
    onSuccess(transports);
  }, onErrors);

const getActivitiesCompleted = (activities) => ({
  type: actionTypes.GET_ACTIVITIES_COMPLETED,
  activities,
});

export const getActivities = (onSuccess, onErrors) => (dispatch) =>
  backend.packService.getActivities((activities) => {
    dispatch(getActivitiesCompleted(activities));
    onSuccess(activities);
  }, onErrors);

const getAccommodationsCompleted = (accommodations) => ({
  type: actionTypes.GET_ACCOMMODATIONS_COMPLETED,
  accommodations,
});

export const getAccommodations = (onSuccess, onErrors) => (dispatch) =>
  backend.packService.getAccommodations((accommodations) => {
    dispatch(getAccommodationsCompleted(accommodations));
    onSuccess(accommodations);
  }, onErrors);

const getTravelsCompleted = (travels) => ({
  type: actionTypes.GET_TRAVELS_COMPLETED,
  travels,
});

export const getTravels = (onSuccess, onErrors) => (dispatch) =>
  backend.packService.getTravels((travels) => {
    dispatch(getTravelsCompleted(travels));
    onSuccess(travels);
  }, onErrors);
