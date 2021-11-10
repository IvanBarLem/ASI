import { makeStyles } from "@material-ui/core/styles";
import {
    Box,
    Button, Grid, Paper,
    TextField, Typography
} from "@mui/material";
import { default as React, useState } from "react";
import { FormattedMessage } from "react-intl";
import { useDispatch, useSelector } from "react-redux";
import { useHistory } from "react-router";
import { Errors } from "../../common";
import * as actions from "../actions";
import * as selectors from "../selectors";

const useStyles = makeStyles((theme) => ({
    paper: {
        paddingBottom: theme.spacing(1),
    },
    paperBody: {
        margin: theme.spacing(2),
    },
    row: {
        marginLeft: theme.spacing(1),
        paddingRight: theme.spacing(2),
        marginTop: theme.spacing(2)
    }
}));

const ChangePassword = () => {
    const classes = useStyles();
    const user = useSelector(selectors.getUser);
    const dispatch = useDispatch();
    const history = useHistory();
    const [oldPassword, setOldPassword] = useState("");
    const [newPassword, setNewPassword] = useState("");
    const [confirmNewPassword, setConfirmNewPassword] = useState("");
    const [backendErrors, setBackendErrors] = useState(null);
    const [passwordsDoNotMatch, setPasswordsDoNotMatch] = useState(false);
    const [invalid, setInvalid] = useState(false);

    const handleSubmit = event => {
        event.preventDefault();
        if (checkConfirmNewPassword()) {
            dispatch(actions.changePassword(user.id, oldPassword, newPassword,
                () => history.push('/'),
                errors => setBackendErrors(errors)));
        }
    }

    function checkConfirmNewPassword() {
        if (newPassword !== confirmNewPassword) {
            setPasswordsDoNotMatch(true);
            return false;
        } else {
            return true;
        }
    }

    const handleOldPasswordChange = (value) => {
        setInvalid(false);
        setOldPassword(value);
    }

    const handleNewPasswordChange = (value) => {
        setInvalid(false);
        setPasswordsDoNotMatch(false)
        setNewPassword(value);
    }

    const handleConfirmNewPasswordChange = (value) => {
        setInvalid(false);
        setPasswordsDoNotMatch(false)
        setConfirmNewPassword(value);
    }

    function handleErrorsClose() {
        setBackendErrors(null);
    }

    const handleInvalid = (event) => {
        event.preventDefault();
        setInvalid(true);
    };

    return (
        <React.Fragment>
            <Errors errors={backendErrors} onClose={() => handleErrorsClose()} />
            <form
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
                            <FormattedMessage id="project.users.ChangePassword.title" />
                        </Typography>
                    </Box>
                    <Box className="paperBody">
                        <Grid className={classes.row} container>
                            <Grid item xs={12} md={4}>
                                <Typography>
                                    <FormattedMessage id="project.users.ChangePassword.fields.oldPassword" />
                                </Typography>
                            </Grid>
                            <Grid item xs={12} md={4}>
                                <TextField
                                    id="oldpassword"
                                    label={
                                        <FormattedMessage id="project.global.validator.required" />
                                    }
                                    variant="outlined"
                                    value={oldPassword}
                                    onChange={(e) => handleOldPasswordChange(e.target.value)}
                                    error={invalid}
                                    size="small"
                                    type="password"
                                    required
                                    fullWidth
                                />
                            </Grid>
                        </Grid>
                        <Grid className={classes.row} container>
                            <Grid item xs={12} md={4}>
                                <Typography>
                                    <FormattedMessage id="project.users.ChangePassword.fields.newPassword" />
                                </Typography>
                            </Grid>
                            <Grid item xs={12} md={4}>
                                <TextField
                                    id="newPassword"
                                    label={
                                        <FormattedMessage id="project.global.validator.required" />
                                    }
                                    variant="outlined"
                                    value={newPassword}
                                    onChange={(e) => handleNewPasswordChange(e.target.value)}
                                    error={invalid || passwordsDoNotMatch}
                                    size="small"
                                    type="password"
                                    required
                                    fullWidth
                                />
                            </Grid>
                        </Grid>
                        <Grid className={classes.row} container>
                            <Grid item xs={12} md={4}>
                                <Typography>
                                    <FormattedMessage id="project.users.ChangePassword.fields.confirmNewPassword" />
                                </Typography>
                            </Grid>
                            <Grid item xs={12} md={4}>
                                <TextField
                                    id="confirmNewPassword"
                                    label={
                                        <FormattedMessage id="project.global.validator.required" />
                                    }
                                    variant="outlined"
                                    value={confirmNewPassword}
                                    onChange={(e) => handleConfirmNewPasswordChange(e.target.value)}
                                    size="small"
                                    type="password"
                                    error={invalid || passwordsDoNotMatch}
                                    helperText={passwordsDoNotMatch ?
                                        <FormattedMessage id='project.global.validator.passwordsDoNotMatch' />
                                        :
                                        ""
                                    }
                                    required
                                    fullWidth
                                />
                            </Grid>
                        </Grid>
                        <Grid className={classes.row} container>
                            <Grid item md={4} />
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

export default ChangePassword;
