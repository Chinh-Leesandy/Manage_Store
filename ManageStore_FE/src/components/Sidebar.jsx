import React, { useState } from 'react';
import { Collapse } from 'react-bootstrap';

export const Sidebar = () => {
  const [open, setOpen] = useState(true);

  return (
      <div className="d-flex flex-column align-items-center align-items-sm-start px-3 pt-2 text-white min-vh-100">
        <a href="/" className="d-flex align-items-center pb-3 mb-md-0 me-md-auto text-white text-decoration-none">
          <span className="fs-5 d-none d-sm-inline">Menu</span>
        </a>
        <ul className="nav nav-pills flex-column mb-sm-auto mb-0 align-items-center align-items-sm-start" id="menu">
          <li className="nav-item">
            <a href="/employee" className="nav-link align-middle px-0">
              <i className="fs-4 bi-house"></i> <span className="ms-1 d-none d-sm-inline">Quản lý nhân viên</span>
            </a>
          </li>
          <li className="nav-item">
            <a href="/productType" className="nav-link align-middle px-0">
              <i className="fs-4 bi-house"></i> <span className="ms-1 d-none d-sm-inline">Quản lý loại mặt hàng</span>
            </a>
          </li>
          <li>
            <spanspan className="nav-link px-0 align-middle">
              <a href="/tk" className="nav-link align-middle px-0">
                  <i className="fs-4 bi-speedometer2"></i> <span className="ms-1 d-none d-sm-inline">Thống kê</span>
              </a>
            </spanspan>
            <Collapse in={open}>
              <ul className="nav flex-column ms-1">
                <li className="w-100">
                  <a href="/tkthang" className="nav-link px-0"> <span className="d-none d-sm-inline">Thống kê doanh thu theo tháng</span>  </a>
                </li>
                <li>
                  <a href="/tkkhachhang" className="nav-link px-0"> <span className="d-none d-sm-inline">Thống kê doanh thu theo khách hàng </span>  </a>
                </li>
                <li>
                  <a href="/tkthoigian" className="nav-link px-0"> <span className="d-none d-sm-inline">Thống kê doanh thu theo khoảng thời gian</span>  </a>
                </li>
              </ul>
            </Collapse>
          </li>
        </ul>
        <hr />
        <div className="dropdown pb-4">
          <a href="#" className="d-flex align-items-center text-white text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
            <img src="https://github.com/mdo.png" alt="hugenerd" width="30" height="30" className="rounded-circle" />
            <span className="d-none d-sm-inline mx-1">loser</span>
          </a>
          <ul className="dropdown-menu dropdown-menu-dark text-small shadow" aria-labelledby="dropdownUser1">
            <li><a className="dropdown-item" href="#">Profile</a></li>
            <li>
              <hr className="dropdown-divider" />
            </li>
            <li><a className="dropdown-item" href="#">Sign out</a></li>
          </ul>
        </div>
      </div>
  );
};
