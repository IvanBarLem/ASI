import { Box, Divider, Grid, Paper, Typography } from "@mui/material";
import { createTheme } from "@mui/material/styles";
import React from "react";

const KPI = ({ name, kpi, otherKpi, respecting }) => {
  const kpiDiff = otherKpi === 0 ? 0 : (kpi / otherKpi) * 100;

  return (
    <Paper>
      <Box sx={{ padding: 1 }}>
        <Typography sx={{ fontWeight: 600 }} variant="h5">
          {name}
        </Typography>
      </Box>
      <Divider />
      <Grid container spacing={1}>
        <Grid item sx={{ margin: 1 }}>
          <Grid container>
            <Typography color="primary" variant="h1">
              {kpiDiff.toFixed(1)}%
            </Typography>
          </Grid>
        </Grid>
        <Grid item>
          <Divider orientation="vertical" />
        </Grid>
        <Grid item sx={{ margin: 1 }}>
          <Box>
            <Typography variant="h6" sx={{ maxWidth: 150 }}>
              {respecting} ({otherKpi}
              {" €"})
            </Typography>
          </Box>
        </Grid>
      </Grid>
    </Paper>
  );
};

export default KPI;
