import React, { useState, useEffect } from 'react'
import { useHistory } from 'react-router-dom';
import MainService from '../services/MainService';

function CreateProduct() {
    const history = useHistory();
    const [stores, setStores] = useState([]);
    const [productForm, setProductForm] = useState({
        name: "",
        category: "",
        price: 0.01,
    })
    const [storeId, setStoreId] = useState();

    const changeHandler = e => {
        if(e.target.name === "store"){
            setStoreId(e.target.value);
        } else if(e.target.name === "price") {
            setProductForm({
                ...productForm,
                [e.target.name]: parseFloat(e.target.value)
            })
        }
        else {
            setProductForm({
                ...productForm,
                [e.target.name]: e.target.value
            })
        }
        
    }

    const submitHandler = e => {
        e.preventDefault();
        MainService.createProduct(productForm, storeId);
        history.push("/");
    }

    useEffect(() => {
        MainService.getAllStores()
            .then(res => setStores(res.data.content));
    },[stores])



    return (
        <div>
            
            <form>

                <input 
                    name="name"
                    placeholder="Name..."
                    value={productForm.name}
                    onChange={changeHandler}
                />

                <input 
                    name="category"
                    placeholder="Category..."
                    value={productForm.category}
                    onChange={changeHandler}
                />

                <input 
                    name="price"
                    type="number"
                    min="0.01"
                    step="0.01"
                    value={productForm.price}
                    onChange={changeHandler}
                />

                <select 
                    name="store"
                    onChange={changeHandler}
                >
                    {
                        stores ?
                        stores.map((store, k) => (
                            <option
                                value={store.id}
                            >
                                {store.name}
                            </option>
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

export default CreateProduct
