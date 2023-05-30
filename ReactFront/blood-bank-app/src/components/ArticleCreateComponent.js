import React from "react";
import { Box, TextField, Button } from "@mui/material";

export default class ArticleCreateComponent extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            title: '',
            description: '',
            content: '',
            writeDate: '',
            lastEditDate: '',
            doctorUuid: this.props.doctorUuid,
            likes:0
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentDidMount(){
        const now = new Date();
        const options = { year: 'numeric', month: '2-digit', day: '2-digit', timeZone: 'Europe/Bucharest' };
        const dateString = now.toLocaleDateString('en-GB', options).split('/').reverse().join('-');
        this.setState({
            // title: this.state.title,
            // description: this.state.description,
            // content: this.state.content,
            writeDate: dateString,
            lastEditDate: dateString,
            // doctorUuid: this.state.doctorUuid,
            // likes: this.state.likes
        })
    }

    handleChange(event) {
        const { name, value } = event.target;
        this.setState({
            [name]: value
        });
        this.componentDidMount();
        console.log(this.state)
    }

    handleSubmit(){
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                title: this.state.title,
                description: this.state.description,
                content: this.state.content,
                doctorUuid: this.state.doctorUuid,
                writeDate: this.state.writeDate,
                lastEditDate: this.state.lastEditDate,
                likes: this.state.likes
            })
        };
        console.log('Body: ' + requestOptions.body)
        fetch("http://localhost:8080/article", requestOptions)
            .then(res => {
                if (res.ok) {
                    console.log("Article created");
                    console.log(res.data)
                    alert("Article created");
                }
                else {
                    console.log("Error in article creation: " + res.status);
                    alert("Error in article creation: " + res.status);
                }
            }
        )
    }

    render() {
        return (
          <Box sx={{ p: 3 }} align="center">
            <h2>Create an article</h2>
            <TextField
              id="title"
              name="title"
              label="Title"
              value={this.state.title}
              onChange={this.handleChange}
              sx={{ width: "100%" }}
            />
            <br />
            <TextField
              id="description"
              name="description"
              label="Description"
              placeholder="Placeholder"
              multiline
              value={this.state.description}
              onChange={this.handleChange}
              sx={{ width: "100%" }}
            />
            <br />
            <TextField
              id="content"
              name="content"
              label="Content"
              placeholder="Placeholder"
              multiline
              value={this.state.content}
              onChange={this.handleChange}
              sx={{ width: "100%" }}
            />
            <br />
            <Button
                variant="contained"
                onClick={() => this.handleSubmit()}
                sx={{ width: "100%" }}
            >
                Submit article
            </Button>
          </Box>
        );
    }      
}
