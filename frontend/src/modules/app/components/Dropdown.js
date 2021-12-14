import { ExpandLess, ExpandMore } from "@material-ui/icons";
import AirplanemodeActiveIcon from "@mui/icons-material/AirplanemodeActive";
import BusinessIcon from "@mui/icons-material/Business";
import CommuteIcon from "@mui/icons-material/Commute";
import ExtensionIcon from "@mui/icons-material/Extension";
import HotelIcon from "@mui/icons-material/Hotel";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import SupportAgentIcon from "@mui/icons-material/SupportAgent";
import SurfingIcon from "@mui/icons-material/Surfing";
import PointOfSaleIcon from "@mui/icons-material/PointOfSale";
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
import { useSelector } from "react-redux";
import { Link } from "react-router-dom";
import users from "../../users";

const Dropdown = (props) => {
  const isGerente = useSelector(users.selectors.isGerente);
  const isAgente = useSelector(users.selectors.isAgente);
  const isInformatico = useSelector(users.selectors.isInformatico);
  const isCliente = useSelector(users.selectors.isCliente);
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
        {(isAgente || isGerente) && (
          <React.Fragment>
            <ListItem button component={Link} to="/packs">
              <ListItemIcon>
                <PermMediaIcon />
              </ListItemIcon>
              <ListItemText>Packs</ListItemText>
            </ListItem>
            <ListItem button component={Link} to="/create-sale">
              <ListItemIcon>
                <AttachMoneyIcon />
              </ListItemIcon>
              <ListItemText>Crear venta</ListItemText>
            </ListItem>
          </React.Fragment>
        )}
        {(isGerente || isAgente || isCliente) && (
          <React.Fragment>
            <ListItem button component={Link} to="/sales">
              <ListItemIcon>
                <PointOfSaleIcon />
              </ListItemIcon>
              <ListItemText>Ventas</ListItemText>
            </ListItem>
          </React.Fragment>
        )}
        {(isInformatico || isGerente) && (
          <React.Fragment>
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
                <ListItem
                  button
                  sx={{ pl: 4 }}
                  component={Link}
                  to="/transports"
                >
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
                <ListItem
                  button
                  sx={{ pl: 4 }}
                  component={Link}
                  to="/activities"
                >
                  <ListItemIcon>
                    <SurfingIcon />
                  </ListItemIcon>
                  <ListItemText>Actividades</ListItemText>
                </ListItem>
              </List>
            </Collapse>
          </React.Fragment>
        )}
        {isGerente && (
          <React.Fragment>
            <Divider />
            <ListItem button component={Link} to="/businessStats">
              <ListItemIcon>
                <BusinessIcon />
              </ListItemIcon>
              <ListItemText>Estadísticas de empresa</ListItemText>
            </ListItem>
            <ListItem button component={Link} to="/agents">
              <ListItemIcon>
                <SupportAgentIcon />
              </ListItemIcon>
              <ListItemText>Estadísticas de agentes</ListItemText>
            </ListItem>
            <ListItem button component={Link} to="/products">
              <ListItemIcon>
                <ShoppingCartIcon />
              </ListItemIcon>
              <ListItemText>Estadísticas de productos</ListItemText>
            </ListItem>
          </React.Fragment>
        )}
      </List>
    </Drawer>
  );
};

export default Dropdown;
