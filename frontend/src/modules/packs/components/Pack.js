import React from "react";
import {
  Typography,
  Card,
  CardMedia,
  CardContent,
  Chip,
  Grid,
  Divider,
  IconButton,
} from "@mui/material";
import TimerIcon from "@mui/icons-material/Timer";
import GroupAddIcon from "@mui/icons-material/GroupAdd";

const Pack = ({ item }) => {
  const image =
    "https://www.sinrumbofijo.com/wp-content/uploads/2016/05/default-placeholder.png";

  return (
    <Grid
      key={item.id}
      item
      sx={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
      }}
      xs={12}
      md={6}
      lg={4}
      xl={3}
    >
      <Card
        sx={{
          maxWidth: 360,
          minWidth: 200,
        }}
      >
        <CardMedia
          component="img"
          height="140"
          src={item.image !== "" ? item.image : image}
        />
        <CardContent>
          <Grid
            sx={{
              display: "flex",
              justifyContent: "flex-end",
              marginTop: "-35px",
            }}
          >
            <Chip
              color="primary"
              label={
                <Typography variant="h6" component="div">
                  {item.price}
                  {" €"}
                </Typography>
              }
            />
          </Grid>
          <Typography gutterBottom variant="h5" component="div">
            {item.title}
          </Typography>
          <Typography noWrap gutterBottom variant="body1" component="div">
            {item.description}
          </Typography>
          <Typography gutterBottom variant="body2" component="div">
            <IconButton>
              <TimerIcon fontSize="small" />
            </IconButton>
            {item.duration} {item.duration - 1 ? "días" : "día"}
          </Typography>
          <Typography gutterBottom variant="body2" component="div">
            <IconButton>
              <GroupAddIcon fontSize="small" />
            </IconButton>
            {item.numPersons}
          </Typography>
          <Divider />
          <Grid sx={{ marginTop: "6px" }} container spacing={1}>
            {item.products.map((product) => (
              <Grid key={product.id} item>
                <Chip
                  key={product.id}
                  label={product.name}
                  variant="outlined"
                />
              </Grid>
            ))}
          </Grid>
        </CardContent>
      </Card>
    </Grid>
  );
};

export default Pack;
