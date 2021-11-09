import SupportAgentIcon from '@mui/icons-material/SupportAgent';
import {
    Alert, Box, Button,
    FormControl, Grid,
    InputLabel, MenuItem, Paper,
    Select, Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead, TableRow,
    TextField, Typography
} from "@mui/material";
import React, { useState } from 'react';
import { FormattedMessage } from "react-intl";
import KPI from './KPI';
import Velocimeter from "./Velocimeter";

const AgentList = () => {
    const [agentBillingObjective, setAgentBillingObjective] = useState(0);
    const [agentSalesObjective, setAgentSalesObjective] = useState(0);
    const [agentSelected, setAgentSelected] = useState(null);
    const [period, setPeriod] = React.useState(0);
    const monthly = 0;

    const agents = [
        {
            id: 1,
            firstName: "Pedro",
            lastName: "Valle",
            facturacionMes: 4000,
            numVentasMes: 42,
            facturacionMesPasado: 3840,
            numVentasMesPasado: 41,
            facturacionSemana: 749,
            numVentasSemana: 9,
            facturacionSemanaPasada: 811,
            numVentasSemanaPasada: 10
        },
        {
            id: 2,
            firstName: "Pablo",
            lastName: "Ramos",
            facturacionMes: 5000,
            numVentasMes: 48,
            facturacionMesPasado: 4387,
            numVentasMesPasado: 44,
            facturacionSemana: 860,
            numVentasSemana: 4,
            facturacionSemanaPasada: 900,
            numVentasSemanaPasada: 12
        },
        {
            id: 3,
            firstName: "Miguel",
            lastName: "Gonzalez",
            facturacionMes: 3000,
            numVentasMes: 23,
            facturacionMesPasado: 3244,
            numVentasMesPasado: 25,
            facturacionSemana: 957,
            numVentasSemana: 12,
            facturacionSemanaPasada: 711,
            numVentasSemanaPasada: 6
        },
        {
            id: 4,
            firstName: "Fernando",
            lastName: "Fuentes",
            facturacionMes: 2400,
            numVentasMes: 20,
            facturacionMesPasado: 1200,
            numVentasMesPasado: 10,
            facturacionSemana: 400,
            numVentasSemana: 3,
            facturacionSemanaPasada: 532,
            numVentasSemanaPasada: 5
        }
    ];

    const changeAgentBillingObjective = (value) => {
        if (value > 0) {
            setAgentBillingObjective(value)
        } else {
            setAgentBillingObjective(0)
        }
    }

    const changeAgentSalesObjective = (value) => {
        if (value > 0) {
            setAgentSalesObjective(value)
        } else {
            setAgentSalesObjective(0)
        }
    }

    const onClickInCell = (agent) => {
        agentSelected && agentSelected.id === agent.id
            ? setAgentSelected(null)
            : setAgentSelected(agent);
    };

    return (
        <React.Fragment>
            <Box sx={{ marginBottom: 3 }}>
                <FormControl>
                    <InputLabel id="period-select-label">
                        <FormattedMessage id="project.agents.agentList.period" />
                    </InputLabel>
                    <Select
                        labelId="period-select-label"
                        id="period-select"
                        value={period}
                        label={<FormattedMessage id="project.agents.agentList.period" />}
                        onChange={e => setPeriod(e.target.value)}
                    >
                        <MenuItem value={0}>
                            <FormattedMessage id="project.agents.fields.month" />
                        </MenuItem>
                        <MenuItem value={1}>
                            <FormattedMessage id="project.agents.fields.week" />
                        </MenuItem>
                    </Select>
                </FormControl>
            </Box>
            <Paper>
                <TableContainer>
                    <Table>
                        <TableHead>
                            <TableRow>
                                <TableCell />
                                <TableCell>
                                    <FormattedMessage id="project.global.fields.firstName" />
                                </TableCell>
                                <TableCell>
                                    <FormattedMessage id="project.global.fields.lastName" />
                                </TableCell>
                                <TableCell>
                                    <FormattedMessage id="project.agents.fields.billing" />
                                </TableCell>
                                <TableCell>
                                    <FormattedMessage id="project.agents.fields.salesNum" />
                                </TableCell>
                                <TableCell />
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {agents.map((agent) => (
                                <TableRow
                                    key={agent.id}
                                    selected={
                                        agentSelected
                                            ? agentSelected.id === agent.id
                                            : false
                                    }
                                >
                                    <TableCell>
                                        <SupportAgentIcon />
                                    </TableCell>
                                    <TableCell onClick={() => onClickInCell(agent)}>
                                        <Typography>
                                            {agent.firstName}
                                        </Typography>
                                    </TableCell>
                                    <TableCell onClick={() => onClickInCell(agent)}>
                                        <Typography>
                                            {agent.lastName}
                                        </Typography>
                                    </TableCell>
                                    <TableCell onClick={() => onClickInCell(agent)}>
                                        {period === monthly ?
                                            <Typography>
                                                {agent.facturacionMes}
                                            </Typography>
                                            :
                                            <Typography>
                                                {agent.facturacionSemana}
                                            </Typography>
                                        }
                                    </TableCell>
                                    <TableCell onClick={() => onClickInCell(agent)}>
                                        {period === monthly ?
                                            <Typography>
                                                {agent.numVentasMes}
                                            </Typography>
                                            :
                                            <Typography>
                                                {agent.numVentasSemana}
                                            </Typography>
                                        }
                                    </TableCell>
                                    <TableCell align="right" onClick={() => onClickInCell(agent)}>
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
            <Box sx={{ marginTop: 3 }}>
                {agentSelected === null ?
                    <Alert severity="info">
                        <FormattedMessage id="project.agents.agentList.selectAgent" />
                    </Alert>
                    :
                    <React.Fragment>
                        <Box display="inline-block">
                            <Paper sx={{ padding: 1 }}>
                                <Typography variant="h5">
                                    {agentSelected.firstName} {agentSelected.lastName}
                                </Typography>
                            </Paper>
                        </Box>
                        <Grid container spacing={2}>
                            <Grid item>
                                <TextField
                                    id="billing"
                                    sx={{ marginTop: 5 }}
                                    value={agentBillingObjective}
                                    onChange={e => changeAgentBillingObjective(e.target.value)}
                                    label={<FormattedMessage id="project.agents.agentList.billingObjective" />}
                                    type="number"
                                />
                            </Grid>
                            <Grid item>
                                <TextField
                                    id="salesNum"
                                    sx={{ marginTop: 5 }}
                                    value={agentSalesObjective}
                                    onChange={e => changeAgentSalesObjective(e.target.value)}
                                    label={<FormattedMessage id="project.agents.agentList.salesNumObjective" />}
                                    type="number"
                                />
                            </Grid>
                        </Grid>
                        <Grid
                            spacing={3}
                            sx={{ marginTop: 3 }}
                            container
                        >
                            <Grid item>
                                {period === monthly ?
                                    <KPI
                                        name="KPI de facturación"
                                        kpi={agentSelected.facturacionMes / agentSelected.numVentasMes}
                                        lastMonthKpi={agentSelected.facturacionMesPasado / agentSelected.numVentasMesPasado}
                                    />
                                    :
                                    <KPI
                                        name="KPI de facturación"
                                        kpi={agentSelected.facturacionSemana / agentSelected.numVentasSemana}
                                        lastMonthKpi={agentSelected.facturacionSemanaPasada / agentSelected.numVentasSemanaPasada}
                                    />
                                }
                            </Grid>
                            {agentBillingObjective !== 0 ?
                                <Grid item>
                                    <Velocimeter
                                        title={<FormattedMessage id="project.agents.agentList.billing" />}
                                        value={period === monthly ? agentSelected.facturacionMes : agentSelected.facturacionSemana}
                                        objective={agentBillingObjective}
                                        symbol="€"
                                    />
                                </Grid>
                                :
                                <React.Fragment />
                            }
                            {agentSalesObjective !== 0 ?
                                <Grid item>
                                    <Velocimeter
                                        title={<FormattedMessage id="project.agents.agentList.salesNum" />}
                                        value={period === monthly ? agentSelected.numVentasMes : agentSelected.numVentasSemana}
                                        objective={agentSalesObjective}
                                    />
                                </Grid>
                                :
                                <React.Fragment />
                            }
                        </Grid>
                    </React.Fragment>
                }
            </Box>
        </React.Fragment>
    );
}

export default AgentList;