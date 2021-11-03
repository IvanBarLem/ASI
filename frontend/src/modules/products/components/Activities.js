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
} from "@mui/material";
import React, { useEffect } from "react";
import { useDispatch } from "react-redux";
import { Link } from "react-router-dom";
import * as actions from "../actions";
import AddIcon from "@mui/icons-material/Add";
import DeleteIcon from "@mui/icons-material/Delete";
import EditIcon from "@mui/icons-material/Edit";
import { FormattedMessage } from "react-intl";
import { Errors } from "../../common";

const Activities = () => {
  const dispatch = useDispatch();
  const [activities, setActivities] = React.useState(null);
  const [backendErrors, setBackendErrors] = React.useState(null);

  useEffect(() => {
    dispatch(
      actions.getActivities(
        (result) => {
          setActivities(result);
        },
        (errors) => setBackendErrors(errors)
      )
    );
  }, []);

  const getActivities = () => {
    dispatch(
      actions.getActivities(
        (result) => {
          setActivities(result);
        },
        (errors) => setBackendErrors(errors)
      )
    );
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

  return (
    <React.Fragment>
      {activities && activities.length !== 0 ? (
        <React.Fragment>
          <Box>
            <Grid>
              <Paper xs={12} spacing={4}>
                <TableContainer>
                  <Table>
                    <TableHead>
                      <TableRow>
                        <TableCell>ID</TableCell>
                        <TableCell align="right">Nombre</TableCell>
                        <TableCell align="right">Precio</TableCell>
                        <TableCell align="right">Acciones</TableCell>
                      </TableRow>
                    </TableHead>
                    <TableBody>
                      {activities.map((row) => (
                        <TableRow
                          key={row.name}
                          sx={{
                            "&:last-child td, &:last-child th": { border: 0 },
                          }}
                        >
                          <TableCell>{row.id}</TableCell>
                          <TableCell align="right">{row.name}</TableCell>
                          <TableCell align="right">{row.price}</TableCell>
                          <TableCell align="right">
                            <IconButton color="primary">
                              <EditIcon />
                            </IconButton>
                            <IconButton
                              color="error"
                              onClick={() => handleDelete(row.id)}
                            >
                              <DeleteIcon />
                            </IconButton>
                          </TableCell>
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
        </React.Fragment>
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
        color="primary"
        aria-label="add"
        component={Link}
        to="/create-pack"
      >
        <AddIcon />
      </Fab>
    </React.Fragment>
  );
};

export default Activities;
