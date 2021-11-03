import React from "react";
//import {FormattedMessage} from 'react-intl';
import {
  List,
  ListItem,
  ListItemIcon,
  ListItemText,
  Divider,
  Drawer,
  Collapse,
} from "@mui/material";
import ListIcon from "@material-ui/icons/List";
import { Link } from "react-router-dom";
import { ExpandLess, ExpandMore } from "@material-ui/icons";
import ExtensionIcon from "@mui/icons-material/Extension";
import AirplanemodeActiveIcon from "@mui/icons-material/AirplanemodeActive";
import CommuteIcon from "@mui/icons-material/Commute";
import HotelIcon from "@mui/icons-material/Hotel";
import LocalActivityIcon from "@mui/icons-material/LocalActivity";

const Dropdown = (props) => {
  const [open, setOpen] = React.useState(true);

  const handleClick = () => {
    setOpen((prevOpen) => !prevOpen);
  };
  return (
    <Drawer
      className="myDrawer"
      classes={{
        paper: "drawerPaper",
      }}
      variant={props.variant}
      anchor="left"
      open={props.open}
      onClose={props.onClose ? props.onClose : null}
    >
      <div className="offset" />
      <Divider />
      <List component="nav">
        <ListItem button component={Link} to="/packs">
          <ListItemIcon>
            <ListIcon />
          </ListItemIcon>
          <ListItemText>Packs</ListItemText>
        </ListItem>
        <ListItem button onClick={handleClick}>
          <ListItemIcon>
            <ExtensionIcon />
          </ListItemIcon>
          <ListItemText>Productos</ListItemText>
          {open ? <ExpandLess /> : <ExpandMore />}
        </ListItem>
        <Collapse in={open} timeout="auto" unmountOnExit>
          <List component="div" disablePadding>
            <ListItem button sx={{ pl: 4 }} component={Link} to="/travels">
              <ListItemIcon>
                <AirplanemodeActiveIcon />
              </ListItemIcon>
              <ListItemText>Viajes</ListItemText>
            </ListItem>
            <ListItem button sx={{ pl: 4 }} component={Link} to="/transports">
              <ListItemIcon>
                <CommuteIcon />
              </ListItemIcon>
              <ListItemText>Transportes</ListItemText>
            </ListItem>
            <ListItem
              button
              sx={{ pl: 4 }}
              component={Link}
              to="/accommodations"
            >
              <ListItemIcon>
                <HotelIcon />
              </ListItemIcon>
              <ListItemText>Alojamientos</ListItemText>
            </ListItem>
            <ListItem button sx={{ pl: 4 }} component={Link} to="/activities">
              <ListItemIcon>
                <LocalActivityIcon />
              </ListItemIcon>
              <ListItemText>Actividades</ListItemText>
            </ListItem>
          </List>
        </Collapse>
      </List>
    </Drawer>
  );
};

export default Dropdown;
