import {
    Alert,
    Box, Grid, Paper,
    TextField, Typography
} from "@mui/material";
import { createTheme } from '@mui/material/styles';
import React, { useEffect, useState } from 'react';
import { FormattedMessage } from "react-intl";
import { useDispatch, useSelector } from "react-redux";
import * as actions from "../actions";
import * as selectors from "../selectors";
import KPI from './KPI';
import Velocimeter from "./Velocimeter";

const BusinessStats = () => {
    const theme = createTheme();
    const dispatch = useDispatch();
    const [billingObjective, setBillingObjective] = useState(0);
    const [salesObjective, setSalesObjective] = useState(0);
    const companyStatistics = useSelector(selectors.getCompanyStatistics);

    useEffect(() => {
        dispatch(actions.findCompanyStatistics())
        return () => {
            dispatch(actions.clearCompanyStatistics())
        }
    }, [])

    const changeBillingObjective = (value) => {
        if (value >= 0) {
            setBillingObjective(value)
        } else {
            setBillingObjective(0)
        }
    }

    const changeSalesObjective = (value) => {
        if (value >= 0) {
            setSalesObjective(value)
        } else {
            setSalesObjective(0)
        }
    }

    return (
        <React.Fragment>
            {companyStatistics === null ?
                <React.Fragment>
                    <Alert severity="info">
                        <FormattedMessage id="project.packs.foundNoPacks" />
                    </Alert>
                </React.Fragment>
                :
                <React.Fragment>
                    <Box display="inline-block">
                        <Paper sx={{ padding: 1, backgroundColor: theme.palette.primary.light }}>
                            <Typography sx={{ color: theme.palette.primary.contrastText }} variant="h5">
                                <FormattedMessage id="project.agents.agentList.businessStats" />
                            </Typography>
                        </Paper>
                        <Grid container spacing={2}>
                            <Grid item>
                                <TextField
                                    id="billing"
                                    sx={{ marginTop: 5 }}
                                    value={billingObjective}
                                    onChange={e => changeBillingObjective(e.target.value)}
                                    label={<FormattedMessage id="project.agents.agentList.billingObjective" />}
                                    type="number"
                                />
                            </Grid>
                            <Grid item>
                                <TextField
                                    id="salesNum"
                                    sx={{ marginTop: 5 }}
                                    value={salesObjective}
                                    onChange={e => changeSalesObjective(e.target.value)}
                                    label={<FormattedMessage id="project.agents.agentList.salesNumObjective" />}
                                    type="number"
                                />
                            </Grid>
                        </Grid>
                    </Box>
                    <Grid
                        sx={{
                            marginTop: 1,
                        }}
                        spacing={3}
                        container
                    >

                        <Grid item>
                            <KPI
                                name="KPI de facturación"
                                kpi={companyStatistics.currentSales === 0 ?
                                    0
                                    :
                                    companyStatistics.currentBilling / companyStatistics.currentSales
                                }
                                lastMonthKpi={companyStatistics.previousSales === 0 ?
                                    0
                                    :
                                    companyStatistics.previousBilling / companyStatistics.previousSales
                                }
                            />
                        </Grid>
                        {parseFloat(billingObjective) !== 0 && billingObjective !== '' &&
                            <Grid item>
                                <Velocimeter
                                    title={<FormattedMessage id="project.agents.agentList.billing" />}
                                    value={companyStatistics.currentBilling}
                                    objective={billingObjective}
                                    symbol="€"
                                />
                            </Grid>
                        }
                        {parseFloat(salesObjective) !== 0 && salesObjective !== '' &&
                            <Grid item>
                                <Velocimeter
                                    title={<FormattedMessage id="project.agents.agentList.salesNum" />}
                                    value={companyStatistics.currentSales}
                                    objective={salesObjective}
                                />
                            </Grid>
                        }
                    </Grid>
                </React.Fragment>
            }
        </React.Fragment>
    );
}

export default BusinessStats