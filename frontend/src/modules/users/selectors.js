const getModuleState = (state) => state.users;

export const getUser = (state) => getModuleState(state).user;

export const getName = (state) =>
  isLoggedIn(state) ? getUser(state).firstName : null;

export const isLoggedIn = (state) => getUser(state) !== null;

export const getEmail = (state) =>
  isLoggedIn(state) ? getUser(state).email : null;
