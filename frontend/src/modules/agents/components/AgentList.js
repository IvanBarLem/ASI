import SupportAgentIcon from '@mui/icons-material/SupportAgent';
import {
    Alert, Box,
    FormControl, Grid,
    InputLabel, MenuItem, Paper,
    Select, Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead, TableRow,
    TextField, Typography,
    Pagination
} from "@mui/material";
import { useDispatch, useSelector } from "react-redux";
import React, { useEffect, useState } from 'react';
import { FormattedMessage } from "react-intl";
import KPI from './KPI';
import Velocimeter from "./Velocimeter";
import * as actions from "../actions";
import * as selectors from "../selectors";

const AgentList = () => {
    const dispatch = useDispatch();
    const [agentBillingObjective, setAgentBillingObjective] = useState(0);
    const [agentSalesObjective, setAgentSalesObjective] = useState(0);
    const [agentSelected, setAgentSelected] = useState(null);
    const [period, setPeriod] = React.useState(0);
    const [page, setPage] = React.useState(1);
    const agentSalesSearch = useSelector(selectors.getAgentSalesSearch);
    const monthly = 0;

    useEffect(() => {
        dispatch(actions.findAgentSales(0, 30));
        return () => {
            dispatch(actions.clearAgentSalesSearch())
        }
    }, [])

    const changeAgentBillingObjective = (value) => {
        if (value >= 0) {
            setAgentBillingObjective(value)
        } else {
            setAgentBillingObjective(0)
        }
    }

    const changeAgentSalesObjective = (value) => {
        if (value >= 0) {
            setAgentSalesObjective(value)
        } else {
            setAgentSalesObjective(0)
        }
    }

    const onClickInCell = (agent) => {
        agentSelected && agentSelected.agentId === agent.agentId
            ? setAgentSelected(null)
            : setAgentSelected(agent);
    };

    const changePeriod = (value) => {
        setPeriod(value);
        setAgentSelected(null)
        if (value === monthly) {
            dispatch(actions.findAgentSales(0, 30));
        } else {
            dispatch(actions.findAgentSales(0, 7));
        }
    }

    const handlePageChange = (event, value) => {
        if (period === monthly) {
            dispatch(actions.findAgentSales(value - 1, 30));
        } else {
            dispatch(actions.findAgentSales(value - 1, 7));
        }
        setPage(value);
    };

    return (
        <React.Fragment>
            {(agentSalesSearch === null || agentSalesSearch.result.content.length === 0) ?
                <React.Fragment>
                    <Alert severity="info">
                        <FormattedMessage id="project.agents.foundNoAgents" />
                    </Alert>
                </React.Fragment>
                :
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
                                onChange={e => changePeriod(e.target.value)}
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
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {agentSalesSearch.result.content.map((agent) => (
                                        <TableRow
                                            key={agent.agentId}
                                            selected={
                                                agentSelected
                                                    ? agentSelected.agentId === agent.agentId
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
                                                <Typography>
                                                    {agent.currentBilling}???
                                                </Typography>
                                            </TableCell>
                                            <TableCell onClick={() => onClickInCell(agent)}>
                                                <Typography>
                                                    {agent.currentSales}
                                                </Typography>
                                            </TableCell>
                                        </TableRow>
                                    ))}
                                </TableBody>
                            </Table>
                        </TableContainer>
                        <Grid container display="flex" justifyContent="center">
                            <Pagination
                                count={agentSalesSearch.result.totalPages}
                                page={page}
                                onChange={handlePageChange}
                            />
                        </Grid>
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
                                        <KPI
                                            name="KPI de facturaci??n"
                                            kpi={agentSelected.currentSales === 0 ? 0 : agentSelected.currentBilling / agentSelected.currentSales}
                                            lastMonthKpi={agentSelected.previousSales === 0 ? 0 : agentSelected.previousBilling / agentSelected.previousSales}
                                        />
                                    </Grid>
                                    {parseFloat(agentBillingObjective) !== 0 && agentBillingObjective !== '' &&
                                        <Grid item>
                                            <Velocimeter
                                                title={<FormattedMessage id="project.agents.agentList.billing" />}
                                                value={agentSelected.currentBilling}
                                                objective={agentBillingObjective}
                                                symbol="???"
                                            />
                                        </Grid>
                                    }
                                    {parseFloat(agentSalesObjective) !== 0 && agentSalesObjective !== '' &&
                                        <Grid item>
                                            <Velocimeter
                                                title={<FormattedMessage id="project.agents.agentList.salesNum" />}
                                                value={agentSelected.currentSales}
                                                objective={agentSalesObjective}
                                            />
                                        </Grid>
                                    }
                                </Grid>
                            </React.Fragment>
                        }
                    </Box>
                </React.Fragment>
            }
        </React.Fragment>
    );
}

export default AgentList;