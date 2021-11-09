import AddIcon from "@mui/icons-material/Add";
import { Alert, Box, Fab, Grid, Pagination } from "@mui/material";
import React from "react";
import { FormattedMessage } from "react-intl";
import { useDispatch, useSelector } from "react-redux";
import { Link, withRouter } from "react-router-dom";
import users from '../../users';
import * as actions from "../actions";
import * as selectors from "../selectors";
import Pack from "./Pack";

const FindPacksResult = () => {
    const dispatch = useDispatch();
    const isGerente = useSelector(users.selectors.isGerente);
    const packSearch = useSelector(selectors.getPackSearch);
    const [page, setPage] = React.useState(1);

    const handlePageChange = (event, value) => {
        dispatch(actions.findPacks(value - 1));
        setPage(value);
        window.scrollTo(0, 0);
    };

    return (
        <React.Fragment>
            {packSearch === null || packSearch.result.content.length === 0 ? (
                <Alert severity="info">
                    <FormattedMessage id="project.packs.foundNoPacks" />
                </Alert>
            ) : (
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
                                <Pagination
                                    count={packSearch.result.totalPages}
                                    page={page}
                                    onChange={handlePageChange}
                                />
                            </Grid>
                        </Grid>
                    </Box>
                )}
            {isGerente ?
                <Fab
                    sx={{ position: "fixed", bottom: 50, right: 50 }}
                    color="primary"
                    aria-label="add"
                    component={Link}
                    to="/create-pack"
                >
                    <AddIcon />
                </Fab>
                :
                <React.Fragment />
            }
        </React.Fragment>
    );
};

export default withRouter(FindPacksResult);
