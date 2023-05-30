import React from "react";
import { Box, Button } from '@mui/material';

export default class ArticleView extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      articleUuid: this.props.articleUuid,
      article: {},
      doctor: {},
      liked: false // Initialize liked state as false
    };
    this.handleLike = this.handleLike.bind(this);
  }

  componentDidMount() {
    console.log('http://localhost:8080/article/' + this.state.articleUuid);
    fetch('http://localhost:8080/article/' + this.state.articleUuid)
      .then(res => res.json())
      .then(data => {
        this.setState({ article: data }, () => {
          this.handleGetDoctor(); 
        });
      })
      .catch(err => console.log(err));
  }

  handleGetDoctor() {
    fetch('http://localhost:8080/doctor/' + this.state.article.doctorUuid)
      .then(res => res.json())
      .then(data => this.setState({ doctor: data }))
      .catch(err => console.log(err));
  }

  handleLike() {
    this.setState({ liked: true });

    const requestOptions = {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        uuid: this.state.article.uuid,
        title: this.state.article.title,
        description: this.state.article.description,
        content: this.state.article.content,
        writeDate: this.state.article.writeDate,
        lastEditDate: this.state.article.lastEditDate,
        likes: this.state.article.likes + 1,
        doctorUuid: this.state.article.doctorUuid
      })
    };
    fetch(`http://localhost:8080/article/${this.state.article.uuid}`, requestOptions)
      .then(res => res.json())
      .then(data => {
        this.setState({ article: data });
        alert('You liked this article!');
      })
      .catch(err => console.log(err));
  }

  render() {
    return (
      <Box align="center">
        <h1>{this.state.article.title}</h1>
        <Box>
          <h5>Author: {this.state.doctor.firstName} {this.state.doctor.lastName}</h5>
          <h5>Posted on {this.state.article.writeDate}</h5>
          <h5>Last edited on {this.state.article.lastEditDate}</h5>
        </Box>
        <p>{this.state.article.content}</p>
        <Button
          variant="contained"
          color="primary"
          size="large"
          onClick={this.handleLike}
          disabled={this.state.liked} // Disable the button when liked is true
        >
          {this.state.liked ? 'Liked!' : 'I like this article'}
        </Button>
      </Box>
    );
  }
}
