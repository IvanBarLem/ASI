import {useEffect} from 'react';
import {useDispatch} from 'react-redux';
import {useHistory} from 'react-router-dom';


import users from '../../users';

const Logout = () => {

    const dispatch = useDispatch();
    const history = useHistory();

    useEffect(() => {
        dispatch(users.actions.logout());
        history.push('/');
    });
    
    return null;
    

}

export default Logout;