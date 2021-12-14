import * as actionTypes from "./actionTypes";
import backend from "../../backend";

const createSaleCompleted = (sale) => ({
  type: actionTypes.CREATE_SALE_COMPLETED,
  sale,
});

export const createSale = (sale, onSuccess, onErrors) => (dispatch) =>
  backend.saleService.createSale(
    sale,
    (sale) => {
      dispatch(createSaleCompleted(sale));
      onSuccess(sale);
    },
    onErrors
  );
