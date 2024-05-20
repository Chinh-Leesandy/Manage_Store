import React from 'react'
import { useLocation, useNavigate } from 'react-router-dom';

export const TKHoaDonThang = () => {
    const location = useLocation();
    const invoices = location.state.invoices;
    console.log(invoices);
    const totalAmountTk = invoices.reduce((total, items) => total + items.invoiceProducts.reduce((sum, item) => sum + item.amount, 0), 0);
    const totalPriceTk =  invoices.reduce((total, items) => total + items.invoiceProducts.reduce((sum, item) => sum + item.totalPrice, 0), 0);
    const formatCurrency = (value) => {
        return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(value);
    };
    const navigate = useNavigate();
    const handleView = (invoiceProducts) => {
        navigate('/tkhoadonkh', { state: { invoiceProducts } })
    }
  return (
    <div className='container'>
        <h2 className='text-center'>Danh Sách Hóa Đơn Theo Tháng</h2>
        <p><span className='strong'>Tổng doanh thu:</span> {formatCurrency(totalPriceTk)}</p>
      <p><span className='strong'>Tổng sản phẩm:</span> {totalAmountTk}</p>
      <table className='table table-striped'>
        <thead>
          <tr className = 'text-center'>
            <td>Tên khách hàng</td>
            <td>Số điện thoại</td>
            <td>Tổng sản phẩm</td>
            <td>Tổng tiền</td>
            <td>Action</td>
          </tr>
        </thead>
        <tbody>
          {invoices.map((items)=> (
            <tr className='text-center'>
              <td>{items.customer.name}</td>
              <td>{items.customer.phone}</td>
              <td>{items.invoiceProducts.reduce((sum, item) => sum + item.amount, 0)}</td>
              <td>{formatCurrency(items.invoiceProducts.reduce((sum, item) => sum + item.totalPrice, 0))}</td>
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
