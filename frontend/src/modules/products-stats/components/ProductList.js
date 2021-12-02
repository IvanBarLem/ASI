import {
  Alert,
  Button,
  Grid,
  Pagination,
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
  const [products, setProducts] = useState({ content: [], totalPages: 0 });
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
        0,
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
        0,
        locationSelected.trim(),
        category,
        (products) => {
          setProducts(products);
        }
      )
    );
  };

  const handleChangePage = (event, page) => {
    setPage(page - 1);
    dispatch(
      actions.getStatisticsProducts(
        page - 1,
        location,
        category,
        (products) => {
          setProducts(products);
        }
      )
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
                  <MenuItem value={"all"}>
                    <FormattedMessage id="project.statistics.products.all" />
                  </MenuItem>
                  <MenuItem value={"accommodation"}>
                    <FormattedMessage id="project.packs.CreatePack.acommodations" />
                  </MenuItem>
                  <MenuItem value={"activity"}>
                    <FormattedMessage id="project.packs.CreatePack.activities" />
                  </MenuItem>
                  <MenuItem value={"travel"}>
                    <FormattedMessage id="project.packs.CreatePack.travels" />
                  </MenuItem>
                  <MenuItem value={"transport"}>
                    <FormattedMessage id="project.packs.CreatePack.transports" />
                  </MenuItem>
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
          {products.content.length === 0 ? (
            <Alert severity="info">
              <FormattedMessage id="project.statistics.products.noProducts" />
            </Alert>
          ) : (
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
                      <FormattedMessage id="project.global.fields.currentBilling" />
                    </TableCell>
                    <TableCell>
                      <FormattedMessage id="project.global.fields.totalSales" />
                    </TableCell>
                  </TableRow>
                </TableHead>

                <TableBody>
                  {products.content.map((product) => (
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
                        <Typography>
                          {product.currentBilling}
                          {" €"}
                        </Typography>
                      </TableCell>
                      <TableCell
                        align="right"
                        onClick={() => onClickInCell(product)}
                      >
                        <Typography>{product.currentSales}</Typography>
                      </TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </TableContainer>
          )}
          <Grid xs={12} display="flex" justifyContent="center">
            {products && products.content.length > 0 && (
              <Pagination
                count={products.totalPages}
                page={products.pageNumber + 1}
                onChange={handleChangePage}
              />
            )}
          </Grid>
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
          <FormattedMessage id="project.statistics.products" />
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
              unid={"unid."}
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
              unid={"€"}
            />
          </Grid>
        </Grid>
      ) : (
        <Typography margin={10} textAlign="center" variant="h6">
          <FormattedMessage id="project.statistics.products.select" />
        </Typography>
      )}
    </React.Fragment>
  );
};

export default ProductList;
