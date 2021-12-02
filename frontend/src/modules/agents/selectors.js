const getModuleState = state => state.agents;

export const getCompanyStatistics = state =>
    getModuleState(state).companyStatistics;

export const getAgentSalesSearch = state =>
    getModuleState(state).agentSalesSearch;