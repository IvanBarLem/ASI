import * as actionTypes from "./actionTypes";
import backend from "../../backend";

const findSalesCompleted = (saleSearch) => ({
    type: actionTypes.FIND_SALES_COMPLETED,
    saleSearch,
});

export const findSales = (page) => (dispatch) => {
    backend.saleService.findSales(page, (result) =>
        dispatch(findSalesCompleted({ page, result }))
    );
};

export const previousFindSalesPage = (page) => findSales(page - 1);

export const nextFindSalesPage = (page) => findSales(page + 1);

export const clearSaleSearch = () => ({
    type: actionTypes.CLEAR_SALE_SEARCH,
});

export const blockSale = (page, saleId) => (dispatch) => {
    backend.saleService.blockSale(saleId,
        () => dispatch(findSales(page - 1))
    );
}

export const paySale = (page, saleId) => (dispatch) => {
    backend.saleService.paySale(saleId,
        () => dispatch(findSales(page - 1))
    );
}