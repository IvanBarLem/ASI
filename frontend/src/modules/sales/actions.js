import * as actionTypes from "./actionTypes";
import backend from "../../backend";

const findSalesCompleted = (saleSearch) => ({
    type: actionTypes.FIND_SALES_COMPLETED,
    saleSearch,
});

export const findSales = (page, client, agent) => (dispatch) => {
    backend.saleService.findSales(page, client, agent, (result) =>
        dispatch(findSalesCompleted({ page, result }))
    );
};

export const previousFindSalesPage = (page, client, agent) => findSales(page - 1, client, agent);

export const nextFindSalesPage = (page, client, agent) => findSales(page + 1, client, agent);

export const clearSaleSearch = () => ({
    type: actionTypes.CLEAR_SALE_SEARCH,
});

export const blockSale = (page, client, agent, saleId) => (dispatch) => {
    backend.saleService.blockSale(saleId,
        () => dispatch(findSales(page - 1, client, agent))
    );
}

export const paySale = (page, client, agent, saleId) => (dispatch) => {
    backend.saleService.paySale(saleId,
        () => dispatch(findSales(page - 1, client, agent))
    );
}
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
