import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { FormattedMessage } from "react-intl";

import { Errors } from "../../common";
import * as actions from "../actions";
import * as selectors from "../selectors";
import { useHistory } from "react-router";
import {
	Paper,
	Typography,
	Box,
	Grid,
	TextField,
	Button
} from "@mui/material";
import { makeStyles } from "@material-ui/core/styles";

const useStyles = makeStyles((theme) => ({
	paper: {
		paddingBottom: theme.spacing(1),
	},
	paperBody: {
		margin: theme.spacing(2),
	},
	row: {
		marginLeft: theme.spacing(1),
		marginTop: theme.spacing(2)
	}
}));

const UpdateProfile = () => {
	const classes = useStyles();
	const user = useSelector(selectors.getUser);
	const dispatch = useDispatch();
	const history = useHistory();

	const [firstName, setFirstName] = useState("");
	const [lastName, setLastName] = useState("");
	const [backendErrors, setBackendErrors] = useState(null);
	const [invalid, setInvalid] = useState(false);
	let form;

	useEffect(() => {
		setFirstName(user.firstName);
		setLastName(user.lastName);
	}, [user]);

	function handleSubmit(event) {
		event.preventDefault();

		if (form.checkValidity()) {
			setInvalid(false)
			updateProfile();
		} else {
			setBackendErrors(null);
			setInvalid(true)
			form.classList.add("was-validated");
		}
	}

	function updateProfile() {
		dispatch(
			actions.updateProfile(
				{
					id: user.id,
					firstName: firstName.trim(),
					lastName: lastName.trim(),
				},
				() => history.push("/"),
				(errors) => setBackendErrors(errors)
			)
		);
	}

	function handleErrorsClose() {
		setBackendErrors(null);
	}

	return (
		<React.Fragment>
			<Errors errors={backendErrors} onClose={() => handleErrorsClose()} />
			<form
	          ref={(node) => (form = node)}
	          className="needs-validation"
	          noValidate
	          onSubmit={(e) => handleSubmit(e)}
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
							<FormattedMessage id="project.users.UpdateProfile.title" />
						</Typography>
					</Box>
					<Box className="paperBody">
						<Grid className={classes.row} container>
							<Grid item xs={12} md={3}>
								<Typography>
									<FormattedMessage id="project.global.fields.firstName" />
								</Typography>
							</Grid>
							<Grid item xs={12} md={4}>
								<TextField
									id="fistName"
									label={
										<FormattedMessage id="project.global.validator.required" />
									}
									variant="outlined"
									value={firstName}
									error={invalid}
									onChange={(e) => setFirstName(e.target.value)}
									size="small"
									required
									fullWidth
								/>
							</Grid>
						</Grid>
						<Grid className={classes.row} container>
							<Grid item xs={12} md={3}>
								<Typography>
									<FormattedMessage id="project.global.fields.lastName" />
								</Typography>
							</Grid>
							<Grid item xs={12} md={4}>
								<TextField
									id="lastName"
									label={
										<FormattedMessage id="project.global.validator.required" />
									}
									variant="outlined"
									value={lastName}
									error={invalid}
									onChange={(e) => setLastName(e.target.value)}
									size="small"
									required
									fullWidth
								/>
							</Grid>
						</Grid>
						<Grid className={classes.row} container>
							<Grid item md={3} />
							<Grid item xs={12} md={4}>
								<Button color="primary" variant="contained" type="submit">
									<FormattedMessage id="project.global.buttons.save" />
								</Button>
							</Grid>
						</Grid>
					</Box>
				</Paper>
			</form>
		</React.Fragment>
	);
};

export default UpdateProfile;
