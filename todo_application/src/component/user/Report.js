import React, { Component } from 'react'
import ApiService from "../../service/ApiService";
import {Bar,Pie,Line} from 'react-chartjs-2';


class Report extends Component{

    constructor(props) {
        super(props)
        this.state = {
            chartdata:{},
            tolal:{}
        }
         this.logout = this.logout.bind(this);
         this.back = this.back.bind(this);

    
    }
    componentDidMount(){
        ApiService.total()
        .then(res => {
            console.log(res.data.result)
            this.setState({u : res.data.result});
        

        const obj={
            
                labels:['active_user','new_user','total_tasks'],
                datasets:[
                    {
                        label : ' in last seven days',
                        data:[
                            this.state.u.activeuser,this.state.u.newuser,this.state.u.totaltask
                        ],
                        backgroundColor:[
                            'rgba(255,99,132,0.6)',
                            'rgba(55,99,132,0.6)',
                            'rgba(265,99,132,0.6)',

                        ]
                    
                    }
                ]
            }
            this.setState({
                chartdata:obj,
            })
        });
    }
    logout(){
        this.props.history.push('/Signin');
    }
    back(){
        this.props.history.push('/Admin');
    }
    
    // componentDidMount(){

    // }
     render() {
         
        
        return (
           <div className="chart">
               <button className="btn btn-primary" style={{width:'100px'}} onClick={()=>this.logout()}>Log out</button>
               <button className="btn btn-primary" style={{width:'100px'}} onClick={()=>this.back()}>Back</button>

                <h2>Report in Graph</h2>
               <Bar
               data = {this.state.chartdata}
               options={{
                scales: {
                  yAxes: [{
                    ticks: {
                      beginAtZero: true
                    }
                  }]
                }
              }}
               />
            </div>
        );
    }

}

export default Report;