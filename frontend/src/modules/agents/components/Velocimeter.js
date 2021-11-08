import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import SportsScoreIcon from '@mui/icons-material/SportsScore';
import TimelineIcon from '@mui/icons-material/Timeline';
import {
    Box,
    Container, Divider,
    List,
    ListItem,
    ListItemIcon, ListItemText, Paper,
    Typography
} from "@mui/material";
import { createTheme } from '@mui/material/styles';
import React from 'react';
import { FormattedMessage } from "react-intl";
import { Cell, Pie, PieChart } from 'recharts';

const Velocimeter = ({ title, value, objective, symbol }) => {
    const theme = createTheme();
    const xoffset = 150;
    const yoffset = 120;
    const velStartAngle = (value < objective && objective !== 0) ? 185 - (180 * value / objective) : 9;
    const velEndAngle = (value < objective && objective !== 0) ? 175 - (180 * value / objective) : -1;
    const objectiveColor = value < objective ? theme.palette.error.main : theme.palette.primary.main;

    const COLORS = objective > value ?
        [theme.palette.primary.main, theme.palette.error.main]
        :
        [theme.palette.primary.main, theme.palette.success.main];

    const data = objective > value ?
        [{
            "name": "value",
            "value": parseFloat(value)
        },
        {
            "name": "value",
            "value": parseFloat(objective - value)
        }]
        :
        [{
            "name": "value",
            "value": parseFloat(objective)
        },
        {
            "name": "value",
            "value": parseFloat(value - objective)
        }];

    const innerData = [
        {
            "name": "Velocimeter",
            "value": 1
        }
    ];

    const ValueListItem = (color) => {
        return (
            <ListItem>
                <ListItemIcon sx={{ color: color }}>
                    <TimelineIcon fontSize='large' />
                </ListItemIcon>
                <ListItemText >
                    <Typography sx={{ color: color }} variant="h6">
                        {title}: {value}{symbol}
                    </Typography>
                </ListItemText>
            </ListItem>
        );
    }

    return (
        <Paper sx={{ height: "100%" }}>
            <Box sx={{ padding: 1 }}>
                <Typography sx={{ fontWeight: 600 }} variant="h5">
                    {title}
                </Typography>
            </Box>
            <Divider />
            <Container>
                <PieChart width={300} height={140} keepCount={true}>
                    <Pie
                        data={data}
                        dataKey="value"
                        nameKey="name"
                        startAngle={180}
                        endAngle={0}
                        cx={xoffset}
                        cy={yoffset}
                        innerRadius={60}
                        outerRadius={80}
                        fill="#82ca9d"
                        label
                    >
                        {data.map((entry, index) => (
                            <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
                        ))}
                    </Pie>
                    <Pie
                        data={innerData}
                        dataKey="value"
                        nameKey="name"
                        startAngle={velStartAngle}
                        endAngle={velEndAngle}
                        cx={xoffset}
                        cy={yoffset}
                        outerRadius={70}
                        fill={'#000000'}
                    >
                    </Pie>
                </PieChart>
            </Container>
            <Divider />
            <List>
                {value < objective ?
                    <ValueListItem color={theme.palette.primary.main} />
                    :
                    <React.Fragment />
                }
                <ListItem>
                    <ListItemIcon sx={{ color: objectiveColor }}>
                        <SportsScoreIcon fontSize='large' />
                    </ListItemIcon>
                    <ListItemText >
                        <Typography sx={{ color: objectiveColor }} variant="h6">
                            <FormattedMessage id="project.agents.velocimeter.Objective" />: {objective}{symbol}
                        </Typography>
                    </ListItemText>
                </ListItem>
                {value >= objective ?
                    <React.Fragment>
                        <ValueListItem color={theme.palette.success.main} />
                        <ListItem>
                            <ListItemIcon sx={{ color: theme.palette.success.main }}>
                                <CheckCircleIcon fontSize='large' />
                            </ListItemIcon>
                            <ListItemText >
                                <Typography sx={{ color: theme.palette.success.main }} variant="h6">
                                    <FormattedMessage id="project.agents.velocimeter.objectiveFulfilled" />
                                </Typography>
                            </ListItemText>
                        </ListItem>
                    </React.Fragment>
                    :
                    <React.Fragment />
                }
            </List>
        </Paper>
    );
}

export default Velocimeter;
