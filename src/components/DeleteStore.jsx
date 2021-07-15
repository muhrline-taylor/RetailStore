import React, { useState, useEffect } from 'react'
import { useHistory } from 'react-router-dom';
import MainService from '../services/MainService';

function DeleteStore({ ...props }) {
    const [store, setStore] = useState({});
    const history = useHistory();

    useEffect(() => {
        MainService.getStoreById(props.match.params.id)
            .then(res => {
                setStore(res.data);
            })
    },[])

    const submitHandler = e => {
        e.preventDefault();
        MainService.deleteStore(props.match.params.id);
        history.push("/");
    }


    return (
        <div>
            <h1>Delete {store.name}?</h1>
            <button onClick={submitHandler}>
                Delete
            </button>
            <a href="/">Cancel</a>
        </div>
    )
}

export default DeleteStore
