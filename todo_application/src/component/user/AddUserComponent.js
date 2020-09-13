import React, { Component } from 'react'
import ApiService from "../../service/ApiService";

class AddUserComponent extends Component{

    constructor(props) {
        super(props)
    
        this.state = {
             firstName:'',
             lastName:'',
             email:'',
             dob:'',
             gender:'',
             password:'',
             confirmpass:'',
             firstNameError:'',
             lastNameError:'',
             emailError:'',
             passwordError:'',
             message:null,
             otp:""
        }
    }
    validate=()=>{
        var isValid=true;
        // alert(isValid)
        if(this.state.firstName.length===0){
            this.setState({
                firstNameError:'Name should not be empty'
            })
            isValid=false
        }
        else if(this.state.firstName.length>0){
            this.setState({
                firstNameError:''
            })
            
        }
        if(this.state.lastName.length===0){
            this.setState({
                lastNameError:'Addess should not be empty'
            })
            isValid=false
        }
        else if(this.state.lastName.length>0){
            this.setState({
                lastNameError:''
            })
            
        }
        if(!this.state.email.match(/^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$/)){
            this.setState({
                emailError:'email should contain @ and .'
            })
            isValid=false
        }
        else if(this.state.email.match(/^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$/)){
            this.setState({
                emailError:''
            })
            
        }
        if(!this.state.password.match(/(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,32}/)){
            this.setState({
                passwordError:'password should contain 1 special symbol 1 capital and length 8 char'
            })
            isValid=false
        }
        else if(this.state.password.match(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,32}$/)){
                    this.setState({
                        passwordError:''
                    })
                    
        }
        if(this.state.password!=this.state.confirmpass){
            this.setState({
                passwordError:'password and confirm password not match'
            })
            isValid=false
        }
        else if(this.state.password=this.state.confirmpass){
            this.setState({
                passwordError:''
            })
            
        }
        return isValid
    }
    changeData=(e)=>{
        this.setState({
        [e.target.name]:e.target.value
            
        })
    }
    submitData=(e)=>
    {
        e.preventDefault();
        const validForm=this.validate();
        
        if(validForm==true)
        {
            let Email = {email:this.state.email}
            ApiService.Email(Email)
            .then(res=>{
                console.log(res.data.result)
                this.setState({otp : res.data.result});
                // alert(this.state.otp);
                this.adduser();
            });
        }
    }
           
          adduser=()=>{ 
            var num = prompt(" enter otp which as send to your email");
            if(this.state.otp==num)
            {
                alert(" email verified ");
                let user = {firstName:this.state.firstName,lastName:this.state.lastName,email:this.state.email,dob:this.state.dob,gender:this.state.gender,password:this.state.password};
                ApiService.addUser(user)
                .then(res => {
                console.log(res.data.result)
                this.setState({message : res.data.result});
                // alert(this.state.message)
                this.props.history.push('/');
            });
            }
            else{
                alert(" signin failed");
            }
          }
 
    
    
    render() {
        const {firstName,lastName,email,dob,gender,password,confirmpass}=this.state;
        return (
            <div>
                <div className="container">
                    <center>
                        <h3>Sign up</h3>
                    <form autoComplete='off'>
                        <div className='form-group col-md-10'>
                            <label className='float-left block-text text-darken-2 s'>First Name<span aria-hidden='true' style={{color:'red'}}> *</span></label>
                            <input type="text" required className='form-control' name='firstName' 
                            value={firstName} placeholder="First name" onChange={this.changeData}/>
                        </div>
                        <div style={{textAlign:"center" ,fontSize:12,color:"red"}}>
                            {this.state.firstNameError}
                        </div>
                        <div className='form-group col-md-10'>
                            <label className='float-left block-text text-darken-2 s'>Last Name<span aria-hidden='true' style={{color:'red'}}> *</span></label>
                            <input type="text" required className='form-control' 
                            value={lastName} name='lastName' placeholder="last name" onChange={this.changeData}/>
                        </div>
                        <div style={{textAlign:"center" ,fontSize:12,color:"red"}}>
                            {this.state.lastNameError}
                        </div>
                        <div className='form-group col-md-10'>
                            <label className='float-left block-text text-darken-2 s'>Email Id<span aria-hidden='true' style={{color:'red'}}> *</span></label>
                            <input type="text" required className='form-control'
                            value={email} name='email' placeholder="Email Id" onChange={this.changeData}/>
                        </div>
                        <div style={{textAlign:"center" ,fontSize:12,color:"red"}}>
                            {this.state.emailError}
                        </div>
                        <div className='form-group col-md-10'>
                            <label className='float-left block-text text-darken-2 s'>Date of birth<span aria-hidden='true' style={{color:'red'}}> *</span></label>
                            <input type="text" required className='form-control' 
                            value={dob} name='dob' placeholder="Date of birth(yyyy-mm-dd)" onChange={this.changeData}/>
                        </div>
                        <div className='form-group col-md-10'>
                        
            <label className='float-left block-text text-darken-2 s'> GENDER : <span aria-hidden='true' style={{color:'red'}}> *</span></label>
        <select value={gender} onChange={this.changeData} name="gender" required className='form-control'>
              <option value="select"> select</option>

              <option value="MALE"> MALE</option>
              <option value="FEMALE"> FEMALE</option>
              <option value="OTHRES"> OTHERS</option>
            </select>
            
        
                        </div>

                        <div className='form-group col-md-10'>
                            <label className='float-left block-text text-darken-2 s'>Password<span aria-hidden='true' style={{color:'red'}}> *</span></label>
                            <input type="password" required className='form-control' 
                            value={password} name='password' placeholder="Password" onChange={this.changeData}/>
                        </div>
                        <div style={{textAlign:"center" ,fontSize:12,color:"red"}}>
                            {this.state.passwordError}
                        </div>
                        <div className='form-group col-md-10'>
                            <label className='float-left block-text text-darken-2 s'>Confirm Password<span aria-hidden='true' style={{color:'red'}}> *</span></label>
                            <input type="password" required className='form-control' 
                            value={confirmpass} name='confirmpass' placeholder="Confirm Password" onChange={this.changeData}/>
                        </div>
                        <div style={{textAlign:"center" ,fontSize:12,color:"red"}}>
                            {this.state.passwordError}
                        </div>
                        <div>
                        
                             
                            <button type="button" className='btn btn-success m-2'onClick={this.submitData}>Sign Up</button>
                            </div>
                    </form>
                    </center>
                </div>
                
            </div>
        )
    }

}

export default AddUserComponent;