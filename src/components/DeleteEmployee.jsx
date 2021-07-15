import React, { useState, useEffect } from 'react'
import { useHistory } from 'react-router-dom';
import MainService from '../services/MainService';

function DeleteEmployee({ ...props }) {
    const history = useHistory();
    const [employee, setEmployee] = useState({});

    useEffect(() => {
        MainService.getEmployeeById(props.match.params.id)
            .then(res => {
                setEmployee(res.data)
            })
    },[])

    const submitHandler = e => {
        e.preventDefault();
        MainService.deleteEmployee(props.match.params.id);
        history.push("/");
    }


    return (
        <div>
            <h1>Delete {employee.fname} {employee.lname}?</h1>
            <button
                onClick={submitHandler}
            >
                Delete
            </button>
            <a href="/">
                Cancel
            </a>
        </div>
    )
}

export default DeleteEmployee
