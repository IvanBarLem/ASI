import React, { useState } from 'react';
import { connect } from 'react-redux';
import { FormattedMessage } from 'react-intl';
import { useDispatch } from 'react-redux';
import { useHistory, Link } from 'react-router-dom';
import { Paper, Typography, Box, Grid, TextField, Button, Divider} from '@mui/material';

import { Errors } from '../../common';
import * as actions from '../actions';

import '../users.css';

const Login = () => {
    const dispatch = useDispatch();
    const history = useHistory();
    const [userName, setUserName] = useState('');
    const [password, setPassword] = useState('');
    const [backendErrors, setBackendErrors] = useState(null);
    const [invalid, setInvalid] = useState(false);
    let form;

    const handleSubmit = event => {
        event.preventDefault();

        if (form.checkValidity()) {
            dispatch(actions.login(
                userName.trim(),
                password,
                () => history.push('/'),
                errors => setBackendErrors(errors),
                () => {
                    history.push('/users/login');
                    dispatch(actions.logout());
                }
            ));
        } else {
            setBackendErrors(null);
        }
    }

    const handleInvalid = event => {
        event.preventDefault();
        setInvalid(true);
    }

    return (
        <div>
            <Errors errors={backendErrors} onClose={() => setBackendErrors(null)}/>
            <form ref={node => form = node}
                onSubmit={e => handleSubmit(e)}
                onInvalid={e => handleInvalid(e)}
            >
                <Paper className="paper">
                    <Box sx={{
                        bgcolor: 'primary.dark',
                        color: 'primary.contrastText',
                        padding: 1,
                        borderRadius:1,
                        borderBottomLeftRadius: 0,
                        borderBottomRightRadius:0,
                    }}>
                        <Typography variant="h5">
                            <FormattedMessage id="project.users.Login.title"/>
                        </Typography>
                    </Box>
                    <Box className="paperBody">
                        <Grid className="row" container>
                            <Grid item xs={12} md={3}>
                                <Typography>
                                    <FormattedMessage id="project.global.fields.userName"/>
                                </Typography>
                            </Grid>
                            <Grid item xs={12} md={4}>
                                <TextField 
                                    id="userName"
                                    label={<FormattedMessage id='project.global.validator.required'/>}
                                    variant="outlined"
                                    value={userName}
                                    onChange={e => setUserName(e.target.value)}
                                    size="small"
                                    error={userName === "" && invalid}
                                    required
                                    fullWidth
                                />
                            </Grid>
                        </Grid>
                        <Grid className="row" container>
                            <Grid item xs={12} md={3}>
                                <Typography>
                                    <FormattedMessage id="project.global.fields.password"/>
                                </Typography>
                            </Grid>
                            <Grid item xs={12} md={4}>
                                <TextField 
                                    id="password"
                                    label={<FormattedMessage id='project.global.validator.required'/>}
                                    variant="outlined"
                                    type="password"
                                    value={password}
                                    onChange={e => setPassword(e.target.value)}
                                    error={password === "" && invalid}
                                    size="small"
                                    required
                                    fullWidth
                                />
                            </Grid>
                        </Grid>
                        <Grid className="row" container>
                            <Grid item md={3}/>
                            <Grid item xs={12} md={4}>
                                <Button color="primary" variant="contained" type="submit">
                                    <FormattedMessage id="project.users.Login.title"/>
                                </Button>
                            </Grid>
                        </Grid>
                        <Divider/>
                        <Link to='/users/signup'>
                            <FormattedMessage id="project.users.SignUp.title"/>
                        </Link>
                    </Box>
                </Paper>
            </form>
        </div>
    );
}

export default connect()(Login);