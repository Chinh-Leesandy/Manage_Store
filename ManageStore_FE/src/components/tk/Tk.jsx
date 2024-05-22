import React from 'react'

export const Tk = () => {
  return (
    <div className='container'>
        <h2 className='text-center'>Lựa Chọn Loại Thống Kê</h2>
        <div className="d-flex flex-column m-auto justify-content-center align-items-center">
        <a href="/tkthang" className="d-inline-flex focus-ring-warning py-1 px-2 mt-3 text-decoration-none border-warning-subtle border rounded-2">
            Thống kê doanh thu theo tháng
        </a>
        <a href="/tkkhachhang" className="d-inline-flex focus-ring-warning py-1 px-2 mt-3 text-decoration-none border-warning-subtle border rounded-2">
            Thống kê doanh thu theo khách hàng
        </a>
        {/* <a href="/tkthoigian" className="d-inline-flex focus-ring-warning py-1 px-2 mt-3 text-decoration-none border-warning-subtle border rounded-2">
            Thống kê doanh thu theo khoảng thời gian
        </a> */}
        </div>
    </div>
  )
}
