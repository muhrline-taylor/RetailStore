import React, { useEffect, useState } from 'react'
import MainService from '../services/MainService';
import "../static/css/ViewAllStores.css";

function ViewAllStores() {
    const [stores, setStores] = useState([]);

    useEffect(() => {
        MainService.getAllStores()
            .then(res => setStores(res.data.content));
    },[])



    return (
        <div className="viewAllStores">
            <div className="viewAllStores__buttonContainer">
                
                <a 
                    href="/stores/create"
                    className="viewAllStores__button"
                    style={{
                        backgroundColor: "green"
                    }}
                >
                    Create
                </a>
                <a 
                    href="/stores/delete"
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
                        <th>Name</th>
                        <th>Address</th>
                        <th>Employees</th>
                        <th>Actions</th>
                    </tr>
                    {
                        stores ?
                        stores.map((store, k) => (
                            <tr>
                                <td className="center">{store.name}</td>
                                <td className="center">{store.address}</td>
                                <td className="center">
                                    {
                                        store.employees.map((employee, key) => {
                                            return `${employee.fname} ${employee.lname}, `
                                        })
                                    }
                                </td>
                                <td className="center">Actions</td>
                            </tr>
                        )):""
                    }
                </table>
            </div>
        </div>
    )
}

export default ViewAllStores;
