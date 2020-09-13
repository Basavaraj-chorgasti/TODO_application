import React from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
// import ListUserComponent from "./component/user/ListUserComponent";
import AddUserComponent from "./component/user/AddUserComponent";
import EditUserComponent from "./component/user/EditUserComponent";
import Signin from './component/user/Signin';
import ListTask from './component/user/ListTask';
import EditTask from './component/user/EditTask';
import Addtask from './component/user/AddTask';
import Admin from './component/user/Admin';
import Report from './component/user/Report';

function App() {
  return (
      <div className="container">
          <Router>
              <div className="col-md-6">
                  <h1 className="text-center" style={style}>TODO Application</h1>
                  <Switch>
                      <Route path="/" exact component={Signin} />
                      <Route path="/Signin" exact component={Signin} />
                      <Route path="/Admin" exact component={Admin}/>
                      {/* <Route path="/users" component={ListUserComponent} /> */}
                      <Route path="/users/:uid" component={ListTask} />
                      <Route path="/add-user" component={AddUserComponent} />
                      <Route path="/edit-user" component={EditUserComponent} />
                      <Route path="/edit-task/:id" component={EditTask} />
                      <Route path="/add-task/:id" component={Addtask} />
                      <Route path = "/Report" exact component={Report}/>

                  </Switch>
                  
              </div>
          </Router>
      </div>
  );
}

const style = {
    color: 'red',
    margin: '10px'
}

export default App;
