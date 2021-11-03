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
