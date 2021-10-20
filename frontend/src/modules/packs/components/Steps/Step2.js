import { List, ListItem } from "@mui/material";
import React from "react";
import { FormattedMessage } from "react-intl";
import { ErrorDialog } from "../../../common";
import { SelectComplement } from "../SelectComplement";

export const Step2 = ({
  transports,
  transportsSelected,
  handleChangeTransports,
  accomodations,
  accomodationsSelected,
  handleChangeAccomodations,
  activities,
  activitiesSelected,
  handleChangeActivities,
  travels,
  travelsSelected,
  handleChangeTravels,
  backendErrorsActivities,
  backendErrorsAccomodations,
  backendErrorsTransports,
  backendErrorsTravels,
  setBackendErrorsActivities,
  setBackendErrorsAccomodations,
  setBackendErrorsTransports,
  setBackendErrorsTravels,
}) => {
  return (
    <List sx={{ padding: 1 }}>
      <ListItem>
        <SelectComplement
          options={travels}
          selectedOptions={travelsSelected}
          handleChange={handleChangeTravels}
          label={<FormattedMessage id="project.packs.CreatePack.travels" />}
        />
        <ErrorDialog
          error={backendErrorsTravels}
          onClose={() => setBackendErrorsTravels(null)}
        />
      </ListItem>
      <ListItem>
        <SelectComplement
          options={transports}
          selectedOptions={transportsSelected}
          handleChange={handleChangeTransports}
          label={<FormattedMessage id="project.packs.CreatePack.transports" />}
        />
        <ErrorDialog
          error={backendErrorsTransports}
          onClose={() => setBackendErrorsTransports(null)}
        />
      </ListItem>
      <ListItem>
        <SelectComplement
          options={accomodations}
          selectedOptions={accomodationsSelected}
          handleChange={handleChangeAccomodations}
          label={
            <FormattedMessage id="project.packs.CreatePack.acommodations" />
          }
        />
        <ErrorDialog
          error={backendErrorsAccomodations}
          onClose={() => setBackendErrorsAccomodations(null)}
        />
      </ListItem>
      <ListItem>
        <SelectComplement
          options={activities}
          selectedOptions={activitiesSelected}
          handleChange={handleChangeActivities}
          label={<FormattedMessage id="project.packs.CreatePack.activities" />}
        />
        <ErrorDialog
          error={backendErrorsActivities}
          onClose={() => setBackendErrorsActivities(null)}
        />
      </ListItem>
    </List>
  );
};
