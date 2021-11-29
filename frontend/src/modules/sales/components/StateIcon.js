import CheckCircleOutlineIcon from "@mui/icons-material/CheckCircleOutline";
import LockIcon from "@mui/icons-material/Lock";
import PriceCheckIcon from "@mui/icons-material/PriceCheck";
import { Grid, Typography } from "@mui/material";
import React from 'react';
import { FormattedMessage } from "react-intl";

const StateIcon = ({ state }) => {
    if (state === "CREATED") {
        return (
            <Grid container spacing={1} justifyContent="center">
                <Grid item>
                    <CheckCircleOutlineIcon color="primary" />
                </Grid>
                <Grid item>
                    <Typography>
                        <FormattedMessage id="project.sales.state.created" />
                    </Typography>
                </Grid>
            </Grid>
        );
    } else if (state === "BLOCKED") {
        return (

            <Grid container spacing={1} justifyContent="center">
                <Grid item>
                    <LockIcon color="error" />
                </Grid>
                <Grid item>
                    <Typography>
                        <FormattedMessage id="project.sales.state.blocked" />
                    </Typography>
                </Grid>
            </Grid>
        );
    } else if (state === "PAID") {
        return (

            <Grid container spacing={1} justifyContent="center">
                <Grid item>
                    <PriceCheckIcon color="success" />
                </Grid>
                <Grid item>
                    <Typography>
                        <FormattedMessage id="project.sales.state.paid" />
                    </Typography>
                </Grid>
            </Grid>
        )
    } else { return (null); }
}

export default StateIcon;