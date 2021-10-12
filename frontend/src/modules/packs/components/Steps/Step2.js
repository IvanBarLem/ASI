import { List, ListItem } from "@mui/material";
import React from "react";
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
  trips,
  tripsSelected,
  handleChangeTrips,
}) => {
  return (
    <List sx={{ padding: 1 }}>
      <ListItem>
        <SelectComplement
          options={trips}
          selectedOptions={tripsSelected}
          handleChange={handleChangeTrips}
          label={"Viajes"}
        />
      </ListItem>
      <ListItem>
        <SelectComplement
          options={transports}
          selectedOptions={transportsSelected}
          handleChange={handleChangeTransports}
          label={"Transportes"}
        />
      </ListItem>
      <ListItem>
        <SelectComplement
          options={accomodations}
          selectedOptions={accomodationsSelected}
          handleChange={handleChangeAccomodations}
          label={"Alojamientos"}
        />
      </ListItem>
      <ListItem>
        <SelectComplement
          options={activities}
          selectedOptions={activitiesSelected}
          handleChange={handleChangeActivities}
          label={"Actividades"}
        />
      </ListItem>
    </List>
  );
};
