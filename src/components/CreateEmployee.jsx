import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { useHistory } from 'react-router-dom';
import MainService from '../services/MainService';

function CreateEmployee() {
    const history = useHistory();
    const [stores, setStores] = useState([]);
    const [employeeForm, setEmployeeForm] = useState({
        fname: "",
        lname: "",
        gender: "",
    })
    const [storeId, setStoreId] = useState(0);

    const changeHandler = e => {
        if(e.target.name === "store_id"){
            setStoreId(
                parseInt(e.target.value)
            )
        } else {
            setEmployeeForm({
                ...employeeForm,
                [e.target.name]: e.target.value
            })
        }
    }

    const submitHandler = e => {
        e.preventDefault();
        console.log(employeeForm);
        console.log(storeId);
        MainService.createEmployee(employeeForm, storeId);
        
    }

    useEffect(() => {
        MainService.getAllStores()
            .then(res => setStores(res.data.content))
    },[])


    return (
        <div>
            
            <form>

                <input 
                    placeholder="First Name..."
                    name="fname"
                    onChange={changeHandler}
                />

                <input 
                    placeholder="Last Name..."
                    name="lname"
                    onChange={changeHandler}
                />

                <input 
                    placeholder="Gender(optional)..."
                    name="gender"
                    onChange={changeHandler}
                />

                <select 
                    name="store_id"
                    onChange={changeHandler}
                >
                    <option value="null">STORE</option>
                    {
                        stores ?
                        stores.map((store, k) => (
                            <option value={store.id}>{store.name}</option>
                        )):""
                    }
                </select>

                <input 
                    type="submit"
                    value="Submit"
                    onClick={submitHandler}
                />


            </form>


        </div>
    )
}

export default CreateEmployee
