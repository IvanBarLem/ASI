import {
    Button,
    Grid,
    Paper,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Typography,
} from "@mui/material";
import { createTheme } from "@mui/material/styles";
import React, { useState } from "react";
import { FormattedMessage } from "react-intl";
import KPI from "./KPI";

import AirplanemodeActiveIcon from "@mui/icons-material/AirplanemodeActive";
import CommuteIcon from "@mui/icons-material/Commute";
import HotelIcon from "@mui/icons-material/Hotel";
import LocalActivityIcon from "@mui/icons-material/LocalActivity";
import {
    FormControl,
    InputLabel,
    List,
    ListItem,
    MenuItem,
    Select,
    TextField,
} from "@material-ui/core";

const calculateTotalProductsKPI = (products) => {
    let total = 0;
    products.map((prod) => (total = total + prod.price * prod.total_sales));

    return total;
};

const products = [
    {
        id: 1,
        category: 4,
        name: "Bus",
        price: 14.99,
        location: "Amazonas",
        total_sales: 20000,
    },
    {
        id: 3,
        category: 4,
        name: "Coche",
        price: 290,
        location: "Tibet",
        total_sales: 5000,
    },
    {
        id: 4,
        category: 3,
        name: "Madrid - Paris",
        price: 800,
        location: "Madrid",
        total_sales: 1200,
    },
    {
        id: 5,
        category: 3,
        name: "Londres - Nueva York",
        price: 2000,
        location: "Londres",
        total_sales: 800,
    },
    {
        id: 7,
        category: 1,
        name: "Hotel",
        price: 80,
        location: "Madrid",
        total_sales: 40000,
    },
    {
        id: 8,
        category: 1,
        name: "Camping",
        price: 10,
        location: "Tibet",
        total_sales: 50000,
    },
    {
        id: 9,
        category: 2,
        name: "Golf",
        price: 50,
        location: "Madrid",
        total_sales: 400,
    },
];

