import {
  Alert,
  Box,
  Fab,
  Grid,
  IconButton,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  TextField,
  Typography,
} from "@mui/material";
import React, { useEffect } from "react";
import { useDispatch } from "react-redux";
import * as actions from "../actions";
import AddIcon from "@mui/icons-material/Add";
import DeleteIcon from "@mui/icons-material/Delete";
import EditIcon from "@mui/icons-material/Edit";
import SaveIcon from "@mui/icons-material/Save";
import CancelIcon from "@mui/icons-material/Cancel";
import VisibilityIcon from "@mui/icons-material/Visibility";
import VisibilityOffIcon from "@mui/icons-material/VisibilityOff";
import Button from "@mui/material/Button";
import SendIcon from "@mui/icons-material/Send";

import { FormattedMessage } from "react-intl";
import { Errors } from "../../common";

const Activities = () => {
  const dispatch = useDispatch();
  const [activities, setActivities] = React.useState(null);
  const [backendErrors, setBackendErrors] = React.useState(null);

  const [editing, setEditing] = React.useState(null);
  const [name, setName] = React.useState(null);
  const [price, setPrice] = React.useState(null);

  const [adding, setAdding] = React.useState(false);
  const [nameAdd, setNameAdd] = React.useState(null);
  const [priceAdd, setPriceAdd] = React.useState(null);

  useEffect(() => {
    dispatch(
      actions.getActivitiesManager(
        (result) => {
          setActivities(result);
        },
        (errors) => setBackendErrors(errors)
      )
    );
  }, []);

  const getActivities = () => {
    dispatch(
      actions.getActivitiesManager(
        (result) => {
          setActivities(result);
        },
        (errors) => setBackendErrors(errors)
      )
    );
  };

  const handleEditing = (id, name, price) => {
    setName(name);
    setPrice(price);
    setEditing(id);
  };

  const handleDelete = (id) => {
    dispatch(
      actions.deleteActivity(
        id,
        () => getActivities(),
        (errors) => setBackendErrors(errors)
      )
    );
  };

  const handleVisibility = (id, name, price, hidden) => {
    handleUpdate(id, name, price, hidden);
  };

  const handleUpdate = (id, name, price, hidden) => {
    dispatch(
      actions.updateActivity(
        { id: id, name: name, price: price, hidden: hidden },
        () => {
          getActivities();
          setEditing(null);
        },
        (errors) => setBackendErrors(errors)
      )
    );
  };

  const handleCreate = () => {
    dispatch(
      actions.createActivity(
        { name: nameAdd, price: priceAdd },
        () => {
          handleAdding(false);
          getActivities();
        },
        (errors) => setBackendErrors(errors)
      )
    );
  };

  const handleAdding = (value) => {
    setAdding(value);
    if (!value) {
      setNameAdd(null);
      setPriceAdd(null);
    }
  };

  return (
    <React.Fragment>
      <Box sx={{ textAlign: "center" }}>
        <Typography variant="h6">
          <FormattedMessage id="project.products.activities" />
        </Typography>
      </Box>
      <Box sx={{ display: "flex", justifyContent: "center", margin: "20px" }}>
        <Grid item lg={8} md={10}>
          {adding && (
            <Grid
              container
              component={Paper}
              rowSpacing={2}
              sx={{
                padding: "30px",
                marginBottom: "30px",
              }}
            >
              <Grid
                item
                xs={12}
                md={4}
                sx={{
                  display: "flex",
                  justifyContent: "center",
                  alignItems: "center",
                }}
              >
                <TextField
                  fullWidth
                  sx={{ paddingInline: "10px" }}
                  label={<FormattedMessage id="project.global.fields.name" />}
                  defaultValue={nameAdd}
                  onChange={(e) => setNameAdd(e.target.value)}
                />
              </Grid>
              <Grid
                item
                xs={12}
                md={4}
                sx={{
                  display: "flex",
                  justifyContent: "center",
                  alignItems: "center",
                }}
              >
                <TextField
                  fullWidth
                  sx={{ paddingInline: "10px" }}
                  type="number"
                  label={<FormattedMessage id="project.global.fields.price" />}
                  defaultValue={priceAdd}
                  onChange={(e) => setPriceAdd(e.target.value)}
                />
              </Grid>
              <Grid item xs={12} md={4}>
                <Button
                  fullWidth
                  variant="contained"
                  startIcon={<CancelIcon />}
                  color="error"
                  onClick={() => handleAdding(false)}
                  sx={{ marginBottom: "5px" }}
                >
                  <FormattedMessage id="project.global.buttons.cancel" />
                </Button>
                <Button
                  fullWidth
                  variant="contained"
                  endIcon={<SendIcon />}
                  onClick={() => handleCreate(nameAdd, priceAdd)}
                >
                  <FormattedMessage id="project.global.buttons.save" />
                </Button>
              </Grid>
            </Grid>
          )}
        </Grid>
      </Box>
      {activities && activities.length !== 0 ? (
        <Box sx={{ display: "flex", justifyContent: "center" }}>
          <Grid item lg={8} md={10}>
            <Paper spacing={4}>
              <TableContainer>
                <Table>
                  <TableHead>
                    <TableRow>
                      <TableCell>
                        <FormattedMessage id="project.global.fields.name" />
                      </TableCell>
                      <TableCell>
                        <FormattedMessage id="project.global.fields.price" />
                      </TableCell>
                      <TableCell align="right">Acciones</TableCell>
                    </TableRow>
                  </TableHead>
                  <TableBody>
                    {activities.map((row) => (
                      <TableRow
                        key={row.id}
                        sx={
                          row.hidden
                            ? {
                              backgroundColor: "#bdbdbd",
                              "&:last-child td, &:last-child th": {
                                border: 0,
                              },
                            }
                            : {
                              "&:last-child td, &:last-child th": {
                                border: 0,
                              },
                            }
                        }
                      >
                        {editing === row.id ? (
                          <React.Fragment>
                            <TableCell>
                              <TextField
                                label={
                                  <FormattedMessage id="project.global.fields.name" />
                                }
                                defaultValue={name}
                                onChange={(e) => setName(e.target.value)}
                              />
                            </TableCell>
                            <TableCell>
                              <TextField
                                type="number"
                                label={
                                  <FormattedMessage id="project.global.fields.price" />
                                }
                                defaultValue={price}
                                onChange={(e) => setPrice(e.target.value)}
                              />
                            </TableCell>
                            <TableCell align="right">
                              <IconButton
                                onClick={() => {
                                  handleEditing(
                                    null,
                                    name,
                                    price,
                                    row.hidden
                                  );
                                  handleUpdate(
                                    row.id,
                                    name,
                                    price,
                                    row.hidden
                                  );
                                }}
                                color="primary"
                              >
                                <SaveIcon />
                              </IconButton>
                              <IconButton
                                color="error"
                                onClick={() =>
                                  handleEditing(
                                    null,
                                    row.name,
                                    row.price,
                                    row.hidden
                                  )
                                }
                              >
                                <CancelIcon />
                              </IconButton>
                            </TableCell>
                          </React.Fragment>
                        ) : (
                            <React.Fragment>
                              <TableCell>{row.name}</TableCell>
                              <TableCell>{row.price}</TableCell>
                              <TableCell align="right">
                                {row.hidden ? (
                                  <IconButton
                                    color="warning"
                                    onClick={() =>
                                      handleVisibility(
                                        row.id,
                                        row.name,
                                        row.price,
                                        !row.hidden
                                      )
                                    }
                                  >
                                    <VisibilityOffIcon />
                                  </IconButton>
                                ) : (
                                    <IconButton
                                      color="success"
                                      onClick={() =>
                                        handleVisibility(
                                          row.id,
                                          row.name,
                                          row.price,
                                          !row.hidden
                                        )
                                      }
                                    >
                                      <VisibilityIcon />
                                    </IconButton>
                                  )}
                                <IconButton
                                  color="primary"
                                  onClick={() =>
                                    handleEditing(
                                      row.id,
                                      row.name,
                                      row.price,
                                      row.hidden
                                    )
                                  }
                                >
                                  <EditIcon />
                                </IconButton>
                                <IconButton
                                  color="error"
                                  onClick={() => handleDelete(row.id)}
                                >
                                  <DeleteIcon />
                                </IconButton>
                              </TableCell>
                            </React.Fragment>
                          )}
                      </TableRow>
                    ))}
                  </TableBody>
                </Table>
              </TableContainer>
            </Paper>
            <Errors
              errors={backendErrors}
              onClose={() => setBackendErrors(null)}
            />
          </Grid>
        </Box>
      ) : (
          <Box
            sx={{
              display: "flex",
              justifyContent: "center",
              alignItems: "center",
            }}
          >
            <Alert severity="info">
              <FormattedMessage id="project.products.foundNoProducts" />
            </Alert>
          </Box>
        )}
      <Fab
        sx={{ position: "fixed", bottom: 50, right: 50 }}
        color={adding ? "secondary" : "primary"}
        onClick={() => handleAdding(!adding)}
      >
        {adding ? <CancelIcon /> : <AddIcon />}
      </Fab>
    </React.Fragment>
  );
};

export default Activities;
