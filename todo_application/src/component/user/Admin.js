import React, { Component } from 'react'
import ApiService from "../../service/ApiService";

class Admin extends Component{

    constructor(props) {
        super(props)
        this.state = {
            user: [],
            message: null
        }
        
        this.deactivate = this.deactivate.bind(this);
        this.activate = this.activate.bind(this);
        this.reloadtaskList = this.reloadtaskList.bind(this);
        this.logout = this.logout.bind(this);
        this.report = this.report.bind(this);
    }
    logout(){
        this.props.history.push('/Signin');
    }
    
    componentDidMount() {
        
        this.reloadtaskList();
    }

    reloadtaskList() {
        
        
        ApiService.fetchUsers()
            .then((res) => {
                console.log(res.data)
                this.setState({user: res.data.result})
        });
     }

   
    deactivate(id) {

        document.getElementById("A").removeAttribute('disabled');
        document.getElementById("D").disabled = true;

        ApiService.deactivate(id)
        .then((res)=>{
            console.log(res.data.result)
            this.reloadtaskList();

        })
    }

    activate(id) {
        document.getElementById("D").removeAttribute('disabled');
        document.getElementById("A").disabled = true;
        ApiService.activate(id)
        .then((res)=>{
            console.log(res.data.result)
            this.reloadtaskList();

        })
        
    }
    report(){
        this.props.history.push('/Report');

    }

    render() {
         
        
        return (
            <div>
                <h2 className="text-center">Admin page</h2>
                <button className="btn btn-primary" style={{width:'100px'}} onClick={()=>this.logout()}>Log out</button>
                <button className="btn btn-primary" style={{width:'100px'}} onClick={() => this.report()}>Report</button>
            
                
                <table className="table table-striped">
                    <thead>
                        <tr>
                            {/* <th className="hidden">Id</th> */}
                            
                            <th>User_id</th>
                            <th>User__FirstName</th>
                            <th>User_lastname</th>
                            <th>User_activeStatus</th>
                            <th>Activate</th>
                            <th>Deactivate</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.user.map( u =>
                                    <tr key={u.id}>
                                        
                                        <td>{u.id}</td>
                                        <td>{u.firstName}</td>
                                        <td>{u.lastName}</td>
                                        <td>{u.isactivate}</td>
                                       <td> <button className="btn btn-success" id= "A" onClick={() => this.activate(u.id)} style={{marginLeft: '20px'}}>Activate</button>
                                        </td>
                                        
                                        <td>
                                            <button className="btn btn-danger" id = "D" onClick={() => this.deactivate(u.id)}>deactivate</button>
                                        </td>
                                    </tr>
                            )
                        }
                        
                    </tbody>
                </table>
                

            </div>
        );
    }

}

export default Admin;