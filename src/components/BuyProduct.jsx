import React, { useEffect, useState } from 'react'
import { useHistory } from 'react-router-dom';
import MainService from '../services/MainService';

function BuyProduct({ ...props }) {
    const history = useHistory();
    const [customers, setCustomers] = useState([]);
    const [product, setProduct] = useState({});
    const [buyer, setBuyer] = useState({});

    useEffect(() => {
        MainService.getAllCustomers()
            .then(res => {
                setCustomers(res.data.content);
            })
    },[])

    useEffect(() => {
        MainService.getProductById(props.match.params.id)
            .then(res => {
                setProduct(res.data);
            })
    },[])

    const changeHandler = e => {
        setBuyer({id: e.target.value});
    }

    const clickHandler = e => {
        e.preventDefault();
        MainService.buyProduct(product.id, buyer);
        history.push("/");
    }


    return (
        <div>
            <h1>Who is buying {product.name}?</h1>
            <form>

                <select name="buyer" onChange={changeHandler}>
                    <option>SELECT BUYER</option>
                    {
                        customers ?
                        customers.map((customer, k) => (
                            <option value={customer.id}>
                                {customer.fname} {customer.lname}
                            </option>
                        )):""
                    }
                </select>


                <input 
                    type="submit"
                    value="Submit"
                    onClick={clickHandler}
                />
            </form>
            
        </div>
    )
}

export default BuyProduct
