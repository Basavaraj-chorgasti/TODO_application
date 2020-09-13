import React, { Component } from 'react'
import ApiService from "../../service/ApiService";

class EditTask extends Component {

    constructor(props){
        super(props);
        this.state ={
            id: '',
            taskname: '',
            taskd: '',
            
            uid:'',
            message:null
        }
        this.saveUser = this.saveUser.bind(this);
        this.loadUser = this.loadUser.bind(this);
    }

    componentDidMount() {
        this.loadUser();
    }

    loadUser() {
        ApiService.fetchTaskById(this.props.match.params.id)
            .then((res) => {
                let task = res.data.result;
                 this.setState({
                id: task.id,
                taskname:task.taskname,
                taskd:task.taskd,
                
                uid:task.uid
                }) 
            });
    }

    onChange = (e) =>
        this.setState({ [e.target.name]: e.target.value });

    saveUser = (e) => {
        e.preventDefault();
        let task = {id: this.state.id, taskname: this.state.taskname, taskd: this.state.taskd,uid:this.state.uid};
        ApiService.editTask(task)
            .then(res => {
               /*  this.setState({message : 'User added successfully.'});
                this.props.history.push('/users'); */
                console.log(res)
                console.log(res.data.result)
                this.setState({message : res.data.result});
                // alert(this.state.message)
                this.props.history.push(`/users/${this.state.uid}`);
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

                   

                  
                    <button className="btn btn-success" onClick={this.saveUser}>Save</button>
                </form>
            </div>
        );
    }
}

export default EditTask;