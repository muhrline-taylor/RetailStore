import React, { useEffect, useState } from 'react';
import MainService from '../services/MainService';
import "../static/css/ViewAllEmployees.css";

function ViewAllEmployees() {
    const [employees, setEmployees] = useState([]);

    useEffect(() => {
        MainService.getAllEmployees()
            .then(res => {
                setEmployees(res.data.content)
            })
    })



    return (
        <div className="viewAllEmployees">
            <div className="viewAllEmployees__buttonContainer">
                
                <a 
                    href="/employees/create"
                    className="viewAllEmployees__button"
                    style={{
                        backgroundColor: "green"
                    }}
                >
                    Create
                </a>
                <a 
                    href="/employees/delete"
                    className="viewAllEmployees__button"
                    style={{
                        backgroundColor: "red"
                    }}
                >
                    Delete
                </a>
            
            </div>
            <div className="viewAllEmployees__table">
                <table style={{width: "100%", paddingTop: "1%"}}>
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Gender</th>
                        <th>Actions</th>
                    </tr>
                    {
                        employees ?
                        employees.map((employee, k) => (
                            <tr>
                                <td className="center">{employee.fname}</td>
                                <td className="center">{employee.lname}</td>
                                <td className="center">{employee.gender}</td>
                                <td className="center">
                                    <a
                                        href={`/employees/${employee.id}/delete`}
                                    >
                                        DELETE
                                    </a>
                                </td>
                            </tr>
                        )):""
                    }
                </table>
            </div>
        </div>
    )
}

export default ViewAllEmployees
