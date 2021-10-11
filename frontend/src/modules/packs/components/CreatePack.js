import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { FormattedMessage } from "react-intl";
import { useHistory } from "react-router-dom";

import { Errors } from "../../common";
import * as actions from "../actions";

import Box from "@mui/material/Box";
import Stepper from "@mui/material/Stepper";
import Step from "@mui/material/Step";
import StepLabel from "@mui/material/StepLabel";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import TextField from "@mui/material/TextField";
import { Grid, List, ListItem } from "@mui/material";

import { SelectComplement } from "./SelectComplement";

const steps = ["Datos básicos", "Complementos", "Características"];

const CreatePack = () => {
  const dispatch = useDispatch();
  const history = useHistory();

  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [imagen, setImagen] = useState(null);
  const [transportsSelected, setTransportsSelected] = useState([]);
  const [accomodationsSelected, setAccomodationsSelected] = useState([]);
  const [activitiesSelected, setActivitiesSelected] = useState([]);

  const transports = [
    { key: 0, name: "avion" },
    { key: 1, name: "coche" },
    { key: 2, name: "tren" },
    { key: 3, name: "metro" },
    { key: 4, name: "bus" },
  ];
  const viajes = [
    { key: 0, name: "avion1" },
    { key: 1, name: "avion2" },
    { key: 2, name: "avion3" },
    { key: 3, name: "avion4" },
    { key: 4, name: "avion5" },
  ];
  const accomodations = [
    { key: 0, name: "casa1" },
    { key: 1, name: "casa2" },
    { key: 2, name: "casa3" },
    { key: 3, name: "casa4" },
    { key: 4, name: "casa5" },
  ];
  const activities = [
    { key: 0, name: "senderismo" },
    { key: 1, name: "motos de agua" },
    { key: 2, name: "escalada" },
    { key: 3, name: "paseo" },
    { key: 4, name: "playa" },
  ];
  const handleDeleteTransport = (chipToDelete) => () => {
    console.log(chipToDelete);
    setTransportsSelected((transportsSelected) =>
      transportsSelected.filter((chip) => chip.key !== chipToDelete.key)
    );
  };

  const handleDeleteActivity = (chipToDelete) => () => {
    setActivitiesSelected((activitiesSelected) =>
      activitiesSelected.filter((chip) => chip.key !== chipToDelete.key)
    );
  };

  const handleDeleteAccomodation = (chipToDelete) => () => {
    setAccomodationsSelected((accomodationsSelected) =>
      accomodationsSelected.filter((chip) => chip.key !== chipToDelete.key)
    );
  };

  const handleChangeTransports = (event) => {
    const {
      target: { value },
    } = event;

    const newTransport = transports.filter(
      (transport) => transport.key === value[value.length - 1]
    )[0];

    setTransportsSelected((prevTransports) => [
      ...prevTransports,
      newTransport,
    ]);
  };

  const handleChangeActivities = (event) => {
    const {
      target: { value },
    } = event;

    const newActivity = activities.filter(
      (activity) => activity.key === value[value.length - 1]
    )[0];

    setActivitiesSelected((prevActivities) => [...prevActivities, newActivity]);
  };

  const handleChangeAccomodations = (event) => {
    const {
      target: { value },
    } = event;

    const newAccomodation = accomodations.filter(
      (accomodation) => accomodation.key === value[value.length - 1]
    )[0];

    setAccomodationsSelected((prevAccomodations) => [
      ...prevAccomodations,
      newAccomodation,
    ]);
  };

  const [activeStep, setActiveStep] = React.useState(0);
  const [skipped, setSkipped] = React.useState(new Set());

  const isStepOptional = (step) => {
    return step === 1;
  };

  const isStepSkipped = (step) => {
    return skipped.has(step);
  };

  const handleNext = () => {
    let newSkipped = skipped;
    if (isStepSkipped(activeStep)) {
      newSkipped = new Set(newSkipped.values());
      newSkipped.delete(activeStep);
    }

    setActiveStep((prevActiveStep) => prevActiveStep + 1);
    setSkipped(newSkipped);
  };

  const handleBack = () => {
    setActiveStep((prevActiveStep) => prevActiveStep - 1);
  };

  const handleSkip = () => {
    if (!isStepOptional(activeStep)) {
      // You probably want to guard against something like this,
      // it should never occur unless someone's actively trying to break something.
      throw new Error("You can't skip a step that isn't optional.");
    }

    setActiveStep((prevActiveStep) => prevActiveStep + 1);
    setSkipped((prevSkipped) => {
      const newSkipped = new Set(prevSkipped.values());
      newSkipped.add(activeStep);
      return newSkipped;
    });
  };

  const handleReset = () => {
    setActiveStep(0);
  };

  return (
    <Box sx={{ width: "100%" }}>
      <Stepper activeStep={activeStep}>
        {steps.map((label, index) => {
          const stepProps = {};
          const labelProps = {};
          if (isStepOptional(index)) {
            labelProps.optional = (
              <Typography variant="caption">Opcional</Typography>
            );
          }
          if (isStepSkipped(index)) {
            stepProps.completed = false;
          }
          return (
            <Step key={label} {...stepProps}>
              {/*StepIcon*/}
              <StepLabel {...labelProps}>{label}</StepLabel>
            </Step>
          );
        })}
      </Stepper>
      {activeStep === steps.length ? (
        <React.Fragment>
          <Typography sx={{ mt: 2, mb: 1 }}>
            All steps completed - you&apos;re finished
          </Typography>
          <Box sx={{ display: "flex", flexDirection: "row", pt: 2 }}>
            <Box sx={{ flex: "1 1 auto" }} />
            <Button onClick={handleReset}>Reset</Button>
          </Box>
        </React.Fragment>
      ) : (
        <Grid className="row" rowSpacing={2} columnSpacing={2}>
          <Grid item xs={6}>
            {activeStep === 0 && (
              <List sx={{ padding: 4 }}>
                <ListItem>
                  <TextField
                    id="name"
                    label={<FormattedMessage id="project.global.fields.name" />}
                    variant="outlined"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                    size="small"
                    //error={!validateEmail(email) && invalid}
                    required
                    fullWidth
                  />
                </ListItem>
                <ListItem>
                  <TextField
                    id="description"
                    label={
                      <FormattedMessage id="project.global.fields.description" />
                    }
                    variant="outlined"
                    value={description}
                    minRows={5}
                    onChange={(e) => setDescription(e.target.value)}
                    size="small"
                    //error={!validateEmail(email) && invalid}
                    required
                    multiline
                    fullWidth
                  />
                </ListItem>
                <ListItem>
                  <Grid className="col" container>
                    <Grid item>
                      <Typography>
                        <FormattedMessage id="project.global.fields.imagen" />
                      </Typography>
                    </Grid>
                    <Grid item>
                      <input
                        id="imagen"
                        label={
                          <FormattedMessage id="project.global.validator.required" />
                        }
                        value={imagen}
                        onChange={(e) => setImagen(e.target.value)}
                        //error={!validateEmail(email) && invalid}
                        required
                        type="file"
                      />
                    </Grid>
                  </Grid>
                </ListItem>
              </List>
            )}
            {activeStep === 1 && (
              <React.Fragment>
                <SelectComplement
                  options={transports}
                  selectedOptions={transportsSelected}
                  handleChange={handleChangeTransports}
                  handleDelete={handleDeleteTransport}
                  label={
                    <FormattedMessage id="project.global.validator.transports" />
                  }
                />
                <SelectComplement
                  options={accomodations}
                  selectedOptions={accomodationsSelected}
                  handleChange={handleChangeAccomodations}
                  handleDelete={handleDeleteAccomodation}
                  label={
                    <FormattedMessage id="project.global.validator.accomodations" />
                  }
                />
                <SelectComplement
                  options={activities}
                  selectedOptions={activitiesSelected}
                  handleChange={handleChangeActivities}
                  handleDelete={handleDeleteActivity}
                  label={
                    <FormattedMessage id="project.global.validator.activities" />
                  }
                />
              </React.Fragment>
            )}
          </Grid>
          <Grid
            item
            xs={2}
            sx={{
              display: "flex",
              justifyContent: "center",
              alignItems: "center",
            }}
          >
            Card
          </Grid>
        </Grid>
      )}
      <React.Fragment>
        <Box sx={{ display: "flex", flexDirection: "row", pt: 2 }}>
          <Button
            color="inherit"
            disabled={activeStep === 0}
            onClick={handleBack}
            sx={{ mr: 1 }}
          >
            Back
          </Button>
          <Box sx={{ flex: "1 1 auto" }} />
          {isStepOptional(activeStep) && (
            <Button color="inherit" onClick={handleSkip} sx={{ mr: 1 }}>
              Skip
            </Button>
          )}

          <Button onClick={handleNext}>
            {activeStep === steps.length - 1 ? "Finish" : "Next"}
          </Button>
        </Box>
      </React.Fragment>
    </Box>
  );
};

export default CreatePack;
