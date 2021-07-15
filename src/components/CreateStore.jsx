import React, { useState } from 'react'
import MainService from '../services/MainService'
import { useHistory } from 'react-router-dom';

function CreateStore() {
    const history = useHistory();
    const [storeForm, setStoreForm] = useState({
        name: "",
        address: ""
    })

    const changeHandler = e => {
        setStoreForm({
            ...storeForm,
            [e.target.name]: e.target.value
        })
    }

    const submitHandler = e => {
        e.preventDefault();
        MainService.createStore(storeForm);
        history.push("/");
    }

    return (
        <div>
            <form>

                <input 
                    placeholder="Name"
                    name="name"
                    onChange={changeHandler}
                />

                <input 
                    placeholder="Address"
                    name="address"
                    onChange={changeHandler}
                />

                <input 
                    type="submit"
                    value="Create"
                    onClick={submitHandler}
                />





            </form>
        </div>
    )
}

export default CreateStore
