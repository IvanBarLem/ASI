import {
  Grid,
  List,
  ListItem,
  TextField,
  Typography,
} from "@mui/material";
import React from "react";
import { FormattedMessage } from "react-intl";

export const Step1 = ({
  name,
  setName,
  description,
  setDescription,
  image,
  handleChangeImage,
}) => {
  return (
    <List sx={{ padding: 1 }}>
      <ListItem>
        <TextField
          id="name"
          label={<FormattedMessage id="project.global.fields.name" />}
          variant="outlined"
          value={name}
          onChange={(e) => setName(e.target.value)}
          size="small"
          required
          fullWidth
        />
      </ListItem>
      <ListItem>
        <TextField
          id="description"
          label={<FormattedMessage id="project.global.fields.description" />}
          variant="outlined"
          value={description}
          minRows={5}
          onChange={(e) => setDescription(e.target.value)}
          size="small"
          required
          multiline
          fullWidth
        />
      </ListItem>
      <ListItem>
        <Grid container spacing={2} direction="column">
          <Grid item>
            <Typography>
              <FormattedMessage id="project.packs.CreatePack.image" />:
            </Typography>
          </Grid>
          <Grid item>
            <input 
              accept="image/png, image/jpeg" 
              onChange={handleChangeImage} 
              type="file"
              required
            />
          </Grid>
        </Grid>
      </ListItem>
    </List>
  );
};
