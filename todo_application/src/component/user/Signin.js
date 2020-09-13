import React, { Component } from 'react';
import ApiService from '../../service/ApiService';


class Signin extends Component {
    constructor(props)
    {
        super(props)
        this.state = {
            email:"",
            password:"",
            emailerror:"",
            u:{}
            
            
        }
    }
    ve=()=>
    {
        
       if(!(this.state.email.match(/^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$/)))
      {
        //e.perventDeflaut(false);
        this.setState({
        emailerror : "invalid email",
        })
        return false;
      }
      else
      {
        this.setState({
          emailerror : "",
          })
        return true;
      }
    };
    HandleData = (e) =>
    {
       this.setState({
       [e.target.name]: e.target.value,
     });
   };
   SubmitData=(e)=>
   {
        e.preventDefault()
       
       if(this.ve())
       {
         if(this.state.email=="admin@gmail.com" && this.state.password=="admin@ADMIN1")
         {
          this.props.history.push(`/Admin`);
         }
         else{
            
        
        
            let user = {email:this.state.email,password:this.state.password};
            ApiService.checkuser(user)
            .then(res => {
                console.log(res.data.result)
                this.setState({u : res.data.result});

              
                if(this.state.u.isactivate==0)
                {
                  alert(" your account is deactive ");
                }
                else if(this.state.u.id==0)
                {
                  alert(" user not registered");
                }
                else{
                this.props.history.push(`/users/${this.state.u.id}`);
                }
            });
          }
       }
    }
    render() {
        const {email,password} = this.state;
        return (
            <div style={{ textAlign: "center" }} className="a">
                
            <h1>SIGN IN </h1>
            <center>
            <form onSubmit={this.SubmitData}>
              <table>
              <tr>
                <td><center> <div className='form-group col-md-10'>
                            <label className='float-left block-text text-darken-2 s'>Registered email id<span aria-hidden='true' style={{color:'red'}}> *</span></label>
                            <input type="text" required className='form-control' name="email" 
                            value={email} placeholder="enter the registered email" onChange={this.HandleData}/>
                        </div>
                        <div style={{textAlign:"center" ,fontSize:12,color:"red"}}>
                            {this.state.emailerror}
                        </div></center></td>
                </tr>
              <tr>
                <td><center> <div className='form-group col-md-10'>
                            <label className='float-left block-text text-darken-2 s'>PASSWORD<span aria-hidden='true' style={{color:'red'}}> *</span></label>
                            <input type="password" required className='form-control' name='password' 
                            value={password} placeholder="password" onChange={this.HandleData}/>
                        </div>
                        
                </center></td></tr>
                               <tr><td><center><button type="submit" className="btn btn-success"> LOG IN </button></center></td></tr>
                 <tr><td><a href="add-user" style={{textDecoration:"none"}}>NEW_ USER?</a>
                 </td></tr>
                
         </table>
         </form>
         <br></br><br></br>

         </center>
                
            </div>
        );
    }
}

export default Signin;