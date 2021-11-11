import {
    Box, Grid, Paper,
    TextField, Typography
} from "@mui/material";
import { createTheme } from '@mui/material/styles';
import React, { useState } from 'react';
import { FormattedMessage } from "react-intl";
import KPI from './KPI';
import Velocimeter from "./Velocimeter";

const BusinessStats = () => {
    const theme = createTheme();
    const [billingObjective, setBillingObjective] = useState(0);
    const [salesObjective, setSalesObjective] = useState(0);

    const facturacion = 11342;
    const numVentas = 104;

    const facturacionMesPasado = 9673;
    const numVentasMesPasado = 97;

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
                    <KPI name="KPI de facturación" kpi={facturacion / numVentas} lastMonthKpi={facturacionMesPasado / numVentasMesPasado} />
                </Grid>
                {billingObjective !== 0 && billingObjective !== null && !isNaN(billingObjective) ?
                    <Grid item>
                        <Velocimeter
                            title={<FormattedMessage id="project.agents.agentList.billing" />}
                            value={facturacion}
                            objective={billingObjective}
                            symbol="€"
                        />
                    </Grid>
                    :
                    <React.Fragment />
                }
                {salesObjective !== 0 && salesObjective !== null && !isNaN(salesObjective) ?
                    <Grid item>
                        <Velocimeter
                            title={<FormattedMessage id="project.agents.agentList.salesNum" />}
                            value={numVentas}
                            objective={salesObjective}
                        />
                    </Grid>
                    :
                    <React.Fragment />
                }
            </Grid>
        </React.Fragment>
    );
}

export default BusinessStats