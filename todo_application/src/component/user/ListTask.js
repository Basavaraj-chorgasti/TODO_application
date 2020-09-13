import React, { Component } from 'react'
import ApiService from "../../service/ApiService";

class ListTask extends Component {

    constructor(props) {
        super(props)
        this.state = {
            tasks: [],
            message: null
        }
        this.deletetask = this.deletetask.bind(this);
        this.edittask = this.edittask.bind(this);
        this.addtaskr = this.addtask.bind(this);
        this.reloadtaskList = this.reloadtaskList.bind(this);
        this.logout = this.logout.bind(this);
        this.comTask = this.comTask.bind(this);
    }
    logout(){
        this.props.history.push('/Signin');
    }
    comTask(){
        var arr = document.getElementsByName("check");
        for(var a = 0;a<arr.length;a++)
        {
            if(arr[a].checked)
            {
                ApiService.updatestatus(arr[a].value)
                .then((res) => {
                    console.log(res.data)
                    window.location.reload(false)
                
            });
            }
        }
        
            

    }

    componentDidMount() {
        
        this.reloadtaskList();
    }

    reloadtaskList() {
        let uid = this.props.match.params.uid;
        console.log({uid})
        ApiService.fetchtask(uid)
            .then((res) => {
                console.log(res.data)
                this.setState({tasks: res.data.result})
        });
     }

    deletetask(id) {
        ApiService.deleteTask(id)
           .then(res => {
               console.log(res)
               if(res.statusText==="OK"){
               this.setState({message : 'Task deleted successfully.'});
            //    alert(this.state.message)
               console.log(this.state.users)
               this.setState({tasks: this.state.tasks.filter(task => task.id !== id)});
            }else{
                this.setState({message : 'unable  to delete Task'});
            //    alert(this.state.message)
               this.reloadtaskList();
            }

           })

    }

    edittask(id) {
        
        this.props.history.push(`/edit-task/${id}`);
    }

    addtask() {
    
        this.props.history.push(`/add-task/${this.props.match.params.uid}`);
    }

    render() {
         console.log(this.state.tasks.map(t=>t.id))
        return (
            <div>
                <h2 className="text-center">User Details</h2>
                <button className="btn btn-primary" style={{width:'100px'}} onClick={()=>this.logout()}>Log out</button>
                <button className="btn btn-primary" style={{width:'100px'}} onClick={() => this.addtask()}> Add Task</button>
                <button type="submit" className="btn btn-primary" style={{width:'100px'}} onClick={() => this.comTask()}>complete</button>
                
                <table className="table table-striped">
                    <thead>
                        <tr>
                            {/* <th className="hidden">Id</th> */}
                            <th></th>
                            <th> Task_Id</th>
                            <th>Task_Name</th>
                            <th>Task_Des</th>
                            <th>Task_status</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.tasks.map( task =>
                                    <tr key={task.id}>
                                        <td><input type="checkbox" name="check" value={task.id}></input></td>
                                        <td>{task.id}</td>
                                        <td>{task.taskname}</td>
                                        <td>{task.taskd}</td>
                                        <td>{task.tasks}</td>
                                        
                                        <td>
                                            <button className="btn btn-success" onClick={() => this.edittask(task.id)} style={{marginLeft: '20px'}}> Edit</button>
                                            <button className="btn btn-danger" onClick={() => this.deletetask(task.id)}> Delete</button>
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

export default ListTask;