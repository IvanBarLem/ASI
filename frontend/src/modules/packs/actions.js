import * as actionTypes from "./actionTypes";
import backend from "../../backend";

const createPackCompleted = (pack) => ({
  type: actionTypes.CREATE_PACK_COMPLETED,
  pack,
});

export const createPack = (pack, onSuccess, onErrors) => (dispatch) =>
  backend.packService.createPack(
    pack,
    (pack) => {
      dispatch(createPackCompleted(pack));
      onSuccess();
    },
    onErrors
  );
