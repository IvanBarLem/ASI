import React from "react";

import {
  Checkbox,
  List,
  ListItem,
  ListItemButton,
  ListItemIcon,
  ListItemText,
  ListSubheader,
} from "@mui/material";

export const SelectComplement = ({
  options,
  selectedOptions,
  handleChange,
  label,
}) => {
  return (
    <List
      sx={{
        width: "100%",
        maxWidth: 360,
        maxHeight: 400,
        overflowY: "scroll",
        bgcolor: "background.paper",
        marginBottom: 10,
      }}
      subheader={<ListSubheader component="div">{label}</ListSubheader>}
    >
      {options.map((value) => {
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
  );
};
