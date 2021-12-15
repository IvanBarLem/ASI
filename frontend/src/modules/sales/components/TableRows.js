import React from "react";
import TableCell from "@mui/material/TableCell";
import TableRow from "@mui/material/TableRow";
import { Typography, TextField } from "@mui/material";

const TableRows = ({ item, value, handleProducts }) => {
  return (
    <TableRow key={JSON.stringify(item) + JSON.stringify(value)}>
      <TableCell component="th" scope="row" style={{ width: 50 }}>
        <TextField
          autoFocus
          variant="standard"
          type="number"
          value={value}
          onChange={(e) => handleProducts(item, e.target.value)}
          size="small"
          sx={{ width: 50 }}
          required
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
