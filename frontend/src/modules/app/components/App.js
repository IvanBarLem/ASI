import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { BrowserRouter as Router } from "react-router-dom";
import { ThemeProvider } from '@mui/material/styles';
import { makeStyles } from '@material-ui/core/styles';
import useMediaQuery from '@mui/material/useMediaQuery';

import theme from '../ThemeConfig';
import Header from "./Header";
import Body from "./Body";
import Footer from "./Footer";
import users from "../../users";

const drawerWidth = 240;

const useStyles = makeStyles({
    "@global": {
        ".offset": theme.mixins.toolbar,
        ".drawerPaper": {
            width: drawerWidth,
        },
        "myDrawer": {
            width: drawerWidth,
            flexShrink: 0,
        }
    }
});

const reauthenticationCallback = dispatch => () =>
    dispatch(users.actions.logout());

export default function App(props) {
    useStyles();
    const loggedIn = useSelector(users.selectors.isLoggedIn);
    const matches = useMediaQuery(theme.breakpoints.up('md'));
    const headerMarginClasses = (loggedIn && matches) ? {
        width: `calc(100% - ${drawerWidth}px)`,
        marginLeft: drawerWidth
    } : {};
    const bodyMarginClasses = (loggedIn && matches) ? {
        marginLeft: `${drawerWidth}px`
    } : {};

    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(
            users.actions.tryLoginFromServiceToken(
                reauthenticationCallback(dispatch))
        )
    });

    return (
        <div>
            <ThemeProvider theme={theme}>
                <Router>
                    <div>
                        <Header dropDownMarginClasses={headerMarginClasses} />
                        <Body loggedIn={loggedIn} dropDownMarginClasses={bodyMarginClasses} />
                    </div>
                </Router>
                <Footer dropDownMarginClasses={bodyMarginClasses} />
            </ThemeProvider>
        </div>
    )
}