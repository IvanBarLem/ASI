import React from 'react';
import {FormattedMessage} from 'react-intl';
import { Box } from '@mui/material';

const Footer = (props) => (

    <div>
        <br/>
        <hr/>
        <footer>
            <p className="text-center">
                <Box sx={props.dropDownMarginClasses}>
                    <FormattedMessage id="project.app.Footer.text"/>
                </Box>
            </p>
        </footer>
    </div>

);

export default Footer;
