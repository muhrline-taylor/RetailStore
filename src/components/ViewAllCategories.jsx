import React from 'react';
import "../static/css/ViewAllStores.css";

function ViewAllCategories() {
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
                        <th>Name</th>
                        <th>Products</th>
                        
                    </tr>
                    
                </table>
            </div>
        </div>
    )
}

export default ViewAllCategories
