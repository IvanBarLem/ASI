import { useEffect } from "react";
import { useSelector } from "react-redux";
import { useHistory } from "react-router-dom";
import * as selectors from "../selectors";

const LoginRedirect = () => {
    const history = useHistory();
    const isCliente = useSelector(selectors.isCliente);

    useEffect(() => {
        if (isCliente !== undefined) {
            if (isCliente) {
                history.push("/sales");
            } else {
                history.push("/packs");
            }
        }
    }, [isCliente])

    return null;
}

export default LoginRedirect