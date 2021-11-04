import {
    Button,
    Grid, Paper,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead, TableRow,
    Typography
} from "@mui/material";
import { createTheme } from '@mui/material/styles';
import React from 'react';
import { FormattedMessage } from "react-intl";
import KPI from './KPI';

const AgentList = () => {
    const theme = createTheme();

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

    return (
        <React.Fragment>
            <Grid container justifyContent="center">
                <Grid item xs={12} md={8} component={Paper} >
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
                </Grid>
            </Grid>
            <Paper sx={{ marginTop: 5, padding: 1, backgroundColor: theme.palette.primary.light }}>
                <Typography sx={{ color: theme.palette.primary.contrastText }} variant="h5">
                    Estadísticas de la empresa
                </Typography>
            </Paper>
            <Grid sx={{ margin: 1, paddingTop: 1 }} spacing={1} container>
                <Grid item>
                    <KPI name="KPI de facturación" kpi={3.6} lastMonthKpi={3} />
                </Grid>
                <Grid item>
                    <KPI name="Otro KPI" kpi={3.1} lastMonthKpi={4} />
                </Grid>
            </Grid>


        </React.Fragment>
    );
}

export default AgentList;