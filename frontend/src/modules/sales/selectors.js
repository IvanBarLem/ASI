const getModuleState = state => state.sales;

export const getSaleSearch = state =>
    getModuleState(state).saleSearch;