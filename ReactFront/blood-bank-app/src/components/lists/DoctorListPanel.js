import React, { Component } from 'react';
import { DataGrid } from '@mui/x-data-grid';
import { Button, MenuItem, Select, Box, Grid, TextField } from '@mui/material';

export default class DoctorList extends Component {
  constructor(props) {
    super(props);
    this.state = {
      doctors: [],
      centers: [],
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
    fetch('http://localhost:8080/doctor')
      .then(res => res.json())
      .then(data => this.setState({ doctors: data }))
      .catch(err => console.log(err));

    fetch('http://localhost:8080/center')
      .then(res => res.json())
      .then(data => this.setState({ centers: data }))
      .catch(err => console.log(err));
  }

  handleDeleteDoctor(uuid) {
    fetch(`http://localhost:8080/doctor/${uuid}`, {
      method: 'DELETE'
    })
      .then(res => {
        if (res.ok) {
          this.setState(prevState => ({
            doctors: prevState.doctors.filter(doctor => doctor.uuid !== uuid)
          }));
          console.log('Doctor deleted successfully');
        } else {
          console.log('Error deleting doctor');
        }
      })
      .catch(err => console.log(err));
  }

  handleEditDoctor(doctor) {
    const { doctors } = this.state;
    const updatedDoctors = doctors.map(d => {
      if (d.uuid === doctor.uuid) {
        return {
          ...doctor,
          center: {
            ...doctor.center
          }
        };
      }
      return d;
    });

    const requestOptions = {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(updatedDoctors.find(d => d.uuid === doctor.uuid))
    };

    fetch(`http://localhost:8080/doctor/${doctor.uuid}`, requestOptions)
      .then(res => {
        if (res.ok) {
          console.log('Doctor edited successfully');
        } else {
          console.log('Error editing doctor');
        }
      })
      .catch(err => console.log(err));
  }

  handleAddDoctor() {
    const {
      newDoctorEmail,
      newDoctorPassword,
      newDoctorFirstName,
      newDoctorLastName,
      newDoctorCnp,
      newDoctorCenter
    } = this.state;

    const requestOptions = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        email: newDoctorEmail,
        password: newDoctorPassword,
        firstName: newDoctorFirstName,
        lastName: newDoctorLastName,
        cnp: newDoctorCnp,
        center: {
          uuid: newDoctorCenter
        }
      })
    };

    fetch('http://localhost:8080/doctor', requestOptions)
      .then(res => {
        if (res.ok) {
          console.log('Doctor added successfully');
          this.componentDidMount();
        } else {
          console.log('Error adding doctor');
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
    const { doctors, centers } = this.state;

    const columns = [
      { field: 'id', headerName: 'ID', editable: true, width: 100 },
      { field: 'email', headerName: 'Email', editable: true, width: 200 },
      { field: 'password', headerName: 'Password', editable: true, width: 150 },
      { field: 'firstName', headerName: 'First Name', editable: true, width: 150 },
      { field: 'lastName', headerName: 'Last Name', editable: true, width: 150 },
      { field: 'cnp', headerName: 'CNP', editable: true, width: 150 },
      {
        field: 'center',
        headerName: 'Center',
        width: 250,
        renderCell: params => (
          <Select
            value={params.row.center.uuid}
            onChange={event => {
              const { value } = event.target;
              const updatedDoctors = doctors.map(doctor =>
                doctor.uuid === params.row.id ? { ...doctor, center: { ...params.row.center, uuid: value } } : doctor
              );
              this.setState({ doctors: updatedDoctors });
            }}
          >
            {centers.map(center => (
              <MenuItem key={center.uuid} value={center.uuid}>
                {center.name}
              </MenuItem>
            ))}
          </Select>
        )
      },
      {
        field: 'actions',
        headerName: 'Actions',
        sortable: false,
        filterable: false,
        width: 200,
        renderCell: params => (
          <>
            <Button
              variant="contained"
              color="primary"
              onClick={() => this.handleEditDoctor(params.row)}
            >
              Edit
            </Button>
            <Button
              variant="contained"
              color="error"
              onClick={() => this.handleDeleteDoctor(params.row.id)}
            >
              Delete
            </Button>
          </>
        )
      }
    ];

    const rows = doctors.map(doctor => ({ id: doctor.uuid, ...doctor }));

    return (
      <Box>
        <h1>Doctors</h1>
        <DataGrid
          columns={columns}
          rows={rows}
          pageSize={10}
          rowsPerPageOptions={[10]}
          autoHeight
        />
        <br />
        <h2>Add Doctor</h2>
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
          <Select
            sx={{
              m: 1,
              width: '220px',
              height: '56px'
            }}
            label="Center"
            name='newDoctorCenter'
            onChange={this.handleChange}
            value={this.state.newDoctorCenter}
            variant="outlined"
            required
          >
            {centers.map(center => (
              <MenuItem key={center.uuid} value={center.uuid}>{center.name}</MenuItem>
            ))}
          </Select>
        </Grid>
        <br/>
        <Button variant="contained" color="success" onClick={this.handleAddDoctor}>
          Add Doctor
        </Button>
      </Box>
    );
  }
}
