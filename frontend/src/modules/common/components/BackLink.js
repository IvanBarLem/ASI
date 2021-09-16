import React from 'react';
import {withRouter} from 'react-router-dom';
import {FormattedMessage} from 'react-intl';

const BackLink = ({history}) => history.length > 2 && (

    <button type="button" className="btn btn-link" 
        onClick={() => window.history.back()}>   

        <FormattedMessage id='project.global.buttons.back'/>

    </button>

);

export default withRouter(BackLink);