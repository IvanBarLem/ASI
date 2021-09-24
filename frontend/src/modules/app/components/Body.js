import React from "react";
import { Route, Switch, withRouter } from "react-router-dom";
import { Box } from '@mui/material';

import AppGlobalComponents from "./AppGlobalComponents";
import Home from "./Home";
import {
  Login,
  SignUp,
  UpdateProfile,
  ChangePassword,
  Logout
} from "../../users";

const Body = (props) => (
	<div className="container">
		<Box sx={props.dropDownMarginClasses} m={2}>
			<AppGlobalComponents />
			<Switch>
				<Route exact path="/" component={Home} />
				{props.loggedIn && (
				<Route exact path="/users/update-profile" component={UpdateProfile} />
				)}
				{props.loggedIn && (
				<Route exact path="/users/change-password" component={ChangePassword} />
				)}
				{props.loggedIn && <Route exact path="/users/logout" component={Logout} />}
				{!props.loggedIn && <Route exact path="/users/login" component={Login} />}
				{!props.loggedIn && <Route exact path="/users/signup" component={SignUp} />}
				<Route component={Home} />
			</Switch>
		</Box>
	</div>
);



/*
 * It is necessary to call withRouter(connect(...)(FindProducts)), since Body
 * must be re-rendered when 'location' changes (among others, withRouter pass
 * 'location' property to the wrapped component).
 */
export default withRouter(Body);
