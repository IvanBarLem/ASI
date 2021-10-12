import React, { useState } from "react";
//import { useDispatch } from "react-redux";
import { FormattedMessage } from "react-intl";
import { useHistory } from "react-router-dom";

//import { Errors } from "../../common";
//import * as actions from "../actions";

import Box from "@mui/material/Box";
import Stepper from "@mui/material/Stepper";
import Step from "@mui/material/Step";
import StepLabel from "@mui/material/StepLabel";
import Button from "@mui/material/Button";
import { Grid, Typography } from "@mui/material";

import Pack from "./Pack";
import { Step1 } from "./Steps/Step1";
import { Step2 } from "./Steps/Step2";
import { Step3 } from "./Steps/Step3";

const transports = [
  { id: 0, name: "avion" },
  { id: 1, name: "coche" },
  { id: 2, name: "tren" },
  { id: 3, name: "metro" },
  { id: 4, name: "bus" },
];
const trips = [
  { id: 0, name: "avion1" },
  { id: 1, name: "avion2" },
  { id: 2, name: "avion3" },
  { id: 3, name: "avion4" },
  { id: 4, name: "avion5" },
];
const accomodations = [
  { id: 0, name: "casa1" },
  { id: 1, name: "casa2" },
  { id: 2, name: "casa3" },
  { id: 3, name: "casa4" },
  { id: 4, name: "casa5" },
];
const activities = [
  { id: 0, name: "senderismo" },
  { id: 1, name: "motos de agua" },
  { id: 2, name: "escalada" },
  { id: 3, name: "paseo" },
  { id: 4, name: "playa" },
];

const steps = ["Datos básicos", "Complementos", "Características", "Resumen"];

const CreatePack = () => {
  //const dispatch = useDispatch();
  const history = useHistory();

  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const image = "";
  const [imagePreview, setImagePreview] = useState(image);

  const [price, setPrice] = useState(0);
  const [duration, setDuration] = useState(0);
  const [numPersons, setNumPersons] = useState("Ninguno");

  const [transportsSelected, setTransportsSelected] = useState([]);
  const [accomodationsSelected, setAccomodationsSelected] = useState([]);
  const [activitiesSelected, setActivitiesSelected] = useState([]);
  const [tripsSelected, setTripsSelected] = useState([]);

  const handleChangeTransports = (e) => {
    const {
      target: { value },
    } = e;

    setTransportsSelected(value);
  };

  const handleChangeActivities = (e) => {
    const {
      target: { value },
    } = e;

    setActivitiesSelected(value);
  };

  const handleChangeAccomodations = (e) => {
    const {
      target: { value },
    } = e;

    setAccomodationsSelected(value);
  };

  const handleChangeTrips = (e) => {
    const {
      target: { value },
    } = e;

    setTripsSelected(value);
  };

  const getBase64 = (inputFile) => {
    const temporaryFileReader = new FileReader();

    return new Promise((resolve, reject) => {
      temporaryFileReader.onerror = () => {
        temporaryFileReader.abort();
        reject(new DOMException("Problem parsing input file."));
      };

      temporaryFileReader.onload = () => {
        resolve(temporaryFileReader.result);
      };
      temporaryFileReader.readAsDataURL(inputFile);
    });
  };

  const handleChangeImage = (e) => {
    getBase64(e.target.files[0]).then((image) => setImagePreview(image));
  };

  const [activeStep, setActiveStep] = React.useState(0);

  const handleNext = () => {
    if (activeStep !== steps.length - 1) {
      setActiveStep((prevActiveStep) => prevActiveStep + 1);
    } else {
      //mandar a backend, si no hay errores hacer push, si los hay mostrar errores
      history.push("/packs");
    }
  };

  const handleBack = () => {
    setActiveStep((prevActiveStep) => prevActiveStep - 1);
  };

  return (
    <Box
      sx={{
        width: "100%",
      }}
    >
      <Stepper activeStep={activeStep}>
        {steps.map((label, index) => {
          const stepProps = {};
          const labelProps = {};
          return (
            <Step key={label} {...stepProps}>
              {/*StepIcon*/}
              <StepLabel {...labelProps}>{label}</StepLabel>
            </Step>
          );
        })}
      </Stepper>
      <Grid className="row" marginTop={4}>
        <Grid
          sx={{
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
          }}
          item
          display={activeStep !== 3}
          xs={12}
          md={6}
          lg={4}
        >
          {activeStep === 0 && (
            <Step1
              name={name}
              setName={setName}
              description={description}
              setDescription={setDescription}
              image={image}
              handleChangeImage={handleChangeImage}
            />
          )}
          {activeStep === 1 && (
            <Step2
              accomodations={accomodations}
              accomodationsSelected={accomodationsSelected}
              activities={activities}
              activitiesSelected={activitiesSelected}
              handleChangeAccomodations={handleChangeAccomodations}
              handleChangeActivities={handleChangeActivities}
              handleChangeTransports={handleChangeTransports}
              transports={transports}
              transportsSelected={transportsSelected}
              trips={trips}
              tripsSelected={tripsSelected}
              handleChangeTrips={handleChangeTrips}
            />
          )}
          {activeStep === 2 && (
            <Step3
              duration={duration}
              numPersons={numPersons}
              price={price}
              setDuration={setDuration}
              setNumPersons={setNumPersons}
              setPrice={setPrice}
            />
          )}
        </Grid>
        {activeStep === 3 && (
          <Typography
            variant="h5"
            sx={{ width: "100%", textAlign: "center", marginBottom: "15px" }}
          >
            ¿Desea guardar este pack?
          </Typography>
        )}
        <Grid
          item
          xs={12}
          md={activeStep === 3 ? 12 : 6}
          lg={activeStep === 3 ? 12 : 8}
          sx={{
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
          }}
        >
          <Pack
            item={{
              id: 0,
              image: imagePreview,
              title: name,
              description: description,
              price: price,
              duration: duration,
              numPersons: numPersons,
              products: tripsSelected
                .concat(transportsSelected)
                .concat(accomodationsSelected)
                .concat(activitiesSelected),
            }}
          />
        </Grid>
      </Grid>
      <Box sx={{ display: "flex", flexDirection: "row", pt: 2 }}>
        <Button
          color="inherit"
          disabled={activeStep === 0}
          onClick={handleBack}
          sx={{ mr: 1 }}
        >
          <FormattedMessage id="project.global.buttons.back" />
        </Button>
        <Box sx={{ flex: "1 1 auto" }} />

        <Button disabled={name.trim() === ""} onClick={handleNext}>
          {activeStep === steps.length - 1 ? (
            <FormattedMessage id="project.global.buttons.save" />
          ) : (
            <FormattedMessage id="project.global.buttons.next" />
          )}
        </Button>
      </Box>
    </Box>
  );
};

export default CreatePack;
