import './App.css';
import { Sidebar } from './components/Sidebar';
import { Employee } from './components/manage-employee/Employee';
import { HomeEmployee } from './components/manage-employee/HomeEmployee';
import {Route, Routes} from 'react-router-dom'
import { ToastContainer } from "react-toastify";
import { HomeTimeSheet } from './components/tk-timesheet/HomeTimeSheet';
import { TimeSheet } from './components/tk-timesheet/TimeSheet';
import { HomeNghiPhep } from './components/tk-nghiphep/HomeNghiPhep';
import { NghiPhep } from './components/tk-nghiphep/NghiPhep';
import { HomeProductType } from './components/manage-producttype/HomeProductType';
import { ProductType } from './components/manage-producttype/ProductType';
import { HomeLuong } from './components/tk-luong/HomeLuong';
import { Luong } from './components/tk-luong/Luong';

function App() {
  return (
    <div className="container-fluid">
    <div className="row flex-nowrap">
      <div className="col-auto col-md-3 col-xl-2 px-sm-2 px-0 bg-dark">
      <Sidebar/>
      </div>
      <div className="col py-3">
      <Routes>
        <Route  path = '/' element = {<HomeEmployee/>}/>
        <Route  path = '/employee' element = {<HomeEmployee/>}/>
        <Route  path = '/employee/:id' element = {<Employee/>}/>
        <Route  path = '/tkgiolam' element = {<HomeTimeSheet/>}/>
        <Route  path = '/tkgiolam/:month' element = {<TimeSheet/>}/>
        <Route  path = '/tknghiphep' element = {<HomeNghiPhep/>}/>
        <Route  path = '/tknghiphep/:month' element = {<NghiPhep/>}/>
        <Route  path = '/productType' element = {<HomeProductType/>}/>
        <Route  path = '/productType/:id' element = {<ProductType/>}/>
        <Route  path = '/tkluong' element = {<HomeLuong/>}/>
        <Route  path = '/tkluong/:month' element = {<Luong/>}/>
      </Routes>
      <ToastContainer/>
      </div>
    </div>
    </div>
  );
}

export default App;
