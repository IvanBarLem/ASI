import React from "react";
import { Box, Fab, Grid } from "@mui/material";
import Pack from "./Pack";
import AddIcon from "@mui/icons-material/Add";
import { Link } from "react-router-dom";

const FindPacks = () => {
  const elements = [
    {
      id: 1,
      image:
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRJ1Opg--WLZ2r5OxT9UekFiO9vuqNO2MKWKA&usqp=CAU",
      title: "Paquete Bienvenida",
      description: "Descripción del paquete mucho más larga de lo normal",
      price: 400,
      duration: 7,
      numPersons: "Parejas",
      products: [
        { id: 1, name: "Viaje" },
        { id: 2, name: "Alojamiento" },
        { id: 3, name: "Transporte" },
        { id: 4, name: "Actividad1" },
        { id: 5, name: "Actividad2" },
      ],
    },
    {
      id: 2,
      image:
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTZajwjWBpAx0Vor93o0BVbvXyn0Sszq-6esQ&usqp=CAU",
      title: "Paquete Bienvenida",
      description: "Descripción del paquete",
      price: 200,
      duration: 7,
      numPersons: "Familias",
      products: [
        { id: 1, name: "Viaje" },
        { id: 2, name: "Alojamiento" },
        { id: 3, name: "Actividad1" },
        { id: 4, name: "Actividad2" },
      ],
    },
    {
      id: 3,
      image:
        "https://whc.unesco.org/uploads/thumbs/activity_477-1200-630-20210726084543.jpg",
      title: "Paquete Bienvenida",
      description: "Descripción del paquete",
      price: 212,
      duration: 7,
      numPersons: "Amigos",
      products: [
        { id: 1, name: "Viaje" },
        { id: 2, name: "Alojamiento" },
        { id: 3, name: "Transporte" },
        { id: 4, name: "Actividad1" },
      ],
    },
    {
      id: 4,
      image:
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQCzneLNF8bv8k5nfhrJjTL7FydCBhmpMf1jA&usqp=CAU",
      title: "Paquete Bienvenida",
      description: "Descripción del paquete",
      price: 20,
      duration: 7,
      numPersons: "Otro",
      products: [
        { id: 1, name: "Viaje" },
        { id: 2, name: "Alojamiento" },
        { id: 3, name: "Transporte" },
        { id: 4, name: "Actividad1" },
      ],
    },
    {
      id: 5,
      image:
        "https://www.photopills.com/sites/default/files/tutorials/2020/landscape-cover-.jpg",
      title: "Paquete Bienvenida",
      description: "Descripción del paquete",
      price: 5200,
      duration: 7,
      numPersons: "Individual",
      products: [
        { id: 1, name: "Viaje" },
        { id: 2, name: "Alojamiento" },
        { id: 3, name: "Transporte" },
        { id: 4, name: "Actividad1" },
      ],
    },
  ];

  return (
    <React.Fragment>
      <Box>
        <Grid container spacing={4}>
          {elements.map((row) => (
            <Pack key={row.id} item={row} />
          ))}
        </Grid>
      </Box>
      <Fab
        sx={{ position: "fixed", bottom: 50, right: 50 }}
        color="primary"
        aria-label="add"
        component={Link}
        to="/create-pack"
      >
        <AddIcon />
      </Fab>
    </React.Fragment>
  );
};

export default FindPacks;
