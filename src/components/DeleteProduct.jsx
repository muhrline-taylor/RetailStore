import React, { useEffect, useState } from 'react'
import { useHistory } from 'react-router-dom';
import MainService from '../services/MainService';

function DeleteProduct({ ...props }) {
    const history = useHistory();
    const [product, setProduct] = useState({});

    useEffect(() => {
        MainService.getProductById(props.match.params.id)
            .then(res => {
                setProduct(res.data)
            })
    },[])

    const submitHandler = e => {
        e.preventDefault();
        MainService.deleteProduct(props.match.params.id);
        history.push("/");
    }


    return (
        <div>
            <h1>Delete {product.name}?</h1>
            <button onClick={submitHandler}>DELETE</button>
            <a href="/">CANCEL</a>
        </div>
    )
}

export default DeleteProduct
