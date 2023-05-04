import React from "react";
import { Box, Grid } from "@mui/material";
import AppointmentListDoctorToday from "../lists/AppointmentListDoctorToday";
import AppointmentListDoctorTotal from "../lists/AppointmentListDoctorTotal";

export default class DoctorDashboard extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            email: props.email,
            password: props.password,
            uuid: '',
            firstName: '',
            lastName: '',
            cnp: '',
            center: null,
        };
    }

    componentDidMount() {

        fetch("http://localhost:8080/doctor/email/" + this.state.email)
            .then(res => {
                if (res.ok) {
                    res.json().then(body => {
                        console.log(body);
                        this.setState({
                            uuid: body.uuid,
                            firstName: body.firstName,
                            lastName: body.lastName,
                            cnp: body.cnp,
                            center: body.center
                        })
                    })
                }
                else {
                    console.log("Error in doctor fetch: " + res.status);
                    alert("Error in doctor fetch: " + res.status);
                }
            })
    }

    handleConfirmAppointment(appointment) {
    }

    
    render() {
        return (
        <Box>
            {this.state.uuid ==='' ?<div></div> :
            <Box sx={{ p: 3 }}>
                <h1>Welcome back, Dr. {this.state.firstName}!</h1>
                <h2>Center: {this.state.center.name}</h2>
                <AppointmentListDoctorToday centerUuid={this.state.center.uuid}/>
                <AppointmentListDoctorTotal centerUuid={this.state.center.uuid}/>
            </Box>
            }
        </Box>
        );
    }
    }
