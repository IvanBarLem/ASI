import React from "react";
import { FormattedMessage } from "react-intl";
import { Box, Fab, Grid, Alert } from "@mui/material";
import Pack from "./Pack";
import AddIcon from "@mui/icons-material/Add";
import { Link } from "react-router-dom";
import { useSelector } from "react-redux";
import * as selectors from '../selectors';

const FindPacksResult = () => {
  const packSearch = useSelector(selectors.getPackSearch);

  return (
    <React.Fragment>
      {(packSearch === null || packSearch.result.content.length === 0) ?
          <Alert severity="info">
            <FormattedMessage id="project.packs.foundNoPacks" />
          </Alert>
        :
        <Box>
          <Grid container spacing={4}>
            {packSearch.result.content.map((row) => (
              <Pack key={row.id} item={row} />
            ))}
          </Grid>
        </Box>
      }
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

export default FindPacksResult;
