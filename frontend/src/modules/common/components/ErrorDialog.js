import React from 'react';
import PropTypes from 'prop-types';
import { injectIntl, FormattedMessage } from 'react-intl';

import { NetworkError } from '../../../backend';

const ErrorDialog = ({ error, onClose, intl }) => {

    if (error == null) {
        return null;
    }

    const modalStyle = { display: 'block', marginTop: 100 };
    const message = error instanceof NetworkError ?
        intl.formatMessage({ id: 'project.global.exceptions.NetworkError' }) :
        error.message;

    return (

        <div className="modal" style={modalStyle} tabIndex="-1" role="dialog">
            <div className="modal-dialog" role="document">
                <div className="modal-content">
                    <div className="modal-header">
                        <h5 className="modal-title">
                            <FormattedMessage id="project.common.ErrorDialog.title" />
                        </h5>
                    </div>
                    <div className="modal-body">
                        <p>{message}</p>
                    </div>
                    <div className="modal-footer">
                        <button type="button" className="btn btn-primary"
                            data-dismiss="modal"
                            onClick={onClose}>
                            <FormattedMessage id="project.global.buttons.close" />
                        </button>
                    </div>
                </div>
            </div>
        </div>

    );

};

ErrorDialog.propTypes = {
    error: PropTypes.object,
    onClose: PropTypes.func.isRequired
}

export default injectIntl(ErrorDialog);
