import React from 'react';
import { FormattedMessage } from 'react-intl';
import { Box } from '@mui/material';

const Footer = (props) => (

    <div>
        <br />
        <hr />
        <footer>
            <Box sx={props.dropDownMarginClasses}>
                <p className="text-center">
                    <FormattedMessage id="project.app.Footer.text" />
                </p>
            </Box>
        </footer>
    </div>

);

export default Footer;
