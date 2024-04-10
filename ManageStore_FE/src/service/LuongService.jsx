import axios from 'axios';

const API_URL = 'http://localhost:8080';

export const LuongService =  {
    getLuong: async () => {
        try {
          const response = await axios.get(`${API_URL}/luong`);
          return response.data;
        } catch (error) {
          throw new Error(error.message);
        }
      },
    
      getTKLuong: async (month) => {
        try {
          const response = await axios.get(`${API_URL}/TKLuong/${month}`);
          return response.data;
        } catch (error) {
          throw new Error(error.message);
        }
      }
};

export default LuongService;
