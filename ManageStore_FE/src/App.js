import './App.css';
import { Sidebar } from './components/Sidebar';
import { Employee } from './components/manage-employee/Employee';
import { HomeEmployee } from './components/manage-employee/HomeEmployee';
import { Route, Routes, useLocation } from 'react-router-dom';
import { ToastContainer } from "react-toastify";
import { HomeProductType } from './components/manage-producttype/HomeProductType';
import { ProductType } from './components/manage-producttype/ProductType';
import { Home } from './components/Home';
import { Tk } from './components/tk/Tk';
import { TKThang } from './components/tk/tkthang/TKThang';
import { TKHoaDonThang } from './components/tk/tkthang/TKHoaDonThang';
import { TKKhachHang } from './components/tk/tkkhachhang/TKKhachHang';
import { TKHoaDonKH } from './components/tk/tkthang/TKHoaDonKH';
import { TKLISTHD } from './components/tk/tkkhachhang/TKLISTHD';
import { HoaDonChiTiet } from './components/tk/tkkhachhang/HoaDonChiTiet';

function App() {
  const location = useLocation();
  const isHomePage = location.pathname === '/';
  return (
    <div className="container-fluid">
      <div className="row flex-nowrap">
        {!isHomePage && (
          <div className="col-auto col-md-3 col-xl-2 px-sm-2 px-0 bg-dark">
            <Sidebar />
          </div>
        )}
        <div className={`col ${isHomePage ? 'py-3 backgroundHome' : ''}`}>
          <Routes>
            <Route path='/' element={<Home />} />
            <Route path='/employee' element={<HomeEmployee />} />
            <Route path='/employee/:id' element={<Employee />} />
            <Route path='/productType' element={<HomeProductType />} />
            <Route path='/productType/:id' element={<ProductType />} />
            <Route path='/tk' element={<Tk/>} />
            <Route path='/tkthang' element={<TKThang/>} />
            <Route path='/tkhoadon' element={<TKHoaDonThang/>} />
            <Route path='/tkhoadonkh' element={<TKHoaDonKH/>} />
            <Route path='/tkkhachhang' element={<TKKhachHang/>} />
            <Route path ='/tklisthd' element = {<TKLISTHD/>}/>
            <Route path ='/hoadonct' element = {<HoaDonChiTiet/>}/>
          </Routes>
          <ToastContainer />
        </div>
      </div>
    </div>
  );
}

export default App;
