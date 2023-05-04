import React from 'react';
import { Box, Grid } from '@mui/material';
import UserLoginPanel from '../registerlogin/UserLoginPanel';
import UserRegisterPanel from '../registerlogin/UserRegisterPanel';
import DonorDashboard from '../dashboard/DonorDashboard';

export default class DonorPanel extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      loggedIn: false,
      loggedEmail: '',
      loggedPassword: ''
    };

    this.setLoggedIn = this.setLoggedIn.bind(this);
    this.setLoggedUser = this.setLoggedUser.bind(this);
  }

  setLoggedIn = () => {
    this.setState({
      loggedIn: true
    });
  };

  setLoggedUser(donor){
    console.log("Donor: " + donor.email + " " + donor.password);
    this.setState({
      loggedEmail: donor.email,
      loggedPassword: donor.password
    }, () => {
      console.log("logged email: " + this.state.loggedEmail);
    });
  }

  render() {
    return (
      <Box sx={{ p: 3 }}>
        {!this.state.loggedIn ? (
        <Grid container spacing={2}>
          <Grid item xs={6}>
            <UserLoginPanel userType="Donor" setLoggedIn={this.setLoggedIn} setLoggedUser={this.setLoggedUser}/>
          </Grid>
          <Grid item xs={6}>
            <UserRegisterPanel userType="Donor" />
          </Grid>
        </Grid>)
          : (<DonorDashboard email={this.state.loggedEmail} password={this.state.loggedPassword} />)}
      </Box>
    );
  }
}
