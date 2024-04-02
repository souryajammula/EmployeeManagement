import {React, useEffect, useState} from 'react'
import { useNavigate } from 'react-router-dom'
import { deleteEmployee, listEmployee } from '../services/EmployeeService'
const ListEmployeeComponents = () => {
    const [employees, setEmployees] = useState([])
    const navigator = useNavigate();
    useEffect(()=>{
        getAllEmployees();
        

    },[])

    function getAllEmployees(){
        listEmployee().then((response)=>{
            setEmployees(response.data);
        }).catch(error=>{
            console.error(error);
        })
        
    }
    function removeEmployee(id){
        deleteEmployee(id).then((response)=>{
            console.log(response)
            getAllEmployees();
        }).catch((error)=> console.error(error));
        
    }
    function addNewEmployee(){
        navigator('/add-employer')

    }
    function updateEmployee(id){
        navigator(`/edit-employee/${id}`)
    }
  return (
    <div className='container'>
        <h2 className='text-center'> List of Employees</h2>
        <button className='btn btn-primary mb-2' onClick={addNewEmployee}>Add Employee</button>
        <table className='table table-striped table-boarded'>
            <thead>
                <tr>
                    <th>Employee Id</th>
                    <th>Employee FirstName</th>
                    <th>Employee LastName</th>
                    <th>Employee Email Id</th> 
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                {
                    employees.map((employee)=><tr key={employee.id}>
                        <td>{employee.id}</td>
                        <td>{employee.firstName}</td>
                        <td>{employee.lastName}</td>
                        <td>{employee.email}</td>
                        <td>
                            <button className='btn btn-info' onClick={()=>updateEmployee(employee.id)}>
                                Update 
                            </button>
                            <button className='btn btn-danger' style={{marginLeft:'10px'}} onClick={()=>removeEmployee(employee.id)}>
                                Delete
                            </button>
                        </td>

                    </tr>)
                }
            </tbody>
        </table>
    </div>
  )
}

export default ListEmployeeComponents
