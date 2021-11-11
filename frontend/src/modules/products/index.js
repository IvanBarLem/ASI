import * as actions from "./actions";
import * as actionTypes from "./actionTypes";
import reducer from "./reducer";
import * as selectors from "./selectors";

export { default as Travels } from "./components/Travels";
export { default as Transports } from "./components/Transports";
export { default as Accommodations } from "./components/Accommodations";
export { default as Activities } from "./components/Activities";

export default { actions, actionTypes, reducer, selectors };
