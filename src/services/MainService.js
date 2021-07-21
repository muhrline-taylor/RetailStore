import axios from 'axios';

const STORE_API_BASE_URL = "http://localhost:8080/api/v1/stores";
const EMPLOYEE_API_BASE_URL = "http://localhost:8080/api/v1/employees";
const PRODUCT_API_BASE_URL = "http://localhost:8080/api/v1/products";
const CUSTOMER_API_BASE_URL = "http://localhost:8080/api/v1/customers";
const CATEGORY_API_BASE_URL = "http://localhost:8080/api/v1/categories";

const FORM_DATA_CONFIG = {
    headers: {
        'content-type':'multipart/form-data'
    }
}


class MainService {

    

    // API CALLS -------------------------------- //

        // STORES ------------------------------- //

            // GET ALL
            getAllStores(){
                return axios.get(`${STORE_API_BASE_URL}/`);
            }

                // NOTE: React hooks are switching between passing the id variable as an integer and as an object causing significant crashing but one always seems to be correct. getStoreById() and getStoreByObjectId() need to be in a try/catch block.

            // GET BY ID
            getStoreById(id){
                return axios.get(`${STORE_API_BASE_URL}/${id}`);
            }

            // GET BY ID, WHERE ID IS AN OBJECT (UNFINISHED)
            getStoreByObjectId(id){
                console.log("into getStoreByObjectId(id)")
                console.log(id);
                return axios.get(`${STORE_API_BASE_URL}/${id.store_id}`);
            }

            // CREATE
            createStore(reqBody){
                console.log("into createStore()");
                console.log(reqBody);
                return axios.post(`${STORE_API_BASE_URL}/new`, reqBody);
            }

            // DELETE
            deleteStore(id){
                return axios.delete(`${STORE_API_BASE_URL}/${id}`);
            }



        // EMPLOYEES ------------------------------- //

            // GET ALL
            getAllEmployees(){
                return axios.get(`${EMPLOYEE_API_BASE_URL}/`);
            }

            // GET BY ID
            getEmployeeById(id){
                return axios.get(`${EMPLOYEE_API_BASE_URL}/${id}`);
            }

            // CREATE
            createEmployee(reqBody, store_id){
                var newReqBody = reqBody;
                console.log(store_id)
                console.log(reqBody)
                
                
                this.getStoreById(store_id)
                    .then(res => {
                        
                        newReqBody['store'] = res.data;
                        console.log(res)
                    })
                
                console.log(newReqBody);

                return axios.post(`${EMPLOYEE_API_BASE_URL}/new/${store_id}`, newReqBody)
            }

            // DELETE
            deleteEmployee(id){
                return axios.delete(`${EMPLOYEE_API_BASE_URL}/${id}`)
            }



        // PRODUCTS ------------------------------------- //

            // GET ALL
            getAllProducts(){
                return axios.get(`${PRODUCT_API_BASE_URL}/`);
            }

            // GET BY ID
            getProductById(id){
                return axios.get(`${PRODUCT_API_BASE_URL}/${id}`);
            }

            // CREATE
            createProduct(reqBody, storeId){
                var newReqBody = reqBody;
                // this.getStoreById(storeId)
                //     .then(res => {
                //         newReqBody['store'] = res.data;
                //     })
                return axios.post(`${PRODUCT_API_BASE_URL}/new/${storeId}`, reqBody);
            }

            // CREATE TESTING
            createProductTesting(reqBody, storeId){
                var newReqBody = reqBody;
                // this.getStoreById(storeId)
                //     .then(res => {
                //         newReqBody['store'] = res.data;
                //     })
                return axios.post(`${PRODUCT_API_BASE_URL}/new/${storeId}/testing`, reqBody);
            }

            // DELETE
            deleteProduct(id){
                return axios.delete(`${PRODUCT_API_BASE_URL}/${id}`);
            }

            // BUY PRODUCT BY CUSTOMER ID
            buyProduct(product_id, customer_id){
                console.log("into buyProduct()");
                console.log(customer_id);
                return axios.post(`${PRODUCT_API_BASE_URL}/${product_id}/buy`, customer_id)
            }


        // CUSTOMERS -------------------------------- //

            // GET ALL
            getAllCustomers(){
                return axios.get(`${CUSTOMER_API_BASE_URL}/`);
            }

            // CREATE
            createCustomer(reqBody){
                return axios.post(`${CUSTOMER_API_BASE_URL}/new`, reqBody);
            }




        // CATEGORIES ---------------------------------------- //
            
            // get all
            getAllCategories(){
                return axios.get(`${CATEGORY_API_BASE_URL}/`);
            }

            // create
            createCategory(reqBody){
                return axios.post(`${CATEGORY_API_BASE_URL}/new`, reqBody);
            }








    



}


export default new MainService();