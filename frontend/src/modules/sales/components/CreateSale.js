import { Box } from "@mui/system";
import {
  Typography,
  Grid,
  Autocomplete,
  TextField,
  CircularProgress,
  InputAdornment,
  Button,
} from "@mui/material";

import React, { useEffect, useState } from "react";
import { FormattedMessage } from "react-intl";
import { useDispatch } from "react-redux";
import { useHistory } from "react-router";
import { SelectComplement } from "./SelectComponent";
import * as actionsProducts from "../../products/actions";
import SendIcon from "@mui/icons-material/Send";

import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import TableRows from "./TableRows";

import { Errors } from "../../common/index";

const clients = [
  { id: 1, name: "Pepito" },
  { id: 2, name: "Juanito" },
  { id: 3, name: "Rodolfo" },
  { id: 4, name: "Alba" },
  { id: 5, name: "Maria" },
];

const CreateSale = () => {
  const dispatch = useDispatch();
  const history = useHistory();

  const howManyPeople = () => {
    let stringPersons = history.location.state
      ? history.location.state.item.persons
      : "";
    if (stringPersons) {
      if (stringPersons.localeCompare("Couples") === 0) {
        return 2;
      }
      if (stringPersons.localeCompare("Friends") === 0) {
        return 5;
      }
      if (stringPersons.localeCompare("Families") === 0) {
        return 4;
      }
      if (stringPersons.localeCompare("Individual") === 0) {
        return 1;
      }
    }
    return 1;
  };

  const [persons, setPersons] = useState(howManyPeople());
  const [client, setClient] = useState(null);
  const [price, setPrice] = useState(
    history.location.state ? history.location.state.item.price : 0
  );
  const [open, setOpen] = useState(false);
  const loading = false;
  const [activities, setActivities] = useState([]);
  const [accommodations, setAccommodations] = useState([]);
  const [transports, setTransports] = useState([]);
  const [travels, setTravels] = useState([]);

  const [transportsSelected, setTransportsSelected] = useState(
    history.location.state ? history.location.state.item.transports : []
  );
  const [accommodationsSelected, setAccommodationsSelected] = useState(
    history.location.state ? history.location.state.item.accommodations : []
  );
  const [activitiesSelected, setActivitiesSelected] = useState(
    history.location.state ? history.location.state.item.activities : []
  );
  const [travelsSelected, setTravelsSelected] = useState(
    history.location.state ? history.location.state.item.travels : []
  );

  const [backendErrors, setBackendErrors] = useState(null);
  const [backendErrorsActivities, setBackendErrorsActivities] = useState(null);
  const [backendErrorsAccommodations, setBackendErrorsAccommodations] =
    useState(null);
  const [backendErrorsTransports, setBackendErrorsTransports] = useState(null);
  const [backendErrorsTravels, setBackendErrorsTravels] = useState(null);

  const [units, setUnits] = useState(
    history.location.state
      ? []
          .concat(
            history.location.state.item.transports.map((transport) => ({
              item: transport,
              value: persons,
            }))
          )
          .concat(
            history.location.state.item.accommodations.map((accommodation) => ({
              item: accommodation,
              value: persons,
            }))
          )
          .concat(
            history.location.state.item.activities.map((activity) => ({
              item: activity,
              value: persons,
            }))
          )
          .concat(
            history.location.state.item.travels.map((travel) => ({
              item: travel,
              value: persons,
            }))
          )
      : []
  );

  useEffect(() => {
    dispatch(
      actionsProducts.getActivities(
        (activities) => setActivities(activities),
        (errors) => setBackendErrorsActivities(errors)
      )
    );
    dispatch(
      actionsProducts.getAccommodations(
        (accommodations) => setAccommodations(accommodations),
        (errors) => setBackendErrorsAccommodations(errors)
      )
    );
    dispatch(
      actionsProducts.getTransports(
        (transports) => setTransports(transports),
        (errors) => setBackendErrorsTransports(errors)
      )
    );
    dispatch(
      actionsProducts.getTravels(
        (travels) => setTravels(travels),
        (errors) => setBackendErrorsTravels(errors)
      )
    );
  }, []);

  const handleChangeClient = (value) => {
    setClient(value);
  };

  const handleChangePrice = (value) => {
    if (value < 0) {
      setPrice(0);
    } else {
      setPrice(value);
    }
  };

  const handleChangeTransports = (value) => () => {
    const currentIndex = transportsSelected
      .map((transport) => transport.name)
      .indexOf(value.name);
    const newChecked = [...transportsSelected];

    if (currentIndex === -1) {
      newChecked.push(value);
      handleUnits(value, persons);
    } else {
      newChecked.splice(currentIndex, 1);
      handleUnits(value, 0);
    }
    setTransportsSelected(newChecked);
  };

  const handleChangeActivities = (value) => () => {
    const currentIndex = activitiesSelected
      .map((activity) => activity.name)
      .indexOf(value.name);
    const newChecked = [...activitiesSelected];

    if (currentIndex === -1) {
      newChecked.push(value);
      handleUnits(value, persons);
    } else {
      newChecked.splice(currentIndex, 1);
      handleUnits(value, 0);
    }
    setActivitiesSelected(newChecked);
  };

  const handleChangeAccommodations = (value) => () => {
    const currentIndex = accommodationsSelected
      .map((accommodation) => accommodation.name)
      .indexOf(value.name);
    const newChecked = [...accommodationsSelected];

    if (currentIndex === -1) {
      newChecked.push(value);
      handleUnits(value, persons);
    } else {
      newChecked.splice(currentIndex, 1);
      handleUnits(value, 0);
    }
    setAccommodationsSelected(newChecked);
  };

  const handleChangeTravels = (value) => () => {
    const currentIndex = travelsSelected
      .map((travel) => travel.name)
      .indexOf(value.name);
    const newChecked = [...travelsSelected];

    if (currentIndex === -1) {
      newChecked.push(value);
      handleUnits(value, persons);
    } else {
      newChecked.splice(currentIndex, 1);
      handleUnits(value, 0);
    }
    setTravelsSelected(newChecked);
  };

  const handleUnits = (item, value) => {
    setUnits((prevState) => {
      const idx = prevState.map((unit) => unit.item.name).indexOf(item.name);
      if (idx === -1) {
        const newState = prevState;
        newState.push({
          item: item,
          value: persons,
        });
        return newState;
      } else if (value > 0) {
        const newState = [...prevState];
        const newItem = { ...newState[idx], value: parseInt(value, 10) };
        newItem.value = parseInt(value, 10);
        newState[idx] = newItem;
        return newState;
      } else {
        const newState = prevState.filter(
          (unit) => unit.item.name !== item.name
        );
        setTransportsSelected(
          transportsSelected.filter((transport) => transport.name !== item.name)
        );
        setTravelsSelected(
          travelsSelected.filter((travel) => travel.name !== item.name)
        );
        setAccommodationsSelected(
          accommodationsSelected.filter(
            (accommodation) => accommodation.name !== item.name
          )
        );
        setActivitiesSelected(
          activitiesSelected.filter((activity) => activity.name !== item.name)
        );
        return newState;
      }
    });
  };

  return (
    <Box>
      <Grid container>
        <Grid
          item
          xs={12}
          display="flex"
          alignItems="center"
          justifyContent="center"
        >
          <Typography variant="h6" align="center" sx={{ marginBottom: 4 }}>
            Datos principales
          </Typography>
        </Grid>
        <Grid
          item
          xs={12}
          display="flex"
          alignItems="center"
          justifyContent="center"
        >
          <Autocomplete
            sx={{ minWidth: "220px", marginBottom: 4 }}
            open={open}
            onOpen={() => {
              setOpen(true);
            }}
            onClose={() => {
              setOpen(false);
            }}
            isOptionEqualToValue={(option, value) => option.name === value.name}
            getOptionLabel={(option) => option.name}
            autoHighlight
            options={clients}
            loading={loading}
            value={client}
            onChange={(event, newValue) => handleChangeClient(newValue)}
            renderInput={(params) => (
              <TextField
                {...params}
                label="Cliente"
                InputProps={{
                  ...params.InputProps,
                  endAdornment: (
                    <React.Fragment>
                      {loading ? (
                        <CircularProgress color="inherit" size={20} />
                      ) : null}
                      {params.InputProps.endAdornment}
                    </React.Fragment>
                  ),
                }}
              />
            )}
          />
        </Grid>
        <Grid item xs={12}>
          <Typography variant="h6" align="center" gutterBottom={true}>
            Productos
          </Typography>
        </Grid>
        <Grid
          item
          xs={12}
          md={4}
          lg={3}
          display="flex"
          alignItems="center"
          justifyContent="center"
          paddingX={1}
        >
          <SelectComplement
            options={travels}
            selectedOptions={travelsSelected}
            handleChange={handleChangeTravels}
            label={<FormattedMessage id="project.packs.CreatePack.travels" />}
          />
          <Errors
            error={backendErrorsTravels}
            onClose={() => setBackendErrorsTravels(null)}
          />
        </Grid>
        <Grid
          item
          xs={12}
          md={4}
          lg={3}
          display="flex"
          alignItems="center"
          justifyContent="center"
          paddingX={1}
        >
          <SelectComplement
            options={transports}
            selectedOptions={transportsSelected}
            handleChange={handleChangeTransports}
            label={
              <FormattedMessage id="project.packs.CreatePack.transports" />
            }
          />
          <Errors
            error={backendErrorsTransports}
            onClose={() => setBackendErrorsTransports(null)}
          />
        </Grid>
        <Grid
          item
          xs={12}
          md={4}
          lg={3}
          display="flex"
          alignItems="center"
          justifyContent="center"
          paddingX={1}
        >
          <SelectComplement
            options={accommodations}
            selectedOptions={accommodationsSelected}
            handleChange={handleChangeAccommodations}
            label={
              <FormattedMessage id="project.packs.CreatePack.acommodations" />
            }
          />
          <Errors
            error={backendErrorsAccommodations}
            onClose={() => setBackendErrorsAccommodations(null)}
          />
        </Grid>
        <Grid
          item
          xs={12}
          md={4}
          lg={3}
          display="flex"
          alignItems="center"
          justifyContent="center"
          paddingX={1}
        >
          <SelectComplement
            options={activities}
            selectedOptions={activitiesSelected}
            handleChange={handleChangeActivities}
            label={
              <FormattedMessage id="project.packs.CreatePack.activities" />
            }
          />
          <Errors
            error={backendErrorsActivities}
            onClose={() => setBackendErrorsActivities(null)}
          />
        </Grid>
        <Grid item xs={12}>
          <Typography variant="h6" align="center" gutterBottom={true}>
            Precios
          </Typography>
        </Grid>
        <Grid
          item
          xs={12}
          marginTop={3}
          display="flex"
          alignItems="center"
          justifyContent="center"
        >
          <TableContainer component={Paper} sx={{ maxWidth: 500 }}>
            <Table size="small">
              <TableBody>
                {units &&
                  units.map((unit) => (
                    <TableRows
                      key={JSON.stringify(unit)}
                      value={unit.value}
                      item={unit.item}
                      handleProducts={handleUnits}
                    />
                  ))}
                <TableRow>
                  <TableCell colSpan={2}></TableCell>
                  <TableCell>Precio productos</TableCell>
                  <TableCell align="right">
                    {units
                      .map((unit) => unit.item.price * unit.value)
                      .reduce((acc, v) => acc + v, 0)
                      .toFixed(2) + " €"}
                  </TableCell>
                </TableRow>
                <TableRow>
                  <TableCell colSpan={2}></TableCell>
                  <TableCell>Precio final</TableCell>
                  <TableCell align="right">
                    <TextField
                      label={null}
                      variant="standard"
                      size="small"
                      type="number"
                      value={price}
                      onChange={(e) => handleChangePrice(e.target.value)}
                      InputProps={{
                        endAdornment: (
                          <InputAdornment position="end">€</InputAdornment>
                        ),
                      }}
                    />
                  </TableCell>
                </TableRow>
              </TableBody>
            </Table>
          </TableContainer>
        </Grid>
      </Grid>
      <Errors error={backendErrors} onClose={() => setBackendErrors(null)} />
      <Grid
        item
        xs={12}
        sx={{ marginTop: 5 }}
        display="flex"
        alignItems="center"
        justifyContent="center"
      >
        <Button variant="contained" endIcon={<SendIcon />}>
          Guardar venta
        </Button>
      </Grid>
    </Box>
  );
};

export default CreateSale;
