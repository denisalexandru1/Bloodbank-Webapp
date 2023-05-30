import { Button, TextField, Box, Grid, Switch} from '@mui/material';
import { Link } from 'react-router-dom';
import React from 'react';
import { DataGrid } from '@mui/x-data-grid';
import ClinicList from '../lists/ClinicListPanel';
import AppointmentListDonor from '../lists/AppointmentListDonor';

export default class DonorDashboard extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            email: this.props.email,
            password: this.props.password,
            uuid: '',
            firstName: '',
            lastName: '',
            bloodType: '',
            phone:'',
            smsReminder: false,
        };

        this.handleEditDonor = this.handleEditDonor.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleSmsChange = this.handleSmsChange.bind(this);
    }

    componentDidMount() {
        fetch("http://localhost:8080/donor/email/" + this.state.email)
            .then(res => {
                if (res.ok) {
                    res.json().then(body => {
                        console.log(body);
                        this.setState({
                            uuid: body.uuid,
                            firstName: body.firstName,
                            lastName: body.lastName,
                            bloodType: body.bloodType,
                            phone: body.phone,
                            smsReminder: body.smsReminder
                        })
                    })
                }
                else {
                    console.log("Error");
                    alert("Error");
                }
            })
    }

    handleEditDonor() {
        const requestOptions = {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'PUT',
            body: JSON.stringify({
                email: this.state.email,
                password: this.state.password,
                firstName: this.state.firstName,
                lastName: this.state.lastName,
                bloodType: this.state.bloodType,
                phone: this.state.phone,
                smsReminder: this.state.smsReminder
            })
        };

        fetch(`http://localhost:8080/donor/${this.state.uuid}`, requestOptions)
            .then(res => {
                if (res.ok) {
                    res.json().then(body => {
                        console.log(body);
                        alert("Account edited successfully");
                    })
                }
                else {
                    console.log("Error");
                    alert("Error");
                }
            })
    }
    
    handleDeleteDonor() {
        const requestOptions = {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'DELETE'
        };

        fetch(`http://localhost:8080/donor/${this.state.uuid}`, requestOptions)
            .then(res => {
                if (res.ok) {
                    alert("Account deleted successfully");
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
    
    handleSmsChange(){
        this.setState({
            smsReminder: !this.state.smsReminder
        })
    }
    
    render() {
        return (
            <Box>
                <Box sx = {{ p: 3}}>
                    <h1>Hello, {this.state.firstName}!</h1>
                    <Grid >
                        <TextField
                            label="First Name"
                            name="firstName"
                            value={this.state.firstName}
                            onChange={this.handleChange}
                            variant="outlined"
                        />
                        <TextField
                            label="Last Name"
                            name="lastName"
                            value={this.state.lastName}
                            onChange={this.handleChange}
                            variant='outlined'
                        />
                        <TextField
                            label="Blood Type"
                            name="bloodType"
                            value={this.state.bloodType}
                            onChange={this.handleChange}
                            variant='outlined'
                        />
                        <TextField
                            label="Password"
                            name="password"
                            type="password"
                            value={this.state.password}
                            onChange={this.handleChange}
                            variant='outlined'
                        />
                        <TextField
                            label="Phone Number"
                            name="phone"
                            value={this.state.phone}
                            onChange={this.handleChange}
                            variant='outlined'
                        />
                        <Switch 
                            name="smsReminder" 
                            checked={this.state.smsReminder}
                            onChange={this.handleSmsChange}
                            variant='outlined'
                        /> SMS Reminder for appointments
                        <br />
                        <br />
                        <Button variant="contained" color="primary" onClick={() => this.handleEditDonor()}>
                        Edit Account  
                        </Button>
                        <Button href = '/donor-panel' variant="contained" color="error" onClick={() => this.handleDeleteDonor()}>
                        Delete Account
                        </Button>
                    </Grid>
                </Box>
                {this.state.uuid ==='' ? <div></div> :
                <ClinicList donorUuid={this.state.uuid} />}
                {this.state.uuid ==='' ? <div></div> :
                <AppointmentListDonor donorUuid={this.state.uuid} />
                }
                 
            </Box>
        );
    }
}