import {
  FormControl,
  InputAdornment,
  InputLabel,
  List,
  ListItem,
  MenuItem,
  Select,
  TextField,
} from "@mui/material";
import React from "react";
import { FormattedMessage } from "react-intl";

export const Step3 = ({
  price,
  setPrice,
  duration,
  setDuration,
  numPersons,
  setNumPersons,
}) => {
  return (
    <List sx={{ padding: 1 }}>
      <ListItem>
        <TextField
          id="price"
          label={<FormattedMessage id="project.global.fields.price" />}
          variant="outlined"
          value={price}
          onChange={(e) => setPrice(e.target.value)}
          size="small"
          type="number"
          fullWidth
          InputProps={{
            endAdornment: <InputAdornment position="end">€</InputAdornment>,
          }}
        />
      </ListItem>
      <ListItem>
        <TextField
          id="duration"
          label={<FormattedMessage id="project.global.fields.duration" />}
          variant="outlined"
          value={duration}
          onChange={(e) => setDuration(e.target.value)}
          size="small"
          type="number"
          fullWidth
          InputProps={{
            endAdornment: <InputAdornment position="end">días</InputAdornment>,
          }}
        />
      </ListItem>
      <ListItem>
        <FormControl sx={{ m: 1, width: "100%" }}>
          <InputLabel id="numPersons-label">Nicho</InputLabel>
          <Select
            labelId="numPersons-label"
            id="numPersons"
            value={numPersons}
            onChange={(e) => setNumPersons(e.target.value)}
            fullWidth
            label="numPersons"
          >
            <MenuItem value={"Ninguno"}>
              <em>Ninguno</em>
            </MenuItem>
            <MenuItem value={"Individual"}>Individual</MenuItem>
            <MenuItem value={"Parejas"}>Parejas</MenuItem>
            <MenuItem value={"Familias"}>Familias</MenuItem>
            <MenuItem value={"Amigos"}>Amigos</MenuItem>
          </Select>
        </FormControl>
      </ListItem>
    </List>
  );
};
