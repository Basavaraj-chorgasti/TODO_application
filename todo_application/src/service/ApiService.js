import axios from 'axios';

//const USER_API_BASE_URL = 'http://localhost:8080/users';
const USER_API_BASE_URL = 'http://localhost:8080/ReactServletDBInt/MyServlet';

class ApiService {

    fetchUsers() {
        return axios.get('http://localhost:8080/ReactAppInt/GetAllUser');
    }
    fetchtask(uid) {
        return axios.get('http://localhost:8080/ReactAppInt/GetAllTask?uid='+ uid);
    }
    updatestatus(id)
    {
        return axios.get('http://localhost:8080/ReactAppInt/UpdateStatus?id='+ id); 
    }

    // fetchUserById(userId) {
    //     return axios.get('http://localhost:8080/ReactAppInt/EditUser?userId='+ userId);
    // }
    deactivate(id){
        return axios.get('http://localhost:8080/ReactAppInt/Deactivate?id='+ id); 

    }
    total(){
        return axios.get('http://localhost:8080/ReactAppInt/TotalReport'); 

    }
    activate(id){
        return axios.get('http://localhost:8080/ReactAppInt/Activate?id='+ id); 

    }

    fetchTaskById(id) {
        return axios.get('http://localhost:8080/ReactAppInt/EditTask?id='+ id);
    }

    /* deleteUser(userId) {
        return axios.delete('http://localhost:8080/ReactAppInt/DeleteSingleUser', userId);
    } */
    // deleteUser(Id) {
    //     return axios.get('http://localhost:8080/ReactAppInt/DeleteSingleUser?userId='+ Id);
    // }
    deleteTask(Id) {
        return axios.get('http://localhost:8080/ReactAppInt/DeleteSingleTask?id='+ Id);
    }
    Email(email){
        return axios.post('http://localhost:8080/ReactAppInt/EMAIL',email);
    }

    addUser(user) {
        return axios.post('http://localhost:8080/ReactAppInt/MyServlet',user);
    }
    AddTask(task) {
        return axios.post('http://localhost:8080/ReactAppInt/AddNewTask',task);
    }
    checkuser(user){
        return axios.post('http://localhost:8080/ReactAppInt/CheckUser',user);
    }

 /*    editUser(user) {
        return axios.put(USER_API_BASE_URL + '/' + user.id, user);
    }
 */
// editUser(user) {
//     return axios.post('http://localhost:8080/ReactAppInt/UpdateUser',user);
// }
editTask(task){
       return axios.post('http://localhost:8080/ReactAppInt/UpdateTask',task);
}
}

export default new ApiService();