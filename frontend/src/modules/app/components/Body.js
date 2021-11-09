import { Box } from "@mui/material";
import React from "react";
import { useSelector } from "react-redux";
import { Route, Switch, withRouter } from "react-router-dom";
import { AgentList, BusinessStats } from "../../agents";
import { CreatePack, FindPacks } from "../../packs";
import {
    Accommodations,
    Activities,
    Transports,
    Travels
} from "../../products";
import { ProductList } from "../../products-stats";
import users, {
    ChangePassword,
    Login,
    Logout,
    SignUp,
    UpdateProfile
} from "../../users";
import AppGlobalComponents from "./AppGlobalComponents";
import Home from "./Home";

const Body = (props) => {
    const isGerente = useSelector(users.selectors.isGerente);
    const isInformatico = useSelector(users.selectors.isInformatico);
    return (
        <Box sx={{ margin: 4 }}>
            <Box sx={props.dropDownMarginClasses} m={2}>
                <AppGlobalComponents />
                <Switch>
                    <Route exact path="/" component={Home} />
                    {props.loggedIn && (
                        <Route exact path="/users/update-profile" component={UpdateProfile} />
                    )}
                    {props.loggedIn && (
                        <Route
                            exact
                            path="/users/change-password"
                            component={ChangePassword}
                        />
                    )}
                    {props.loggedIn && (
                        <Route exact path="/users/logout" component={Logout} />
                    )}
                    {isGerente && (
                        <Route exact path="/create-pack" component={CreatePack} />
                    )}
                    {(isGerente || isInformatico) && <Route exact path="/travels" component={Travels} />}
                    {(isGerente || isInformatico) && (
                        <Route exact path="/accommodations" component={Accommodations} />
                    )}
                    {(isGerente || isInformatico) && (
                        <Route exact path="/transports" component={Transports} />
                    )}
                    {(isGerente || isInformatico) && (
                        <Route exact path="/activities" component={Activities} />
                    )}
                    {props.loggedIn && <Route exact path="/packs" component={FindPacks} />}
                    {isGerente && <Route exact path="/agents" component={AgentList} />}
                    {isGerente && <Route exact path="/businessStats" component={BusinessStats} />}
                    {isGerente && (
                        <Route exact path="/products" component={ProductList} />
                    )}
                    {!props.loggedIn && (
                        <Route exact path="/users/login" component={Login} />
                    )}
                    {!props.loggedIn && (
                        <Route exact path="/users/signup" component={SignUp} />
                    )}
                    <Route component={Home} />
                </Switch>
            </Box>
        </Box>
    );
}

/*
 * It is necessary to call withRouter(connect(...)(FindProducts)), since Body
 * must be re-rendered when 'location' changes (among others, withRouter pass
 * 'location' property to the wrapped component).
 */
export default withRouter(Body);
