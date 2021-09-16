import React from 'react';
import PropTypes from 'prop-types';
import { FormattedMessage } from 'react-intl';

const Success = ({message, onClose}) => {
    return (
        <div className="alert alert-success alert-dismissible fade show" role="alert">
            <FormattedMessage id={message}/>
            <button type="button" className="close" data-dismiss="alert" aria-label="Close" 
                onClick={() => onClose()}>
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    );
}

Success.propTypes = {
    message: PropTypes.string,
    onClose: PropTypes.func.isRequired
};

export default Success;