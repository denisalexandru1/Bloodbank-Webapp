import React from 'react';
import {Box} from '@mui/material';
import UserLoginPanel from '../registerlogin/UserLoginPanel';
import DoctorDashboard from '../dashboard/DoctorDashboard';

export default class  DoctorPanel extends React.Component {
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

  setLoggedUser(doctor){
    console.log("Doctor: " + doctor.email + " " + doctor.password);
    this.setState({
      loggedEmail: doctor.email,
      loggedPassword: doctor.password
    }, () => {
      console.log("logged email: " + this.state.loggedEmail);
    });
  }

  render() {
    return (
      <Box 
        sx={{
          display: 'flex',
          justifyContent: 'center',
          alignItems: 'flex-start',
          height: '100vh'
        }}
      >
        <Box sx = {{p: 3}}>
          {!this.state.loggedIn ? (
            <UserLoginPanel userType="Doctor" setLoggedIn={this.setLoggedIn} setLoggedUser={this.setLoggedUser}/>
          ) 
          : (<DoctorDashboard email={this.state.loggedEmail} password={this.state.loggedPassword} />)}
        </Box>
      </Box>
    );
  } 
}
