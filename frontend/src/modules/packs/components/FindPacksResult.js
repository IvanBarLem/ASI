import React from "react";
import { FormattedMessage } from "react-intl";
import { Box, Fab, Grid, Alert, Pagination } from "@mui/material";
import Pack from "./Pack";
import AddIcon from "@mui/icons-material/Add";
import { Link } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import * as selectors from '../selectors';
import * as actions from '../actions';
import { withRouter } from 'react-router-dom';

const FindPacksResult = () => {
  const dispatch = useDispatch();
  const packSearch = useSelector(selectors.getPackSearch);
  const [page, setPage] = React.useState(1);

  const handlePageChange = (event, value) => {
    dispatch(actions.findPacks(value-1));
    setPage(value);
    window.scrollTo(0, 0);
  }

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
            <Grid 
              item 
              xs={12}
              sx={{
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
              }}
            >
              <Pagination count={packSearch.result.totalPages} page={page} onChange={handlePageChange} />
            </Grid>
            
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

export default withRouter(FindPacksResult);
