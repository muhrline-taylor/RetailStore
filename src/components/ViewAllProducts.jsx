import React from 'react';
import "../static/css/ViewAllProducts.css";

function ViewAllProducts() {
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
                {/* <table style={{width: "100%", paddingTop: "1%"}}>
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
                </table> */}
            </div>
        </div>
    )
}

export default ViewAllProducts
