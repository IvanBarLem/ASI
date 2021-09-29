import React from "react";
import { useSelector } from "react-redux";
import {
  AppBar,
  Toolbar,
  IconButton,
  Typography,
  Hidden,
  useTheme,
} from "@mui/material";
import MenuIcon from "@material-ui/icons/Menu";
import useMediaQuery from "@mui/material/useMediaQuery";
import { makeStyles } from "@material-ui/core/styles";

import users from "../../users";
import Dropdown from "./Dropdown";
import Userbutton from "./UserButton";

const useStyles = makeStyles((theme) => ({
  menuButton: {
    marginRight: "1%",
  },
  title: {
    flexGrow: 1,
  },
}));

const Header = (props) => {
  const classes = useStyles();
  const theme = useTheme();
  const matches = useMediaQuery(theme.breakpoints.up("md"));
  const name = useSelector(users.selectors.getName);
  const isLoggedIn = useSelector(users.selectors.isLoggedIn);
  const [dropdownOpened, setDropdownOpened] = React.useState(false);
  const toogleDropdown = () => {
    setDropdownOpened(!dropdownOpened);
  };

  return (
    <div>
      <AppBar sx={props.dropDownMarginClasses}>
        <Toolbar>
          {isLoggedIn && !matches ? (
            <IconButton
              className={classes.menuButton}
              color="inherit"
              aria-label="menu"
              onClick={() => toogleDropdown()}
            >
              <MenuIcon />
            </IconButton>
          ) : (
            <div />
          )}
          <Typography className={classes.title} variant="h6">
            Agencia Viajes
          </Typography>
          <Userbutton name={name} />
        </Toolbar>
      </AppBar>
      <div className="offset"></div>
      {isLoggedIn ? (
        <div>
          <Hidden mdDown>
            <Dropdown variant="permanent" open={true} />
          </Hidden>
          <Hidden lgUp>
            <Dropdown
              variant="temporary"
              open={dropdownOpened}
              onClose={toogleDropdown}
            />
          </Hidden>
        </div>
      ) : (
        <div />
      )}
    </div>
  );
};

export default Header;
