import {
  Button,
  Grid,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TablePagination,
  TableRow,
  Typography,
} from "@mui/material";
import { createTheme } from "@mui/material/styles";
import React, { useEffect, useState } from "react";
import { FormattedMessage } from "react-intl";
import KPI from "./KPI";
import * as actions from "../actions";

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
import { useDispatch } from "react-redux";

const ProductList = () => {
  const dispatch = useDispatch();
  const [products, setProducts] = useState(null);
  const [location, setLocation] = useState("");
  const [category, setCategory] = useState("all");
  const [page, setPage] = useState(0);

  const [productSelected, setProductSelected] = useState(null);

  const [companyStatistics, setCompanyStatistics] = useState(null);

  const theme = createTheme();

  const onClickInCell = (product) => {
    productSelected && productSelected.productId === product.productId
      ? setProductSelected(null)
      : setProductSelected(product);
  };

  useEffect(() => {
    dispatch(
      actions.getStatisticsProducts(page, location, category, (products) => {
        setProducts(products);
      })
    );
    dispatch(
      actions.getStatisticsCompany((statistics) => {
        setCompanyStatistics(statistics);
      })
    );
  }, []);

  const handleCategory = (categorySelected) => {
    setCategory(categorySelected);
    dispatch(
      actions.getStatisticsProducts(
        page,
        location,
        categorySelected,
        (products) => {
          setProducts(products);
        }
      )
    );
  };

  const handleLocation = (locationSelected) => {
    setLocation(locationSelected.trim());
    dispatch(
      actions.getStatisticsProducts(
        page,
        locationSelected.trim(),
        category,
        (products) => {
          setProducts(products);
        }
      )
    );
  };

  const handleChangePage = (event, page) => {
    setPage(page);
    dispatch(
      actions.getStatisticsProducts(page, location, category, (products) => {
        setProducts(products);
      })
    );
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
                  <MenuItem value={"all"}>Ninguna</MenuItem>
                  <MenuItem value={"accommodation"}>Alojamiento</MenuItem>
                  <MenuItem value={"activity"}>Actividad</MenuItem>
                  <MenuItem value={"travel"}>Viaje</MenuItem>
                  <MenuItem value={"transport"}>Transporte</MenuItem>
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
                {products &&
                  products.content.map((product) => (
                    <TableRow
                      key={product.productId + product.name}
                      selected={
                        productSelected
                          ? productSelected.productId === product.productId
                          : false
                      }
                      sx={{ cursor: "pointer" }}
                    >
                      <TableCell onClick={() => onClickInCell(product)}>
                        {product.category === "activity" && (
                          <LocalActivityIcon />
                        )}
                        {product.category === "transport" && <CommuteIcon />}
                        {product.category === "travel" && (
                          <AirplanemodeActiveIcon />
                        )}
                        {product.category === "accommodation" && <HotelIcon />}
                      </TableCell>
                      <TableCell onClick={() => onClickInCell(product)}>
                        <Typography>{product.name}</Typography>
                      </TableCell>
                      <TableCell onClick={() => onClickInCell(product)}>
                        <Typography>{product.location}</Typography>
                      </TableCell>
                      <TableCell
                        onClick={() => onClickInCell(product)}
                        align="right"
                      >
                        <Typography>
                          {product.price}
                          {" €"}
                        </Typography>
                      </TableCell>
                      <TableCell
                        onClick={() => onClickInCell(product)}
                        align="right"
                      >
                        <Typography>{product.currentSales}</Typography>
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
          {products && products.totalPages && (
            <TablePagination
              component="div"
              rowsPerPageOptions={[10]}
              count={products.totalPages}
              rowsPerPage={10}
              page={page}
              onPageChange={handleChangePage}
            />
          )}
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
              otherName={"Número de ventas de productos"}
              respecting={
                <FormattedMessage id="project.products.kpi.respectingTotalProducts" />
              }
              kpi={productSelected.currentSales}
              otherKpi={
                companyStatistics.currentSales - productSelected.currentSales
              }
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
              kpi={productSelected.currentBilling}
              otherKpi={
                companyStatistics.currentBilling -
                productSelected.currentBilling
              }
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
