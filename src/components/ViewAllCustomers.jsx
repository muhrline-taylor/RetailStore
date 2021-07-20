import React, { useState, useEffect } from 'react';
import MainService from '../services/MainService';
import "../static/css/ViewAllStores.css";

function ViewAllCustomers() {
    const [customers, setCustomers] = useState([]);

    useEffect(() => {
        MainService.getAllCustomers()
            .then(res => {
                setCustomers(res.data.content)
            })
    },[])


    return (
        <div className="viewAllStores">
            <div className="viewAllStores__buttonContainer">
                
                <a 
                    href="/customers/create"
                    className="viewAllStores__button"
                    style={{
                        backgroundColor: "green"
                    }}
                >
                    Create
                </a>
                <a 
                    href="/customers/delete"
                    className="viewAllStores__button"
                    style={{
                        backgroundColor: "red"
                    }}
                >
                    Delete
                </a>
            
            </div>
            <div className="viewAllStores__table">
                <table style={{width: "100%", paddingTop: "1%"}}>
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Purchases</th>
                        <th>Actions</th>
                    </tr>
                    {
                        customers ?
                        customers.map((customer, k) => (
                            <tr>
                                <td className="center">{customer.fname}</td>
                                <td className="center">{customer.lname}</td>
                                <td className="center">_PURCHASES_</td>
                                <td className="center">_ACTIONS_</td>
                            </tr>
                        )):""
                    }
                </table>
            </div>
        </div>
    )
}

export default ViewAllCustomers
