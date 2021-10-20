import { combineReducers } from "redux";

import app from "../modules/app";
import users from "../modules/users";
import packs from "../modules/packs";

const rootReducer = combineReducers({
  app: app.reducer,
  packs: packs.reducer,
  users: users.reducer
});

export default rootReducer;
