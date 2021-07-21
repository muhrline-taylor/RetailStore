import DefaultHeader from "./components/DefaultHeader";
import ViewAllStores from "./components/ViewAllStores";
import "./static/css/App.css";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
import CreateStore from "./components/CreateStore";
import ViewAllEmployees from "./components/ViewAllEmployees";
import CreateEmployee from "./components/CreateEmployee";
import ViewAllProducts from "./components/ViewAllProducts";
import CreateProduct from "./components/CreateProduct";
import DeleteProduct from "./components/DeleteProduct";
import DeleteEmployee from "./components/DeleteEmployee";
import DeleteStore from "./components/DeleteStore";
import ViewAllCustomers from "./components/ViewAllCustomers";
import CreateCustomer from "./components/CreateCustomer";
import BuyProduct from "./components/BuyProduct";
import ViewAllCategories from "./components/ViewAllCategories";
import ViewOneEmployee from "./components/ViewOneEmployee";
import EditEmployee from "./components/EditEmployee";
import CreateCategory from "./components/CreateCategory";

function App() {
  return (
    <div className="app">
      <div className="app__header">
        <DefaultHeader />
      </div>
      <div className="app__body">
        <Router>
          <Route exact path="/"><h1>HOME</h1></Route>
          <Route exact path="/stores"><ViewAllStores /></Route>
          <Route exact path="/stores/create"><CreateStore /></Route>
          <Route exact path="/stores/:id/delete" component={DeleteStore}></Route>


          <Route exact path="/employees"><ViewAllEmployees /></Route>
          <Route exact path="/employees/create"><CreateEmployee /></Route>
          <Route exact path="/employees/:id" component={ViewOneEmployee}></Route>
          <Route exact path="/employees/:id/delete" component={DeleteEmployee}></Route>
          <Route exact path="/employees/:id/edit" component={EditEmployee}></Route>
          


          <Route exact path="/products"><ViewAllProducts /></Route>
          <Route exact path="/products/create"><CreateProduct /></Route>
          {/* PATH VARIABLE ROUTE REQUIRES component PROP IN ROUTE */}
          <Route exact path="/products/:id/delete" component={DeleteProduct}></Route>
          <Route exact path="/products/:id/buy" component={BuyProduct}></Route>
          

          <Route exact path="/customers" component={ViewAllCustomers}></Route>
          <Route exact path="/customers/create" component={CreateCustomer}></Route>

          <Route exact path="/categories" component={ViewAllCategories}></Route>
          <Route exact path="/categories/create" component={CreateCategory}></Route>

        </Router>
      </div>
      
    </div>
  );
}

export default App;
