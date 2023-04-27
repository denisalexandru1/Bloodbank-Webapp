import React from 'react';
import {Box} from '@mui/material';
import UserLoginPanel from './registerlogin/UserLoginPanel';

function DoctorPanel() {
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
        <UserLoginPanel userType="Doctor"/>
      </Box>
    </Box>
  );
}

export default DoctorPanel;
