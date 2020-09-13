import React, { Component } from 'react'
import ApiService from "../../service/ApiService";

class Addtask extends Component {

    constructor(props){
        super(props);
        this.state ={
        
            taskname: '',
            taskd: '',
            
            
            message:null
        }
        this.savetask = this.savetask.bind(this);
     }

  
    onChange = (e) =>
        this.setState({ [e.target.name]: e.target.value });

    savetask = (e) => {
        e.preventDefault();
        let task = { taskname: this.state.taskname, taskd: this.state.taskd,uid:this.props.match.params.id};
        console.log({task})
        ApiService.AddTask(task)
            .then(res => {
               /*  this.setState({message : 'User added successfully.'});
                this.props.history.push('/users'); */
                console.log(res)
                console.log(res.data.result)
                this.setState({message : res.data.result});
                // alert(this.state.message)
                this.props.history.push(`/users/${this.props.match.params.id}`);
            });
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Edit User</h2>
                <form>

                    <div className="form-group">
                        <label>Task name:</label>
                        <input type="text" placeholder="taskname" name="taskname" className="form-control" value={this.state.taskname} onChange={this.onChange}/>
                    </div>

                    <div className="form-group">
                        <label>Task Description:</label>
                        <input placeholder="task Description" name="taskd" className="form-control" value={this.state.taskd} onChange={this.onChange}/>
                    </div>

                   

                  
                    <button className="btn btn-success" onClick={this.savetask}>Save</button>
                </form>
            </div>
        );
    }
}

export default Addtask;