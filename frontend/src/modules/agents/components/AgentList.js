import {
    Box, Button,
    Container, Grid, Paper,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead, TableRow,
    Typography,
    TextField,
} from "@mui/material";
import { createTheme } from '@mui/material/styles';
import React, { useState } from 'react';
import { FormattedMessage } from "react-intl";
import KPI from './KPI';
import Velocimeter from "./Velocimeter";

const AgentList = () => {
    const theme = createTheme();
    const [billingObjective, setBillingObjective] = useState(0);
    const [salesObjective, setSalesObjective] = useState(0);

    const agents = [
        {
            id: 1,
            firstName: "name",
            lastName: "surname"
        },
        {
            id: 2,
            firstName: "Chuck",
            lastName: "Norris"
        },
        {
            id: 3,
            firstName: "Michael",
            lastName: "Myers"
        },
        {
            id: 4,
            firstName: "Willy",
            lastName: "Rex"
        }
    ];

    const facturacion = 11342;
    const numVentas = 104;

    const facturacionMesPasado = 9673;
    const numVentasMesPasado = 97;

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
                            onChange={e => setBillingObjective(e.target.value)}
                            label={<FormattedMessage id="project.agents.agentList.billingObjective" />}
                            type="number"
                        />
                    </Grid>
                    <Grid item>
                        <TextField
                            id="salesNum"
                            sx={{ marginTop: 5 }}
                            value={salesObjective}
                            onChange={e => setSalesObjective(e.target.value)}
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
                {billingObjective !== 0 && billingObjective !== null ?
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
                {salesObjective !== 0 && salesObjective !== null ?
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
            <Box display="inline-block">
                <Paper sx={{ marginTop: 5, marginBottom: 5, padding: 1, backgroundColor: theme.palette.primary.light }}>
                    <Typography sx={{ color: theme.palette.primary.contrastText }} variant="h5">
                        <FormattedMessage id="project.agents.agentList.businessStats" />
                    </Typography>
                </Paper>
            </Box>
            <Paper>
                <TableContainer>
                    <Table>
                        <TableHead>
                            <TableRow>
                                <TableCell>
                                    <FormattedMessage id="project.global.fields.firstName" />
                                </TableCell>
                                <TableCell>
                                    <FormattedMessage id="project.global.fields.lastName" />
                                </TableCell>
                                <TableCell />
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {agents.map((agent) => (
                                <TableRow key={agent.id}>
                                    <TableCell>
                                        <Typography>
                                            {agent.firstName}
                                        </Typography>
                                    </TableCell>
                                    <TableCell>
                                        <Typography>
                                            {agent.lastName}
                                        </Typography>
                                    </TableCell>
                                    <TableCell align="right">
                                        <Button>
                                            <FormattedMessage id="project.global.fields.details" />
                                        </Button>
                                    </TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </Paper>
        </React.Fragment>
    );
}

export default AgentList;