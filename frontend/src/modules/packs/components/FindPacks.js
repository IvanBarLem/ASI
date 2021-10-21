import React, {useEffect} from 'react';
import {useDispatch} from 'react-redux';
import * as actions from '../actions';
import FindPacksResult from './FindPacksResult';

const FindPacks = () => {
    const dispatch = useDispatch();
    const initialPage = 0;

    useEffect(() => {
        dispatch(actions.findPacks(initialPage));
        return () => {
            dispatch(actions.clearPackSearch());
        }
    });

    return (
        <FindPacksResult/>
    )
}

export default FindPacks;