import React, { useEffect } from "react";
import { useDispatch } from "react-redux";
import { BrowserRouter as Router } from "react-router-dom";
import {ThemeProvider} from '@mui/material/styles';

import theme from '../ThemeConfig';
import Header from "./Header";
import Body from "./Body";
import Footer from "./Footer";
import users from "../../users";

const reauthenticationCallback = dispatch => () =>
  dispatch(users.actions.logout());

  export default function App(props) {
    const dispatch = useDispatch();

    useEffect(() => {
      dispatch(
        users.actions.tryLoginFromServiceToken(
          reauthenticationCallback(dispatch))
    )});

  return (
    <div>
      <ThemeProvider theme={theme}>
        <Router>
          <div>
            <Header />
            <Body />
          </div>
        </Router>
        <Footer />
      </ThemeProvider>
    </div>
  )
}