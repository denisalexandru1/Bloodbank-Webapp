import React from 'react';
import { Typography, Box, TextField, Button } from '@mui/material';

export default class UserLoginPanel extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      email: '',
      password: '',
      userType: props.userType,
    };
    this.logInListener = this.logInListener.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }

  logInListener() {
    const requestOptions = {
      headers: {
        'Content-Type': 'application/json'
      },
      method: 'POST',
      body: JSON.stringify({
        email: this.state.email,
        password: this.state.password
      })
    };

    fetch(`http://localhost:8080/login/${(this.state.userType.toLowerCase())}`, requestOptions)
      .then(res => {
        if (res.ok) {
          res.json().then(body => {
            console.log(body);
            if (body.email === this.state.email && body.password === this.state.password) {
              console.log("Logged in successfully");
              alert("Logged in successfully");
              this.props.setLoggedIn();
              this.props.setLoggedUser(body);
            }
            else {
              console.log("Invalid email or password");
              alert("Invalid email or password");
            }
          })
          .catch(err => {
            console.log(err);
          })
        }
        else {
          console.log("Error");
          alert("Error");
        }
      })
  }

  
  handleChange(event) {
    const { name, value } = event.target;
    this.setState({
      [name]: value
    });
  }

  render() {
    return (
      <Box sx={{ p: 3 }}>
        <Typography variant="h4" sx={{ mb: 2 }}>
          {this.state.userType} Panel
        </Typography>
        <Typography variant="h6" sx={{ mb: 2 }}>
          Log In
        </Typography>
        <Box sx={{ display: 'flex', flexDirection: 'column', maxWidth: '400px' }}>
          <TextField
            label="Email"
            name="email"
            onChange={this.handleChange}
            value={this.state.email}
            variant="outlined"
            sx={{ mb: 2 }}
          />
          <TextField
            label="Password"
            name="password"
            type="password"
            onChange={this.handleChange}
            value={this.state.password}
            variant="outlined"
            sx={{ mb: 2 }}
          />
          <Button variant="contained" onClick={this.logInListener}>
            Log In
          </Button>
        </Box>
      </Box>
    );
  }
}
