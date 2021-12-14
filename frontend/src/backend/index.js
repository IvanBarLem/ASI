import { init } from "./appFetch";
import * as userService from "./userService";
import * as packService from "./packService";
import * as productService from "./productService";
import * as statisticsService from "./statisticsService";
import * as saleService from "./saleService";

export { default as NetworkError } from "./NetworkError";

export default {
  init,
  userService,
  packService,
  productService,
  statisticsService,
  saleService,
};
