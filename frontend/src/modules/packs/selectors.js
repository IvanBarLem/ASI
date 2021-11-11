const getModuleState = state => state.packs;

export const getPackSearch = state =>
    getModuleState(state).packSearch;