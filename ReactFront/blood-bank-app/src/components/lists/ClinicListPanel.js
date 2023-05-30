import React from "react";
import { DataGrid } from "@mui/x-data-grid";
import { Box, Button, Checkbox  } from "@mui/material";
import { DemoContainer } from '@mui/x-date-pickers/internals/demo';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';


export default class ClinicList extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            centers: [],
            selectedDate: null,
            donorUuid: this.props.donorUuid,
            donor: {
                uuid: this.props.donorUuid,
                firstName: '',
                lastName: '',
                email: '',
                password: '',
                bloodType: '',
                smsReminder: false
            }
        };
        this.handleDateChange = this.handleDateChange.bind(this);
        this.handleSchedule = this.handleSchedule.bind(this);
    }

    componentDidMount(){    
        fetch("http://localhost:8080/center")
            .then(res => res.json())
            .then(data => {this.setState({centers: data})
             console.log("Centers: " , data)})
            .catch(err => console.log(err));

        const requestOptions = {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
            //mode: 'no-cors'
            };

        if(this.state.donorUuid !== ''){
            fetch("http://localhost:8080/donor/" + this.state.donorUuid, requestOptions)
                .then(res => {
                    if(res.ok){
                        res.json().then(body => {
                            console.log(body);
                            this.setState({
                                donor: body
                            })
                        })
                        .then(() => console.log(this.state.donor))
                    }
                    else{
                        console.log("Error in donor fetch: " + res.status);
                        console.log(this.state.donorUuid)
                    }
                })
            .catch(err => console.log(err));
        }
    }

    handleDateChange(date) {
        this.setState({ selectedDate: date });
        const dateString = date.format("YYYY-MM-DD");
        const promises = this.state.centers.map((center) => {
            return fetch(`http://localhost:8080/appointment/${dateString}/${center.uuid}`)
                .then((res) => res.json())
                .then((data) => {
                    const availableAppointments = center.maxDonors - data.length;
                    const updatedCenter = { ...center, availableAppointments };
                    return updatedCenter;
                })
                .catch((err) => console.log(err));
        });
        Promise.all(promises).then(updatedCenters => {
            this.setState({ centers: updatedCenters });
        });
    }

    handleSchedule(center){
        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                donor: this.state.donor,
                center: center,
                date: this.state.selectedDate.format('YYYY-MM-DD'),
                validated: false,
            }) 
        };
        fetch("http://localhost:8080/appointment", requestOptions)
            .then(res => {
                if(res.ok){
                    alert("Appointment scheduled successfully!");
                }
                else{
                    alert("Error in appointment scheduling");
                }
            })
            .catch(err => console.log(err));
        this.handleDateChange(this.state.selectedDate);
        this.componentDidMount();
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
                const disabled = center.availableAppointments <= 0;
                return (
                  <>
                    <Button
                      variant="contained"
                      color="primary"
                      onClick={() => this.handleSchedule(center)}
                      disabled={disabled}
                    >
                      Schedule an appointment
                    </Button>
                  </>
                );
              }              
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
                <LocalizationProvider dateAdapter={AdapterDayjs}>
                    <DemoContainer components={['DatePicker']}>
                        <DatePicker
                            label="Pick a date"
                            value={this.state.selectedDate}
                            onChange={this.handleDateChange}
                        />
                    </DemoContainer>
                </LocalizationProvider>
            </Box>
        );
    }
}