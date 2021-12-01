import React, { Fragment, useState } from "react";

import {
  Checkbox,
  Grid,
  List,
  ListItem,
  ListItemButton,
  ListItemIcon,
  ListItemText,
  ListSubheader,
  TextField,
} from "@mui/material";

export const SelectComplement = ({
  options,
  selectedOptions,
  handleChange,
  label,
}) => {
  const [value, setValue] = useState("");

  const handleChangeValue = (value) => {
    setValue(value);
  };

  return (
    <Grid container direction="column">
      <TextField
        label={label}
        value={value}
        onChange={(e) => handleChangeValue(e.target.value)}
        sx={{ marginBottom: 3 }}
      />
      <List
        sx={{
          width: "100%",
          maxWidth: 360,
          height: 300,
          overflowY: "scroll",
          bgcolor: "#fafafa",
          marginBottom: 10,
        }}
      >
        {options
          .filter((option) => option.name.includes(value))
          .map((value) => {
            const labelId = `checkbox-list-label-${value.name}`;

            return (
              <ListItem key={value.name} disablePadding>
                <ListItemButton
                  role={undefined}
                  onClick={handleChange(value)}
                  dense
                >
                  <ListItemIcon>
                    <Checkbox
                      edge="start"
                      checked={
                        selectedOptions
                          .map((value) => value.name)
                          .indexOf(value.name) !== -1
                      }
                      tabIndex={-1}
                      disableRipple
                    />
                  </ListItemIcon>
                  <ListItemText
                    id={labelId}
                    primary={value.name}
                    secondary={value.price + " €"}
                  />
                </ListItemButton>
              </ListItem>
            );
          })}
      </List>
    </Grid>
  );
};
