import React from 'react';
import { Box, Grid } from '@mui/material';
import DoctorList from '../lists/DoctorListPanel';

export default class AdminDashboard extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      email: props.email,
      userType: props.userType
    };
  }

  render() {
    return (
        <Box sx={{ p: 3 }}>
            <DoctorList />  
        </Box>
    );
  }
}