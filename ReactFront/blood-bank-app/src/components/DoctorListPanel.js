import React from 'react';
import { Box, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Button, Grid, TextField } from '@mui/material';
import { DataGrid } from '@mui/x-data-grid';

export default class DoctorList extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      doctors: [],
      newDoctorEmail: '',
      newDoctorPassword: '',
      newDoctorFirstName: '',
      newDoctorLastName: '',
      newDoctorCnp: '',
      newDoctorCenter: null
      };
    this.handleChange = this.handleChange.bind(this);
    this.handleAddDoctor = this.handleAddDoctor.bind(this);
    this.handleDeleteDoctor = this.handleDeleteDoctor.bind(this);
    this.handleEditDoctor = this.handleEditDoctor.bind(this);
  }

  componentDidMount() {
    // Fetch list of doctors from server and update state
    fetch('http://localhost:8080/doctor')
      .then(res => res.json())
      .then(data => this.setState({ doctors: data }))
      .then(() => console.log(this.state.doctors))
      .catch(err => console.log(err));
  }

  handleDeleteDoctor(id) {
    // Send DELETE request to server to delete doctor with given id
    fetch(`http://localhost:8080/doctor/${id}`, {
      method: 'DELETE'
    })
      .then(res => {
        if (res.ok) {
          // Remove deleted doctor from state
          this.setState(prevState => ({
            doctors: prevState.doctors.filter(doctor => doctor.uuid !== id)
          }));
        }
        else {
          console.log('Error deleting doctor');
        }
      })
      .catch(err => console.log(err));
  }

  handleEditDoctor(doctor) {
    fetch(`http://localhost:8080/doctor/${doctor.uuid}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        email: doctor.email,
        password: doctor.password,
        firstName: doctor.firstName,
        lastName: doctor.lastName,
        cnp: doctor.cnp,
        center: doctor.center
      })
    })
      .then(res => {
        if (res.ok) {
          // Update doctor in state
          this.setState(prevState => ({
            doctors: prevState.doctors.map(doc => {
              if (doc.uuid === doctor.uuid) {
                return {
                  ...doc,
                  email: doctor.email,
                  password: doctor.password,
                  firstName: doctor.firstName,
                  lastName: doctor.lastName,
                  cnp: doctor.cnp,
                  center: doctor.center
                };
              }
              else {
                return doc;
              }
            })
          }));
          console.log('Doctor updated successfully');
        }
        else {
          console.log('Error updating doctor');
        }
      })
      .catch(err => console.log(err));
  }

  handleAddDoctor() {
    const requestOptions = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        email: this.state.newDoctorEmail,
        password: this.state.newDoctorPassword,
        firstName: this.state.newDoctorFirstName,
        lastName: this.state.newDoctorLastName,
        cnp: this.state.newDoctorCnp,
        center: this.state.newDoctorCenter
      })
    };
  
    fetch('http://localhost:8080/doctor', requestOptions)
      .then(res => {
        if (res.ok) {
          console.log('Doctor added successfully');
          alert('Doctor added successfully');
          this.componentDidMount();
        } else {
          console.log('Error adding doctor');
          alert('Error adding doctor');
        }
      })
      .catch(err => console.log(err));
  }
  
  
  handleChange(event) {
    const { name, value } = event.target;
    this.setState({
        [name]: value
    });
  }

  render() {
    const columns = [
      { field: 'id', headerName: 'Id', editable: true, width: 100 },
      { field: 'email', headerName: 'Email', editable: true, width: 200 },
      { field: 'password', headerName: 'Password', editable: true, width: 150 },
      { field: 'firstName', headerName: 'First Name', editable: true, width: 150 },
      { field: 'lastName', headerName: 'Last Name', editable: true, width: 150 },
      { field: 'cnp', headerName: 'CNP', editable: true, width: 150 },
      { field: 'center', headerName: 'Center', editable: true, width: 200 },
      {
        field: 'actions',
        headerName: 'Actions',
        sortable: false,
        filterable: false,
        width: 200,
        renderCell: (params) => {
          const doctor = params.row;
          return (
            <>
              <Button variant="contained" color="primary" onClick={() => this.handleEditDoctor(doctor)}>
                Edit
              </Button>
              <Button variant="contained" color="error" onClick={() => this.handleDeleteDoctor(doctor.id)}>
                Delete
              </Button>
            </>
          );
        }
      }
    ];

    const rows = this.state.doctors.map(doctor => ({ id: doctor.uuid, ...doctor }));

    return (
      <Box sx={{ p: 3 }}>
        <h1>Doctors</h1>
        <DataGrid
          columns={columns}
          rows={rows}
          pageSize={10}
          rowsPerPageOptions={[10]}
          autoHeight
        />
        <br/>
        <Grid>
          <TextField
            label="Email"
            name='newDoctorEmail'
            onChange={this.handleChange}
            value={this.state.newDoctorEmail}
            variant="outlined"
            required
          />
          <TextField
            label="Password"
            name='newDoctorPassword'
            onChange={this.handleChange}
            value={this.state.newDoctorPassword}
            variant="outlined"
            required
          />
          <TextField
            label="First Name"
            name='newDoctorFirstName'
            onChange={this.handleChange}
            value={this.state.newDoctorFirstName}
            variant="outlined"
            required
          />
          <br/>
          <br/>
          <TextField
            label="Last Name"
            name='newDoctorLastName'
            onChange={this.handleChange}
            value={this.state.newDoctorLastName}
            variant="outlined"
            required
          />
          <TextField
            label="CNP"
            name='newDoctorCnp'
            onChange={this.handleChange}
            value={this.state.newDoctorCnp}
            variant="outlined"
            required
          />
          <TextField
            label="Center"
            name='newDoctorCenter'
            onChange={this.handleChange}
            value={this.state.newDoctorCenter}
            variant="outlined"
          />
        </Grid>
        <br/>
        <Button variant="contained" color="success" onClick={this.handleAddDoctor}>
          Add Doctor
        </Button>
      </Box>
    );
  }
}
