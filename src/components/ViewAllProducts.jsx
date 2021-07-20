import React, { useEffect, useState } from 'react';
import MainService from '../services/MainService';
import "../static/css/ViewAllProducts.css";

function ViewAllProducts() {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        MainService.getAllProducts()
            .then(res => {
                setProducts(res.data.content)
            })
    },[])



    return (
        <div className="viewAllProducts">
            <div className="viewAllProducts__buttonContainer">
                
                <a 
                    href="/products/create"
                    className="viewAllProducts__button"
                    style={{
                        backgroundColor: "green"
                    }}
                >
                    Create
                </a>
                <a 
                    href="/products/delete"
                    className="viewAllProducts__button"
                    style={{
                        backgroundColor: "red"
                    }}
                >
                    Delete
                </a>
            
            </div>
            <div className="viewAllProducts__table">
                <table style={{width: "100%", paddingTop: "1%"}}>
                    <tr>
                        <th>Name</th>
                        <th>Category</th>
                        <th>Price</th>
                        <th>Store</th>
                        <th>Actions</th>
                    </tr>
                    {
                        products ?
                        products.map((product, k) => (
                            <tr>
                                <td className="center">{product.name}</td>
                                <td className="center">{product.category}</td>
                                <td className="center">{product.price}</td>
                                <td className="center"></td>
                                <td className="center">
                                    <a href={`/products/${product.id}/buy`}>BUY</a>
                                </td>
                            </tr>
                        )):""
                    }
                </table>
            </div>
        </div>
    )
}

export default ViewAllProducts
