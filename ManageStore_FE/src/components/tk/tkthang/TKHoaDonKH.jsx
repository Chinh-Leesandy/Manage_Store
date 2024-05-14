import React from 'react'
import { useLocation, useNavigate } from 'react-router-dom';

export const TKHoaDonKH = () => {
    const location = useLocation();
    const invoiceProducts = location.state.invoiceProducts;
    const handleView= () =>{}
  return (
    <div className='container'>
        <h2 className='text-center'>Danh Sách Hóa Đơn Của Khách Hàng</h2>
      <table className='table table-striped'>
        <thead>
          <tr className = 'text-center'>
            <td>Tổng sản phẩm</td>
            <td>Tổng tiền</td>
            <td>Action</td>
          </tr>
        </thead>
        <tbody>
          {invoiceProducts.map((items)=> (
            <tr className='text-center'>
              <td>{items.amount}</td>
              <td>{items.totalPrice}</td>
              <td>
                <button type="button" class="btn btn-outline-warning" onClick={() => handleView(items.invoiceProducts)}>Xem chi tiết</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}
