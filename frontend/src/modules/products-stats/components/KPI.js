import { Box, Divider, Grid, Paper, Typography } from "@mui/material";
import React from "react";
import Chart from "./Chart";

const KPI = ({ name, productName, otherName, kpi, otherKpi, respecting }) => {
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
            <Chart
              productName={productName}
              kpi={kpi}
              otherKpi={otherKpi}
              otherName={otherName}
            />
          </Grid>
        </Grid>
        <Grid item>
          <Divider orientation="vertical" />
        </Grid>
        <Grid item sx={{ margin: 1 }}>
          <Box>
            <Typography color="primary" variant="h3">
              {kpiDiff.toFixed(2)}%
            </Typography>
            <Typography variant="h6" sx={{ maxWidth: 150 }}>
              {respecting} ({otherKpi + kpi}
              {" €"})
            </Typography>
          </Box>
        </Grid>
      </Grid>
    </Paper>
  );
};

export default KPI;
