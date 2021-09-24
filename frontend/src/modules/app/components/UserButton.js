import React from 'react';
import {Link} from 'react-router-dom';
import {FormattedMessage} from 'react-intl';
import {Button, MenuItem, Menu} from '@mui/material';
import ArrowDropDownIcon from '@material-ui/icons/ArrowDropDown';

const Userbutton = (props) => {
    const [anchorEl, setAnchorEl] = React.useState(null);
    const userOpen = Boolean(anchorEl);

    const toogleUserOpen = (event) => {
        setAnchorEl(anchorEl ? null : event.currentTarget);
    }

    const closeMenu = () => {
        setAnchorEl(null);
    }

    return(
        <div>
        {props.userName ?
            <div>
                <Button 
                    variant="contained"
                    color="primary"
                    endIcon={<ArrowDropDownIcon/>}
                    disableElevation
                    aria-controls={userOpen ? 'menu-list-grow' : undefined}
                    aria-haspopup="true"
                    onClick={event => toogleUserOpen(event)}
                >
                    {props.userName}
                </Button>
                <Menu open={userOpen} anchorEl={anchorEl} onClose={closeMenu}>
                    <MenuItem onClick={closeMenu} component={Link} to="/users/update-profile">
                        <FormattedMessage id="project.users.UpdateProfile.title"/>
                    </MenuItem>
                    <MenuItem onClick={closeMenu} component={Link} to="/users/change-password">
                        <FormattedMessage id="project.users.ChangePassword.title"/>
                    </MenuItem>
                    <MenuItem onClick={closeMenu} component={Link} to="/users/logout">
                        <FormattedMessage id="project.app.Header.logout"/>
                    </MenuItem>
                </Menu>
            </div>
            :
            <Button variant='text' component={Link} to="/users/login" color='inherit'>
                <FormattedMessage id="project.users.Login.title"/>
            </Button>
        }
        </div>
    );
}

export default Userbutton