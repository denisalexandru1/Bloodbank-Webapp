import React from "react";
import { Box, Button, TextField } from "@mui/material";
import { DataGrid } from "@mui/x-data-grid";

export default class AppointmentListDoctorTotal extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            appointments: [],
            centerUuid: this.props.centerUuid,
            page: 0,
            pageSize: 10
        }

        this.handleChange = this.handleChange.bind(this);
        this.handleConfirm = this.handleConfirm.bind(this);
    }

    componentDidMount(){
        if(this.pageSize !== 0 || this.pageSize !== undefined){
            fetch("http://localhost:8080/appointment/center/" + this.state.centerUuid +
            "?page=" + this.state.page + "&size=" + this.state.pageSize)
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
    }

    handleChange(event) {
        const { name, value } = event.target;
        this.setState({
          [name]: value
        });
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

    render(){
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
                <h1>All appointments for this donation center</h1>
                <DataGrid
                    columns={columns}
                    rows={rows}
                    pageSize={this.state.pageSize}
                    autoHeight
                />
                <br></br>
                <TextField
                    label="Page"
                    id="outlined-size-small"
                    name="page"
                    onChange={this.handleChange}
                    value={this.state.page}
                    defaultValue="Small"
                    size="small"
                />
                <TextField
                    label="Centers per page"
                    id="outlined-size-small"
                    name="pageSize"
                    onChange={this.handleChange}
                    value={this.state.pageSize}
                    defaultValue="Small"
                    size="small"
                />  
                <Button
                    variant="contained"
                    color="success"
                    onClick={() => this.componentDidMount()}
                >
                    Refresh appointments
                </Button>
            </Box>
        );
    }
}