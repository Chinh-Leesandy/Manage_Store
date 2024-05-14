import React from 'react'
import { useLocation } from 'react-router-dom';

export const TKHoaDonKH = () => {
    const location = useLocation();
    const invoiceProducts = location.state.invoiceProducts;
    const formatCurrency = (value) => {
        return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(value);
    };
  return (
    <div className='container'>
        <h2 className='text-center'>Hóa Đơn Chi Tiết</h2>
      <table className='table table-striped'>
        <thead>
          <tr className = 'text-center'>
            <td>Ảnh</td>
            <td>Tên Sản phẩm</td>
            <td>Giá tiền</td>
            <td>Số sản phẩm</td>
            <td>Thành tiền</td>
          </tr>
        </thead>
        <tbody>
          {invoiceProducts.map((items)=> (
            <tr className='text-center'>
              <td><img style={{width: '50px', height : '50px'}} src={items.imageUrl} alt="" srcset="" /></td> 
              <td>{items.product.name}</td> 
              <td>{formatCurrency(items.product.price)}</td> 
              <td>{items.amount}</td>
              <td>{formatCurrency(items.totalPrice)}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}
