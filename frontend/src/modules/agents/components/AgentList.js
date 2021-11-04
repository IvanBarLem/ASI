import { createTheme } from '@mui/material/styles';
import {
    Box,
    Button,
    Card,
    CardContent, Grid, Paper,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead, TableRow,
    Typography
} from "@mui/material";
import React from 'react';
import { FormattedMessage } from "react-intl";
import { Cell, Pie, PieChart, Tooltip } from 'recharts';

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
    const data01 = [
        {
            "name": "Group A",
            "value": 100
        },
    ];
    const data02 = [
        {
            "name": "Group A",
            "value": 80
        },
        {
            "name": "Group B",
            "value": 20
        }
    ];

    const data03 = [
        {
            "name": "Group A",
            "value": 70
        },
        {
            "name": "Group B",
            "value": 30
        },
    ];
    const data04 = [
        {
            "name": "Group A",
            "value": 80
        },
        {
            "name": "Group B",
            "value": 20
        }
    ];
    console.log(theme.palette.primary.main)
    const COLORS = [theme.palette.primary.main, theme.palette.error.main];

    const COLORS2 = [theme.palette.primary.main, '#FFFFFF'];

    const RADIAN = Math.PI / 180;
    const renderCustomizedLabel = ({ cx, cy, midAngle, innerRadius, outerRadius, percent, index }) => {
        const radius = outerRadius * 1.2;
        const x = cx + radius * Math.cos(-midAngle * RADIAN);
        const y = cy + radius * Math.sin(-midAngle * RADIAN);

        return (
            <text x={x} y={y} fill="red" textAnchor={x > cx ? 'start' : 'end'} dominantBaseline="central">
                {`${(percent * 100).toFixed(0)}%`}
            </text>
        );
    };

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
            <Paper sx={{ marginTop: 5 }}>
                <Box sx={{ padding: 1, backgroundColor: theme.palette.primary.light }}>
                    <Typography sx={{ color: theme.palette.primary.contrastText }} variant="h5">
                        Estadísticas de la empresa
                    </Typography>
                </Box>
                <Grid container>
                    <Grid item>
                        <PieChart width={700} height={250}>

                            <Pie
                                data={data01}
                                dataKey="value"
                                nameKey="name"
                                startAngle={10 + 30}
                                endAngle={0 + 30}
                                cx="50%"
                                cy="50%"
                                outerRadius={65}
                                fill={theme.palette.error.main}
                            >
                            </Pie>
                            <Pie
                                data={data02}
                                dataKey="value"
                                nameKey="name"
                                startAngle={180}
                                endAngle={0}
                                cx="50%"
                                cy="50%"
                                innerRadius={60}
                                outerRadius={80}
                                fill="#82ca9d"
                                label
                            >
                                {data02.map((entry, index) => (
                                    <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
                                ))}
                            </Pie>
                        </PieChart>
                    </Grid>
                    <PieChart width={700} height={250}>

                        <Pie
                            data={data03}
                            dataKey="value"
                            nameKey="name"
                            startAngle={180}
                            endAngle={0}
                            cx="50%"
                            cy="50%"
                            innerRadius={90}
                            outerRadius={110}
                            fill="#82ca9d"
                        >
                            {data02.map((entry, index) => (
                                <Cell key={`cell-${index}`} fill={COLORS2[index % COLORS2.length]} />
                            ))}
                        </Pie>
                        <Pie
                            data={data04}
                            dataKey="value"
                            nameKey="name"
                            startAngle={180}
                            endAngle={0}
                            cx="50%"
                            cy="50%"
                            innerRadius={60}
                            outerRadius={80}
                            fill="#82ca9d"
                            labelLine={true}
                            label={renderCustomizedLabel}
                        >
                            {data02.map((entry, index) => (
                                <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
                            ))}
                        </Pie>
                    </PieChart>
                </Grid>
            </Paper>

        </React.Fragment>
    );
}

export default AgentList;