import React from "react";
import { withRouter, Link } from "react-router-dom";
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import { Box, CardActionArea, CardActions, Button, Divider } from '@mui/material';

class ArticlesPanel extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
    articles: []
    };
  }

  componentDidMount() {
    fetch("http://localhost:8080/article")
      .then(res => res.json())
      .then(data => this.setState({ articles: data }))
      .catch(err => console.log(err));
  }

  render() {
    const { articles } = this.state;

    return (
      <Box>
        {articles.map((article) => (
          <Card key={article.uuid} sx={{ maxWidth: "100%" }}>
            <CardActionArea component="a" href={`/article/${article.uuid}`}>              <CardContent>
                <Typography gutterBottom variant="h5" component="div">
                  {article.title}
                </Typography>
                <Typography variant="body2" color="text.secondary">
                  {article.description}
                </Typography>
              </CardContent>
            </CardActionArea>
            <h5 align = "center">{article.likes} people liked this</h5>
            <Divider />
          </Card>
        ))}
      </Box>
    );
  }
}

export default withRouter(ArticlesPanel);
