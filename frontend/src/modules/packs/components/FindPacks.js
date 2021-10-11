import React from "react";
import {
	Typography,
	Box,
    Card,
    CardMedia,
    CardContent,
    Chip,
    Grid
} from "@mui/material";

const FindPacks = () => {
    const elements = [
        {
            id:1,
            image: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRJ1Opg--WLZ2r5OxT9UekFiO9vuqNO2MKWKA&usqp=CAU",
            title: "Paquete Bienvenida",
            products: ["Viaje", "Alojamiento" , "Transporte" , "Actividad1" , "Actividad2"]
        },
        {
            id:2,
            image: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTZajwjWBpAx0Vor93o0BVbvXyn0Sszq-6esQ&usqp=CAU",
            title: "Paquete Bienvenida",
            products: ["Viaje", "Alojamiento" , "Actividad1" , "Actividad2"]
        },
        {
            id:3,
            image: "https://whc.unesco.org/uploads/thumbs/activity_477-1200-630-20210726084543.jpg",
            title: "Paquete Bienvenida",
            products: ["Viaje", "Alojamiento" , "Transporte" , "Actividad1"]
        },
        {
            id:4,
            image: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQCzneLNF8bv8k5nfhrJjTL7FydCBhmpMf1jA&usqp=CAU",
            title: "Paquete Bienvenida",
            products: ["Viaje", "Alojamiento" , "Transporte" , "Actividad1"]
        },
        {
            id:5,
            image: "https://www.photopills.com/sites/default/files/tutorials/2020/landscape-cover-.jpg",
            title: "Paquete Bienvenida",
            products: ["Viaje", "Alojamiento" , "Transporte" , "Actividad1"]
        }
    ];

    return (
        <Box>
            <Grid container spacing={4}>
                {elements.map((row) => (
                    <Grid key={row.id} item xs={12} md={6} lg={4} xl={3}>
                        <Card sx={{ maxWidth: 360 }}>
                            <CardMedia
                                component="img"
                                height="140"
                                image={row.image}
                            />
                            <CardContent>
                                <Typography gutterBottom variant="h5" component="div">
                                    {row.title}
                                </Typography>
                                <Grid container spacing={1}>
                                    {row.products.map((product) => (
                                        <Grid key={row.id + product} item>
                                            <Chip label={product} variant="outlined" />
                                        </Grid>
                                    ))}
                                </Grid>
                            </CardContent>
                        </Card>
                    </Grid>
                ))}
            </Grid>
            
            
        </Box>
    );
}

export default FindPacks;