import React, { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { FormattedMessage } from "react-intl";
import { useHistory } from "react-router-dom";

import { Errors } from "../../common";
import * as actions from "../actions";
import * as actionsProducts from "../../products/actions";

import Box from "@mui/material/Box";
import Stepper from "@mui/material/Stepper";
import Step from "@mui/material/Step";
import StepLabel from "@mui/material/StepLabel";
import Button from "@mui/material/Button";
import { Grid, Typography, Divider } from "@mui/material";

import Pack from "./Pack";
import { Step1 } from "./Steps/Step1";
import { Step2 } from "./Steps/Step2";
import { Step3 } from "./Steps/Step3";

const steps = [
  <FormattedMessage id="project.packs.CreatePack.step1" />,
  <FormattedMessage id="project.packs.CreatePack.step2" />,
  <FormattedMessage id="project.packs.CreatePack.step3" />,
  <FormattedMessage id="project.packs.CreatePack.step4" />,
];

const CreatePack = () => {
  const dispatch = useDispatch();
  const history = useHistory();

  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const image = "";
  const [imagePreview, setImagePreview] = useState(image);

  const [price, setPrice] = useState(0);
  const [duration, setDuration] = useState(0);
  const [numPersons, setNumPersons] = useState("");

  const [activities, setActivities] = useState([]);
  const [accommodations, setAccommodations] = useState([]);
  const [transports, setTransports] = useState([]);
  const [travels, setTravels] = useState([]);

  const [transportsSelected, setTransportsSelected] = useState([]);
  const [accommodationsSelected, setAccommodationsSelected] = useState([]);
  const [activitiesSelected, setActivitiesSelected] = useState([]);
  const [travelsSelected, setTravelsSelected] = useState([]);

  const [backendErrors, setBackendErrors] = useState(null);
  const [backendErrorsActivities, setBackendErrorsActivities] = useState(null);
  const [backendErrorsAccommodations, setBackendErrorsAccommodations] =
    useState(null);
  const [backendErrorsTransports, setBackendErrorsTransports] = useState(null);
  const [backendErrorsTravels, setBackendErrorsTravels] = useState(null);

  useEffect(() => {
    dispatch(
      actionsProducts.getActivities(
        (activities) => setActivities(activities),
        (errors) => setBackendErrorsActivities(errors)
      )
    );
    dispatch(
      actionsProducts.getAccommodations(
        (accommodations) => setAccommodations(accommodations),
        (errors) => setBackendErrorsAccommodations(errors)
      )
    );
    dispatch(
      actionsProducts.getTransports(
        (transports) => setTransports(transports),
        (errors) => setBackendErrorsTransports(errors)
      )
    );
    dispatch(
      actionsProducts.getTravels(
        (travels) => setTravels(travels),
        (errors) => setBackendErrorsTravels(errors)
      )
    );
  }, []);

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

  const handleChangeAccommodations = (e) => {
    const {
      target: { value },
    } = e;

    setAccommodationsSelected(value);
  };

  const handleChangeTravels = (e) => {
    const {
      target: { value },
    } = e;

    setTravelsSelected(value);
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

  const calculatePrice = () => {
    let price = 0;

    transportsSelected.map((transport) => (price = transport.price + price));
    accommodationsSelected.map(
      (accommodation) => (price = accommodation.price + price)
    );
    activitiesSelected.map((activity) => (price = activity.price + price));
    travelsSelected.map((travel) => (price = travel.price + price));

    return price.toFixed(2);
  };

  const [activeStep, setActiveStep] = React.useState(0);

  const handleNext = () => {
    if (activeStep !== steps.length - 1) {
      setActiveStep((prevActiveStep) => prevActiveStep + 1);
    } else {
      dispatch(
        actions.createPack(
          {
            title: name.trim(),
            description: description.trim(),
            image: imagePreview,
            price: price,
            duration: duration,
            persons: numPersons,
            accommodations: accommodationsSelected,
            transports: transportsSelected,
            travels: travelsSelected,
            activities: activitiesSelected,
          },
          (pack) => history.push("/packs"),
          (errors) => setBackendErrors(errors)
        )
      );
    }
  };

  const handleBack = () => {
    setActiveStep((prevActiveStep) => prevActiveStep - 1);
  };

  const haveErrors = () => {
    if (activeStep === 0) {
      if (name === null || name.trim() === "") return true;
      if (description === null || description.trim() === "") return true;
      if (imagePreview === "") return true;
    }
    if (activeStep === 1) {
      if (
        accommodationsSelected.length +
          transportsSelected.length +
          travelsSelected.length +
          activitiesSelected.length <
        1
      )
        return true;
    }
    if (activeStep === 2) {
      if (duration < 1 || duration > 30000) return true;
      if (price < 1 || price > 30000) return true;
      if (numPersons === "") return true;
    }
    return false;
  };

  return (
    <Box
      sx={{
        width: "100%",
      }}
    >
      <Grid className="row" marginTop={4}>
        <Grid
          item
          display={activeStep !== 3}
          xs={12}
          lg={activeStep === 3 ? 12 : 8}
        >
          <Box sx={{ width: "90%" }}>
            <Stepper activeStep={activeStep}>
              {steps.map((label, index) => {
                const stepProps = {};
                const labelProps = {};
                return (
                  <Step key={index} {...stepProps}>
                    {/*StepIcon*/}
                    <StepLabel {...labelProps}>{label}</StepLabel>
                  </Step>
                );
              })}
            </Stepper>
            <Box
              sx={{
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
              }}
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
                  accommodations={accommodations}
                  accommodationsSelected={accommodationsSelected}
                  activities={activities}
                  activitiesSelected={activitiesSelected}
                  transports={transports}
                  transportsSelected={transportsSelected}
                  travels={travels}
                  travelsSelected={travelsSelected}
                  handleChangeAccommodations={handleChangeAccommodations}
                  handleChangeActivities={handleChangeActivities}
                  handleChangeTransports={handleChangeTransports}
                  handleChangeTravels={handleChangeTravels}
                  errorsTranports={backendErrorsTransports}
                  errorsActivities={backendErrorsActivities}
                  errorsAccommodations={backendErrorsAccommodations}
                  backendErrorsTravels={backendErrorsTravels}
                  backendErrorsTransports={backendErrorsTransports}
                  backendErrorsActivities={backendErrorsActivities}
                  backendErrorsAccommodations={backendErrorsAccommodations}
                  setBackendErrorsActivities={setBackendErrorsActivities}
                  setBackendErrorsAccommodations={
                    setBackendErrorsAccommodations
                  }
                  setBackendErrorsTransports={setBackendErrorsTransports}
                  setBackendErrorsTravels={setBackendErrorsTravels}
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
            </Box>
            {activeStep !== 3 && (
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

                <Button disabled={haveErrors()} onClick={handleNext}>
                  {activeStep === steps.length - 1 ? (
                    <FormattedMessage id="project.global.buttons.save" />
                  ) : (
                    <FormattedMessage id="project.global.buttons.next" />
                  )}
                </Button>
              </Box>
            )}
          </Box>
        </Grid>
        {activeStep === 3 ? (
          <React.Fragment />
        ) : (
          <Grid
            item
            sx={{
              marginLeft: 1,
              marginRight: 5,
              display: { xs: "none", lg: "block" },
            }}
          >
            <Divider orientation="vertical" />
          </Grid>
        )}

        <Grid
          item
          xs={12}
          lg={activeStep === 3 ? 12 : 3}
          sx={{
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
          }}
        >
          <Box>
            <Box
              sx={{
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
              }}
            >
              {activeStep === 3 ? (
                <Box>
                  <Typography
                    variant="h5"
                    sx={{
                      width: "100%",
                      textAlign: "center",
                      marginBottom: "15px",
                    }}
                  >
                    <FormattedMessage id="project.packs.CreatePack.save" />
                  </Typography>
                  <Typography
                    variant="body2"
                    sx={{
                      width: "100%",
                      textAlign: "center",
                      marginBottom: "15px",
                    }}
                  >
                    <FormattedMessage id="project.packs.ERP" />{" "}
                    {calculatePrice()}
                    {" €"}
                  </Typography>
                </Box>
              ) : (
                <Box>
                  <Typography variant="h5">
                    <FormattedMessage id="project.packs.CreatePack.previsualization" />
                  </Typography>
                  <Typography variant="body2">
                    <FormattedMessage id="project.packs.ERP" />{" "}
                    {calculatePrice()}
                    {" €"}
                  </Typography>
                </Box>
              )}
            </Box>
            <Box
              sx={{
                marginTop: "1%",
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
                  persons: numPersons,
                  accommodations: accommodationsSelected,
                  transports: transportsSelected,
                  activities: activitiesSelected,
                  travels: travelsSelected,
                }}
                creating={true}
              />
              <Errors
                errors={backendErrors}
                onClose={() => setBackendErrors(null)}
              />
            </Box>
          </Box>
        </Grid>
        <Grid item xs={12}>
          {activeStep === 3 && (
            <Box
              sx={{
                width: "90%",
                display: "flex",
                flexDirection: "row",
                pt: 2,
              }}
            >
              <Button
                color="inherit"
                disabled={activeStep === 0}
                onClick={handleBack}
                sx={{ mr: 1 }}
              >
                <FormattedMessage id="project.global.buttons.back" />
              </Button>
              <Box sx={{ flex: "1 1 auto" }} />

              <Button disabled={haveErrors()} onClick={handleNext}>
                {activeStep === steps.length - 1 ? (
                  <FormattedMessage id="project.global.buttons.save" />
                ) : (
                  <FormattedMessage id="project.global.buttons.next" />
                )}
              </Button>
            </Box>
          )}
        </Grid>
      </Grid>
    </Box>
  );
};

export default CreatePack;
