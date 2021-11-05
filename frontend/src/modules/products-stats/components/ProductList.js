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
    category: "Transporte",
    name: "Amazonas Salvaje",
    price: 14.99,
    location: "Amazonas",
    total_sales: 2000,
  },
  {
    id: 3,
    category: "Transporte",
    name: "Tibet Espiritual",
    price: 290,
    location: "Tibet",
    total_sales: 800,
  },
  {
    id: 4,
    category: "Viaje",
    name: "Tibet Espiritual",
    price: 290,
    location: "Tibet",
    total_sales: 800,
  },
  {
    id: 5,
    category: "Viaje",
    name: "Tibet Espiritual",
    price: 290,
    location: "Tibet",
    total_sales: 800,
  },
  {
    id: 7,
    category: "Alojamiento",
    name: "Tibet Espiritual",
    price: 290,
    location: "Tibet",
    total_sales: 800,
  },
  {
    id: 8,
    category: "Alojamiento",
    name: "Tibet Espiritual",
    price: 290,
    location: "Tibet",
    total_sales: 800,
  },
  {
    id: 9,
    category: "Actividad",
    name: "Tibet Espiritual",
    price: 290,
    location: "Tibet",
    total_sales: 800,
  },
];

const ProductList = () => {
  const [location, setLocation] = useState("");
  const [category, setCategory] = useState(0);

  const [productSelected, setProductSelected] = useState(null);

  const theme = createTheme();

  const onClickInCell = (product) => {
    productSelected && productSelected.id === product.id
      ? setProductSelected(null)
      : setProductSelected(product);
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
                  onChange={(e) => setCategory(e.target.value)}
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
                  onChange={(e) => setLocation(e.target.value)}
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
                {products.map((product) => (
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
                      {product.category === "Actividad" && (
                        <LocalActivityIcon />
                      )}
                      {product.category === "Transporte" && <CommuteIcon />}
                      {product.category === "Viaje" && (
                        <AirplanemodeActiveIcon />
                      )}
                      {product.category === "Alojamiento" && <HotelIcon />}
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
          Estadísticas de la empresa
        </Typography>
      </Paper>
      <Grid sx={{ margin: 1, paddingTop: 1 }} spacing={1} container>
        <Grid item>
          <KPI
            name="KPI de productos"
            respecting={
              <FormattedMessage id="project.products.kpi.respectingTotalProducts" />
            }
            kpi={
              productSelected
                ? productSelected.price * productSelected.total_sales
                : 0
            }
            otherKpi={calculateTotalProductsKPI(products)}
          />
        </Grid>
        <Grid item>
          <KPI
            name="KPI de facturación"
            respecting={
              <FormattedMessage id="project.products.kpi.respectingTotalBilling" />
            }
            kpi={
              productSelected
                ? productSelected.price * productSelected.total_sales
                : 0
            }
            otherKpi={2000000}
          />
        </Grid>
      </Grid>
    </React.Fragment>
  );
};

export default ProductList;
