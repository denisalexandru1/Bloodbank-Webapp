import React from 'react';
import { DataGrid } from '@mui/x-data-grid';
import {Box, Button} from '@mui/material';

export default class AppointmentListDonor extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            appointments: [],
            donorUuid: this.props.donorUuid
        }
    }

    componentDidMount(){
        fetch("http://localhost:8080/appointment/donor/" + this.state.donorUuid)
            .then(res => res.json())
            .then(data => {this.setState({appointments: data})
             console.log("Appointments: " , data)})
            .catch(err => console.log(err));
    }

    handleRefresh(){
        this.componentDidMount();
    }

    render(){
        const columns = [
            {field: 'centerName', headerName: 'Center Name', width: 200},
            {field: 'date', headerName: 'Date', width: 200},
            {field: 'dalidated', headerName: 'Validated', width: 200,
            renderCell: (params) => (
                <strong>
                    {params.value ? 'Yes' : 'No'}
                </strong>
            )}
        ];

        const rows = this.state.appointments.map(appointment => ({id: appointment.uuid, centerName: appointment.center.name, date: appointment.date, dalidated: appointment.validated}));
        return(
            <Box sx={{p:3}}>
                <h1>My Appointments</h1>
                <DataGrid
                    columns={columns}
                    rows={rows}
                    pageSize={10}
                    rowsPerPageOptions={[10]}
                    autoHeight
                />
                <Button variant="contained" onClick={() => this.handleRefresh()}>
                    Refresh appointments
                </Button>
            </Box>
        );
    }
}