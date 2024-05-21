import React, { useEffect, useState } from 'react';
import { TKService } from '../../../service/TKService';
import { useNavigate } from "react-router-dom";

export const TKThang = () => {
  const [tkMonth, setTkMonth] = useState([]);
  const [isLoading, setIsLoading] = useState(true); 

  useEffect(() => {
    loadTkMonth();
  }, []);

  const loadTkMonth = async () => {
    try {
      const data = await TKService.getTkByTime();
      setTkMonth(data);
    } catch (error) {
      console.error("Load fail data tk by month");
    } finally {
      setIsLoading(false); 
    }
  };

  console.log(tkMonth);
  const totalAmountTk = tkMonth.reduce((total, items) => total + items.invoices.reduce((sum, item) => sum + item.totalAmount, 0), 0);
  const totalPriceTk =  tkMonth.reduce((total, items) => total + items.invoices.reduce((sum, item) => sum + item.totalPrice, 0), 0);
  const formatCurrency = (value) => {
    return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(value);
  };
  const formatPercentage = (value) => {
    return value.toFixed(2);
  };
  const navigate = useNavigate();
  const handleView = (invoices) => {
    navigate('/tkhoadon', { state: { invoices } });
  };

  if (isLoading) {
    return (
      <div className="container text-center" style={{marginTop: '50vh'}}>
        <div className="spinner-border" role="status">
          <span className="visually-hidden">Loading...</span>
        </div>
      </div>
    );
  }

  return (
    <div className='container'>
      <h2 className ='text-center'>Thống Kê Doanh Thu Theo Tháng</h2>
      <p><span className='strong'>Tổng doanh thu:</span> {formatCurrency(totalPriceTk)}</p>
      <p><span className='strong'>Tổng sản phẩm:</span> {totalAmountTk}</p>
      <table className='table table-striped'>
        <thead>
          <tr className='text-center'>
            <td>Thời gian</td>
            <td>Tổng sản phẩm</td>
            <td>Tổng tiền</td>
            <td>Tỷ lệ doanh thu</td>
            <td>Action</td>
          </tr>
        </thead>
        <tbody>
          {tkMonth.map((items, index) => (
            <tr key={index} className='text-center'>
              <td>{items.month}/{items.year}</td>
              <td>{items.invoices.reduce((sum, item) => sum + item.totalAmount, 0)}</td>
              <td>{formatCurrency(items.invoices.reduce((sum, item) => sum + item.totalPrice, 0))}</td>
              <td>{formatPercentage(items.invoices.reduce((sum, item) => sum + item.totalPrice, 0) * 100 / totalPriceTk)}</td>
              <td>
                <button type="button" className="btn btn-outline-warning" onClick={() => handleView(items.invoices)}>Xem chi tiết</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};
