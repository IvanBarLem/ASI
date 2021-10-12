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
        <ListItem button component={Link} to="/packs">
          <ListItemIcon>
            <ListIcon />
          </ListItemIcon>
          <ListItemText>Ver Packs</ListItemText>
        </ListItem>
      </List>
    </Drawer>
  );
};

export default Dropdown;
