import ArrowDropDownIcon from '@mui/icons-material/ArrowDropDown';
import ArrowDropUpIcon from '@mui/icons-material/ArrowDropUp';
import {
    Box,
    Divider, Grid, Paper,
    Typography
} from "@mui/material";
import { createTheme } from '@mui/material/styles';
import React from 'react';
import { FormattedMessage } from "react-intl";

const KPI = ({ name, kpi, lastMonthKpi }) => {
    const theme = createTheme();

    const kpiColor = kpi > lastMonthKpi ? theme.palette.success.main : theme.palette.error.main;

    const kpiDiff = lastMonthKpi === 0 ? 0 : (kpi / lastMonthKpi - 1) * 100

    return (
        <Paper sx={{ height: "100%" }}>
            <Box sx={{ padding: 1, height: 50 }}>
                <Typography sx={{ fontWeight: 600 }} variant="h5">
                    {name}
                </Typography>
            </Box>
            <Divider />
            <Grid container spacing={1} sx={{ height: "calc(100% - 50px)" }}>
                <Grid item sx={{ margin: 1 }}>
                    <Grid container>
                        {kpi > lastMonthKpi ?
                            <ArrowDropUpIcon fontSize="large" color="success" />
                            :
                            <ArrowDropDownIcon fontSize="large" color="error" />
                        }
                        <Typography sx={{ color: kpiColor }} variant="h1">
                            {kpi.toFixed(1)}
                        </Typography>
                    </Grid>
                </Grid>
                <Grid item >
                    <Divider orientation="vertical" />
                </Grid>
                <Grid item sx={{ margin: 1 }}>
                    <Box>
                        <Typography sx={{ color: kpiColor }} variant="h3">
                            {kpiDiff > 0 ? "+" : ""}{kpiDiff.toFixed(1)}%
                        </Typography>
                        <Typography variant="h6" sx={{ maxWidth: 150 }}>
                            <FormattedMessage id="project.agents.kpi.respectingLastMonth" /> ({lastMonthKpi.toFixed(1)})
                        </Typography>
                    </Box>
                </Grid>
            </Grid>
        </Paper>
    );
}

export default KPI;