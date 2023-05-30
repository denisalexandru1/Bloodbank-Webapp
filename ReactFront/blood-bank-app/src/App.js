import React from 'react';
import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom';
import { AppBar, Toolbar, Typography, Button } from '@mui/material';
import Home from './components/Home';
import DonorPanel from './components/homepanel/DonorHomePanel';
import DoctorPanel from './components/homepanel/DoctorHomePanel';
import AdminPanel from './components/homepanel/AdminHomePanel';
import ArticlesPanel from './components/homepanel/ArticlesPanel';
import ArticleView from './components/ArticleView';

function App() {
  return (
    <Router>
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Blood Bank
          </Typography>
          <Button href = "/" color="inherit">Home</Button>
          <Button href = "/donor-panel" color="inherit">Donor Panel</Button>
          <Button href = "/doctor-panel" color="inherit">Doctor Panel</Button>
          <Button href = "/admin-panel" color="inherit">Admin Panel</Button>
          <Button href = "/articles-panel" color="inherit">News and Blogs</Button>
        </Toolbar>
      </AppBar>
      <Switch>
        <Route exact path="/" component={Home} />
        <Route path="/donor-panel" component={DonorPanel} />
        <Route path="/doctor-panel" component={DoctorPanel} />
        <Route path="/admin-panel" component={AdminPanel} />
        <Route path="/articles-panel" component = {ArticlesPanel} />
        <Route path="/article/:uuid" render={(props) => <ArticleView articleUuid={props.match.params.uuid} />} />
        <Route path="*" component={() => "404 NOT FOUND"} />
      </Switch>
    </Router>
  );
}

export default App;
