import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import * as actions from "../actions";
import FindPacksResult from "./FindPacksResult";
import users from "../../users";

const FindPacks = () => {
  const dispatch = useDispatch();
  const initialPage = 0;
  const isGerente = useSelector(users.selectors.isGerente);

  useEffect(() => {
    isGerente
      ? dispatch(actions.findAllPacks(initialPage))
      : dispatch(actions.findPacks(initialPage));
    return () => {
      dispatch(actions.clearPackSearch());
    };
  }, [isGerente]);

  return <FindPacksResult />;
};

export default FindPacks;
