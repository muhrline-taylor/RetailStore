import React, { useState } from 'react'
import { useHistory } from 'react-router-dom';
import MainService from '../services/MainService';

function CreateCategory() {
    const history = useHistory();
    const [categoryForm, setCategoryForm] = useState({name: ""});

    const changeHandler = e => {
        setCategoryForm({name: e.target.value});
    }

    const submitHandler = e => {
        e.preventDefault();
        console.log(categoryForm);
        MainService.createCategory(categoryForm);
        history.push("/");
    }


    return (
        <div className="createCategory">
            <form>

                <p>Category: </p>
                <input 
                    name="name"
                    placeholder="Category..."
                    onChange={changeHandler}
                />

                <input 
                    type="submit"
                    value="Add Category"
                    onClick={submitHandler}
                />


            </form>
        </div>
    )
}

export default CreateCategory
