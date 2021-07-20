import React, { useEffect, useState } from 'react'
import MainService from '../services/MainService';

function ViewOneEmployee({ ...props }) {
    const [employee, setEmployee] = useState({});

    useEffect(() => {
        if(props.match){
            MainService.getEmployeeById(props.match.params.id)
            .then(res => {
                console.log(res.data)
                setEmployee(res.data)
            })
            .catch(err => {
                console.log(err)
            })
        }
    },[])




    return (
        <div>
            <h1>{employee.fname} {employee.lname}</h1>
            {
                employee.gender ? 
                <h3>Gender: {employee.gender}</h3>:""
            }
            <a 
                href={`/employees/${employee.id}/edit`}
            >
                Edit
            </a>
        </div>
    )
}

export default ViewOneEmployee
