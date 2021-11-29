import React from "react";
import TableCell from "@mui/material/TableCell";
import TableRow from "@mui/material/TableRow";
import { Typography, Input } from "@mui/material";

const TableRows = ({ item, value, handleUnits }) => {
  return (
    <TableRow key={JSON.stringify(item) + JSON.stringify(value)}>
      <TableCell component="th" scope="row" style={{ width: 50 }}>
        <Input
          type="number"
          value={value}
          onChange={(e) => handleUnits(item, e.target.value)}
          label={null}
          size="small"
          sx={{ width: 50 }}
        />
      </TableCell>
      <TableCell style={{ width: 300 }}>
        <Typography variant="body">{item.name}</Typography>
      </TableCell>
      <TableCell style={{ width: 350 }}>
        <Typography variant="body">
          {item.price}
          {" € unid."}
        </Typography>
      </TableCell>
      <TableCell style={{ width: 350 }} align="right">
        <Typography variant="body">
          {(item.price * value).toFixed(2)}
          {" €"}
        </Typography>
      </TableCell>
    </TableRow>
  );
};

export default TableRows;
