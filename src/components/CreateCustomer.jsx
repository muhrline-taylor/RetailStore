import React, { useState } from 'react'
import { useHistory } from 'react-router-dom';
import MainService from '../services/MainService';

function CreateCustomer() {
    const history = useHistory();
    const [customer, setCustomer] = useState({
        fname: "",
        lname: ""
    });

    const changeHandler = e => {
        setCustomer({
            ...customer,
            [e.target.name]: e.target.value
        })
    }

    const submitHandler = e => {
        e.preventDefault();
        MainService.createCustomer(customer);
        history.push("/");
    }


    return (
        <div>
            <form>
                <input 
                    name="fname"
                    placeholder="First Name..."
                    value={customer.fname}
                    onChange={changeHandler}
                />
                <input 
                    name="lname"
                    placeholder="Last Name..."
                    value={customer.lname}
                    onChange={changeHandler}
                />
                <input 
                    type="submit"
                    value="Submit"
                    onClick={submitHandler}
                />
            </form>
            
        </div>
    )
}

export default CreateCustomer
