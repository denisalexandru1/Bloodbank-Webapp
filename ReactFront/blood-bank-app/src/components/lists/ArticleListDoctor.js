import React from "react";
import { Accordion, AccordionSummary, AccordionDetails, Box, Typography, TextField, Button } from "@mui/material";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";

export default class ArticleListDoctor extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      doctorUuid: this.props.doctorUuid,
      articles: []
    };
    this.handleTitleChange = this.handleTitleChange.bind(this);
    this.handleDescriptionChange = this.handleDescriptionChange.bind(this);
    this.handleContentChange = this.handleContentChange.bind(this);
    this.handleSaveChanges = this.handleSaveChanges.bind(this);
  }

  componentDidMount() {
    fetch('http://localhost:8080/article/doctor/' + this.state.doctorUuid)
      .then(res => res.json())
      .then(data => this.setState({ articles: data }))
      .catch(err => console.log(err));
  }

  handleTitleChange = (event, index) => {
    const { articles } = this.state;
    articles[index].title = event.target.value;
    this.setState({ articles });
  };

  handleDescriptionChange = (event, index) => {
    const { articles } = this.state;
    articles[index].description = event.target.value;
    this.setState({ articles });
  };

  handleContentChange = (event, index) => {
    const { articles } = this.state;
    articles[index].content = event.target.value;
    this.setState({ articles });
  };

  handleSaveChanges(article){
    const now = new Date();
    const options = { year: 'numeric', month: '2-digit', day: '2-digit', timeZone: 'Europe/Bucharest' };
    const dateString = now.toLocaleDateString('en-GB', options).split('/').reverse().join('-');
       
    const requestOptions = {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            uuid: article.uuid,
            title: article.title,
            description: article.description,
            content: article.content,
            writeDate: article.writeDate,
            lastEditDate: dateString,
            likes: article.likes,
            doctorUuid: article.doctorUuid
        })
    };
    console.log('Body: ' + requestOptions.body)
    fetch(`http://localhost:8080/article/${article.uuid}`, requestOptions)
        .then(res => {
            console.log(res);
            if (res.ok) {
                alert("Article updated successfully");
            }
        }
        )
        .catch(err => console.log(err));
    }


  render() {
    const { articles } = this.state;

    return (
      <Box>
        <h2 align="center">My articles</h2>
        {articles.map((article, index) => (
          <Accordion key={article.uuid}>
            <AccordionSummary expandIcon={<ExpandMoreIcon />}>
              <TextField
                fullWidth
                value={article.title}
                onChange={(event) => this.handleTitleChange(event, index)}
              />
            </AccordionSummary>
            <AccordionDetails>
              <div style={{ display: "flex", flexDirection: "column", gap: "8px" }}>
                <TextField
                  value={article.description}
                  onChange={(event) => this.handleDescriptionChange(event, index)}
                />
                <TextField
                  value={article.content}
                  onChange={(event) => this.handleContentChange(event, index)}
                  multiline
                  rows={4}
                />
              </div>
            </AccordionDetails>
            <Button
                variant="contained"
                color="primary"
                fullWidth
                onClick={() => this.handleSaveChanges(article)}
                >
            Save Changes
            </Button>
          </Accordion>
        ))}
        <Button
                variant="contained"
                color="primary"
                fullWidth
                onClick={() => this.componentDidMount()}
                >
            Refresh articles
            </Button>
      </Box>
    );
  }
}
