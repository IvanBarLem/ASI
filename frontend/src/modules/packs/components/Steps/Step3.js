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
            endAdornment: (
              <InputAdornment position="end">
                <FormattedMessage id="project.packs.CreatePack.days" />
              </InputAdornment>
            ),
          }}
        />
      </ListItem>
      <ListItem>
        <FormControl sx={{ m: 1, width: "100%" }}>
          <InputLabel id="numPersons-label">
            <FormattedMessage id="project.packs.CreatePack.niche" />
          </InputLabel>
          <Select
            labelId="numPersons-label"
            id="numPersons"
            value={numPersons}
            onChange={(e) => setNumPersons(e.target.value)}
            fullWidth
            label="numPersons"
          >
            <MenuItem value={"Individual"}>
              <FormattedMessage id="project.packs.CreatePack.select.individual" />
            </MenuItem>
            <MenuItem value={"Couples"}>
              <FormattedMessage id="project.packs.CreatePack.select.couples" />
            </MenuItem>
            <MenuItem value={"Families"}>
              <FormattedMessage id="project.packs.CreatePack.select.families" />
            </MenuItem>
            <MenuItem value={"Friends"}>
              <FormattedMessage id="project.packs.CreatePack.select.friends" />
            </MenuItem>
          </Select>
        </FormControl>
      </ListItem>
    </List>
  );
};
