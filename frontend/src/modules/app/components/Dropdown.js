import { ExpandLess, ExpandMore } from "@material-ui/icons";
import ListIcon from "@material-ui/icons/List";
import AirplanemodeActiveIcon from "@mui/icons-material/AirplanemodeActive";
import CommuteIcon from "@mui/icons-material/Commute";
import ExtensionIcon from "@mui/icons-material/Extension";
import GroupIcon from "@mui/icons-material/Group";
import HotelIcon from "@mui/icons-material/Hotel";
import LocalActivityIcon from "@mui/icons-material/LocalActivity";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
//import {FormattedMessage} from 'react-intl';
import {
  Collapse,
  Divider,
  Drawer,
  List,
  ListItem,
  ListItemIcon,
  ListItemText,
} from "@mui/material";
import React from "react";
import { Link } from "react-router-dom";

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
        <ListItem button component={Link} to="/agents">
          <ListItemIcon>
            <GroupIcon />
          </ListItemIcon>
          <ListItemText>Agentes/Estadísticas</ListItemText>
        </ListItem>
        <ListItem button component={Link} to="/products">
          <ListItemIcon>
            <ShoppingCartIcon />
          </ListItemIcon>
          <ListItemText>Estadísticas de productos</ListItemText>
        </ListItem>
      </List>
    </Drawer>
  );
};

export default Dropdown;
