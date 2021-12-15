import AirplanemodeActiveIcon from "@mui/icons-material/AirplanemodeActive";
import CommuteIcon from "@mui/icons-material/Commute";
import HotelIcon from "@mui/icons-material/Hotel";
import SurfingIcon from "@mui/icons-material/Surfing";
import ExtensionIcon from "@mui/icons-material/Extension";
import MoreVertIcon from '@mui/icons-material/MoreVert';
import {
    Alert,
    Badge,
    Box,
    Button, Chip,
    List, ListItem, Paper, Popover,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Typography,
    Grid,
    Pagination,
    TextField
} from "@mui/material";
import React, { useEffect } from "react";
import { FormattedMessage } from "react-intl";
import StateIcon from './StateIcon';
import { useDispatch, useSelector } from "react-redux";
import * as actions from "../actions";
import * as selectors from "../selectors";
import users from "../../users";

const FindSales = () => {
    const dispatch = useDispatch();
    const [clientTextField, setClientTextField] = React.useState("");
    const [agentTextField, setAgentTextField] = React.useState("");
    const [client, setClient] = React.useState("");
    const [agent, setAgent] = React.useState("")
    const [open, setOpen] = React.useState("");
    const [page, setPage] = React.useState(1);
    const [anchorEl, setAnchorEl] = React.useState(null);
    const saleSearch = useSelector(selectors.getSaleSearch);
    const isGerente = useSelector(users.selectors.isGerente);
    const isCliente = useSelector(users.selectors.isCliente);

    useEffect(() => {
        dispatch(actions.findSales(0, client, agent))
        return () => {
            dispatch(actions.clearSaleSearch())
        }
    }, [])

    const searchButtonClick = () => {
        setClient(clientTextField);
        setAgent(agentTextField);
        dispatch(actions.findSales(page - 1, clientTextField, agentTextField));
    }

    const handlePopoverOpen = (event, id) => {
        setOpen(id);
        setAnchorEl(event.currentTarget);
    };

    const handlePopoverClose = () => {
        setOpen("")
        setAnchorEl(null);
    };

    const handlePageChange = (event, value) => {
        dispatch(actions.findSales(value - 1, client, agent));
        setPage(value);
    };

    const blockSale = (saleId) => {
        dispatch(actions.blockSale(page, client, agent, saleId));
    }

    const paySale = (saleId) => {
        dispatch(actions.paySale(page, client, agent, saleId));
    }

    const saveBill = (sale) => {
        var billText = "===================== FACTURA =======================\n\n";
        billText = billText + "Cliente: " + sale.client.firstName + " " + sale.client.lastName + "\n";
        billText = billText + "Agente: " + sale.agent.firstName + " " + sale.agent.lastName + "\n";
        billText = billText + "\n";
        billText = billText + "***** PRODUCTOS INCLUIDOS *****\n";
        sale.products.map((product) => {
            const espType = product.type === "TRAVEL" ?
                "Viaje"
                :
                product.type === "TRANSPORT" ?
                    "Transporte"
                    :
                    product.type === "ACCOMMODATION" ?
                        "Alojamiento"
                        :
                        "Actividad";
            return billText = billText + espType + " - " + product.name + " x" + product.number + "\n";
        })
        billText = billText + "\n";
        billText = billText + "Precio total: " + sale.price + "€";
        var element = document.createElement('a');
        element.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(billText));
        element.setAttribute('download', "factura.txt");

        element.style.display = 'none';
        document.body.appendChild(element);

        element.click();

        document.body.removeChild(element);
    }

    const PoppoverItem = ({ item }) => {
        return (
            <ListItem key={item.id}>
                <Chip
                    icon={item.type === "TRAVEL" ?
                        <AirplanemodeActiveIcon />
                        :
                        item.type === "TRANSPORT" ?
                            <CommuteIcon />
                            :
                            item.type === "ACCOMMODATION" ?
                                <HotelIcon />
                                :
                                <SurfingIcon />
                    }
                    label={item.name}
                    variant="outlined"
                />
                <Typography sx={{ marginLeft: 1 }}>
                    -
                </Typography>
                <Chip
                    label={
                        <Typography variant="h6">
                            x{item.number}
                        </Typography>
                    }
                    color="primary"
                    sx={{ marginLeft: 1 }}
                />
            </ListItem>
        )
    }
    const MyPopover = ({ id, items }) => {
        const tooLong = items.length > 12
        const orderedProducts = items.sort(
            (product1, product2) => {
                return product1.id - product2.id;
            }
        )
        return (
            <Popover
                id="mouse-over-popover"
                sx={{
                    pointerEvents: 'none',
                }}
                open={open === id}
                anchorEl={anchorEl}
                anchorOrigin={{
                    vertical: 'bottom',
                    horizontal: 'center',
                }}
                onClose={handlePopoverClose}
            >
                <List dense>
                    {orderedProducts.slice(0, 12).map((item) => (
                        <PoppoverItem key={item.id} item={item} />
                    ))}
                    {tooLong &&
                        <Grid container display="flex" justifyContent="center">
                            <MoreVertIcon fontSize="large" />
                        </Grid>
                    }

                </List>
            </Popover>
        );
    }

    return (
        <React.Fragment>
            {(saleSearch === null || saleSearch.result.content.length === 0) ?
                <React.Fragment>
                    <Alert severity="info">
                        <FormattedMessage id="project.sales.foundNoSales" />
                    </Alert>
                </React.Fragment>
                :
                <React.Fragment>
                    {isGerente &&
                        <Paper sx={{ paddingTop: 1, paddingLeft: 1, paddingRight: 1 }}>
                            <Grid container spacing={1} sx={{ justifyContent: "space-between", alignItems: "center" }}>
                                <Grid item >
                                    <TextField
                                        label={<FormattedMessage id="project.global.fields.client" />}
                                        value={clientTextField}
                                        onChange={event => setClientTextField(event.target.value)}
                                        sx={{ marginBottom: 1, marginRight: 2 }}
                                    />
                                    <TextField
                                        label={<FormattedMessage id="project.global.fields.agent" />}
                                        value={agentTextField}
                                        onChange={event => setAgentTextField(event.target.value)}
                                        sx={{ marginBottom: 1 }}
                                    />
                                </Grid>
                                <Grid item>
                                    <Box>
                                        <Button
                                            variant="outlined"
                                            size="large"
                                            sx={{ marginBottom: 1 }}
                                            onClick={() => searchButtonClick()}
                                        >
                                            <FormattedMessage id="project.global.buttons.search" />
                                        </Button>
                                    </Box>
                                </Grid>
                            </Grid>
                        </Paper>
                    }
                    <Paper sx={{ marginTop: 2 }}>
                        <React.Fragment>
                            <TableContainer>
                                <Table>
                                    <TableHead>
                                        <TableRow>
                                            <TableCell>
                                                <FormattedMessage id="project.global.fields.id" />
                                            </TableCell>
                                            <TableCell align="center">
                                                <FormattedMessage id="project.global.fields.client" />
                                            </TableCell>
                                            <TableCell align="center">
                                                <FormattedMessage id="project.global.fields.agent" />
                                            </TableCell>
                                            <TableCell align="center">
                                                <FormattedMessage id="project.global.fields.products" />
                                            </TableCell>
                                            <TableCell align="center">
                                                <FormattedMessage id="project.global.fields.price" />
                                            </TableCell>
                                            <TableCell align="center">
                                                <FormattedMessage id="project.global.fields.state" />
                                            </TableCell>
                                            <TableCell align="center">
                                                <FormattedMessage id="project.global.fields.action" />
                                            </TableCell>
                                        </TableRow>
                                    </TableHead>
                                    <TableBody>
                                        {saleSearch.result.content.map((sale) =>
                                            <TableRow key={sale.id}>
                                                <TableCell>{sale.id}</TableCell>
                                                <TableCell align="center">{sale.client.firstName + " " + sale.client.lastName}</TableCell>
                                                <TableCell align="center">{sale.agent.firstName + " " + sale.agent.lastName}</TableCell>
                                                <TableCell align="center">
                                                    <Box
                                                        onMouseEnter={event => handlePopoverOpen(event, sale.id)}
                                                        onMouseLeave={handlePopoverClose}
                                                    >
                                                        <Badge badgeContent={sale.products.length} color="primary">
                                                            <ExtensionIcon />
                                                        </Badge>
                                                    </Box>
                                                    <MyPopover id={sale.id} items={sale.products} />
                                                </TableCell>
                                                <TableCell align="center">
                                                    <Typography variant="h6">
                                                        {sale.price}€
                                                    </Typography>
                                                </TableCell>
                                                <TableCell align="center">
                                                    <StateIcon state={sale.state} />
                                                </TableCell>
                                                <TableCell align="center">
                                                    {sale.state === "NORMAL" &&
                                                        <Button onClick={() => blockSale(sale.id)}>
                                                            <FormattedMessage id="project.sales.salesTable.blockSale" />
                                                        </Button>
                                                    }
                                                    {sale.state === "FREEZE" &&
                                                        <Button
                                                            onClick={() => paySale(sale.id)}
                                                            disabled={!isGerente && !isCliente}
                                                        >
                                                            <FormattedMessage id="project.sales.salesTable.paySale" />
                                                        </Button>
                                                    }
                                                    {sale.state === "PAID" &&
                                                        <Button onClick={() => saveBill(sale)}>
                                                            <FormattedMessage id="project.sales.salesTable.watchBill" />
                                                        </Button>
                                                    }
                                                </TableCell>
                                            </TableRow>
                                        )}
                                    </TableBody>
                                </Table>
                            </TableContainer>
                            <Grid container display="flex" justifyContent="center">
                                <Pagination
                                    count={saleSearch.result.totalPages}
                                    page={page}
                                    onChange={handlePageChange}
                                />
                            </Grid>
                        </React.Fragment>
                    </Paper>
                </React.Fragment>
            }
        </React.Fragment>
    );
}

export default FindSales;