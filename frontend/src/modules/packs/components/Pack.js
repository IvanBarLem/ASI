import React, { Fragment } from "react";
import { useDispatch } from "react-redux";
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
import { FormattedMessage } from "react-intl";
import StarIcon from "@mui/icons-material/Star";
import StarBorderIcon from "@mui/icons-material/StarBorder";
import VisibilityIcon from "@mui/icons-material/Visibility";
import VisibilityOffIcon from "@mui/icons-material/VisibilityOff";
import Button from "@mui/material/Button";
import VisibilityOffOutlinedIcon from "@mui/icons-material/VisibilityOffOutlined";
import * as actions from "../actions";

const Pack = ({ item, creating }) => {
  const dispatch = useDispatch();
  const image =
    "https://www.sinrumbofijo.com/wp-content/uploads/2016/05/default-placeholder.png";

  const getProducts = () => {
    var products = [];
    var position = 1;
    item.accommodations.forEach((accommodation) => {
      products.push({ id: position, name: accommodation.name });
      ++position;
    });
    item.activities.forEach((activity) => {
      products.push({ id: position, name: activity.name });
      ++position;
    });
    item.transports.forEach((transport) => {
      products.push({ id: position, name: transport.name });
      ++position;
    });
    item.travels.forEach((travel) => {
      products.push({ id: position, name: travel.name });
      ++position;
    });
    return products;
  };

  const findAllPacks = () => {
    dispatch(actions.findAllPacks(0));
  };

  const hide = () => {
    dispatch(actions.hidePack(item.id));
  };

  const outstand = () => {
    dispatch(actions.outstandingPack(item.id));
  };

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
        sx={
          item.outstanding && !item.hidden
            ? {
                boxShadow: "0px 0px 22px 5px #ED6C02",
                height: "100%",
                maxWidth: 360,
                minWidth: 280,
              }
            : item.hidden && !item.outstanding
            ? {
                height: "100%",
                maxWidth: 360,
                minWidth: 280,
                backgroundColor: "#ebebeb",
                opacity: "0.8",
                background:
                  "repeating-linear-gradient( 45deg, #ebebeb, #ebebeb 5px, #e5e5f7 5px, #e5e5f7 25px )",
              }
            : item.hidden && item.outstanding
            ? {
                backgroundColor: "#ebebeb",
                opacity: "0.8",
                background:
                  "repeating-linear-gradient( 45deg, #ebebeb, #ebebeb 5px, #e5e5f7 5px, #e5e5f7 25px )",
                height: "100%",
                maxWidth: 360,
                minWidth: 280,
                boxShadow: "0px 0px 22px 5px #ED6C02",
              }
            : {
                height: "100%",
                maxWidth: 360,
                minWidth: 280,
              }
        }
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
            {item.title} {item.hidden && <VisibilityOffOutlinedIcon />}
          </Typography>
          <Typography gutterBottom variant="body1" component="div">
            {item.description}
          </Typography>
          <Typography gutterBottom variant="body2" component="div">
            <IconButton disabled>
              <TimerIcon fontSize="small" />
            </IconButton>
            {item.duration}{" "}
            {item.duration - 1 ? (
              <FormattedMessage id="project.packs.findpacks.days" />
            ) : (
              <FormattedMessage id="project.packs.findpacks.day" />
            )}
          </Typography>
          <Typography gutterBottom variant="body2" component="div">
            <IconButton disabled>
              <GroupAddIcon fontSize="small" />
            </IconButton>
            {item.persons === "Individual" ? (
              <FormattedMessage id="project.packs.CreatePack.select.individual" />
            ) : item.persons === "Couples" ? (
              <FormattedMessage id="project.packs.CreatePack.select.couples" />
            ) : item.persons === "Families" ? (
              <FormattedMessage id="project.packs.CreatePack.select.families" />
            ) : item.persons === "Friends" ? (
              <FormattedMessage id="project.packs.CreatePack.select.friends" />
            ) : (
              ""
            )}
          </Typography>
          <Divider />
          <Grid sx={{ marginTop: "6px" }} container spacing={1}>
            {getProducts().map((product) => (
              <Grid key={product.id} item>
                <Chip
                  key={product.id}
                  label={product.name}
                  variant="outlined"
                />
              </Grid>
            ))}
          </Grid>
          {!creating && (
            <Fragment>
              <Divider sx={{ marginTop: "10px" }} />
              <Grid container spacing={1} sx={{ marginTop: "6px" }}>
                <Grid item xs={6}>
                  {item.outstanding ? (
                    <Button
                      size="small"
                      fullWidth
                      variant="outlined"
                      startIcon={<StarBorderIcon />}
                      color="warning"
                      onClick={() => outstand()}
                    >
                      <FormattedMessage id="project.packs.unhighlight" />
                    </Button>
                  ) : (
                    <Button
                      size="small"
                      fullWidth
                      variant="contained"
                      startIcon={<StarIcon />}
                      color="warning"
                      onClick={() => outstand()}
                    >
                      <FormattedMessage id="project.packs.highlight" />
                    </Button>
                  )}
                </Grid>
                <Grid item xs={6}>
                  {item.hidden ? (
                    <Button
                      size="small"
                      fullWidth
                      variant="outlined"
                      startIcon={<VisibilityIcon />}
                      onClick={() => hide()}
                    >
                      <FormattedMessage id="project.packs.show" />
                    </Button>
                  ) : (
                    <Button
                      size="small"
                      fullWidth
                      variant="contained"
                      startIcon={<VisibilityOffIcon />}
                      onClick={() => hide()}
                    >
                      <FormattedMessage id="project.packs.unshow" />
                    </Button>
                  )}
                </Grid>
              </Grid>
            </Fragment>
          )}
        </CardContent>
      </Card>
    </Grid>
  );
};

export default Pack;
