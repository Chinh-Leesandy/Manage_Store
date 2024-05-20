import React, { useEffect, useState } from 'react';
import { TKService } from '../../../service/TKService';
import { useNavigate } from "react-router-dom";
export const TKKhachHang = () => {
  const [tkCutomer, setTkCustomer] = useState([]);
  
  useEffect(() => {
    loadTkCutomer();
  }, []);

  const loadTkCutomer = async () => {
    try {
      const data = await TKService.getTkByCustomer();
      setTkCustomer(data);
    } catch (error) {
      console.error("Load fail data tk by month")
    }
  }
  console.log(tkCutomer);
  const totalAmountTk = tkCutomer.reduce((total, items) => total + items.invoices.reduce((sum, item) => sum + item.totalAmount, 0), 0);
  const totalPriceTk =  tkCutomer.reduce((total, items) => total + items.invoices.reduce((sum, item) => sum + item.totalPrice, 0), 0);
  const formatCurrency = (value) => {
    return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(value);
  };
  const formatPercentage = (value) => {
    return value.toFixed(2);
  };
  const navigate = useNavigate();
  const handleView = (invoices) => {
    navigate('/tklisthd', { state: { invoices } })
  }
  return (
    <div className='container'>
      <h2 className ='text-center'>Thống Kê Doanh Thu Theo Khách Hàng</h2>
      <p><span className='strong'>Tổng doanh thu:</span> {formatCurrency(totalPriceTk)}</p>
      <p><span className='strong'>Tổng sản phẩm:</span> {totalAmountTk}</p>
      <table className='table table-striped'>
        <thead>
          <tr className = 'text-center'>
            <td>Tên khách hàng</td>
            <td>Số điện thoại</td>
            <td>Tổng sản phẩm</td>
            <td>Tổng tiền</td>
            <td>Tỷ lệ doanh thu</td>
            <td>Action</td>
          </tr>
        </thead>
        <tbody>
          {tkCutomer.map((items)=> (
            <tr className='text-center'>
              <td>{items.customer.name}</td>
              <td>{items.customer.phone}</td>
              <td>{items.invoices.reduce((sum, item) => sum + item.totalAmount, 0)}</td>
              <td>{formatCurrency(items.invoices.reduce((sum, item) => sum + item.totalPrice, 0))}</td>
              <td>{formatPercentage(items.invoices.reduce((sum, item) => sum + item.totalPrice, 0) *100 / totalPriceTk)}</td>
              <td>
                <button type="button" class="btn btn-outline-warning" onClick={() => handleView(items.invoices)}>Xem chi tiết</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}