const ProductList = () => {
    const [location, setLocation] = useState("");
    const [category, setCategory] = useState(0);

    const [productFilters, setProductFilters] = useState(products);

    const [productSelected, setProductSelected] = useState(null);

    const theme = createTheme();

    const onClickInCell = (product) => {
        productSelected && productSelected.id === product.id
            ? setProductSelected(null)
            : setProductSelected(product);
    };

    const handleCategory = (categorySelected) => {
        setCategory(categorySelected);
        setProductSelected(null);

        if (categorySelected !== 0 && location !== "") {
            const result = products.filter(
                (product) =>
                    product.category === categorySelected &&
                    product.location.includes(location.trim())
            );
            setProductFilters(result);
        } else if (location === "" && categorySelected !== 0) {
            const result = products.filter(
                (product) => product.category === categorySelected
            );
            setProductFilters(result);
        } else if (location !== "" && categorySelected === 0) {
            const result = products.filter((product) =>
                product.location.includes(location)
            );
            setProductFilters(result);
        } else {
            setProductFilters(products);
        }
    };

    const handleLocation = (locationSelected) => {
        setLocation(locationSelected.trim());
        setProductSelected(null);

        if (category !== 0 && locationSelected.trim() !== "") {
            const result = products.filter(
                (product) =>
                    product.location.includes(locationSelected.trim()) &&
                    product.category === category
            );
            setProductFilters(result);
        } else if (category === 0 && locationSelected !== "") {
            const result = products.filter((product) =>
                product.location.includes(locationSelected.trim())
            );
            setProductFilters(result);
        } else if (category !== 0 && locationSelected === "") {
            const result = products.filter(
                (product) => product.category === category
            );
            setProductFilters(result);
        } else {
            setProductFilters(products);
        }
    };

    return (
        <React.Fragment>
            <Grid container justifyContent="center">
                <Grid item xs={12} md={3}>
                    <List>
                        <Typography>
                            <FormattedMessage id="project.global.fields.filters" />:
            </Typography>
                        <ListItem>
                            <FormControl fullWidth>
                                <InputLabel id="category">
                                    <FormattedMessage id="project.global.fields.category" />
                                </InputLabel>
                                <Select
                                    labelId="category"
                                    id="category-select"
                                    value={category}
                                    onChange={(e) => handleCategory(e.target.value)}
                                >
                                    <MenuItem value={0}>Ninguna</MenuItem>
                                    <MenuItem value={1}>Alojamiento</MenuItem>
                                    <MenuItem value={2}>Actividad</MenuItem>
                                    <MenuItem value={3}>Viaje</MenuItem>
                                    <MenuItem value={4}>Transporte</MenuItem>
                                </Select>
                            </FormControl>
                        </ListItem>
                        <ListItem>
                            <FormControl fullWidth>
                                <TextField
                                    label={
                                        <FormattedMessage id="project.global.fields.location" />
                                    }
                                    variant="outlined"
                                    value={location}
                                    onChange={(e) => handleLocation(e.target.value)}
                                />
                            </FormControl>
                        </ListItem>
                    </List>
                </Grid>
                <Grid item xs={12} md={9} component={Paper}>
                    <TableContainer>
                        <Table>
                            <TableHead>
                                <TableRow>
                                    <TableCell></TableCell>
                                    <TableCell>
                                        <FormattedMessage id="project.global.fields.name" />
                                    </TableCell>
                                    <TableCell>
                                        <FormattedMessage id="project.global.fields.location" />
                                    </TableCell>
                                    <TableCell>
                                        <FormattedMessage id="project.global.fields.price" />
                                    </TableCell>
                                    <TableCell>
                                        <FormattedMessage id="project.global.fields.totalSales" />
                                    </TableCell>
                                    <TableCell />
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {productFilters.map((product) => (
                                    <TableRow
                                        key={product.id}
                                        selected={
                                            productSelected
                                                ? productSelected.id === product.id
                                                : false
                                        }
                                        sx={{ cursor: "pointer" }}
                                    >
                                        <TableCell onClick={() => onClickInCell(product)}>
                                            {product.category === 2 && <LocalActivityIcon />}
                                            {product.category === 4 && <CommuteIcon />}
                                            {product.category === 3 && <AirplanemodeActiveIcon />}
                                            {product.category === 1 && <HotelIcon />}
                                        </TableCell>
                                        <TableCell onClick={() => onClickInCell(product)}>
                                            <Typography>{product.name}</Typography>
                                        </TableCell>
                                        <TableCell onClick={() => onClickInCell(product)}>
                                            <Typography>{product.location}</Typography>
                                        </TableCell>
                                        <TableCell onClick={() => onClickInCell(product)}>
                                            <Typography>{product.price}</Typography>
                                        </TableCell>
                                        <TableCell onClick={() => onClickInCell(product)}>
                                            <Typography>{product.total_sales}</Typography>
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
            <Paper
                sx={{
                    marginTop: 5,
                    padding: 1,
                    backgroundColor: theme.palette.primary.light,
                }}
            >
                <Typography
                    sx={{ color: theme.palette.primary.contrastText }}
                    variant="h5"
                >
                    Estadísticas de los productos
        </Typography>
            </Paper>
            {productSelected ? (
                <Grid sx={{ marginTop: 1, paddingTop: 1 }} spacing={3} container>
                    <Grid item>
                        <KPI
                            name="KPI de productos"
                            productName={productSelected.name}
                            otherName={"Facturación de productos"}
                            respecting={
                                <FormattedMessage id="project.products.kpi.respectingTotalProducts" />
                            }
                            kpi={productSelected.price * productSelected.total_sales}
                            otherKpi={calculateTotalProductsKPI(products)}
                        />
                    </Grid>
                    <Grid item>
                        <KPI
                            name="KPI de facturación"
                            productName={productSelected.name}
                            otherName={"Facturación de la empresa"}
                            respecting={
                                <FormattedMessage id="project.products.kpi.respectingTotalBilling" />
                            }
                            kpi={productSelected.price * productSelected.total_sales}
                            otherKpi={20000000}
                        />
                    </Grid>
                </Grid>
            ) : (
                    <Typography margin={10} textAlign="center" variant="h6">
                        Selecciona un producto
                    </Typography>
                )}
        </React.Fragment>
    );
};

export default ProductList;
