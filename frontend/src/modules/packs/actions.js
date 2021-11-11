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

const findAllPacksCompleted = (packSearch) => ({
  type: actionTypes.FIND_ALL_PACKS_COMPLETED,
  packSearch,
});

export const findAllPacks = (page) => (dispatch) => {
  backend.packService.findAllPacks(page, (result) =>
    dispatch(findAllPacksCompleted({ page, result }))
  );
};

export const previousFindPacksPage = (page) => findPacks(page - 1);

export const nextFindPacksPage = (page) => findPacks(page + 1);

export const previousFindAllPacksPage = (page) => findAllPacks(page - 1);

export const nextFindAllPacksPage = (page) => findAllPacks(page + 1);

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

export const hidePack = (id) => (dispatch) =>
  backend.packService.hidePack(id, () => {
    dispatch(findAllPacks(0));
  });

export const outstandingPack = (id) => (dispatch) =>
  backend.packService.outstandingPack(id, () => {
    dispatch(findAllPacks(0));
  });
