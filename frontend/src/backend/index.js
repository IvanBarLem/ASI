import { init } from "./appFetch";
import * as userService from "./userService";
import * as packService from "./packService";
import * as productService from "./productService";
import * as statisticsService from "./statisticsService";

export { default as NetworkError } from "./NetworkError";

export default { init, userService, packService, productService, statisticsService };
