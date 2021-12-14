import AirplanemodeActiveIcon from "@mui/icons-material/AirplanemodeActive";
import CommuteIcon from "@mui/icons-material/Commute";
import HotelIcon from "@mui/icons-material/Hotel";
import SurfingIcon from "@mui/icons-material/Surfing";
import AddIcon from "@mui/icons-material/Add";
import { Link } from "react-router-dom";

import {
  Badge,
  Box,
  Button,
  Chip,
  Fab,
  List,
  ListItem,
  Paper,
  Popover,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Typography,
} from "@mui/material";
import React from "react";
import { FormattedMessage } from "react-intl";
import StateIcon from "./StateIcon";
import { useSelector } from "react-redux";
import users from "../../users";

const FindSalesResult = () => {
  const [open, setOpen] = React.useState("");
  const [anchorEl, setAnchorEl] = React.useState(null);
  const isGerente = useSelector(users.selectors.isGerente);
  const isAgente = useSelector(users.selectors.isAgente);

  const handlePopoverOpen = (event, id) => {
    setOpen(id);
    setAnchorEl(event.currentTarget);
  };

  const handlePopoverClose = () => {
    setOpen("");
    setAnchorEl(null);
  };

  const sales = [
    {
      id: 1,
      client: "Paco Perez",
      agent: "Concha Lopez",
      travels: ["Chicago", "BuenosDias"],
      acommodations: ["hesperia"],
      transports: ["catamaran"],
      activities: ["karts"],
      price: 340.99,
      state: "CREATED",
    },
    {
      id: 2,
      client: "Paco Perez",
      agent: "Samuel Lopez",
      travels: [
        "Chicago-BuenosAires",
        "Chicago-BuenosDias",
        "Chicago-BuenasNoches",
      ],
      acommodations: ["hesperia"],
      transports: ["catamaran"],
      activities: ["surf"],
      price: 1000.95,
      state: "BLOCKED",
    },
    {
      id: 3,
      client: "Manuel Sanchez",
      agent: "Samuel Lopez",
      travels: ["Chicago-BuenosAires", "Chicago-BuenasNoches"],
      acommodations: ["hesperia"],
      transports: ["catamaran"],
      activities: ["surf"],
      price: 2000.23,
      state: "PAID",
    },
  ];

  const MyPopover = ({ id, items }) => {
    return (
      <Popover
        id="mouse-over-popover"
        sx={{
          pointerEvents: "none",
        }}
        open={open === id}
        anchorEl={anchorEl}
        anchorOrigin={{
          vertical: "bottom",
          horizontal: "center",
        }}
        onClose={handlePopoverClose}
      >
        <List dense>
          {items.map((item, i) => (
            <ListItem key={i}>
              <Chip label={item} variant="outlined" />
              <Typography sx={{ marginLeft: 1 }}>-</Typography>
              <Chip
                label={<Typography variant="h6">x{items.length}</Typography>}
                color="primary"
                sx={{ marginLeft: 1 }}
              />
            </ListItem>
          ))}
        </List>
      </Popover>
    );
  };

  return (
    <React.Fragment>
      <Paper>
        <TableContainer>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>
                  <FormattedMessage id="project.global.fields.id" />
                </TableCell>
                <TableCell align="center">
                  <FormattedMessage id="project.global.fields.client" />
                </TableCell>
                <TableCell align="center">
                  <FormattedMessage id="project.global.fields.agent" />
                </TableCell>
                <TableCell align="center">
                  <FormattedMessage id="project.global.fields.travels" />
                </TableCell>
                <TableCell align="center">
                  <FormattedMessage id="project.global.fields.acommodations" />
                </TableCell>
                <TableCell align="center">
                  <FormattedMessage id="project.global.fields.transports" />
                </TableCell>
                <TableCell align="center">
                  <FormattedMessage id="project.global.fields.activities" />
                </TableCell>
                <TableCell align="center">
                  <FormattedMessage id="project.global.fields.price" />
                </TableCell>
                <TableCell align="center">
                  <FormattedMessage id="project.global.fields.state" />
                </TableCell>
                <TableCell align="center">
                  <FormattedMessage id="project.global.fields.action" />
                </TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {sales.map((sale) => (
                <TableRow key={sale.id}>
                  <TableCell>{sale.id}</TableCell>
                  <TableCell align="center">{sale.client}</TableCell>
                  <TableCell align="center">{sale.agent}</TableCell>
                  <TableCell align="center">
                    <Box
                      onMouseEnter={(event) =>
                        handlePopoverOpen(event, "travels" + sale.id)
                      }
                      onMouseLeave={handlePopoverClose}
                    >
                      <Badge badgeContent={sale.travels.length} color="primary">
                        <AirplanemodeActiveIcon />
                      </Badge>
                    </Box>
                    <MyPopover id={"travels" + sale.id} items={sale.travels} />
                  </TableCell>
                  <TableCell align="center">
                    <Box
                      onMouseEnter={(event) =>
                        handlePopoverOpen(event, "acommodations" + sale.id)
                      }
                      onMouseLeave={handlePopoverClose}
                    >
                      <Badge
                        badgeContent={sale.acommodations.length}
                        color="primary"
                      >
                        <HotelIcon />
                      </Badge>
                    </Box>
                    <MyPopover
                      id={"acommodations" + sale.id}
                      items={sale.acommodations}
                    />
                  </TableCell>
                  <TableCell align="center">
                    <Box
                      onMouseEnter={(event) =>
                        handlePopoverOpen(event, "transports" + sale.id)
                      }
                      onMouseLeave={handlePopoverClose}
                    >
                      <Badge
                        badgeContent={sale.transports.length}
                        color="primary"
                      >
                        <CommuteIcon />
                      </Badge>
                    </Box>
                    <MyPopover
                      id={"transports" + sale.id}
                      items={sale.transports}
                    />
                  </TableCell>
                  <TableCell align="center">
                    <Box
                      onMouseEnter={(event) =>
                        handlePopoverOpen(event, "activities" + sale.id)
                      }
                      onMouseLeave={handlePopoverClose}
                    >
                      <Badge
                        badgeContent={sale.activities.length}
                        color="primary"
                      >
                        <SurfingIcon />
                      </Badge>
                    </Box>
                    <MyPopover
                      id={"activities" + sale.id}
                      items={sale.activities}
                    />
                  </TableCell>
                  <TableCell align="center">
                    <Typography variant="h6">{sale.price}€</Typography>
                  </TableCell>
                  <TableCell align="center">
                    <StateIcon state={sale.state} />
                  </TableCell>
                  <TableCell align="center">
                    {sale.state === "CREATED" && (
                      <Button>
                        <FormattedMessage id="project.sales.salesTable.blockSale" />
                      </Button>
                    )}
                    {sale.state === "BLOCKED" && (
                      <Button>
                        <FormattedMessage id="project.sales.salesTable.paySale" />
                      </Button>
                    )}
                    {sale.state === "PAID" && (
                      <Button>
                        <FormattedMessage id="project.sales.salesTable.watchBill" />
                      </Button>
                    )}
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </Paper>
      {(isAgente || isGerente) && (
        <Fab
          sx={{ position: "fixed", bottom: 50, right: 50 }}
          color="primary"
          aria-label="add"
          component={Link}
          to="/create-sale"
        >
          <AddIcon />
        </Fab>
      )}
    </React.Fragment>
  );
};

export default FindSalesResult;
