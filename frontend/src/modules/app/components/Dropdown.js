import React from "react";
//import {FormattedMessage} from 'react-intl';
import {
  List,
  ListItem,
  ListItemIcon,
  ListItemText,
  Divider,
  Drawer,
} from "@mui/material";
import AddIcon from "@material-ui/icons/Add";
import HomeIcon from "@material-ui/icons/Home";
import ListIcon from "@material-ui/icons/List";
import { Link } from "react-router-dom";

const Dropdown = (props) => {
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
        <ListItem button component={Link} to="/create-pack">
          <ListItemIcon>
            <AddIcon />
          </ListItemIcon>
          <ListItemText>Crear Pack</ListItemText>
        </ListItem>
        <ListItem button component={Link} to="/">
          <ListItemIcon>
            <ListIcon />
          </ListItemIcon>
          <ListItemText>Boton 2</ListItemText>
        </ListItem>
      </List>
    </Drawer>
  );
};

export default Dropdown;
