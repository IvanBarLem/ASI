import { combineReducers } from "redux";

import app from "../modules/app";
import users from "../modules/users";
import packs from "../modules/packs";
import agents from "../modules/agents";
import sales from "../modules/sales";

const rootReducer = combineReducers({
  app: app.reducer,
  packs: packs.reducer,
  users: users.reducer,
  agents: agents.reducer,
  sales: sales.reducer
});

export default rootReducer;
