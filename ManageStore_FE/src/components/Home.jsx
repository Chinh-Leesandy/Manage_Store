import React from 'react'

export const Home = () => {
  return (
    <div className='container m-auto'>
        <h2 className='text-center' style={{marginTop: '150px', color: '#D2691E		',}}> Trang Chủ Quản Lý Cửa Hàng Quần Áo</h2>
        <div className="d-flex flex-column m-auto justify-content-center align-items-center">
          <a href="/employee" className="d-inline-flex focus-ring-warning py-1 px-2 mt-3 text-decoration-none border-danger border rounded-2">
              Quản lý nhân viên
          </a>
          <a href="/productType" className="d-inline-flex focus-ring-warning py-1 px-2 mt-3 text-decoration-none border-danger border rounded-2">
              Quản lý loại mặt hàng
          </a>
          <a href="/tk" className="d-inline-flex focus-ring-warning py-1 px-2 mt-3 text-decoration-none border-danger border rounded-2">
              Thống kê
          </a>
        </div>
    </div>
  )
}
