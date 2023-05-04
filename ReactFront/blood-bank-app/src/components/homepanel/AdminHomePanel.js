import React from 'react';
import { Typography, Box, TextField, Button } from '@mui/material';
import UserLoginPanel from '../registerlogin/UserLoginPanel';
import AdminDashboard from '../dashboard/AdminDashboard';

export default class AdminPanel extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      loggedIn: false
    };
  }

  setLoggedIn = () => {
    this.setState({
      loggedIn: true
    });
  };
  
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
        <Box sx={{ p: 3 }}>
          {!this.state.loggedIn ?
           (<UserLoginPanel userType="Admin" setLoggedIn={this.setLoggedIn}/>)
          : (<AdminDashboard />)}
        </Box>
      </Box>
    );
  }
}
