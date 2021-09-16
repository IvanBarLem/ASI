import React from 'react';
import {connect} from 'react-redux';

import users from '../../users';

class Logout extends React.Component {

    componentDidMount() {

        this.props.dispatch(users.actions.logout());
        this.props.history.push('/');

    }

    render() {
        return null;
    }

}

export default connect()(Logout);