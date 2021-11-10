import { makeStyles } from "@material-ui/core/styles";
import {
	Alert, Box,
	Button, Grid, Paper,
	TextField, Typography
} from "@mui/material";
import React, { useState } from "react";
import { FormattedMessage } from "react-intl";
import { connect, useDispatch } from "react-redux";
import { useHistory } from "react-router-dom";
import { Errors } from "../../common";
import * as actions from "../actions";
import "../users.css";

const useStyles = makeStyles((theme) => ({
	paper: {
		paddingBottom: theme.spacing(1),
	},
	header: {
		backgroundColor: theme.palette.primary.main,
		color: theme.palette.primary.contrastText,
		padding: theme.spacing(1),
		borderTopLeftRadius: theme.spacing(1),
		borderTopRightRadius: theme.spacing(1),
	},
	paperBody: {
		margin: theme.spacing(2),
	},
	row: {
		marginBottom: theme.spacing(1)
	}
}));

const Login = () => {
	const classes = useStyles();
	const dispatch = useDispatch();
	const history = useHistory();
	const [email, setEmail] = useState("");
	const [password, setPassword] = useState("");
	const [backendErrors, setBackendErrors] = useState(null);
	const [invalid, setInvalid] = useState(false);
	let form;

	const validateEmail = (email) => {
		var re = /\S+@\S+\.\S+/;
		return re.test(email) && email !== "";
	};

	const handleSubmit = (event) => {
		event.preventDefault();

		if (form.checkValidity()) {
			dispatch(
				actions.login(
					email.trim(),
					password,
					() => history.push("/packs"),
					(errors) => setBackendErrors(errors),
					() => {
						history.push("/users/login");
						dispatch(actions.logout());
					}
				)
			);
		} else {
			setBackendErrors(null);
		}
	};

	const handleInvalid = (event) => {
		event.preventDefault();
		setInvalid(true);
	};

	return (
		<div>
			<Errors errors={backendErrors} onClose={() => setBackendErrors(null)} />
			<form
				ref={(node) => (form = node)}
				onSubmit={(e) => handleSubmit(e)}
				onInvalid={(e) => handleInvalid(e)}
			>
				<Paper className={classes.paper}>
					<Box
						sx={{
							bgcolor: "primary.dark",
							color: "primary.contrastText",
							padding: 1,
							borderRadius: 1,
							borderBottomLeftRadius: 0,
							borderBottomRightRadius: 0,
						}}
					>
						<Typography variant="h5">
							<FormattedMessage id="project.users.Login.title" />
						</Typography>
					</Box>
					<Box className="paperBody">
						<Grid className="row" container>
							<Grid item xs={12} md={3}>
								<Typography>
									<FormattedMessage id="project.global.fields.email" />
								</Typography>
							</Grid>
							<Grid item xs={12} md={4}>
								<TextField
									id="email"
									label={
										<FormattedMessage id="project.global.validator.required" />
									}
									variant="outlined"
									value={email}
									onChange={(e) => setEmail(e.target.value)}
									size="small"
									error={!validateEmail(email) && invalid}
									required
									fullWidth
									type="email"
								/>
							</Grid>
						</Grid>
						{!validateEmail(email) && invalid ?
							<Grid className="row" container>
								<Grid item xs={12} md={3} />
								<Grid item xs={12} md={4}>
									<Alert severity="error">
										<FormattedMessage id="project.global.validator.email" />
									</Alert>
								</Grid>
							</Grid>
							:
							<div />
						}
						<Grid className="row" container>
							<Grid item xs={12} md={3}>
								<Typography>
									<FormattedMessage id="project.global.fields.password" />
								</Typography>
							</Grid>
							<Grid item xs={12} md={4}>
								<TextField
									id="password"
									label={
										<FormattedMessage id="project.global.validator.required" />
									}
									variant="outlined"
									type="password"
									value={password}
									onChange={(e) => setPassword(e.target.value)}
									error={password === "" && invalid}
									size="small"
									required
									fullWidth
								/>
							</Grid>
						</Grid>
						<Grid className="row" container>
							<Grid item md={3} />
							<Grid item xs={12} md={4}>
								<Button color="primary" variant="contained" type="submit">
									<FormattedMessage id="project.users.Login.title" />
								</Button>
							</Grid>
						</Grid>
					</Box>
				</Paper>
			</form>
		</div>
	);
};

export default connect()(Login);
