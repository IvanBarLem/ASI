import * as actionTypes from "./actionTypes";
import backend from "../../backend";

const getStatisticsProductsCompleted = (products) => ({
  type: actionTypes.GET_STATISTICS_PRODUCTS_COMPLETED,
  products,
});

export const getStatisticsProducts =
  (page, location, category, onSuccess) => (dispatch) =>
    backend.statisticsService.getSalesProducts(
      page,
      location,
      category,
      (products) => {
        dispatch(getStatisticsProductsCompleted(products));
        onSuccess(products);
      }
    );

const getStatisticsCompanyCompleted = (statistics) => ({
  type: actionTypes.GET_STATISTICS_COMPANY_COMPLETED,
  statistics,
});

export const getStatisticsCompany = (onSuccess) => (dispatch) =>
  backend.statisticsService.getSalesCompany((statistics) => {
    dispatch(getStatisticsCompanyCompleted(statistics));
    onSuccess(statistics);
  });
