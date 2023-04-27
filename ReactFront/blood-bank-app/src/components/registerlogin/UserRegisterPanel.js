import React from 'react';
import { Typography, Box, TextField, Button } from '@mui/material';

export default class UserRegisterPanel extends React.Component {
    constructor(props) {
        super();
        this.state = {
            email: '',
            password: '',
            firstName: '',
            lastName: '',
            bloodType: '',
            userType: props.userType
        };
        this.registerListener = this.registerListener.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    registerListener() {
        const requestOptions = {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify({
                email: this.state.email,
                password: this.state.password,
                firstName: this.state.firstName,
                lastName: this.state.lastName,
                bloodType: this.state.bloodType
            })
        };

        fetch(`http://localhost:8080/${this.state.userType.toLowerCase()}/register`, requestOptions)
            .then(res => {
                if(res.ok){
                    console.log("Registered successfully");
                    alert("Registered successfully");
                }
                else{
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
        return(
            <Box sx={{ p: 3 }}>
                <Typography variant="h6" sx={{ mb: 2, mt: 4 }}>Sign Up</Typography>
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
                        <TextField 
                            label="First Name"
                            name="firstName"
                            onChange={this.handleChange}
                            value={this.state.firstName} 
                            variant="outlined" 
                            sx={{ mb: 2 }} 
                        />
                        <TextField 
                            label="Last Name"
                            name="lastName"
                            onChange={this.handleChange}
                            value={this.state.lastName} 
                            variant="outlined" 
                            sx={{ mb: 2 }} 
                        />
                        <TextField 
                            label="Blood Type"
                            name="bloodType"
                            onChange={this.handleChange}
                            value={this.state.bloodType} 
                            variant="outlined" 
                            sx={{ mb: 2 }} 
                        />
                        <Button variant="contained" onClick={this.registerListener}>
                            Sign Up
                        </Button>
                </Box>
            </Box>
        )
    }
}
