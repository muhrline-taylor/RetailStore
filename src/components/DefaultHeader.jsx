import React from 'react';
import "../static/css/DefaultHeader.css";

function DefaultHeader() {
    return (
        <div className="defaultHeader">
            <div className="defaultHeader__top">
                <h1>Retail Store</h1>
            </div>
            <div className="defaultHeader__bottom">
                <div className="defaultHeader__bottomButtonContainer">
                    <a 
                        href="/stores"
                        className="defaultHeader__bottomButton"
                    >
                        Stores
                    </a>
                    <a 
                        href="/employees"
                        className="defaultHeader__bottomButton"
                    >
                        Employees
                    </a>
                    <a 
                        href="/products"
                        className="defaultHeader__bottomButton"
                    >
                        Products
                    </a>
                </div>
            </div>
            
        </div>
    )
}

export default DefaultHeader;
