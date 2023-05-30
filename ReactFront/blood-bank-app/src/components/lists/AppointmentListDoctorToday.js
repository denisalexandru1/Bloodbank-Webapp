import React from "react";
import { Box, Button } from "@mui/material";
import { DataGrid } from "@mui/x-data-grid";

export default class AppointmentListDoctorToday extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            centerUuid: props.centerUuid,
            appointments: []
        };

        this.handleConfirm = this.handleConfirm.bind(this);
    }

    componentDidMount() {
        const now = new Date();
        const options = { year: 'numeric', month: '2-digit', day: '2-digit', timeZone: 'Europe/Bucharest' };
        const dateString = now.toLocaleDateString('en-GB', options).split('/').reverse().join('-');
        fetch("http://localhost:8080/appointment/" + dateString + "/" + this.state.centerUuid)
            .then(res => {
                if (res.ok) {
                    res.json().then(body => {
                        console.log(body);
                        this.setState({
                            appointments: body
                        })
                    })
                }
                else {
                    console.log("Error in appointment fetch: " + res.status);
                    alert("Error in appointment fetch: " + res.status);
                }
            }
        )
    }

    handleConfirm(appointment) {
        const requestOptions = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                donor: appointment.donor,
                date: appointment.date,
                center: appointment.center,
                validated: true })
        };

        console.log(appointment)
        fetch("http://localhost:8080/appointment/" + appointment.id, requestOptions)
            .then(res => {
                console.log("RES:" + res.data)
                if (res.ok) {
                    console.log("Appointment confirmed");
                    alert("Appointment confirmed");
                    this.componentDidMount();
                }
                else {
                    console.log("Error in appointment confirmation: " + res.status);
                    alert("Error in appointment confirmation: " + res.status);
                }
            }
        )
    }


    render() {
        const columns = [
            { field: 'donorFirstName', headerName: 'Donor First Name', width: 200 },
            { field: 'donorLastName', headerName: 'Donor Last Name', width: 200 },
            { field: 'donorBloodType', headerName: 'Donor Blood Type', width: 200 },
            { field: 'date', headerName: 'Date', width: 200 },
            { field: 'validated', headerName: 'Validated', width: 200},
            { field: 'action', headerName: 'Action', width: 400,
            renderCell: (params) => {
                const donor = params.row.donor;
                const disabled = params.row.validated;
                return (
                    <>
                        <Button
                            variant="contained"
                            color="success"
                            onClick={() => this.handleConfirm(params.row)}
                            disabled={disabled}
                        >
                        Confirm appointment
                        </Button>
                    </>
                );
            }
        }];

        const rows = this.state.appointments.map(appointment => ({  id: appointment.uuid, 
                                                                    donorFirstName: appointment.donor.firstName, 
                                                                    donorLastName: appointment.donor.lastName, 
                                                                    donorBloodType: appointment.donor.bloodType, 
                                                                    date: appointment.date, 
                                                                    validated: appointment.validated, 
                                                                    donor: appointment.donor,
                                                                    center: appointment.center }));
        return (
            <Box sx={{ p: 3 }}>
                <h1>Today's Appointments</h1>
                <DataGrid
                    columns={columns}
                    rows={rows}
                    pageSize={10}
                    rowsPerPageOptions={[10]}
                    autoHeight
                />
            </Box>
        );  
    }
}
