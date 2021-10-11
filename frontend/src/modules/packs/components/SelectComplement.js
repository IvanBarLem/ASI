import React from "react";

import OutlinedInput from "@mui/material/OutlinedInput";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import Select from "@mui/material/Select";
import Chip from "@mui/material/Chip";
import Box from "@mui/material/Box";

export const SelectComplement = ({
  options,
  selectedOptions,
  handleChange,
  handleDelete,
  label,
}) => {
  const ITEM_HEIGHT = 48;
  const ITEM_PADDING_TOP = 8;
  const MenuProps = {
    PaperProps: {
      style: {
        maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
        width: 250,
      },
    },
  };
  return (
    <FormControl sx={{ m: 1, width: 300 }}>
      <Box sx={{ display: "flex", flexWrap: "wrap", gap: 0.5 }}>
        {selectedOptions.map((value) => (
          <Chip
            key={value.key}
            label={value.name}
            onDelete={handleDelete(value)}
            clickable={true}
          />
        ))}
      </Box>
      <InputLabel id="select-label">{label}</InputLabel>
      <Select
        labelId="select-label"
        id="select"
        multiple
        value={selectedOptions}
        onChange={handleChange}
        input={<OutlinedInput id="select" label="Chip" />}
        MenuProps={MenuProps}
      >
        {options.map((option) => (
          <MenuItem key={option.key} value={option.key}>
            {option.name}
          </MenuItem>
        ))}
      </Select>
    </FormControl>
  );
};
