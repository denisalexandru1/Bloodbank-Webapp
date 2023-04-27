import React from "react";
import { DataGrid } from "@mui/x-data-grid";
import { Box, Button  } from "@mui/material";
import { DemoContainer } from '@mui/x-date-pickers/internals/demo';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';


export default class ClinicList extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            centers: []
        };
        this.handleSchedule = this.handleSchedule.bind(this);
    }

    componentDidMount(){    
        fetch("http://localhost:8080/center")
            .then(res => res.json())
            .then(data => {this.setState({centers: data})
             console.log("Centers: " , data)})
            .catch(err => console.log(err));
    }

    handleSchedule(center){
    }

    render(){
        const columns = [
            {field: 'centerName', headerName: 'Center Name', width: 200},
            {field: 'address', headerName: 'Address', width: 200},
            {field: 'startHour', headerName: 'Start Hour', width: 200},
            {field: 'endHour', headerName: 'End Hour', width: 200},
            {field: 'actions', headerName: 'Actions', width: 600, 
            renderCell: (params) => {
                const center = params.row;
                return (
                    <>
                    <Button variant="contained" color="primary" onClick={() => this.handleSchedule(center)}>
                        Schedule an appointment
                    </Button>
                    <LocalizationProvider dateAdapter={AdapterDayjs}>
                        <DemoContainer components={['DatePicker']}>
                            <DatePicker label="Basic date picker" />
                        </DemoContainer>
                    </LocalizationProvider>
                    </>
                )}
            }
        ]

        const rows = this.state.centers.map(center => ({id:center.uuid, centerName : center.name, address: center.address, startHour: center.startHour, endHour: center.endHour, ...center}));
            return(
            <Box sx={{ p: 3 }}>
                <h1>Donation Centers</h1>
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